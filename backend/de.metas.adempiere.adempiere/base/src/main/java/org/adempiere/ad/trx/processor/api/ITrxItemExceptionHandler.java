package org.adempiere.ad.trx.processor.api;

/** */

/**
 * {@link ITrxItemProcessorExecutor}'s exception handler.
 *
 * In case any of these methods are throwing an exception then entire batch processing will stop and exception will pass-through.
 *
 * @author tsa
 *
 */
public interface ITrxItemExceptionHandler
{
	/**
	 * Called when starting a new chunk fails
	 *
	 * @param e exception
	 * @param item item which was used to start the new chunk
	 *
	 */
	void onNewChunkError(final Throwable e, Object item);

	/**
	 * Called when an item processing fails
	 *
	 * @param e exception
	 * @param item item that failed on processing
	 */
	void onItemError(final Throwable e, Object item);

	/**
	 * Called when completing a chunk fails.
	 *
	 * This method is called before transaction is commited.
	 *
	 * @param e exception
	 */
	void onCompleteChunkError(Throwable e);

	/**
	 * Called after complete chunk failed.
	 * 
	 * This method is called after current transaction was closed (commited or rolled back).
	 * So at the moment when this method is called we are running out of transaction.
	 * Also, if {@link #onCompleteChunkError(Throwable)} throws exception, this method won't be called.
	 *
	 * @param e exception
	 */
	void afterCompleteChunkError(Throwable e);

	/**
	 * Called after completing a chunk, if commiting the transaction fails.
	 *
	 * @param e exception
	 */
	void onCommitChunkError(Throwable e);

	/**
	 * Called after trying to cancel the current chunk.
	 *
	 * @param e exception
	 */
	void onCancelChunkError(Throwable e);

}
