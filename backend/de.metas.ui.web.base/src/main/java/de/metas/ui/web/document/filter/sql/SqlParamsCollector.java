package de.metas.ui.web.document.filter.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import org.adempiere.ad.dao.ISqlQueryFilter;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.google.common.base.MoreObjects;

import lombok.EqualsAndHashCode;
import lombok.NonNull;



/**
 * Helper class used to collect SQL parameters and convert parameter values to strings with correct SQL syntax. See the unit tests for usage examples.
 * 
 *
 *
 */
@EqualsAndHashCode
public final class SqlParamsCollector
{
	public static SqlParamsCollector newInstance()
	{
		return new SqlParamsCollector(new ArrayList<>());
	}

	/**
	 * Wraps the given list if not {@code null}. All changes will be directed the list.
	 * If the list is null, the new instance is in "not-collecting" mode.
	 * 
	 * @param list may be {@code null}, but not immutable.
	 */
	public static SqlParamsCollector wrapNullable(@Nullable final List<Object> list)
	{
		if (list == null)
		{
			return NOT_COLLECTING;
		}
		return new SqlParamsCollector(list);
	}

	/** An {@link SqlParamsCollector} which is actually not collecting the parameters but it's automatically translating it to SQL code. */
	public static SqlParamsCollector notCollecting()
	{
		return NOT_COLLECTING;
	}

	private static final SqlParamsCollector NOT_COLLECTING = new SqlParamsCollector(null);

	private final List<Object> params;
	private final List<Object> paramsRO;

	private SqlParamsCollector(final List<Object> params)
	{
		this.params = params;
		paramsRO = params != null ? Collections.unmodifiableList(params) : null;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.addValue(paramsRO)
				.toString();
	}

	public boolean isCollecting()
	{
		return params != null;
	}

	/**
	 * Directly append all sqlParams from the given {@code sqlQueryFilter}.
	 * 
	 * @param sqlQueryFilter
	 * 
	 */
	public void collectAll(@NonNull final ISqlQueryFilter sqlQueryFilter)
	{
		final List<Object> sqlParams = sqlQueryFilter.getSqlParams(Env.getCtx());
		collectAll(sqlParams);
	}

	/**
	 * Directly append the given {@code sqlParams}. Please prefer using {@link #placeholder(Object)} instead.<br>
	 * "Package" scope because currently this method is needed only by {@link SqlDefaultDocumentFilterConverter}.
	 * 
	 * Please avoid using it. It's used mainly to adapt with old code
	 * 
	 * @param sqlParams
	 */
	public void collectAll(@Nullable final Collection<? extends Object> sqlParams)
	{
		if (sqlParams == null || sqlParams.isEmpty())
		{
			return;
		}

		if (params == null)
		{
			throw new IllegalStateException("Cannot append " + sqlParams + " to not collecting params");
		}
		params.addAll(sqlParams);
	}

	public void collect(@NonNull final SqlParamsCollector from)
	{
		collectAll(from.params);
	}

	/**
	 * Collects given SQL value and returns an SQL placeholder, i.e. "?"
	 * 
	 * In case this is in non-collecting mode, the given SQL value will be converted to SQL code and it will be returned.
	 * The internal list won't be affected, because it does not exist.
	 */
	public String placeholder(@Nullable final Object sqlValue)
	{
		if (params == null)
		{
			return DB.TO_SQL(sqlValue);
		}
		else
		{
			params.add(sqlValue);
			return "?";
		}
	}

	/** @return readonly live list */
	public List<Object> toList()
	{
		return paramsRO;
	}

	/** @return read/write live list */
	public List<Object> toLiveList()
	{
		return params;
	}

}
