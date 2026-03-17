package org.adempiere.ad.trx.processor.api;

/** */

import org.adempiere.exceptions.AdempiereException;

/**
 * An {@link ITrxItemProcessorExecutor}'s exception handler which fails on first error.
 *
 *
 *
 */
public class FailTrxItemExceptionHandler implements ITrxItemExceptionHandler
{
	public static final FailTrxItemExceptionHandler instance = new FailTrxItemExceptionHandler();

	protected FailTrxItemExceptionHandler()
	{
		super();
	}

	protected void fail(final Throwable e, final Object item)
	{
		throw AdempiereException.wrapIfNeeded(e);
	}

	@Override
	public void onNewChunkError(final Throwable e, final Object item)
	{
		fail(e, item);
	}

	@Override
	public void onItemError(final Throwable e, final Object item)
	{
		fail(e, item);
	}

	@Override
	public void onCompleteChunkError(final Throwable e)
	{
		final Object item = null;
		fail(e, item);
	}

	@Override
	public void afterCompleteChunkError(final Throwable e)
	{
		// Nothing to do.
		// This method will never be called because "onCompleteChunkError" method already threw exception.
	}

	@Override
	public void onCommitChunkError(final Throwable e)
	{
		final Object item = null;
		fail(e, item);
	}

	@Override
	public void onCancelChunkError(final Throwable e)
	{
		final Object item = null;
		fail(e, item);
	}
}
