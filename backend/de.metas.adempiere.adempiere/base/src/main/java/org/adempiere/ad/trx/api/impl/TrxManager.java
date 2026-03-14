package org.adempiere.ad.trx.api.impl;

/** */


import org.adempiere.ad.trx.api.ITrx;
import org.compiere.util.Trx;

public class TrxManager extends AbstractTrxManager
{
	@Override
	protected ITrx createTrx(final String trxName, final boolean autoCommit)
	{
		final Trx trx = new org.compiere.util.Trx(this, trxName, autoCommit);
		return trx;
	}
}
