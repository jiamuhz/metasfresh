package de.metas.ui.web.handlingunits.process;

import org.compiere.model.X_M_InOut;

import de.metas.ui.web.quickinput.inout.EmptiesQuickInputDescriptorFactory;



public class WEBUI_M_ReceiptSchedule_CreateEmptiesReturnsFromCustomer extends WEBUI_M_ReceiptSchedule_CreateEmptiesReturns_Base
{
	public WEBUI_M_ReceiptSchedule_CreateEmptiesReturnsFromCustomer()
	{
		super(X_M_InOut.MOVEMENTTYPE_CustomerReturns, EmptiesQuickInputDescriptorFactory.CustomerReturns_Window_ID);
	}
}
