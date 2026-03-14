package org.adempiere.ad.dao.impl;

/** */

import lombok.NonNull;
import org.adempiere.ad.dao.ICompositeQueryUpdaterExecutor;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.IQueryUpdater;
import org.compiere.model.IQuery;

import javax.annotation.Nullable;
import java.math.BigDecimal;

/* package */class CompositeQueryUpdaterExecutor<T>
		extends CompositeQueryUpdater<T>
		implements ICompositeQueryUpdaterExecutor<T>
{
	private final IQuery<T> query;
	private boolean executeDirectly = true;

	public CompositeQueryUpdaterExecutor(@NonNull final IQuery<T> query)
	{
		this.query = query;
	}

	@Override
	public int execute()
	{
		if (executeDirectly)
		{
			return query.updateDirectly(this);
		}
		else
		{
			return query.update(this);
		}
	}

	@Override
	public ICompositeQueryUpdaterExecutor<T> setExecuteDirectly(final boolean executeDirectly)
	{
		this.executeDirectly = executeDirectly;
		return this;
	}

	@Override
	public ICompositeQueryUpdaterExecutor<T> addQueryUpdater(final @NonNull IQueryUpdater<T> updater)
	{
		super.addQueryUpdater(updater);
		return this;
	}

	@Override
	public ICompositeQueryUpdaterExecutor<T> addSetColumnValue(final String columnName, @Nullable final Object value)
	{
		super.addSetColumnValue(columnName, value);
		return this;
	}

	@Override
	public ICompositeQueryUpdaterExecutor<T> addSetColumnFromColumn(final String columnName, final ModelColumnNameValue<T> fromColumnName)
	{
		super.addSetColumnFromColumn(columnName, fromColumnName);
		return this;
	}

	@Override
	public ICompositeQueryUpdaterExecutor<T> addAddValueToColumn(final String columnName, final BigDecimal valueToAdd)
	{
		super.addAddValueToColumn(columnName, valueToAdd);
		return this;
	}

	@Override
	public ICompositeQueryUpdaterExecutor<T> addAddValueToColumn(final String columnName, final BigDecimal valueToAdd, final IQueryFilter<T> onlyWhenFilter)
	{
		super.addAddValueToColumn(columnName, valueToAdd, onlyWhenFilter);
		return this;
	}
}
