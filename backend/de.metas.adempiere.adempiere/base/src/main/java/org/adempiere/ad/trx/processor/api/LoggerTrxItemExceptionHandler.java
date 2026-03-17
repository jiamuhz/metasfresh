package org.adempiere.ad.trx.processor.api;

/** */

import org.slf4j.Logger;

import de.metas.logging.LogManager;

/**
 * An {@link ITrxItemProcessorExecutor}'s exception handler which just logs the exception but does nothing.
 * Use {@link #instance} to obtain your instance.
 * <p>
 * May be overridden.
 *
 * @author tsa
 *
 */
public class LoggerTrxItemExceptionHandler implements ITrxItemExceptionHandler
{
	public static final LoggerTrxItemExceptionHandler instance = new LoggerTrxItemExceptionHandler();

	private final transient Logger logger = LogManager.getLogger(getClass());

	protected LoggerTrxItemExceptionHandler()
	{
	}

	@Override
	public void onNewChunkError(final Throwable e, final Object item)
	{
		logger.warn("Error while trying to create a new chunk for item: " + item, e);
	}

	@Override
	public void onItemError(final Throwable e, final Object item)
	{
		logger.warn("Error while trying to process item: " + item, e);
	}

	@Override
	public void onCompleteChunkError(final Throwable e)
	{
		logger.warn("Error while completing current chunk", e);
	}

	@Override
	public void onCommitChunkError(final Throwable e)
	{
		logger.info("Processor failed to commit current chunk => rollback transaction", e);
	}

	@Override
	public void afterCompleteChunkError(final Throwable e)
	{
		// nothing to do.
		// error was already logged by onCompleteChunkError
	}

	@Override
	public void onCancelChunkError(final Throwable e)
	{
		logger.warn("Error while cancelling current chunk. Ignored.", e);
	}
}
