package org.adempiere.ad.dao.impl;

/** */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.annotation.Nullable;

import org.adempiere.ad.dao.ICompositeQueryFilter;
import org.adempiere.ad.dao.ICompositeQueryUpdater;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.ad.dao.IQueryOrderByBuilder;
import org.compiere.Adempiere;
import org.compiere.SpringContextHolder;

import de.metas.dao.selection.pagination.PaginationService;
import de.metas.dao.selection.pagination.QueryResultPage;
import lombok.NonNull;

public class QueryBL implements IQueryBL
{
	@Override
	public <T> IQueryBuilder<T> createQueryBuilder(final Class<T> modelClass, final Properties ctx, @Nullable final String trxName)
	{
		return new QueryBuilder<>(modelClass, null)
				.setContext(ctx, trxName);
	}

	@Override
	public <T> IQueryBuilder<T> createQueryBuilder(final Class<T> modelClass, final Object contextProvider)
	{
		return new QueryBuilder<>(modelClass, null)
				.setContext(contextProvider);
	}

	@Override
	public <T> IQueryBuilder<T> createQueryBuilder(Class<T> modelClass, String tableName, Object contextProvider)
	{
		return new QueryBuilder<>(modelClass, tableName)
				.setContext(contextProvider);
	}

	@Override
	public IQueryBuilder<Object> createQueryBuilder(final String modelTableName, final Properties ctx, final String trxName)
	{
		return QueryBuilder.createForTableName(modelTableName)
				.setContext(ctx, trxName);
	}

	@Override
	public IQueryBuilder<Object> createQueryBuilder(final String modelTableName, final Object contextProvider)
	{
		return QueryBuilder.createForTableName(modelTableName)
				.setContext(contextProvider);
	}

	@Deprecated
	@Override
	public <T> IQueryOrderByBuilder<T> createQueryOrderByBuilder()
	{
		return new QueryOrderByBuilder<>();
	}

	@Override
	public <T> IQueryOrderByBuilder<T> createQueryOrderByBuilder(final Class<T> modelClass)
	{
		return new QueryOrderByBuilder<>();
	}

	@Override
	public IQueryOrderBy createSqlQueryOrderBy(final String orderBy)
	{
		return new SqlQueryOrderBy(orderBy);
	}

	@Override
	public <T> ICompositeQueryFilter<T> createCompositeQueryFilter(final Class<T> modelClass)
	{
		return new CompositeQueryFilter<>(modelClass);
	}

	@Override
	public ICompositeQueryFilter<Object> createCompositeQueryFilter(final String modelTableName)
	{
		return new CompositeQueryFilter<>(modelTableName);
	}

	@Override
	public <T> ICompositeQueryUpdater<T> createCompositeQueryUpdater(final Class<T> modelClass)
	{
		return new CompositeQueryUpdater<T>();
	}

	@Override
	public <T> String debugAccept(final IQueryFilter<T> filter, final T model)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("\n-------------------------------------------------------------------------------");
		sb.append("\nModel: " + model);
		final List<IQueryFilter<T>> filters = extractAllFilters(filter);
		for (final IQueryFilter<T> f : filters)
		{
			final boolean accept = f.accept(model);
			sb.append("\nFilter(accept=" + accept + "): " + f.toString());
		}
		sb.append("\n-------------------------------------------------------------------------------");

		return sb.toString();
	}

	private <T> List<IQueryFilter<T>> extractAllFilters(@Nullable final IQueryFilter<T> filter)
	{
		if (filter == null)
		{
			return Collections.emptyList();
		}

		final List<IQueryFilter<T>> result = new ArrayList<>();

		result.add(filter);

		if (filter instanceof ICompositeQueryFilter)
		{
			final ICompositeQueryFilter<T> compositeFilter = (ICompositeQueryFilter<T>)filter;
			for (final IQueryFilter<T> f : compositeFilter.getFilters())
			{
				final List<IQueryFilter<T>> resultLocal = extractAllFilters(f);
				result.addAll(resultLocal);
			}
		}

		return result;
	}

	@Override
	public <T> QueryResultPage<T> retrieveNextPage(
			@NonNull final Class<T> clazz,
			@NonNull final String next)
	{
		if (Adempiere.isUnitTestMode())
		{
			return POJOQuery.getPage(clazz, next);
		}
		return SpringContextHolder.instance
				.getBean(PaginationService.class)
				.loadPage(clazz, next);
	}
}
