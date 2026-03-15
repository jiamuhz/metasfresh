package org.adempiere.ad.trx.api.impl;

/** */


import javax.annotation.concurrent.Immutable;

import org.adempiere.ad.trx.api.ITrxRunConfig;

import de.metas.util.Check;

/**
 * Default immutable implementation for {@link ITrxRunConfig}
 *

 *
 */
@Immutable
final class TrxRunConfig implements ITrxRunConfig
{
	private final TrxPropagation trxPropagation;
	private final OnRunnableSuccess onRunnableSuccess;
	private final OnRunnableFail onRunnableFail;
	private final boolean autocommit;

	public TrxRunConfig(final TrxPropagation trxMode,
			final OnRunnableSuccess onRunnableSuccess,
			final OnRunnableFail onRunnableFail,
			final boolean autoCommit)
	{
		Check.assumeNotNull(trxMode, "Param 'trxMode' is not null");
		Check.assumeNotNull(onRunnableSuccess, "Param 'onRunnableSuccess' is not null");
		Check.assumeNotNull(onRunnableFail, "Param 'onRunnableFail' is not null");

		this.trxPropagation = trxMode;
		this.onRunnableSuccess = onRunnableSuccess;
		this.onRunnableFail = onRunnableFail;
		this.autocommit = autoCommit;
	}

	@Override
	public String toString()
	{
		return "TrxRunConfig [trxPropagation=" + trxPropagation + ", onRunnableSuccess=" + onRunnableSuccess + ", onRunnableFail=" + onRunnableFail + "]";
	}



	@Override
	public TrxPropagation getTrxPropagation()
	{
		return trxPropagation;
	}

	@Override
	public OnRunnableSuccess getOnRunnableSuccess()
	{
		return onRunnableSuccess;
	}

	@Override
	public OnRunnableFail getOnRunnableFail()
	{
		return onRunnableFail;
	}

	@Override
	public boolean isAutoCommit()
	{
		return autocommit;
	}
}
