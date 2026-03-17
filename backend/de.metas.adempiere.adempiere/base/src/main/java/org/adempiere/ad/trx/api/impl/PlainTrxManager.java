package org.adempiere.ad.trx.api.impl;

/** */

import java.util.List;

import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.DBException;
import org.compiere.Adempiere;

import de.metas.util.Check;
import de.metas.util.Services;

/**
 * This implementation is intended for unit and module testing in scenarios where you want the trxManager to get out of the way.
 * <p>
 * Hint: if you want to actually test trx related behavior (e.g. if some trx was committed and so on), then there is {@link MockedTrxManager}.
 *
 *
 *
 */
public class PlainTrxManager extends AbstractTrxManager
{
	/** Convenient method to get the {@link PlainTrxManager} via {@link Services} */
	public static PlainTrxManager get()
	{
		return (PlainTrxManager)Services.get(ITrxManager.class);
	}

	//
	// Flags used to check transaction lifecycle and consistency: COMMIT and ROLLBACK
	// NOTE: atm, the actual JDBC are not failing in this case, but, i think is helpful in tests to be much more strict to enforce consistency
	private boolean failCommitIfTrxNotStarted = true;
	private boolean failRollbackIfTrxNotStarted = true;
	private boolean debugTrxLog;

	public PlainTrxManager()
	{
		Adempiere.assertUnitTestMode();
	}

	@Override
	protected PlainTrx createTrx(String trxName, final boolean autoCommit)
	{
		Adempiere.assertUnitTestMode();

		try
		{
			return new PlainTrx(this, trxName, autoCommit);
		}
		catch (Exception e)
		{
			throw DBException.wrapIfNeeded(e);
		}
	}

	public PlainTrxManager setFailCommitIfTrxNotStarted(final boolean failCommitIfTrxNotStarted)
	{
		this.failCommitIfTrxNotStarted = failCommitIfTrxNotStarted;
		return this;
	}

	public boolean isFailCommitIfTrxNotStarted()
	{
		return failCommitIfTrxNotStarted;
	}

	public PlainTrxManager setFailRollbackIfTrxNotStarted(final boolean failRollbackIfTrxNotStarted)
	{
		this.failRollbackIfTrxNotStarted = failRollbackIfTrxNotStarted;
		return this;
	}

	public boolean isFailRollbackIfTrxNotStarted()
	{
		return failRollbackIfTrxNotStarted;
	}

	public void assertNoActiveTransactions()
	{
		final List<ITrx> activeTrxs = getActiveTransactionsList();
		Check.assume(activeTrxs.isEmpty(), "Expected no active transactions but got: {}", activeTrxs);
	}

	/**
	 * Ask the transactions to log their major events like COMMIT, ROLLBACK.
	 * Those events will be visible on {@link PlainTrx#toString()}.
	 * 
	 * @param debugTrxLog
	 */
	public void setDebugTrxLog(boolean debugTrxLog)
	{
		this.debugTrxLog = debugTrxLog;
	}

	/**
	 * @see #setDebugTrxLog(boolean)
	 */
	public boolean isDebugTrxLog()
	{
		return debugTrxLog;
	}
}
