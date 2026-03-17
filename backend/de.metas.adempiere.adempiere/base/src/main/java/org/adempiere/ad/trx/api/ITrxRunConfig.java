package org.adempiere.ad.trx.api;

/** */

import org.compiere.util.TrxRunnable;
import org.compiere.util.TrxRunnable2;

/**
 * This config is used by {@link ITrxManager#run(String, ITrxRunConfig, org.compiere.util.TrxRunnable)} to decide the particular behavior.<br>
 * Use {@link ITrxManager#newTrxRunConfigBuilder()} to obtain an instance.
 */
public interface ITrxRunConfig
{
	public enum TrxPropagation
	{
		/**
		 * The run method creates its own local transaction which is closed at the end.<br>
		 * This implies that the transaction is also committed, no matter what {@link OnRunnableSuccess} value was selected.
		 *
		 * If a not-NULL <code>trxName</code> is given to the run method, then that trxName is used as prefix for the local transaction's name.
		 */
		REQUIRES_NEW,

		/**
		 * The run method doesn't create a transaction name of it's own, but uses the given <code>trxName</code>.
		 */
		NESTED,

		// TODO: implement REQUIRED; see http://static.springsource.org/spring/docs/3.0.0.M3/reference/html/ch11s05.html#tx-propagation
	}

	public enum OnRunnableSuccess
	{
		/**
		 * If the {@link TrxRunnable}'s run method succeeds, then the transaction is committed.
		 */
		COMMIT,

		/**
		 * Don't commit on success.
		 * <p>
		 * <b>IMORTANT:</b> even with {@link #DONT_COMMIT} the transaction will be committed if {@link TrxPropagation#REQUIRES_NEW} was selected.
		 */
		DONT_COMMIT
	}

	/**
	 * Decides what to do if the {@link TrxRunnable}'s run() method throws an Exception.
	 *
	 */
	public enum OnRunnableFail
	{
		/**
		 * The transaction is rolled back to the start it had before the run() method.
		 */
		ROLLBACK,

		/**
		 * The transaction is rolled back only if the given runnable is not an instance of <code>TrxRunnable2</code> or if {@link TrxRunnable2#doCatch(Throwable)} returns <code>true</code>.
		 */
		ASK_RUNNABLE,

		/**
		 * Don't rollback even if the runnable failed.
		 *
		 * If this option it's used the transaction won't be rolled back but also NO SAVEPOINT will be created (which can improve performances a lot).
		 */
		DONT_ROLLBACK
	}

	/**
	 * Decide if the connection should perform an auto-commit after each statement.
	 * Makes e.g. sense with long-running transactions that only do selects (yes, also a select acquires a lock).
	 * The default is <code>false</code>.
	 */
	boolean isAutoCommit();

	TrxPropagation getTrxPropagation();

	OnRunnableSuccess getOnRunnableSuccess();

	OnRunnableFail getOnRunnableFail();

}
