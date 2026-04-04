package de.metas.ui.web.handlingunits.process;

import java.math.BigDecimal;

import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI_Item;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.ui.web.handlingunits.process.WebuiHUTransformCommand.ActionType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

 

/**
 * {@link WebuiHUTransformCommand} parameters.
 * 
 *
 *
 */
@Value
@Builder
public class WebuiHUTransformParameters
{
	@NonNull
	private final ActionType actionType;
	private final I_M_HU_PI_Item_Product huPIItemProduct;
	private final I_M_HU_PI_Item huPIItem;
	private final I_M_HU tuHU;
	private final I_M_HU luHU;
	private final BigDecimal qtyCU;
	private final BigDecimal qtyTU;
	private final boolean huPlanningReceiptOwnerPM_LU;
	private final boolean huPlanningReceiptOwnerPM_TU;
}
