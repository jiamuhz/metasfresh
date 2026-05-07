package de.metas.handlingunits.ordercandidate.spi.impl;

import de.metas.handlingunits.model.I_C_OrderLine;
import de.metas.ordercandidate.api.IOLCandEffectiveValuesBL;
import de.metas.ordercandidate.api.OLCand;
import de.metas.ordercandidate.spi.IOLCandListener;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.model.InterfaceWrapperHelper;
import org.springframework.stereotype.Component;

/**
 * See {@link #onOrderLineCreated(OLCand, org.compiere.model.I_C_OrderLine)}.
 */
@Component
public class OLCandPIIPListener implements IOLCandListener
{
	private final IOLCandEffectiveValuesBL olCandEffectiveValuesBL = Services.get(IOLCandEffectiveValuesBL.class);
	
	/**
	 * Sets the new order line's <code>M_HU_PI_Item_Product_ID</code> and IsManualQtyItemCapacity from the olCand's effective <code>M_HU_PI_Item_Product_ID</code>, so that the system won't have to guess.
	 */
	@Override
	public void onOrderLineCreated(@NonNull final OLCand olCand, @NonNull final org.compiere.model.I_C_OrderLine newOrderLine)
	{
		final I_C_OrderLine newOrderLineExt = InterfaceWrapperHelper.create(newOrderLine, I_C_OrderLine.class);
		newOrderLineExt.setM_HU_PI_Item_Product_ID(olCand.getHUPIProductItemId());

		newOrderLineExt.setIsManualQtyItemCapacity(olCand.unbox().isManualQtyItemCapacity());
		
		// note that I_C_OrderLine.setQtyItemCapacity was already called in the OLCandOrderFactory
	}
}
