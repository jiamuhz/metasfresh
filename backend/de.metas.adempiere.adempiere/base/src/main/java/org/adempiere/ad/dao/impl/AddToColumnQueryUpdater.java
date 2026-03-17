package org.adempiere.ad.dao.impl;

import de.metas.util.Check;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.ISqlQueryFilter;
import org.adempiere.ad.dao.ISqlQueryUpdater;
import org.adempiere.exceptions.DBException;
import org.adempiere.model.InterfaceWrapperHelper;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

/** */

class AddToColumnQueryUpdater<T> implements ISqlQueryUpdater<T>
{
	private final String columnName;
	private final BigDecimal value;
	private final IQueryFilter<T> onlyWhenFilter;

	public AddToColumnQueryUpdater(final String columnName, final BigDecimal value, @Nullable final IQueryFilter<T> onlyWhenFilter)
	{
		Check.assumeNotEmpty(columnName, "columnName not empty");
		this.columnName = columnName;

		Check.assumeNotNull(value, "value not null");
		this.value = value;
		
		this.onlyWhenFilter = onlyWhenFilter;
	}

	@Override
	public String getSql(final Properties ctx, final List<Object> params)
	{
		final StringBuilder sql = new StringBuilder();
		final StringBuilder sqlEnding = new StringBuilder();
		
		sql.append(columnName).append("=");
		
		if (onlyWhenFilter == null)
		{
			// nothing
		}
		else if (onlyWhenFilter instanceof ISqlQueryFilter)
		{
			final ISqlQueryFilter onlyWhenSqlFilter = ISqlQueryFilter.cast(onlyWhenFilter);
			
			sql.append("(CASE WHEN ").append(onlyWhenSqlFilter.getSql()).append(" THEN ");
			params.addAll(onlyWhenSqlFilter.getSqlParams(ctx));
			//
			sqlEnding.append(" ELSE ").append(columnName).append(" END)");
		}
		else
		{
			throw new DBException("Cannot convert filter to SQL: "+onlyWhenFilter);
		}

		sql.append(columnName).append(" + ?").append(sqlEnding);
		params.add(value);

		return sql.toString();
	}

	@Override
	public boolean update(final T model)
	{
		if (onlyWhenFilter != null && !onlyWhenFilter.accept(model))
		{
			return MODEL_SKIPPED; // not updated
		}
		
		BigDecimal valueOld = InterfaceWrapperHelper.getValueOrNull(model, columnName);
		if(valueOld == null)
		{
			valueOld = BigDecimal.ZERO;
		}
		
		final BigDecimal valueNew = valueOld.add(value);

		return InterfaceWrapperHelper.setValue(model, columnName, valueNew);
	}

}
