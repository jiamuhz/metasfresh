package de.metas.adempiere.model;

/** */


import org.compiere.model.I_C_BPartner_Location;

public interface I_OrderOrInOut
{
	int getC_BPartner_ID();

	org.compiere.model.I_C_BPartner getC_BPartner();

	int getC_BPartner_Location_ID();

	I_C_BPartner_Location getC_BPartner_Location();

	String getDeliveryViaRule();

	int getM_Shipper_ID();

	String getDocumentNo();

	int getAD_Org_ID();



	String getFreightCostRule();

}
