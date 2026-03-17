package org.adempiere.ad.dao.impl;

/** */

import org.adempiere.ad.dao.IQueryFilterModifier;
import org.adempiere.model.ModelColumn;

import lombok.EqualsAndHashCode;

import javax.annotation.Nullable;

/**
 * Filter for equals. Also supports {@code NULL} values.
 *
 * @param <T>
 */
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
public class EqualsQueryFilter<T> extends CompareQueryFilter<T>
{
	public static <T> EqualsQueryFilter<T> isNull(final String columnName)
	{
		return new EqualsQueryFilter<>(columnName, null);
	}

	public static <T> EqualsQueryFilter<T> of(final String columnName, final Object value)
	{
		return new EqualsQueryFilter<>(columnName, value);
	}

	public static <T> EqualsQueryFilter<T> of(final ModelColumn<T, ?> column, final Object value)
	{
		return new EqualsQueryFilter<>(column, value);
	}

	public EqualsQueryFilter(final String columnName, @Nullable final Object value, final IQueryFilterModifier modifier)
	{
		super(columnName, Operator.EQUAL, value, modifier);
	}

	public EqualsQueryFilter(final String columnName, @Nullable final Object value)
	{
		this(columnName, value, NullQueryFilterModifier.instance);
	}

	public EqualsQueryFilter(final ModelColumn<T, ?> column, final Object value)
	{
		this(column.getColumnName(), value, NullQueryFilterModifier.instance);
	}
}
