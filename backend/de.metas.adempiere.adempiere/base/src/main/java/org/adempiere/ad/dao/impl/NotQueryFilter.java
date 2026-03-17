package org.adempiere.ad.dao.impl;

/** */

import java.util.List;
import java.util.Properties;

import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.ISqlQueryFilter;

import com.google.common.annotations.VisibleForTesting;

import lombok.NonNull;

public class NotQueryFilter<T> implements IQueryFilter<T>, ISqlQueryFilter
{
	public static final <T> IQueryFilter<T> of(@NonNull final IQueryFilter<T> filter)
	{
		if (filter instanceof NotQueryFilter)
		{
			final NotQueryFilter<T> notFilter = (NotQueryFilter<T>)filter;
			return notFilter.filter;
		}
		else
		{
			return new NotQueryFilter<>(filter);
		}
	}

	private final IQueryFilter<T> filter;

	private NotQueryFilter(@NonNull final IQueryFilter<T> filter)
	{
		this.filter = filter;
	}

	@Override
	public String toString()
	{
		return "NOT " + filter;
	}

	@Override
	public boolean accept(T model)
	{
		return !filter.accept(model);
	}

	@Override
	public String getSql()
	{
		final ISqlQueryFilter sqlFilter = ISqlQueryFilter.cast(filter);
		return "NOT (" + sqlFilter.getSql() + ")";
	}

	@Override
	public List<Object> getSqlParams(final Properties ctx)
	{
		final ISqlQueryFilter sqlFilter = ISqlQueryFilter.cast(filter);
		return sqlFilter.getSqlParams(ctx);
	}

	@VisibleForTesting
	public IQueryFilter<T> getFilter()
	{
		return filter;
	}
}
