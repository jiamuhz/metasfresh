package de.metas.ui.web.picking.pickingslot.process;

import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.picking.husToPick.HUsToPickViewFactory;
import de.metas.ui.web.picking.packageable.filters.ProductBarcodeFilterData;
import de.metas.ui.web.picking.pickingslot.PickingSlotRowId;
import de.metas.ui.web.picking.pickingslot.PickingSlotView;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import org.compiere.SpringContextHolder;



/**
 * This process opens the HUsToPick view.
 *
 *
 */
public class WEBUI_Picking_HUEditor_Launcher extends PickingSlotViewBasedProcess
{
	private final IViewsRepository viewsRepo = SpringContextHolder.instance.getBean(IViewsRepository.class);
	private final HUsToPickViewFactory husToPickViewFactory = SpringContextHolder.instance.getBean(HUsToPickViewFactory.class);

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (!getSelectedRowIds().isSingleDocumentId())
		{
			return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
		}
		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt()
	{
		final IView husToPickView = createHUsToPickView();

		getResult().setWebuiViewToOpen(WebuiViewToOpen.builder()
											   .viewId(husToPickView.getViewId().getViewId())
											   .profileId("husToPick")
											   .target(ViewOpenTarget.IncludedView)
											   .build());

		return MSG_OK;
	}

	private IView createHUsToPickView()
	{
		final PickingSlotView pickingSlotsView = getPickingSlotView();
		final PickingSlotRowId pickingSlotRowId = getSingleSelectedPickingSlotRow().getPickingSlotRowId();

		return viewsRepo.createView(husToPickViewFactory.createViewRequest(
				pickingSlotsView.getViewId(),
				pickingSlotRowId,
				pickingSlotsView.getCurrentShipmentScheduleId(),
				getBarcodeFilterData().orElse(null)));
	}
}
