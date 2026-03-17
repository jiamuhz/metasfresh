package org.adempiere.ad.dao.impl;

/** */

import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
import org.adempiere.ad.dao.ISqlQueryUpdater;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.TimeUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Properties;

/* package */class SetColumnNameQueryUpdater<T> implements ISqlQueryUpdater<T>
{
	private final String columnName;
	private final Object value;
	private final ModelColumnNameValue<?> valueColumn;

	public SetColumnNameQueryUpdater(@NonNull final String columnName, @Nullable final Object value)
	{
		Check.assumeNotEmpty(columnName, "columnName not empty");
		this.columnName = columnName;

		if (value instanceof ModelColumnNameValue<?>)
		{
			this.valueColumn = (ModelColumnNameValue<?>)value;
			this.value = null;
		}
		else
		{
			this.valueColumn = null;
			this.value = value;
		}
	}

	@Override
	public String getSql(final Properties ctx, final List<Object> params)
	{
		final StringBuilder sql = new StringBuilder();

		if (valueColumn != null)
		{
			sql.append(columnName).append("=").append(valueColumn.getColumnName());
		}
		else
		{
			sql.append(columnName).append("=?");
			params.add(value);
		}

		return sql.toString();
	}

	@Override
	public boolean update(final T model)
	{
		final Object valueToSet;
		if (valueColumn != null)
		{
			valueToSet = InterfaceWrapperHelper.getValueOrNull(model, valueColumn.getColumnName());
		}
		else
		{
			valueToSet = convertToPOValue(value);
		}

		return InterfaceWrapperHelper.setValue(model, columnName, valueToSet);
	}

	@Nullable
	private static Object convertToPOValue(@Nullable final Object value)
	{
		if (value == null)
		{
			return null;
		}
		else if (value instanceof RepoIdAware)
		{
			return ((RepoIdAware)value).getRepoId();
		}
		else if (TimeUtil.isDateOrTimeObject(value))
		{
			return TimeUtil.asTimestamp(value);
		}
		else
		{
			return value;
		}
	}

}
