package org.adempiere.ad.trx.api;

import java.util.concurrent.Callable;

/** */
@FunctionalInterface
public interface TrxCallable<ResultType> extends Callable<ResultType>
{
	/** Value to be returned by {@link #doCatch(Throwable)} */
	boolean ROLLBACK = true;
	/** Value to be returned by {@link #doCatch(Throwable)} */
	boolean DONT_ROLLBACK = false;
	
	@Override
	ResultType call() throws Exception;

	/**
	 * Method called when {@link #call()} throws an exception. In this method you can handle this exception or throw another exception. If an exception is thrown or method returns true, the
	 * transaction will be rollback.
	 * 
	 * Please note, this method is called before transaction is rolled-back or savepoint is released
	 * 
	 * @param e exception
	 * @return true if transaction should be rollback
	 */
	default boolean doCatch(Throwable e) throws Throwable
	{
		throw e;
	}

	/**
	 * Method called after {@link #call()} runs.
	 * 
	 * Please note, this method is called AFTER transaction is rolled-back or savepoint is released
	 */
	default void doFinally()
	{
		// nothing
	}
}
