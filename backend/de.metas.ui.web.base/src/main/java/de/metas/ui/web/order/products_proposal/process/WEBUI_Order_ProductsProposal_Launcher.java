package de.metas.ui.web.order.products_proposal.process;

import org.adempiere.util.lang.impl.TableRecordReference;
import org.springframework.beans.factory.annotation.Autowired;

import de.metas.adempiere.model.I_C_Order;
import de.metas.document.engine.DocStatus;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.order.products_proposal.view.OrderProductsProposalViewFactory;
import de.metas.ui.web.view.CreateViewRequest;


public class WEBUI_Order_ProductsProposal_Launcher extends WEBUI_ProductsProposal_Launcher_Template implements IProcessPrecondition
{
	@Autowired
	private OrderProductsProposalViewFactory productsProposalViewFactory;

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable(final IProcessPreconditionsContext context)
	{
		if (!context.isSingleSelection())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("one and only one order shall be selected");
		}

		final I_C_Order salesOrder = context.getSelectedModel(I_C_Order.class);
		final DocStatus docStatus = DocStatus.ofCode(salesOrder.getDocStatus());
		if (!docStatus.isDraftedOrInProgress())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("only Drafted or InProgress orders are allowed");
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected CreateViewRequest createViewRequest(final TableRecordReference recordRef)
	{
		return productsProposalViewFactory.createViewRequest(recordRef);
	}
}
