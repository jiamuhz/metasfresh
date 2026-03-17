package org.adempiere.ad.trx.api;

import java.sql.SQLException;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

import org.adempiere.exceptions.DBException;

import javax.annotation.Nullable;


/** */

/**
 * {@link ITrx} implementation to be used as null/out-of-transaction placeholder where null {@link ITrx} are not allowed (e.g. guava cache).
 * 
 *
 *
 */
public final class NullTrxPlaceholder implements ITrx
{
	public static final transient NullTrxPlaceholder instance = new NullTrxPlaceholder();

	public static ITrx boxNotNull(@Nullable final ITrx trx)
	{
		return trx != null ? trx : instance;
	}

	@Nullable
	public static ITrx unboxToNull(@Nullable final ITrx trx)
	{
		return trx != instance ? trx : null;
	}

	private NullTrxPlaceholder()
	{
	}

	@Override
	public String getTrxName()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean start()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean close()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isActive()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isAutoCommit()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Date getStartTime()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean commit(final boolean throwException) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean rollback()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean rollback(final boolean throwException) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean rollback(final ITrxSavepoint savepoint) throws DBException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ITrxSavepoint createTrxSavepoint(final String name) throws DBException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void releaseSavepoint(final ITrxSavepoint savepoint)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ITrxListenerManager getTrxListenerManager()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ITrxManager getTrxManager()
	{
		throw new UnsupportedOperationException();

	}

	@Override
	public <T> T setProperty(final String name, final Object value)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T getProperty(final String name)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T getProperty(final String name, final Supplier<T> valueInitializer)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T getProperty(final String name, final Function<ITrx, T> valueInitializer)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T setAndGetProperty(final String name, final Function<T, T> valueRemappingFunction)
	{
		throw new UnsupportedOperationException();
	}

}
