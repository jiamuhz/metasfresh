 

package de.metas.ui.web.pporder.process;

import de.metas.handlingunits.pporder.api.IHUPPOrderBL;
import de.metas.handlingunits.pporder.api.PPOrderIssueServiceProductRequest;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.ui.web.pporder.PPOrderLineRow;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.util.Services;

public class WEBUI_PP_Order_IssueServiceProduct
		extends WEBUI_PP_Order_Template
		implements IProcessPrecondition

{
	private final IHUPPOrderBL ppOrderBL = Services.get(IHUPPOrderBL.class);
	private final IProductBL productBL = Services.get(IProductBL.class);


	@Param(parameterName = "IsOverrideExistingValues")
	private boolean overrideExistingValues;

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (!getSelectedRowIds().isSingleDocumentId())
		{
			return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
		}

		final PPOrderLineRow ppOrderLineRow = getSingleSelectedRow();
		if (!ppOrderLineRow.isIssue())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("not an issue line");
		}

		final ProductId productId = ppOrderLineRow.getProductId();
		if (productId == null || productBL.isStocked(productId))
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("not a service product");
		}

		//
		// OK
		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt()
	{
		final PPOrderLinesView ppOrder = getView();
		final PPOrderLineRow issueRow = getSingleSelectedRow();

		ppOrderBL.issueServiceProduct(PPOrderIssueServiceProductRequest.builder()
				.ppOrderId(ppOrder.getPpOrderId())
				.ppOrderBOMLineId(issueRow.getOrderBOMLineId())
				.overrideExistingValues(overrideExistingValues)
				.build());

		return MSG_OK;
	}

	@Override
	protected void postProcess(final boolean success)
	{
		invalidateView();
	}
}
