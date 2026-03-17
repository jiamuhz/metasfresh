package org.compiere.model;

/** */

/**
 * Interface which exposes AD_Client_ID and AD_Org_ID properties.
 *
 * @author tsa
 *
 */
public interface IClientOrgAware
{
	// @formatter:off
	String COLUMNNAME_AD_Client_ID = "AD_Client_ID";
	int getAD_Client_ID();
	I_AD_Client getAD_Client();

	String COLUMNNAME_AD_Org_ID = "AD_Org_ID";
	int getAD_Org_ID();
	void setAD_Org_ID(int AD_Org_ID);
	I_AD_Org getAD_Org();
	// @formatter:on
}
