package org.adempiere.ad.trx.processor.api;

/** */

import java.util.Properties;

import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.processor.spi.ITrxItemProcessor;
import org.adempiere.util.api.IParams;

import de.metas.util.ISingletonService;

/**
 * Item processor executor service
 *
 * @author tsa
 *
 */
public interface ITrxItemProcessorExecutorService extends ISingletonService
{
	/**
	 * Creates context with <code>null</code> params.<br>
	 * Note: instead of using this method, you can also call {@link #createExecutor()} to get a builder and then call {@link ITrxItemExecutorBuilder#setContext(Properties, String)}.
	 */
	ITrxItemProcessorContext createProcessorContext(Properties ctx, ITrx trx);

	/**
	 * Creates context
	 */
	ITrxItemProcessorContext createProcessorContext(Properties ctx, ITrx trx, IParams params);

	/**
	 * Creates executor for given <code>processor</code>, using the defaults declared in the constants of {@link ITrxItemProcessorExecutor}.
	 */
	<IT, RT> ITrxItemProcessorExecutor<IT, RT> createExecutor(ITrxItemProcessorContext processorCtx, ITrxItemProcessor<IT, RT> processor);

	/**
	 * Creates an executor builder which will help you to configure and execute a given processor.
	 *
	 * @return builder
	 */
	<IT, RT> ITrxItemExecutorBuilder<IT, RT> createExecutor();
}
