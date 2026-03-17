package org.adempiere.ad.trx.processor.api;

/** */

import java.util.Iterator;

import org.adempiere.ad.trx.processor.api.ITrxItemExecutorBuilder.OnItemErrorPolicy;
import org.adempiere.ad.trx.processor.spi.ITrxItemProcessor;

/**
 * {@link ITrxItemProcessor} executor. Ise {@link ITrxItemExecutorBuilder} to get yours.
 *
 * Implementations of this class is responsible with {@link ITrxItemProcessor} methods invocations, error handling and transaction management.
 *
 * @author tsa
 *
 * @param <IT> item type
 * @param <RT> result type
 */
public interface ITrxItemProcessorExecutor<IT, RT>
{
	/**
	 * Default exception handler used.
	 */
	ITrxItemExceptionHandler DEFAULT_ExceptionHandler = LoggerTrxItemExceptionHandler.instance;

	/**
	 * Default policy for the case that processing one item fails.
	 */
	OnItemErrorPolicy DEFAULT_OnItemErrorPolicy = OnItemErrorPolicy.CancelChunkAndRollBack;

	/**
	 * default: true - backward compatibility;
	 */
	boolean DEFAULT_UseTrxSavepoints = true;

	/**
	 * Process given items.
	 *
	 * @param items
	 * @return result
	 */
	RT execute(Iterator<? extends IT> items);

	/**
	 * Gets used item processor.
	 *
	 * @return item processor
	 */
	ITrxItemProcessor<IT, RT> getProcessor();

	/**
	 * Instead of setting the exception handler here, you can also use {@link ITrxItemExecutorBuilder#setExceptionHandler(ITrxItemExceptionHandler)}.
	 *
	 * @param trxItemExceptionHandler
	 * @deprecated please use {@link ITrxItemExecutorBuilder#setExceptionHandler(ITrxItemExceptionHandler)} instead.<br>
	 *             See <a href="https://github.com/metasfresh/metasfresh/commit/03496dfbccde6156227b8f9d5ad20c6f5ed8854d">this commit</a> for an example on how to migrate the code.
	 */
	@Deprecated
	ITrxItemProcessorExecutor<IT, RT> setExceptionHandler(ITrxItemExceptionHandler trxItemExceptionHandler);

	/**
	 * See {@link ITrxItemExecutorBuilder#setUseTrxSavepoints(boolean)}.
	 *
	 * @param useTrxSavepoints
	 * @deprecated please use {@link ITrxItemExecutorBuilder#setUseTrxSavepoints(boolean)} instead.<br>
	 *             See <a href="https://github.com/metasfresh/metasfresh/commit/03496dfbccde6156227b8f9d5ad20c6f5ed8854d">this commit</a> for an example on how to migrate the code.
	 */
	@Deprecated
	ITrxItemProcessorExecutor<IT, RT> setUseTrxSavepoints(boolean useTrxSavepoints);

}
