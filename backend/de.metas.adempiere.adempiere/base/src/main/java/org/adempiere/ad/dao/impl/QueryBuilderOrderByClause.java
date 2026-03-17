package org.adempiere.ad.dao.impl;

/** */

import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.IQueryBuilderOrderByClause;
import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.ad.dao.IQueryOrderBy.Direction;
import org.adempiere.ad.dao.IQueryOrderBy.Nulls;
import org.adempiere.ad.dao.IQueryOrderByBuilder;
import org.adempiere.model.ModelColumn;

import lombok.NonNull;

class QueryBuilderOrderByClause<ModelType> implements IQueryBuilderOrderByClause<ModelType>
{
	private final IQueryBuilder<ModelType> parent;
	private final IQueryOrderByBuilder<ModelType> orderByBuilder;

	public QueryBuilderOrderByClause(@NonNull final IQueryBuilder<ModelType> parent)
	{
		this.parent = parent;
		this.orderByBuilder = new QueryOrderByBuilder<>();
	}

	/** Copy constructor */
	private QueryBuilderOrderByClause(@NonNull final QueryBuilderOrderByClause<ModelType> orderByClause)
	{
		this.parent = orderByClause.parent;
		this.orderByBuilder = orderByClause.orderByBuilder.copy();
	}

	@Override
	public IQueryBuilder<ModelType> endOrderBy()
	{
		return parent;
	}

	@Override
	public IQueryOrderBy createQueryOrderBy()
	{
		return orderByBuilder.createQueryOrderBy();
	}

	@Override
	public IQueryBuilderOrderByClause<ModelType> clear()
	{
		orderByBuilder.clear();
		return this;
	}

	@Override
	public QueryBuilderOrderByClause<ModelType> copy()
	{
		return new QueryBuilderOrderByClause<>(this);
	}

	@Override
	public IQueryBuilderOrderByClause<ModelType> addColumn(String columnName)
	{
		orderByBuilder.addColumn(columnName);
		return this;
	}

	@Override
	public IQueryBuilderOrderByClause<ModelType> addColumn(ModelColumn<ModelType, ?> column)
	{
		orderByBuilder.addColumn(column);
		return this;
	}

	@Override
	public IQueryBuilderOrderByClause<ModelType> addColumnAscending(String columnName)
	{
		orderByBuilder.addColumnAscending(columnName);
		return this;
	}

	@Override
	public IQueryBuilderOrderByClause<ModelType> addColumnDescending(String columnName)
	{
		orderByBuilder.addColumnDescending(columnName);
		return this;
	}

	@Override
	public IQueryBuilderOrderByClause<ModelType> addColumn(String columnName, Direction direction, Nulls nulls)
	{
		orderByBuilder.addColumn(columnName, direction, nulls);
		return this;
	}

	@Override
	public IQueryBuilderOrderByClause<ModelType> addColumn(ModelColumn<ModelType, ?> column, Direction direction, Nulls nulls)
	{
		orderByBuilder.addColumn(column, direction, nulls);
		return this;
	}
}
