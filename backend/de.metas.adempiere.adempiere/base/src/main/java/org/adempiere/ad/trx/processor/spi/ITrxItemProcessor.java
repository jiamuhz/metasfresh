package org.adempiere.ad.trx.processor.spi;

/** */


import org.adempiere.ad.trx.processor.api.ITrxItemProcessorContext;

/**
 * Simple item processor.
 *
 * Implementations of this interface are responsible with processing given item (see {@link #process(Object)}).
 * <p>
 * Hint: you might want to subclass {@link TrxItemProcessorAdapter} instead of directly implementing this interface.
 *
 * @author tsa
 *
 * @param <IT> input type
 * @param <RT> result type
 */
public interface ITrxItemProcessor<IT, RT>
{
	/**
	 * Called by API to set the initial running context or when running context changes.
	 *
	 * @param processorCtx
	 */
	void setTrxItemProcessorCtx(ITrxItemProcessorContext processorCtx);

	/**
	 * Process given item
	 *
	 * @param item
	 * @throws Exception on any error
	 */
	void process(IT item) throws Exception;

	/**
	 *
	 * @return current processing aggregated result
	 */
	RT getResult();
}
