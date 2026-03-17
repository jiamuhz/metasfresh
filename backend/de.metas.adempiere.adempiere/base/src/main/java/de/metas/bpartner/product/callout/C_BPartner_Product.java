package de.metas.bpartner.product.callout;

import org.adempiere.ad.callout.annotations.Callout;
import org.adempiere.ad.callout.annotations.CalloutMethod;
import org.compiere.model.I_C_BPartner_Product;
import org.compiere.model.I_M_Product;

import static org.adempiere.model.InterfaceWrapperHelper.loadOutOfTrx;

/** */

@Callout(I_C_BPartner_Product.class)
public class C_BPartner_Product
{
	@CalloutMethod(columnNames = I_C_BPartner_Product.COLUMNNAME_M_Product_ID)
	public void setOrgOnProductChange(final I_C_BPartner_Product bpartnerProduct)
	{
		if (bpartnerProduct.getM_Product_ID() <= 0)
		{
			// nothing to change
			return;
		}
		final I_M_Product productRecord = loadOutOfTrx(bpartnerProduct.getM_Product_ID(), I_M_Product.class);
		bpartnerProduct.setAD_Org_ID(productRecord.getAD_Org_ID());

		bpartnerProduct.setPicking_AgeTolerance_BeforeMonths(productRecord.getPicking_AgeTolerance_BeforeMonths());
		bpartnerProduct.setPicking_AgeTolerance_AfterMonths(productRecord.getPicking_AgeTolerance_AfterMonths());
	}
}
