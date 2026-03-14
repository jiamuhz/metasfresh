package org.compiere.model;

/** */

import de.metas.bpartner.BPartnerLocationAndCaptureId;
import de.metas.tax.api.ITaxBL;
import de.metas.tax.api.VatCodeId;
import de.metas.util.Services;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.util.Properties;

public class Tax
{
	public static int get (Properties ctx, int M_Product_ID, int C_Charge_ID,
			Timestamp billDate, Timestamp shipDate,
			int AD_Org_ID, int M_Warehouse_ID,
			BPartnerLocationAndCaptureId billC_BPartner_Location_ID,
			BPartnerLocationAndCaptureId shipC_BPartner_Location_ID,
			boolean IsSOTrx,
			@Nullable VatCodeId vatCodeId)
	{
		return Services.get(ITaxBL.class).get(ctx, M_Product_ID, C_Charge_ID, billDate, shipDate, AD_Org_ID, M_Warehouse_ID, billC_BPartner_Location_ID, shipC_BPartner_Location_ID, IsSOTrx, vatCodeId);
	}

}
