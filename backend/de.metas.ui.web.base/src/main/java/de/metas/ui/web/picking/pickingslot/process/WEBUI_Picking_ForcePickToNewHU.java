 

package de.metas.ui.web.picking.pickingslot.process;

import de.metas.handlingunits.HuId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;

import java.util.Optional;

public class WEBUI_Picking_ForcePickToNewHU extends WEBUI_Picking_PickQtyToNewHU
		implements IProcessPrecondition
{
	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		final Optional<ProcessPreconditionsResolution> preconditionsResolution = checkValidSelection();

		if (preconditionsResolution.isPresent())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason(preconditionsResolution.get().getRejectReason());
		}

		if (!isForceDelivery())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason(" Use 'WEBUI_Picking_PickQtyToNewHU' for non force shipping records!");
		}

		return ProcessPreconditionsResolution.accept();
	}

	protected String doIt()
	{
		final HuId packToHuId = createNewHuId();

		forcePick(getQtyToPack(), packToHuId);

		printPickingLabelIfAutoPrint(packToHuId);

		invalidatePackablesView();
		invalidatePickingSlotsView();
		return MSG_OK;
	}
}
