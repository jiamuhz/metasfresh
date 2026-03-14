package org.adempiere.ad.trx.processor.api;

import org.adempiere.ad.trx.processor.spi.ITrxItemProcessor;
import org.adempiere.exceptions.AdempiereException;

/** */

/**
 * Exception thrown when there is a configuration issue while creating the {@link ITrxItemProcessor} or {@link ITrxItemProcessorExecutor}.
 *
 * @author metas-dev <dev@metasfresh.com>
 *
 */
public class TrxItemProcessorConfigException extends AdempiereException
{
	private static final long serialVersionUID = 415994901244958144L;

	public TrxItemProcessorConfigException(final String message)
	{
		super(message);
	}
}
