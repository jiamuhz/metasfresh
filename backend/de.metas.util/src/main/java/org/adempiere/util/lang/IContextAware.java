package org.adempiere.util.lang;

/** */

import java.util.Properties;

/**
 * Simulates context objects.
 */
public interface IContextAware
{
	Properties getCtx();

	String getTrxName();

	/**
	 * Advise the caller if it should check for and prefer an existing inherited trx name.
	 *
	 * If {@link #getTrxName()} returned a local/null transaction name (see {@link org.adempiere.ad.trx.api.ITrxManager#isNull(String)})
	 * and if there is a thread-inherited transaction (see {@link org.adempiere.ad.trx.api.ITrxManager#getThreadInheritedTrx(org.adempiere.ad.trx.api.OnTrxMissingPolicy)}),
	 * then this method advises the caller whether is OK to use the thread-inherited transaction instead.
	 * <p>
	 * Background: the default implementation return <code>true</code> for backward compatibility reasons.
	 * In many cases the business logic developer didn't care if the <i>really</i> wanted a null-transaction or not, and in the past,
	 * {@link org.adempiere.model.InterfaceWrapperHelper#newInstance(Class, Object, boolean)} always assumed <code>true</code>, so a lot of business logic might rely on this behavior.
	 *
	 * @see PlainContextAware#withOutOfTrx(Properties).
	 */
	default boolean isAllowThreadInherited()
	{
		return true;
	}
}
