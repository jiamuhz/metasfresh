package org.adempiere.ad.dao.impl;

/** */


import java.util.Comparator;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.adempiere.util.comparator.AccessorComparator;
import org.adempiere.util.comparator.ComparableComparator;
import org.adempiere.util.comparator.ComparableComparatorNullsEqual;
import org.adempiere.util.comparator.ComparatorChain;
import org.adempiere.util.comparator.NullComparator;

import com.google.common.collect.ImmutableList;

@Immutable
class QueryOrderBy extends AbstractQueryOrderBy
{
	private final List<QueryOrderByItem> items;

	private boolean sqlOrderByCompiled = false;
	private String sqlOrderBy = null;

	private Comparator<Object> comparator = null;

	public QueryOrderBy(final List<QueryOrderByItem> items)
	{
		super();
		if (items == null || items.isEmpty())
		{
			this.items = ImmutableList.of();
		}
		else
		{
			this.items = ImmutableList.copyOf(items);
		}
	}

	@Override
	public String toString()
	{
		return "QueryOrderBy[" + items + "]";
	}

	@Override
	public String getSql()
	{
		if (sqlOrderByCompiled)
		{
			return sqlOrderBy;
		}

		if (items != null && !items.isEmpty())
		{
			final StringBuilder sqlBuf = new StringBuilder();
			for (final QueryOrderByItem item : items)
			{
				appendSql(sqlBuf, item);
			}
			sqlOrderBy = sqlBuf.toString();
		}
		else
		{
			sqlOrderBy = null;
		}
		sqlOrderByCompiled = true;

		return sqlOrderBy;
	}

	// NOTE: not static just because we want to build nice exceptions
	private final void appendSql(final StringBuilder sql, final QueryOrderByItem item)
	{
		if (sql.length() > 0)
		{
			sql.append(", ");
		}

		//
		// ColumnName
		sql.append(item.getColumnName());

		//
		// Direction ASC/DESC
		final Direction direction = item.getDirection();
		if (direction == Direction.Ascending)
		{
			sql.append(" ASC");
		}
		else if (direction == Direction.Descending)
		{
			sql.append(" DESC");
		}
		else
		{
			throw new IllegalStateException("Unknown direction '" + direction + "' for " + this);
		}

		//
		// Nulls First/Last
		final Nulls nulls = item.getNulls();
		if (nulls == Nulls.First)
		{
			sql.append(" NULLS FIRST");
		}
		else if (nulls == Nulls.Last)
		{
			sql.append(" NULLS LAST");
		}
		else
		{
			throw new IllegalStateException("Unknown NULLS option '" + nulls + "' for " + this);
		}
	}

	@Override
	public Comparator<Object> getComparator()
	{
		if (comparator != null)
		{
			return comparator;
		}

		if (items != null && !items.isEmpty())
		{
			final ComparatorChain<Object> cmpChain = new ComparatorChain<Object>();
			for (final QueryOrderByItem item : items)
			{
				@SuppressWarnings("rawtypes")
				final ModelAccessor<Comparable> accessor = new ModelAccessor<Comparable>(item.getColumnName());


				final boolean reverse = item.getDirection() == Direction.Descending;
				@SuppressWarnings("rawtypes")
				final Comparator<Object> cmpDirection = new AccessorComparator<Object, Comparable>(
						ComparableComparatorNullsEqual.getInstance(),
						accessor);
				cmpChain.addComparator(cmpDirection, reverse);

				final boolean nullsFirst = item.getNulls() == Nulls.First ? true : false;
				@SuppressWarnings("rawtypes")
				final Comparator<Object> cmpNulls = new AccessorComparator<Object, Comparable>(
						ComparableComparator.getInstance(nullsFirst),
						accessor);

				cmpChain.addComparator(cmpNulls);
			}
			comparator = cmpChain;
		}
		else
		{
			comparator = NullComparator.getInstance();
		}

		return comparator;
	}
}
