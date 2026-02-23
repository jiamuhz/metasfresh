/*
 * #%L
 * de.metas.adempiere.adempiere.base
 * %%
 * Copyright (C) 2021 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */
/**
 * 管理窗口定制化链，用于跟踪一个定制窗口从原始基础窗口到当前定制版本的完整继承链
 */

package de.metas.document.references.zoom_into;

import com.google.common.collect.ImmutableList;
import de.metas.i18n.ImmutableTranslatableString;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.ad.element.api.AdWindowId;

@Value
@Builder(toBuilder = true)
public class CustomizedWindowInfo
{
	@NonNull ImmutableTranslatableString customizationWindowCaption; // 定制窗口标题

	@NonNull AdWindowId customizationWindowId; // 当前定制窗口ID

	/**
	 * 历史定制窗口ID列表
	 * 记录从基础窗口到当前定制窗口之间的所有中间定制版本
	 * 比如：
	 * 基础窗口 → 第一次定制 → 第二次定制 → 第三次定制
	 * 这个列表就会包含第一次和第二次的定制ID
	 */
	@NonNull @Builder.Default ImmutableList<AdWindowId> previousCustomizationWindowIds = ImmutableList.of();

	@NonNull AdWindowId baseWindowId; // 基础窗口ID

	/**
	 *   作用： 控制这个定制窗口是否替换基础窗口在菜单中的显示
	 *  true： 菜单中只显示定制版本，隐藏基础版本
	 * false： 基础版本和定制版本同时在菜单中可见
	 */
	boolean overrideInMenu;

	/**
	 * 构建了从基础到当前定制的完整窗口ID列表
	 */
	public ImmutableList<AdWindowId> getWindowIdsFromBaseToCustomization()
	{
		return ImmutableList.<AdWindowId>builder()
				.add(baseWindowId)     // 1. 基础版本
				.addAll(previousCustomizationWindowIds)    // 2. 中间的所有定制版本
				.add(customizationWindowId)    // 3. 当前使用的定制版本
				.build();
	}
}
