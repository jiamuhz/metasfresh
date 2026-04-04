package de.metas.ui.web.picking.husToPick.process;

import org.adempiere.exceptions.AdempiereException;

import de.metas.handlingunits.HuId;
import de.metas.process.IProcessPrecondition;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_HUEditor_Launcher;

 

/**
 * Assigns an existing HU to a picking slot.
 * 
 * This process is called from the HU selection dialog that is opened by {@link WEBUI_Picking_HUEditor_Launcher}.
 * 
 *
 *
 */
public class WEBUI_Picking_HUEditor_PickHU
		extends HUsToPickViewBasedProcess
		implements IProcessPrecondition
{
	@Override
	protected String doIt() throws Exception
	{
		retrieveEligibleHUEditorRows().forEach(this::pickHuRow);

		invalidateAndGoBackToPickingSlotsView();
		return MSG_OK;
	}

	void pickHuRow(final HUEditorRow huRow)
	{
		final HuId huId = huRow.getHuId();
		if (!huRow.isTopLevel())
		{
			// TODO: extract as top level
			throw new AdempiereException("Not a top level HU");
		}
		
		addHUIdToCurrentPickingSlot(huId);
	}
}
