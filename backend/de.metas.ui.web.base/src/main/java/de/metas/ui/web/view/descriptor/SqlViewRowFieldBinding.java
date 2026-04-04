package de.metas.ui.web.view.descriptor;

import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlEntityFieldBinding;
import de.metas.ui.web.window.descriptor.sql.SqlOrderByValue;
import de.metas.ui.web.window.descriptor.sql.SqlSelectDisplayValue;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * 简单理解：它是 SqlViewBinding 中每个字段的“配置清单”
 * 定义了一个字段的前端名、数据库列、类型、如何取值、如何排序
 *
 */

@Value
public class SqlViewRowFieldBinding implements SqlEntityFieldBinding
{
	/**
	 * Retrieves a particular field from given {@link ResultSet}.
	 */
	@FunctionalInterface
	public interface SqlViewRowFieldLoader
	{
		@Nullable
		Object retrieveValue(ResultSet rs, String adLanguage) throws SQLException;
	}

	@NonNull String fieldName;  /* 界面字段名 */
	@NonNull String columnName;  /* 数据库列名 */ 
	boolean keyColumn;
	@NonNull DocumentFieldWidgetType widgetType;
	boolean virtualColumn;
	boolean mandatory;

	@NonNull Class<?> sqlValueClass;
	@NonNull SqlSelectValue sqlSelectValue;
	@Nullable SqlSelectDisplayValue sqlSelectDisplayValue;

	@NonNull SqlOrderByValue sqlOrderBy;

	@NonNull SqlViewRowFieldLoader fieldLoader;

	@Builder
	private SqlViewRowFieldBinding(
			@NonNull final String fieldName,
			@Nullable final String columnName,
			final boolean keyColumn,
			@NonNull final DocumentFieldWidgetType widgetType,
			final boolean virtualColumn,
			final boolean mandatory,
			//
			@Nullable final Class<?> sqlValueClass,
			@NonNull final SqlSelectValue sqlSelectValue,
			@Nullable final SqlSelectDisplayValue sqlSelectDisplayValue,
			//
			@Nullable final SqlOrderByValue sqlOrderBy,
			@NonNull final SqlViewRowFieldLoader fieldLoader)
	{
		this.fieldName = fieldName;
		this.columnName = columnName != null ? columnName : this.fieldName;
		this.keyColumn = keyColumn;
		this.widgetType = widgetType;
		this.virtualColumn = virtualColumn;
		this.mandatory = mandatory;

		this.sqlValueClass = sqlValueClass != null ? sqlValueClass : widgetType.getValueClass();
		this.sqlSelectValue = sqlSelectValue;
		this.sqlSelectDisplayValue = sqlSelectDisplayValue;

		this.sqlOrderBy = sqlOrderBy != null
				? sqlOrderBy
				: SqlOrderByValue.builder().sqlSelectDisplayValue(sqlSelectDisplayValue).sqlSelectValue(sqlSelectValue).columnName(columnName).build();
		this.fieldLoader = fieldLoader;
	}

	@Override
	public boolean isMandatory()
	{
		return mandatory;
	}
}
