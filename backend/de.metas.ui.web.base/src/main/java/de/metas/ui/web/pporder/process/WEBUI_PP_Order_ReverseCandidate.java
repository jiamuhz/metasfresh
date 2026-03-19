package de.metas.ui.web.pporder.process;

import de.metas.handlingunits.model.I_PP_Order_Qty;
import de.metas.handlingunits.pporder.api.IHUPPOrderQtyBL;
import de.metas.handlingunits.pporder.api.IHUPPOrderQtyDAO;
import de.metas.handlingunits.pporder.api.PPOrderQtyId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pporder.PPOrderLineRow;
import de.metas.util.Services;
import de.metas.util.StringUtils;
import org.eevolution.api.BOMComponentIssueMethod;

 

/**
 * Reverse(and deletes) a draft manufacturing order issue/receipt candidate.
 * 
 *
 * Task https://github.com/metasfresh/metasfresh-webui-api/issues/356
 */
public class WEBUI_PP_Order_ReverseCandidate
		extends WEBUI_PP_Order_Template
		implements IProcessPrecondition
{
	// services
	private final transient IHUPPOrderQtyBL huPPOrderQtyBL = Services.get(IHUPPOrderQtyBL.class);
	private final transient IHUPPOrderQtyDAO huPPOrderQtyDAO = Services.get(IHUPPOrderQtyDAO.class);

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (!getSelectedRowIds().isSingleDocumentId())
		{
			final String internalReason = StringUtils.formatMessage("Select one line");
			return ProcessPreconditionsResolution.rejectWithInternalReason(internalReason);
		}

		final PPOrderLineRow row = getSingleSelectedRow();
		if (row.isSourceHU())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("Not available for source HU line");
		}

		if (row.getPpOrderQtyId() == null)
		{
			final String internalReason = StringUtils.formatMessage("Not an issue/receipt line");
			return ProcessPreconditionsResolution.rejectWithInternalReason(internalReason);

		}

		if (row.isProcessed() && !(BOMComponentIssueMethod.IssueOnlyForReceived.equals(row.getIssueMethod())))
		{
			final String internalReason = StringUtils.formatMessage("Only not processed");
			return ProcessPreconditionsResolution.rejectWithInternalReason(internalReason);
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt() throws Exception
	{
		final PPOrderQtyId ppOrderQtyId = getSingleSelectedRow().getPpOrderQtyId();
		final I_PP_Order_Qty candidate = huPPOrderQtyDAO.retrieveById(ppOrderQtyId);

		huPPOrderQtyBL.reverseDraftCandidate(candidate);

		return MSG_OK;
	}

	@Override
	protected void postProcess(final boolean success)
	{
		getView().invalidateAll();
	}
}
