package org.adempiere.ad.dao.impl;

/** */

import de.metas.util.Check;
import lombok.NonNull;
import org.adempiere.ad.dao.ICompositeQueryUpdater;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.IQueryUpdater;
import org.adempiere.ad.dao.ISqlQueryUpdater;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/* package */class CompositeQueryUpdater<T> implements ICompositeQueryUpdater<T>
{
	private final List<IQueryUpdater<T>> queryUpdaters = new ArrayList<>();

	private String sql = null;
	private List<Object> sqlParams = null;
	private boolean sqlBuilt = false;

	public CompositeQueryUpdater()
	{
	}

	@Override
	public ICompositeQueryUpdater<T> addQueryUpdater(@NonNull final IQueryUpdater<T> updater)
	{
		queryUpdaters.add(updater);

		sqlBuilt = false;

		return this;
	}

	@Override
	public ICompositeQueryUpdater<T> addSetColumnValue(final String columnName, @Nullable final Object value)
	{
		final IQueryUpdater<T> updater = new SetColumnNameQueryUpdater<>(columnName, value);
		return addQueryUpdater(updater);
	}

	@Override
	public ICompositeQueryUpdater<T> addSetColumnFromColumn(final String columnName, final ModelColumnNameValue<T> fromColumnName)
	{
		final IQueryUpdater<T> updater = new SetColumnNameQueryUpdater<>(columnName, fromColumnName);
		return addQueryUpdater(updater);
	}

	@Override
	public ICompositeQueryUpdater<T> addAddValueToColumn(final String columnName, final BigDecimal valueToAdd)
	{
		final IQueryFilter<T> onlyWhenFilter = null;
		final IQueryUpdater<T> updater = new AddToColumnQueryUpdater<>(columnName, valueToAdd, onlyWhenFilter);
		return addQueryUpdater(updater);
	}

	@Override
	public ICompositeQueryUpdater<T> addAddValueToColumn(final String columnName, final BigDecimal valueToAdd, final IQueryFilter<T> onlyWhenFilter)
	{
		final IQueryUpdater<T> updater = new AddToColumnQueryUpdater<>(columnName, valueToAdd, onlyWhenFilter);
		return addQueryUpdater(updater);
	}

	@Override
	public boolean update(final T model)
	{
		boolean updated = false;
		for (final IQueryUpdater<T> updater : queryUpdaters)
		{
			if (updater.update(model))
			{
				updated = true;
			}
		}
		return updated;
	}

	@Override
	public String getSql(final Properties ctx, final List<Object> params)
	{
		buildSql(ctx);

		params.addAll(sqlParams);
		return sql;
	}

	private void buildSql(final Properties ctx)
	{
		if (sqlBuilt)
		{
			return;
		}

		if (queryUpdaters.isEmpty())
		{
			throw new AdempiereException("Cannot build sql update query for an empty " + CompositeQueryUpdater.class);
		}

		final StringBuilder sql = new StringBuilder();
		final List<Object> params = new ArrayList<>();

		for (final IQueryUpdater<T> updater : queryUpdaters)
		{
			final ISqlQueryUpdater<T> sqlUpdater = (ISqlQueryUpdater<T>)updater;
			final String sqlChunk = sqlUpdater.getSql(ctx, params);

			if (Check.isEmpty(sqlChunk))
			{
				continue;
			}

			if (sql.length() > 0)
			{
				sql.append(", ");
			}
			sql.append(sqlChunk);
		}

		this.sql = sql.toString();
		this.sqlParams = params;
		this.sqlBuilt = true;
	}
}
