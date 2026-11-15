package de.metas.inoutcandidate.callout;

import de.metas.inoutcandidate.api.IReceiptScheduleBL;
import de.metas.inoutcandidate.model.I_M_ReceiptSchedule;
import de.metas.util.Services;
import org.adempiere.ad.callout.api.ICalloutField;
import org.compiere.model.CalloutEngine;

/**
 * Callouts for the receipt schedule window.
 * <p>
 * They do the same as {@link de.metas.inoutcandidate.modelvalidator.M_ReceiptSchedule} does on save,
 * but perform it while the user is still editing, so the values show up right away.
 */
public class CalloutReceiptSchedule extends CalloutEngine
{
	/**
	 * C_BPartner_ID changed => set the bpartner's default contact and render the address.
	 */
	public String bpartner(final ICalloutField calloutField)
	{
		if (isCalloutActive())
		{
			return NO_ERROR;
		}

		final I_M_ReceiptSchedule receiptSchedule = calloutField.getModel(I_M_ReceiptSchedule.class);
		final IReceiptScheduleBL receiptScheduleBL = Services.get(IReceiptScheduleBL.class);

		receiptScheduleBL.setDefaultBPartnerContact(receiptSchedule);
		receiptScheduleBL.updateBPartnerAddress(receiptSchedule);

		return NO_ERROR;
	}

	/**
	 * C_BPartner_Location_ID changed => re-render the address.
	 */
	public String bpartnerLocation(final ICalloutField calloutField)
	{
		if (isCalloutActive())
		{
			return NO_ERROR;
		}

		final I_M_ReceiptSchedule receiptSchedule = calloutField.getModel(I_M_ReceiptSchedule.class);
		Services.get(IReceiptScheduleBL.class).updateBPartnerAddress(receiptSchedule);

		return NO_ERROR;
	}

	/**
	 * AD_User_ID changed => drop it if it does not belong to the bpartner (but do not bring back the default contact)
	 * and re-render the address.
	 */
	public String adUser(final ICalloutField calloutField)
	{
		if (isCalloutActive())
		{
			return NO_ERROR;
		}

		final I_M_ReceiptSchedule receiptSchedule = calloutField.getModel(I_M_ReceiptSchedule.class);
		final IReceiptScheduleBL receiptScheduleBL = Services.get(IReceiptScheduleBL.class);

		receiptScheduleBL.resetInvalidBPartnerContact(receiptSchedule);
		receiptScheduleBL.updateBPartnerAddress(receiptSchedule);

		return NO_ERROR;
	}
}
