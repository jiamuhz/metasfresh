 

/**
 * 当用户在UI上看到一个下拉框或搜索框时，背后就是 LookupDescriptor 在定义：数据从哪里来、如何搜索、如何展示、如何过滤
 * LookupDescriptor 在运行时如何工作：
 *
 * 初始化阶段：系统读取应用字典配置，为每个查找字段创建 LookupDescriptor
 * 渲染阶段：前端请求查找字段的数据时，系统使用 LookupDescriptor 构建查询
 * 搜索阶段：用户输入搜索关键词，系统根据 LookupDescriptor 的搜索配置执行查询
 * 选择阶段：用户选择一个值，系统使用 LookupDescriptor 的值转换器处理选中值
 * 显示阶段：在只读模式下显示字段值时，系统使用 LookupDescriptor 的显示格式将ID转换为可读文本
 */
package de.metas.ui.web.window.descriptor;

import com.google.common.collect.ImmutableSet;
import de.metas.adempiere.service.impl.TooltipType;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;

public interface LookupDescriptor
{
	default Optional<String> getTableName()
	{
		return Optional.empty();
	}

	default Optional<WindowDocumentTypeId> getZoomIntoWindowId()
	{
		return Optional.empty();
	}

	@Override
	boolean equals(Object obj);

	@Override
	int hashCode();

	LookupDataSourceFetcher getLookupDataSourceFetcher();

	boolean isHighVolume();

	LookupSource getLookupSourceType();

	boolean hasParameters();

	boolean isNumericKey();

	Set<String> getDependsOnFieldNames();

	default Set<String> getDependsOnTableNames()
	{
		return ImmutableSet.of();
	}

	default Class<?> getValueClass()
	{
		return isNumericKey() ? IntegerLookupValue.class : StringLookupValue.class;
	}

	default int getSearchStringMinLength()
	{
		return -1;
	}

	default Optional<Duration> getSearchStartDelay()
	{
		return Optional.empty();
	}

	default <T extends LookupDescriptor> T cast(final Class<T> ignoredLookupDescriptorClass)
	{
		@SuppressWarnings("unchecked")
		final T thisCasted = (T)this;
		return thisCasted;
	}

	default <T extends LookupDescriptor> T castOrNull(final Class<T> lookupDescriptorClass)
	{
		if (lookupDescriptorClass.isAssignableFrom(getClass()))
		{
			@SuppressWarnings("unchecked")
			final T thisCasted = (T)this;
			return thisCasted;
		}

		return null;
	}

	default TooltipType getTooltipType()
	{
		return TooltipType.DEFAULT;
	}
}
