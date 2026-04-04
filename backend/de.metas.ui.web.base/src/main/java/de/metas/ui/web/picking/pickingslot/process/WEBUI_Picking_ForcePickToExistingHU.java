 

package de.metas.ui.web.picking.pickingslot.process;

import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;

import java.util.Optional;

public class WEBUI_Picking_ForcePickToExistingHU extends WEBUI_Picking_PickQtyToExistingHU
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
			return ProcessPreconditionsResolution.rejectWithInternalReason("Use WEBUI_Picking_PickQtyToExistingHU for non force shipping cases!");
		}

		return ProcessPreconditionsResolution.accept();
	}

	protected String doIt() throws Exception
	{
		validatePickingToHU();

		forcePick(getQtyToPack(), getPackToHuId());

		invalidateView();
		invalidateParentView();

		return MSG_OK;
	}
}
