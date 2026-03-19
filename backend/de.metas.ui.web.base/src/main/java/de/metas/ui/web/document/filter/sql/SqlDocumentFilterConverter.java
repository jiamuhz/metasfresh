package de.metas.ui.web.document.filter.sql;

import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.view.descriptor.SqlAndParams;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.collections.CollectionUtils;
import lombok.NonNull;
import org.adempiere.ad.dao.IQueryFilter;
import org.compiere.util.DB;

import javax.annotation.Nullable;
import java.util.HashSet;



/**
 * Converts a {@link DocumentFilter} to SQL.
 * <p>
 * Implementations are automatically discovered if they are spring beans.
 */
public interface SqlDocumentFilterConverter
{
	/**
	 * @return true if the filter identified by <code>filterId</code> can be converted to SQL by this converter
	 */
	boolean canConvert(final String filterId);

	/**
	 * Converts given <code>filter</code> to SQL and returns it.
	 * In case the produced SQL requires parameters, those parameters will be added to <code>sqlParamsOut</code> parameter.
	 *
	 * @return SQL
	 */
	@Nullable
	FilterSql getSql(DocumentFilter filter,
					 SqlOptions sqlOpts,
					 SqlDocumentFilterConverterContext context);

	@NonNull
	default FilterSql getSql(
			@NonNull final DocumentFilterList filters,
			@NonNull final SqlOptions sqlOpts,
			@NonNull final SqlDocumentFilterConverterContext context)
	{
		if (filters.isEmpty())
		{
			return FilterSql.ALLOW_ALL;
		}

		final SqlAndParams.Builder whereClauseBuilder = SqlAndParams.builder();
		boolean whereClauseAllowNone = false;
		final HashSet<FilterSql.FullTextSearchResult> filterByFTS = new HashSet<>();
		final HashSet<FilterSql.RecordsToAlwaysIncludeSql> alwaysIncludeSqls = new HashSet<>();
		final SqlAndParams.Builder orderByBuilder = SqlAndParams.builder();

		for (final DocumentFilter filter : filters.toList())
		{
			final FilterSql filterSql = getSql(filter, sqlOpts, context);
			if (filterSql == null)
			{
				continue;
			}

			//
			// Where Clause
			if (!whereClauseAllowNone)
			{
				final SqlAndParams filterWhereClause = filterSql.getWhereClause();
				if (filterWhereClause != null && !filterWhereClause.isEmpty())
				{
					// Got and ALLOW NONE where clause
					if (SqlAndParams.equals(filterWhereClause, FilterSql.ALLOW_NONE.getWhereClause()))
					{
						whereClauseAllowNone = true;
						whereClauseBuilder.clear();
						// NOTE: we cleared the code from all other filters because it makes no sense.
						// bellow we will add the SQL code for this filter together with the filterId and sql comment if any.
						// this will be the only filter that we will keep in the resulting where clause.

						filterByFTS.clear();
					}

					whereClauseBuilder
							.appendIfNotEmpty("\n AND ")
							.append(DB.TO_COMMENT(filter.getFilterId()))
							.append(filterSql.getSqlComment() != null ? DB.TO_COMMENT(filterSql.getSqlComment()) : "")
							.append(" (").append(filterWhereClause).append(")");
				}
			}

			//
			// Full Text Search
			if (!whereClauseAllowNone)
			{
				if (filterSql.getFilterByFTS() != null)
				{
					filterByFTS.add(filterSql.getFilterByFTS());
				}
			}

			//
			// Always Include SQLs
			if (filterSql.getAlwaysIncludeSql() != null)
			{
				alwaysIncludeSqls.add(filterSql.getAlwaysIncludeSql());
			}

			//
			// ORDER BY
			if (filterSql.getOrderBy() != null && !filterSql.getOrderBy().isEmpty())
			{
				if (!orderByBuilder.isEmpty())
				{
					orderByBuilder.append("\n, ");
				}
				orderByBuilder.append(DB.TO_COMMENT(filter.getFilterId())).append(filterSql.getOrderBy());
			}
		}

		//
		// Allow NONE
		if (whereClauseAllowNone && alwaysIncludeSqls.isEmpty())
		{
			return FilterSql.ALLOW_NONE;
		}
		else
		{
			return FilterSql.builder()
					.whereClause(!whereClauseBuilder.isEmpty() ? whereClauseBuilder.build() : null)
					.filterByFTS(CollectionUtils.emptyOrSingleElement(filterByFTS).orElse(null))
					.alwaysIncludeSql(FilterSql.RecordsToAlwaysIncludeSql.mergeOrNull(alwaysIncludeSqls))
					.orderBy(!orderByBuilder.isEmpty() ? orderByBuilder.build() : null)
					.build();
		}
	}

	default <T> IQueryFilter<T> createQueryFilter(
			@NonNull final DocumentFilterList filters,
			@NonNull final SqlOptions sqlOpts,
			@NonNull final SqlDocumentFilterConverterContext context)
	{
		return getSql(filters, sqlOpts, context)
				.toQueryFilterOrAllowAll(sqlOpts);
	}
}
