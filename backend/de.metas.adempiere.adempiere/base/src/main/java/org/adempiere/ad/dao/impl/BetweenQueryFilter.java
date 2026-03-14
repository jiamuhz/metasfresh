package org.adempiere.ad.dao.impl;

/** */


import java.util.List;
import java.util.Properties;

import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.IQueryFilterModifier;
import org.adempiere.ad.dao.ISqlQueryFilter;
import org.adempiere.ad.dao.impl.CompareQueryFilter.Operator;
import org.adempiere.model.ModelColumn;

public class BetweenQueryFilter<T> implements IQueryFilter<T>, ISqlQueryFilter
{
	private final CompositeQueryFilter<T> filter;

	public BetweenQueryFilter(final String tableName, final String columnName, final Object valueFrom, final Object valueTo)
	{
		this(tableName, columnName, valueFrom, valueTo, NullQueryFilterModifier.instance);
	}

	public BetweenQueryFilter(final String tableName, final String columnName, final Object valueFrom, final Object valueTo, IQueryFilterModifier modifier)
	{

		filter = new CompositeQueryFilter<>(tableName);
		filter.setJoinAnd();
		filter.addCompareFilter(columnName, Operator.GREATER_OR_EQUAL, valueFrom, modifier);
		filter.addCompareFilter(columnName, Operator.LESS_OR_EQUAL, valueTo, modifier);
	}

	public BetweenQueryFilter(final ModelColumn<T, ?> column, final Object valueFrom, final Object valueTo)
	{
		this(column, valueFrom, valueTo, NullQueryFilterModifier.instance);
	}

	public BetweenQueryFilter(final ModelColumn<T, ?> column, final Object valueFrom, final Object valueTo, IQueryFilterModifier modifier)
	{
		super();

		filter = new CompositeQueryFilter<>(column.getModelClass());
		filter.setJoinAnd();
		filter.addCompareFilter(column, Operator.GREATER_OR_EQUAL, valueFrom, modifier);
		filter.addCompareFilter(column, Operator.LESS_OR_EQUAL, valueTo, modifier);
	}

	@Override
	public String toString()
	{
		return filter.toString();
	}

	@Override
	public String getSql()
	{
		return filter.getSql();
	}

	@Override
	public List<Object> getSqlParams(Properties ctx)
	{
		return filter.getSqlParams(ctx);
	}

	@Override
	public boolean accept(final T model)
	{
		return filter.accept(model);
	}

}
