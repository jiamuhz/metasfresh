package de.metas.ui.web.handlingunits.process;

import de.metas.document.DocTypeId;
import de.metas.handlingunits.inventory.internaluse.HUInternalUseInventoryCreateRequest;
import de.metas.handlingunits.model.I_M_Inventory;
import de.metas.process.Param;
import org.compiere.util.Env;

import javax.annotation.Nullable;



/**
 * Create Internal Use Inventory and destroy given HUs.
 *
 *
 * Task initial task https://github.com/metasfresh/metasfresh-webui-api/issues/396
 */
public class WEBUI_M_HU_MoveToGarbage extends WEBUI_M_HU_InternalUse_Template
{
	@Param(parameterName = "C_DocType_ID")
	@Nullable
	private DocTypeId p_internalUseInventoryDocTypeId;

	@Param(parameterName = I_M_Inventory.COLUMNNAME_Description)
	@Nullable
	private String p_description;

	protected HUInternalUseInventoryCreateRequest createHUInternalUseInventoryCreateRequest()
	{
		return HUInternalUseInventoryCreateRequest.builder()
				.hus(getHUsToInternalUse())
				.movementDate(Env.getZonedDateTime(getCtx()))
				.internalUseInventoryDocTypeId(p_internalUseInventoryDocTypeId)
				.description(p_description)
				.completeInventory(true)
				.moveEmptiesToEmptiesWarehouse(true)
				.sendNotifications(true)
				.build();
	}
}
