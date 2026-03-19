package de.metas.ui.web.handlingunits.process;

import de.metas.handlingunits.inventory.internaluse.HUInternalUseInventoryCreateRequest;
import de.metas.handlingunits.model.I_M_Inventory;
import de.metas.process.Param;
import de.metas.product.acct.api.ActivityId;
import org.compiere.util.Env;

import javax.annotation.Nullable;



/**
 * Create Internal Use Inventory and destroy given HUs.
 *
 *
 * Task initial task https://github.com/metasfresh/metasfresh-webui-api/issues/396
 */
public class WEBUI_M_HU_Mass_Disposal extends WEBUI_M_HU_InternalUse_Template
{
	@Param(parameterName = I_M_Inventory.COLUMNNAME_C_Activity_ID)
	@Nullable
	private ActivityId p_C_Activity_ID;

	@Param(parameterName = I_M_Inventory.COLUMNNAME_Description)
	@Nullable
	private String p_Description;

	@Param(parameterName = "IsComplete")
	private boolean p_IsCompleteInventory;

	@Override
	protected HUInternalUseInventoryCreateRequest createHUInternalUseInventoryCreateRequest()
	{
		return HUInternalUseInventoryCreateRequest.builder()
				.hus(getHUsToInternalUse())
				.movementDate(Env.getZonedDateTime(getCtx()))
				.activityId(p_C_Activity_ID)
				.description(p_Description)
				.completeInventory(p_IsCompleteInventory)
				.moveEmptiesToEmptiesWarehouse(false)
				.sendNotifications(true)
				.build();
	}
}
