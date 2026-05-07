package de.metas.handlingunits.allocation.transfer.impl;

import de.metas.handlingunits.IHUContext;
import de.metas.handlingunits.hutransaction.IHUTransactionCandidate;
import de.metas.handlingunits.hutransaction.IHUTrxListener;

/**
 * This listener is added to a {@link IHUContext} before a split or merge or sth similar is executed.
 * Its job is to invoke the other listeners' {@link IHUTrxListener#onSplitTransaction(IHUContext, IHUTransactionCandidate, IHUTransactionCandidate)} method if its own {@link #onUnloadLoadTransaction(IHUContext, IHUTransactionCandidate, IHUTransactionCandidate)} method is invoked.
 *
 *
 *
 */
/* package */final class HUSplitBuilderTrxListener implements IHUTrxListener
{
	public static final transient HUSplitBuilderTrxListener instance = new HUSplitBuilderTrxListener();

	private HUSplitBuilderTrxListener()
	{
	}

	/**
	 * Invokes {@link IHUTrxListener#onSplitTransaction(IHUContext, IHUTransactionCandidate, IHUTransactionCandidate)} on the trxListeners that are registered with the given {@code huContext}.
	 */
	@Override
	public void onUnloadLoadTransaction(final IHUContext huContext, final IHUTransactionCandidate unloadTrx, final IHUTransactionCandidate loadTrx)
	{
		huContext.getTrxListeners().onSplitTransaction(huContext, unloadTrx, loadTrx);
	}

	/**
	 * Does nothing, because we triggered this event.
	 */
	@Override
	public void onSplitTransaction(final IHUContext huContext, final IHUTransactionCandidate unloadTrx, final IHUTransactionCandidate loadTrx)
	{
		// nothing, because we triggered this event; let the others do their stuff...
	}

}
