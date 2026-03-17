package org.adempiere.ad.dao;

/** */

import org.adempiere.ad.dao.IQueryOrderBy.Direction;
import org.adempiere.ad.dao.IQueryOrderBy.Nulls;
import org.adempiere.model.ModelColumn;

import lombok.NonNull;

public interface IQueryOrderByBuilder<T>
{
	/** @return created {@link IQueryOrderBy} instance */
	IQueryOrderBy createQueryOrderBy();

	/**
	 * Clear current ordering
	 *
	 * @return this
	 */
	IQueryOrderByBuilder<T> clear();

	IQueryOrderByBuilder<T> copy();

	/** Order by the given columnName ascending, nulls last. */
	IQueryOrderByBuilder<T> addColumn(String columnName);

	/** Order by the given column ascending, nulls last. */
	IQueryOrderByBuilder<T> addColumn(ModelColumn<T, ?> column);

	/**
	 * @deprecated please use {@link #addColumnAscending(String)} or {@link #addColumnDescending(String)}.
	 */
	@Deprecated
	default IQueryOrderByBuilder<T> addColumn(@NonNull final String columnName, final boolean asc)
	{
		return  asc ? addColumnAscending(columnName) : addColumnDescending(columnName);
	}

	IQueryOrderByBuilder<T> addColumnAscending(String columnName);

	IQueryOrderByBuilder<T> addColumnDescending(String columnName);

	IQueryOrderByBuilder<T> addColumn(String columnName, Direction direction, Nulls nulls);

	IQueryOrderByBuilder<T> addColumn(ModelColumn<T, ?> column, Direction direction, Nulls nulls);
}
