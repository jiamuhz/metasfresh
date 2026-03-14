package org.adempiere.ad.dao.impl;

/** */


import java.util.ArrayList;
import java.util.List;

import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.ad.dao.IQueryOrderBy.Direction;
import org.adempiere.ad.dao.IQueryOrderBy.Nulls;
import org.adempiere.ad.dao.IQueryOrderByBuilder;
import org.adempiere.model.ModelColumn;

import lombok.NonNull;

final class QueryOrderByBuilder<T> implements IQueryOrderByBuilder<T>
{
	private List<QueryOrderByItem> orderBys = new ArrayList<QueryOrderByItem>();

	public QueryOrderByBuilder()
	{
	}

	@Override
	public IQueryOrderByBuilder<T> clear()
	{
		orderBys.clear();
		return this;
	}

	@Override
	public QueryOrderByBuilder<T> copy()
	{
		final QueryOrderByBuilder<T> copy = new QueryOrderByBuilder<T>();
		copy.orderBys.addAll(this.orderBys);
		return copy;
	}

	@Override
	public IQueryOrderByBuilder<T> addColumn(final String columnName)
	{
		final Direction direction = Direction.Ascending;
		final Nulls nulls = getNulls(direction);
		return addColumn(columnName, direction, nulls);
	}

	@Override
	public IQueryOrderByBuilder<T> addColumn(ModelColumn<T, ?> column)
	{
		final String columnName = column.getColumnName();
		return addColumn(columnName);
	}

	@Override
	public IQueryOrderByBuilder<T> addColumnAscending(@NonNull final String columnName)
	{
		final Direction direction = Direction.Ascending;
		final Nulls nulls = getNulls(direction);
		return addColumn(columnName, direction, nulls);
	}

	@Override
	public IQueryOrderByBuilder<T> addColumnDescending(@NonNull final String columnName)
	{
		final Direction direction = Direction.Descending;
		final Nulls nulls = getNulls(direction);
		return addColumn(columnName, direction, nulls);
	}

	@Override
	public IQueryOrderByBuilder<T> addColumn(
			@NonNull final String columnName,
			@NonNull final Direction direction,
			@NonNull final Nulls nulls)
	{
		final QueryOrderByItem orderByItem = new QueryOrderByItem(columnName, direction, nulls);
		orderBys.add(orderByItem);

		return this;
	}

	@Override
	public IQueryOrderByBuilder<T> addColumn(@NonNull final ModelColumn<T, ?> column, final Direction direction, final Nulls nulls)
	{
		final String columnName = column.getColumnName();
		return addColumn(columnName, direction, nulls);
	}

	private Nulls getNulls(final Direction direction)
	{
		// NOTE: keeping backward compatibility
		// i.e. postgresql 9.1. specifications:
		// "By default, null values sort as if larger than any non-null value;
		// that is, NULLS FIRST is the default for DESC order, and NULLS LAST otherwise."
		//
		// see https://www.postgresql.org/docs/9.5/queries-order.html
		if (direction == Direction.Descending)
		{
			return Nulls.First;
		}
		else
		{
			return Nulls.Last;
		}
	}

	@Override
	public IQueryOrderBy createQueryOrderBy()
	{
		final QueryOrderBy orderBy = new QueryOrderBy(orderBys);
		return orderBy;
	}
}
