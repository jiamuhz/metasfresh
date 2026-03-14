package org.adempiere.ad.dao.impl;

/** */

import java.util.List;
import java.util.Properties;

import org.adempiere.ad.dao.ICompositeQueryFilter;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.ISqlQueryFilter;
import org.adempiere.exceptions.AdempiereException;
import de.metas.common.util.pair.IPair;
import de.metas.common.util.pair.ImmutablePair;
import org.compiere.model.IQuery;

public class PlainQueryBuilderDAO extends AbstractQueryBuilderDAO
{
	@Override
	protected <T> IQuery<T> createQuery(final QueryBuildContext<T> queryBuildCtx,
			final ISqlQueryFilter sqlFilters,
			final IQueryFilter<T> nonSqlFilters)
	{
		final Class<T> modelClass = queryBuildCtx.getModelClass();
		final String tableName = queryBuildCtx.getModelTableName();

		final Properties ctx = queryBuildCtx.getCtx();
		final String trxName = queryBuildCtx.getTrxName();

		final POJOQuery<T> query = new POJOQuery<>(ctx, modelClass, tableName, trxName)
				.setOrderBy(queryBuildCtx.getQueryOrderBy())
				.setLimit(queryBuildCtx.getQueryLimit())
				.setOnlySelection(queryBuildCtx.getQueryOnlySelectionId())
				.setOptions(queryBuildCtx.getQueryOptions());

		//
		// Add the SQL filters
		if (sqlFilters != null)
		{
			if (sqlFilters instanceof IQueryFilter)
			{
				@SuppressWarnings("unchecked")
				final IQueryFilter<T> sqlFiltersCasted = (IQueryFilter<T>)sqlFilters;
				query.addFilter(sqlFiltersCasted);
			}
			else
			{
				throw new AdempiereException("Sql filter could not be converted to regular filter: " + sqlFilters);
			}
		}

		//
		// Add the non-SQL filters
		if (nonSqlFilters != null)
		{
			query.addFilter(nonSqlFilters);
		}

		return query;
	}

	@Override
	protected <T> IPair<ISqlQueryFilter, IQueryFilter<T>> extractSqlAndNonSqlFilters(IQueryFilter<T> filter)
	{
		// NOTE: SQL filters are not supported in Plain DAO
		final ISqlQueryFilter sqlFilters = null;
		final IQueryFilter<T> nonSqlFilters = filter;
		return ImmutablePair.of(sqlFilters, nonSqlFilters);
	}

	@Override
	public <T> String getSql(final Properties ctx, final ICompositeQueryFilter<T> filter, final List<Object> sqlParamsOut)
	{
		throw new UnsupportedOperationException();
	}

}
