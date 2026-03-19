package de.metas.ui.web.picking.pickingslot.process;

import static de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_NO_PROCESSED_RECORDS;
import static de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_SELECT_PICKED_HU;

import org.compiere.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

import de.metas.handlingunits.HuId;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotViewFactory;



/**
 * Unprocesses the processed picking candidate of the currently selected TU.<br>
 * Unprocessing means that
 * <ul>
 * <li>the HU is changed from status "picked" to "active" (even if it was only "planned" before the candidate was processed!)</li>
 * <li>the HU is deassociated with its shipment schedule (changes QtyPicked and QtyToDeliver)</li>
 * </ul>
 *
 * Note: this process is declared in the {@code AD_Process} table, but <b>not</b> added to it's respective window or table via application dictionary.<br>
 * Instead it is assigned to it's place by {@link PickingSlotViewFactory}.
 *
 *
 *
 */
public class WEBUI_Picking_M_Picking_Candidate_Unprocess extends PickingSlotViewBasedProcess
{
	private final PickingCandidateService pickingCandidateService = SpringContextHolder.instance.getBean(PickingCandidateService.class);

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (!getSelectedRowIds().isSingleDocumentId())
		{
			return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
		}

		final PickingSlotRow pickingSlotRow = getSingleSelectedRow();
		if (!pickingSlotRow.isPickedHURow())
		{
			return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_SELECT_PICKED_HU));
		}

		if (!pickingSlotRow.isProcessed())
		{
			return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_NO_PROCESSED_RECORDS));
		}
		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt()
	{
		final PickingSlotRow rowToProcess = getSingleSelectedRow();
		final HuId huId = rowToProcess.getHuId();
		pickingCandidateService.unprocessAndRestoreSourceHUsByHUId(huId);

		return MSG_OK;
	}

	@Override
	protected void postProcess(final boolean success)
	{
		if (!success)
		{
			return;
		}

		invalidatePickingSlotsView();
		invalidatePackablesView();
	}
}
