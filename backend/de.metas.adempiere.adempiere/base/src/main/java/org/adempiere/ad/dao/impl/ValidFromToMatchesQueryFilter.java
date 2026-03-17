package org.adempiere.ad.dao.impl;

/** */

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.ISqlQueryFilter;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.model.ModelColumn;

import com.google.common.collect.ImmutableList;

public class ValidFromToMatchesQueryFilter<T> implements IQueryFilter<T>, ISqlQueryFilter
{
	private final String validFromColumnName;
	private final String validToColumnName;
	private final Date dateValue;

	private boolean sqlBuilt;
	private String sqlWhereClause = null;
	private List<Object> sqlParams = null;

	public ValidFromToMatchesQueryFilter(final ModelColumn<T, ?> validFromColumn, final ModelColumn<T, ?> validToColumn, final Date dateValue)
	{
		super();
		this.validFromColumnName = validFromColumn == null ? null : validFromColumn.getColumnName();
		this.validToColumnName = validToColumn == null ? null : validToColumn.getColumnName();
		this.dateValue = (Date)dateValue.clone();
	}

	@Override
	public String getSql()
	{
		buildSql();
		return sqlWhereClause;
	}

	@Override
	public List<Object> getSqlParams(final Properties ctx)
	{
		buildSql();
		return sqlParams;
	}

	private final void buildSql()
	{
		if (sqlBuilt)
		{
			return;
		}

		final StringBuilder sqlWhereClause = new StringBuilder();
		final ImmutableList.Builder<Object> sqlParams = ImmutableList.builder();

		// (ValidFrom IS NULL OR ValidFrom <= Date)
		if (validFromColumnName != null)
		{
			sqlWhereClause.append("(");
			sqlWhereClause.append(validFromColumnName).append(" IS NULL");
			sqlWhereClause.append(" OR ").append(validFromColumnName).append(" <= ?");
			sqlWhereClause.append(")");
			sqlParams.add(dateValue);
		}

		// (ValidTo IS NULL OR ValidTo >= Date)
		if (validToColumnName != null)
		{
			if (sqlWhereClause.length() > 0)
			{
				sqlWhereClause.append(" AND ");
			}
			sqlWhereClause.append("(");
			sqlWhereClause.append(validToColumnName).append(" IS NULL");
			sqlWhereClause.append(" OR ").append(validToColumnName).append(" >= ?");
			sqlWhereClause.append(")");
			sqlParams.add(dateValue);
		}

		this.sqlWhereClause = sqlWhereClause.toString();
		this.sqlParams = sqlParams.build();
		this.sqlBuilt = true;
	}

	@Override
	public boolean accept(final T model)
	{
		final Date validFrom = getDate(model, validFromColumnName);
		if (validFrom != null && validFrom.compareTo(dateValue) > 0)
		{
			return false;
		}

		final Date validTo = getDate(model, validToColumnName);
		if (validTo != null && validTo.compareTo(dateValue) < 0)
		{
			return false;
		}

		return true;
	}

	private final Date getDate(final T model, final String dateColumnName)
	{
		if (dateColumnName == null)
		{
			return null;
		}

		if (!InterfaceWrapperHelper.hasModelColumnName(model, dateColumnName))
		{
			return null;
		}

		final Optional<Date> date = InterfaceWrapperHelper.getValue(model, dateColumnName);
		return date.orElse(null);
	}
}
