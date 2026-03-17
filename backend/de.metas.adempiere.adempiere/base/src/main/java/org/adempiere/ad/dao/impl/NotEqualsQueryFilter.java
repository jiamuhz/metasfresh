package org.adempiere.ad.dao.impl;

import lombok.EqualsAndHashCode;

import javax.annotation.Nullable;

/** */

@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
public class NotEqualsQueryFilter<T> extends CompareQueryFilter<T>
{
	public static <T> NotEqualsQueryFilter<T> of(final String columnName, @Nullable final Object value)
	{
		return new NotEqualsQueryFilter<>(columnName, value);
	}

	public NotEqualsQueryFilter(final String columnName, @Nullable final Object value)
	{
		super(columnName, Operator.NOT_EQUAL, value);
	}
}
