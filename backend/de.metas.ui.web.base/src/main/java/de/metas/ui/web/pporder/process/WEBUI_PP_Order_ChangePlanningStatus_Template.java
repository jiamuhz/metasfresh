package de.metas.ui.web.pporder.process;

import de.metas.handlingunits.model.I_PP_Order;
import de.metas.handlingunits.pporder.api.IHUPPOrderBL;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.util.Services;
import lombok.NonNull;
import org.eevolution.api.PPOrderId;
import de.metas.material.event.pporder.PPOrderPlanningStatus;



class WEBUI_PP_Order_ChangePlanningStatus_Template extends WEBUI_PP_Order_Template implements IProcessPrecondition
{
	private final transient IHUPPOrderBL huPPOrderBL = Services.get(IHUPPOrderBL.class);

	private final PPOrderPlanningStatus targetPlanningStatus;

	WEBUI_PP_Order_ChangePlanningStatus_Template(@NonNull final PPOrderPlanningStatus targetPlanningStatus)
	{
		this.targetPlanningStatus = targetPlanningStatus;
	}

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		final PPOrderLinesView view = getView();
		final PPOrderPlanningStatus planningStatus = view.getPlanningStatus();
		if (!huPPOrderBL.canChangePlanningStatus(planningStatus, targetPlanningStatus))
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("not applicable for current status");
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt()
	{
		huPPOrderBL.processPlanning(targetPlanningStatus, getView().getPpOrderId());
		return MSG_OK;
	}

	@Override
	protected void postProcess(final boolean success)
	{
		final PPOrderLinesView ppOrderLinesView = getView();
		ppOrderLinesView.invalidateAll();

		final PPOrderId ppOrderId = ppOrderLinesView.getPpOrderId();
		getViewsRepo().notifyRecordsChangedAsync(I_PP_Order.Table_Name, ppOrderId.getRepoId());
	}
}
