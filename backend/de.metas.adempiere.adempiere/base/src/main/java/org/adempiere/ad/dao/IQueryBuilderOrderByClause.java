package org.adempiere.ad.dao;

/** */

import org.adempiere.ad.dao.IQueryOrderBy.Direction;
import org.adempiere.ad.dao.IQueryOrderBy.Nulls;
import org.adempiere.model.ModelColumn;

/**
 * {@link IQueryBuilder}'s ORDER BY clause.
 *
 * @param <ModelType>
 * @author tsa
 */
public interface IQueryBuilderOrderByClause<ModelType> extends IQueryOrderByBuilder<ModelType>
{
	/**
	 * Ends current ORDER BY clause and returns the {@link IQueryBuilder}.
	 * <p>
	 * This allows the developer to write fluently.
	 */
	IQueryBuilder<ModelType> endOrderBy();

	@Override
	IQueryBuilderOrderByClause<ModelType> clear();

	@Override
	IQueryOrderBy createQueryOrderBy();

	@Override
	IQueryBuilderOrderByClause<ModelType> copy();

	/**
	 * order ascending, with {@code NULLS LAST}
	 */
	@Override
	IQueryBuilderOrderByClause<ModelType> addColumn(String columnName);

	@Override
	IQueryBuilderOrderByClause<ModelType> addColumn(ModelColumn<ModelType, ?> column);

	/**
	 * @deprecated please use {@link #addColumnAscending(String)} and {@link #addColumnDescending(String)}.
	 */
	@Override
	@Deprecated
	default IQueryBuilderOrderByClause<ModelType> addColumn(String columnName, boolean asc)
	{
		return asc ? addColumnAscending(columnName) : addColumnDescending(columnName);
	}

	/**
	 * Note: ascending will by default have {@code NULLS LAST}
	 */
	@Override
	IQueryBuilderOrderByClause<ModelType> addColumnAscending(String columnName);

	/**
	 * Note: descending will by default have {@code NULLS FIRST}
	 */
	@Override
	IQueryBuilderOrderByClause<ModelType> addColumnDescending(String columnName);

	@Override
	IQueryBuilderOrderByClause<ModelType> addColumn(String columnName, Direction direction, Nulls nulls);

	@Override
	IQueryBuilderOrderByClause<ModelType> addColumn(ModelColumn<ModelType, ?> column, Direction direction, Nulls nulls);
}
