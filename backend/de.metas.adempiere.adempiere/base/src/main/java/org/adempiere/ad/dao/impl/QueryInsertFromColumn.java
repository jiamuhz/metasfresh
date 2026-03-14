package org.adempiere.ad.dao.impl;

/** */


import java.util.List;

import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.ObjectUtils;

import de.metas.util.Check;

/**
 * Set the column in target model, using the value of a column from the source model.
 *
 * @author tsa
 *
 */
class QueryInsertFromColumn implements IQueryInsertFromColumn
{
	private final String columnName;

	public QueryInsertFromColumn(final String columnName)
	{
		super();
		Check.assumeNotEmpty(columnName, "columnName not empty");
		this.columnName = columnName;
	}

	@Override
	public String toString()
	{
		return ObjectUtils.toString(this);
	}

	@Override
	public String getSql(final List<Object> sqlParams)
	{
		return columnName;
	}

	@Override
	public boolean update(final Object toModel, final String toColumnName, final Object fromModel)
	{
		final Object value = InterfaceWrapperHelper.getValue(fromModel, toColumnName).orElse(null);
		InterfaceWrapperHelper.setValue(toModel, toColumnName, value);
		return true;
	}
}
