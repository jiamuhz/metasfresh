package org.adempiere.ad.trx.processor.api.impl;

/** */

import org.adempiere.ad.trx.processor.api.ITrxItemProcessorContext;
import org.adempiere.ad.trx.processor.spi.ITrxItemChunkProcessor;
import org.adempiere.ad.trx.processor.spi.ITrxItemProcessor;

import lombok.NonNull;
import lombok.ToString;

/**
 * Wraps an {@link ITrxItemProcessor} to {@link ITrxItemChunkProcessor}.
 *
 * @author tsa
 *
 * @param <IT> item type
 * @param <RT> result type
 */
@ToString
/* package */class TrxItemProcessor2TrxItemChunkProcessorWrapper<IT, RT> implements ITrxItemChunkProcessor<IT, RT>
{
	public static final <IT, RT> ITrxItemChunkProcessor<IT, RT> wrapIfNeeded(@NonNull final ITrxItemProcessor<IT, RT> processor)
	{
		if (processor instanceof ITrxItemChunkProcessor)
		{
			final ITrxItemChunkProcessor<IT, RT> chunkProcessor = (ITrxItemChunkProcessor<IT, RT>)processor;
			return chunkProcessor;
		}
		else
		{
			return new TrxItemProcessor2TrxItemChunkProcessorWrapper<>(processor);
		}
	}

	private final ITrxItemProcessor<IT, RT> processor;

	private TrxItemProcessor2TrxItemChunkProcessorWrapper(@NonNull final ITrxItemProcessor<IT, RT> processor)
	{
		this.processor = processor;
	}

	@Override
	public void setTrxItemProcessorCtx(final ITrxItemProcessorContext processorCtx)
	{
		processor.setTrxItemProcessorCtx(processorCtx);
	}

	@Override
	public void process(final IT item) throws Exception
	{
		processor.process(item);
	}

	@Override
	public RT getResult()
	{
		return processor.getResult();
	}

	/**
	 * @return always return <code>false</code>. Each item is a separated chunk
	 */
	@Override
	public boolean isSameChunk(final IT item)
	{
		return false;
	}

	@Override
	public void newChunk(final IT item)
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
