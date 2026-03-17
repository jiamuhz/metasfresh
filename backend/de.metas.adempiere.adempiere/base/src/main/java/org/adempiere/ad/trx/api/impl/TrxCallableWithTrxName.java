package org.adempiere.ad.trx.api.impl;

import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.ad.trx.api.TrxCallable;
import org.adempiere.ad.wrapper.IInterfaceWrapperHelper;
import org.compiere.util.TrxRunnable;

/** */

/**
 * Legacy adapter interface: it's used to adapt {@link TrxRunnable#run(String)} calls to {@link TrxCallableWithTrxName#call(String)} calls.
 * 
 * This is needed because we want to provide to wrapped {@link TrxRunnable} exactly the same "localTrxName" which it was getting it before introducing the {@link TrxCallable} support.
 * 
 * The main reason for doing this are:
 * <ul>
 * <li>some of the JUnit tests are currently failing if we provide {@link ITrx#TRXNAME_ThreadInherited} instead of the actual "localTrxName"
 * <li>it might be that some other BLs would fail too.
 * </ul>
 * 
 * Main things to check before getting rid of this and always using {@link ITrx#TRXNAME_ThreadInherited}:
 * <ul>
 * <li>{@link IInterfaceWrapperHelper}'s model getters: atm they are String comparing the trxNames in order to find out if the cached model is still valid and can be returned.
 * <li>check all other places where trxNames are String compared and consider replacing that with {@link ITrxManager#isSameTrxName(ITrx, String)} which is handling the
 * {@link ITrx#TRXNAME_ThreadInherited} case
 * </ul>
 * 
 * 
 *
 *
 * @param <ResultType>
 */
interface TrxCallableWithTrxName<ResultType> extends TrxCallable<ResultType>
{
	ResultType call(String localTrxName) throws Exception;
}
