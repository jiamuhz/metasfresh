package de.metas.ui.web.window.descriptor.sql;

import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;



public interface SqlEntityFieldBinding
{
	String getColumnName();

	SqlSelectValue getSqlSelectValue();

	DocumentFieldWidgetType getWidgetType();

	Class<?> getSqlValueClass();

	SqlOrderByValue getSqlOrderBy();

	boolean isVirtualColumn();

	boolean isMandatory();
}
