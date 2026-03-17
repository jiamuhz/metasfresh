package org.adempiere.ad.trx.api.impl;

/** */

import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxListenerManager;
import org.adempiere.exceptions.AdempiereException;

import lombok.NonNull;

/**
 * An {@link ITrxListenerManager} implementation which directly executes {@link ITrxListener#beforeCommit(ITrx)} and {@link ITrxListener#afterCommit(ITrx)} when a listener is registered.
 *
 * @author tsa
 *
 */
/* package */final class AutoCommitTrxListenerManager implements ITrxListenerManager
{
	public static final transient AutoCommitTrxListenerManager instance = new AutoCommitTrxListenerManager();

	private AutoCommitTrxListenerManager()
	{
	}

	@Override
	public void registerListener(@NonNull final RegisterListenerRequest listener)
	{
		execute(listener);
	}

	@Override
	public boolean canRegisterOnTiming(@NonNull final TrxEventTiming timing)
	{
		// any timing is accepted because we are executing directly
		return true;
	}

	private void execute(final RegisterListenerRequest listener)
	{
		if (!listener.isActive())
		{
			return; // nothing to do
		}
		if (!TrxEventTiming.BEFORE_COMMIT.equals(listener.getTiming())
				&& !TrxEventTiming.AFTER_COMMIT.equals(listener.getTiming())
				&& !TrxEventTiming.AFTER_CLOSE.equals(listener.getTiming()))
		{
			return; // nothing to do
		}
		try
		{
			listener.getHandlingMethod().onTransactionEvent(ITrx.TRX_None);
		}
		catch (Exception e)
		{
			throw AdempiereException.wrapIfNeeded(e)
					.setParameter("listener", listener)
					.appendParametersToMessage();
		}
	}

	@Override
	public void fireBeforeCommit(final ITrx trx)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void fireAfterCommit(final ITrx trx)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void fireAfterRollback(final ITrx trx)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void fireAfterClose(ITrx trx)
	{
		throw new UnsupportedOperationException();
	}
}
