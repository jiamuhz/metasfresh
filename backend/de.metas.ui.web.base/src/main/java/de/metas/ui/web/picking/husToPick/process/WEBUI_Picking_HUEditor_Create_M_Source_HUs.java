package de.metas.ui.web.picking.husToPick.process;

import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.process.IProcessPrecondition;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_HUEditor_Launcher;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_PickQtyToComputedHU;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_PickQtyToExistingHU;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_PickQtyToNewHU;

 

/**
 * This process is available from the HU editor window opened by {@link WEBUI_Picking_HUEditor_Launcher}.<br>
 * Its job is to flag the currently selected HUs so they are available as source-HUs for {@link WEBUI_Picking_PickQtyToNewHU} or {@link WEBUI_Picking_PickQtyToExistingHU} or {@link WEBUI_Picking_PickQtyToComputedHU}.
 * 
 * @task https://github.com/metasfresh/metasfresh/issues/2298
 * 
 *
 *
 */
public class WEBUI_Picking_HUEditor_Create_M_Source_HUs
		extends HUsToPickViewBasedProcess
		implements IProcessPrecondition
{

	@Override
	protected String doIt() throws Exception
	{
		final SourceHUsService sourceHuService = SourceHUsService.get();

		retrieveEligibleHUEditorRows().forEach(huEditorRow -> sourceHuService.addSourceHuMarker(huEditorRow.getHuId()));

		invalidateAndGoBackToPickingSlotsView();
		return MSG_OK;
	}
}
