package org.adempiere.ad.trx.api.impl;

/** */


import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxListenerManager;

import lombok.NonNull;

/**
 * Null {@link ITrxListenerManager} implementation
 *
 * @author tsa
 *
 */
public final class NullTrxListenerManager implements ITrxListenerManager
{
	public static final NullTrxListenerManager instance = new NullTrxListenerManager();

	private NullTrxListenerManager()
	{
	}

	@Override
	public void registerListener(final RegisterListenerRequest listener)
	{
		// nothing
	}
	
	@Override
	public boolean canRegisterOnTiming(@NonNull final TrxEventTiming timing)
	{
		return false;
	}

	/**
	 * Does nothing
	 */
	@Override
	public void fireBeforeCommit(final ITrx trx)
	{
		// nothing
	}

	/**
	 * Does nothing
	 */
	@Override
	public void fireAfterCommit(final ITrx trx)
	{
		// nothing
	}

	/**
	 * Does nothing
	 */
	@Override
	public void fireAfterRollback(final ITrx trx)
	{
		// nothing
	}

	/**
	 * Does nothing
	 */
	@Override
	public void fireAfterClose(ITrx trx)
	{
		// nothing
	}


}
