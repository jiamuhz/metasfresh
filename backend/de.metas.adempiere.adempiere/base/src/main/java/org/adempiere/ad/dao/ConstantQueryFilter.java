package org.adempiere.ad.dao;

/** */

import lombok.EqualsAndHashCode;

import javax.annotation.concurrent.Immutable;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Constant {@link IQueryFilter} implementation (with {@link ISqlQueryFilter} support).
 *
 * When you need a constant query filter, just call {@link #of(boolean)}.
 *
 * @author tsa
 *
 * @param <T> model type
 */
@Immutable
@EqualsAndHashCode
public final class ConstantQueryFilter<T> implements IQueryFilter<T>, ISqlQueryFilter
{
	private static final ConstantQueryFilter<Object> TRUE = new ConstantQueryFilter<>(true);
	private static final ConstantQueryFilter<Object> FALSE = new ConstantQueryFilter<>(false);

	@SuppressWarnings("unchecked")
	public static <T> ConstantQueryFilter<T> of(final boolean value)
	{
		return (ConstantQueryFilter<T>)(value ? TRUE : FALSE);
	}

	/* package */static final String SQL_TRUE = "1=1";
	/* package */static final String SQL_FALSE = "1=0";

	private final boolean value;

	private ConstantQueryFilter(final boolean value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return String.valueOf(value);
	}

	@Override
	public String getSql()
	{
		return value ? SQL_TRUE : SQL_FALSE;
	}

	@Override
	public List<Object> getSqlParams(final Properties ctx)
	{
		return getSqlParams();
	}

	public List<Object> getSqlParams()
	{
		return Collections.emptyList();
	}

	@Override
	public boolean accept(final T model)
	{
		return value;
	}
}
