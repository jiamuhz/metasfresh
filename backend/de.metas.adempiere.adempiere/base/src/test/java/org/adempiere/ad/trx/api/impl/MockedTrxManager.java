package org.adempiere.ad.trx.api.impl;

/** */

import java.util.ArrayList;
import java.util.List;

import org.adempiere.ad.trx.api.ITrx;
import org.junit.Ignore;

import javax.annotation.Nullable;

@Ignore
public class MockedTrxManager extends PlainTrxManager
{
	private final List<ITrx> removedTransactions = new ArrayList<>();

	@Override
	protected MockedTrx createTrx(final String trxName, final boolean autoCommit)
	{
		return new MockedTrx(this, trxName, autoCommit);
	}

	@Override
	public boolean remove(final ITrx trx)
	{
		final boolean removed = super.remove(trx);
		if (removed)
		{
			removedTransactions.add(trx);
		}
		return removed;
	}

	public List<ITrx> getRemovedTransactions()
	{
		return removedTransactions;
	}

	@Nullable
	public ITrx getRemovedTransactionByName(final String trxName)
	{
		return removedTransactions
				.stream()
				.filter(trx -> trxName.equals(trx.getTrxName()))
				.findFirst()
				.orElse(null);
	}
}
