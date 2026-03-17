package org.adempiere.ad.trx.processor.api.impl;

/** */

import java.util.Properties;

import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.processor.api.ITrxItemProcessorContext;
import org.adempiere.util.api.IParams;

/* package */class TrxItemProcessorContext implements ITrxItemProcessorContext
{
	private final Properties ctx;
	private ITrx trx = null;
	private IParams params = null;

	public TrxItemProcessorContext(final Properties ctx)
	{
		super();
		this.ctx = ctx;
	}

	@Override
	public TrxItemProcessorContext copy()
	{
		final TrxItemProcessorContext contextNew = new TrxItemProcessorContext(ctx);
		contextNew.trx = trx;
		contextNew.params = params;

		return contextNew;
	}

	@Override
	public Properties getCtx()
	{
		return ctx;
	}

	@Override
	public String getTrxName()
	{
		if (trx == null)
		{
			return ITrx.TRXNAME_None;
		}
		return trx.getTrxName();
	}

	@Override
	public void setTrx(final ITrx trx)
	{
		this.trx = trx;
	}

	@Override
	public ITrx getTrx()
	{
		return trx;
	}

	@Override
	public IParams getParams()
	{
		return params;
	}

	public void setParams(IParams params)
	{
		this.params = params;
	}

	@Override
	public String toString()
	{
		return "TrxItemProcessorContext [trx=" + trx + ", params=" + params + "]";
	}

	/**
	 * Returning <code>false</code> to ensure that the trx which was set via {@link #setTrx(ITrx)} is actually used, even if it's <code>null</code>.
	 */
	@Override
	public boolean isAllowThreadInherited()
	{
		return false;
	}
}
