package org.adempiere.ad.modelvalidator;

/** */

import org.compiere.model.I_AD_Client;
import org.compiere.model.ModelValidator;
import org.compiere.util.Ini;

/**
 * Model Interceptor interface.
 *
 * This is the replacement of {@link ModelValidator} which will become deprecated.
 *
 * @author tsa
 *
 */
public interface IModelInterceptor
{
	public void initialize(IModelValidationEngine engine, I_AD_Client client);

	/**
	 * Get Client to be monitored.
	 *
	 * If the interceptor was not already initialized or it was registered for all clients this method will return <code>-1</code>.
	 *
	 * @return AD_Client_ID or <code>-1</code>
	 */
	public int getAD_Client_ID();

	/**
	 * Called when user logs in.
	 *
	 * NOTE:
	 * <ul>
	 * <li>called before preferences are set
	 * <li>called only if we run in Client mode (see {@link Ini#isSwingClient()})
	 * </ul>
	 */
	default void onUserLogin(int AD_Org_ID, int AD_Role_ID, int AD_User_ID)
	{
		// does nothing by default
	}

	/**
	 * Model Change of a monitored Table. Called after PO.beforeSave/PO.beforeDelete when you called addModelChange for the table
	 *
	 * @param model persistent object
	 *
	 * @exception Exception if the recipient wishes the change to be not accept.
	 */
	default void onModelChange(Object model, ModelChangeType changeType) throws Exception
	{
		// does nothing by default
	}

	/**
	 * Validate Document. Called as first step of DocAction.prepareIt or at the end of DocAction.completeIt when you called addDocValidate for the table. Note that totals, etc. may not be correct
	 * before the prepare stage.
	 *
	 * @param model persistent object
	 *
	 */
	default void onDocValidate(Object model, DocTimingType timing) throws Exception
	{
		// does nothing by default
	}

}
