package org.adempiere.ad.trx.processor.spi;

/** */

/**
 * Implement what you need adapter for {@link ITrxItemChunkProcessor}.
 * 
 *
 *
 * @param <IT> input type
 * @param <RT> result type
 */
public abstract class TrxItemChunkProcessorAdapter<IT, RT> extends TrxItemProcessorAdapter<IT, RT> implements ITrxItemChunkProcessor<IT, RT>
{

	@Override
	public abstract boolean isSameChunk(IT item);

	@Override
	public void newChunk(IT item)
	{
		// nothing
	}

	@Override
	public void completeChunk()
	{
		// nothing
	}

	@Override
	public void cancelChunk()
	{
		// nothing
	}
}
