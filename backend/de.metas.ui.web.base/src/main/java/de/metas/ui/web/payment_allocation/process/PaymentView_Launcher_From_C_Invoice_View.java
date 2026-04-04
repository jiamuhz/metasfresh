 

package de.metas.ui.web.payment_allocation.process;

import de.metas.bpartner.BPartnerId;
import de.metas.invoice.InvoiceId;
import de.metas.invoice.service.IInvoiceDAO;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.payment_allocation.PaymentsViewFactory;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Services;
import org.compiere.SpringContextHolder;
import org.compiere.model.I_C_Invoice;

import java.util.Optional;

public class PaymentView_Launcher_From_C_Invoice_View extends ViewBasedProcessTemplate implements IProcessPrecondition
{
	private final IViewsRepository viewsFactory = SpringContextHolder.instance.getBean(IViewsRepository.class);

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (!getSingleSelectedInvoiceId().isPresent())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("not a single selected Invoice");
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt()
	{
		final I_C_Invoice invoice = Services.get(IInvoiceDAO.class).getByIdInTrx(getSingleSelectedInvoiceId().get());
		final BPartnerId bPartnerId = BPartnerId.ofRepoId(invoice.getC_BPartner_ID());

		final ViewId viewId = viewsFactory.createView(CreateViewRequest.builder(PaymentsViewFactory.WINDOW_ID)
				.setParameter(PaymentsViewFactory.PARAMETER_TYPE_BPARTNER_ID, bPartnerId)
				.build())
				.getViewId();

		getResult().setWebuiViewToOpen(WebuiViewToOpen.builder()
				.viewId(viewId.getViewId())
				.target(ViewOpenTarget.ModalOverlay)
				.build());

		return MSG_OK;
	}

	private Optional<InvoiceId> getSingleSelectedInvoiceId()
	{
		final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
		return selectedRowIds.isSingleDocumentId()
				? Optional.of(selectedRowIds.getSingleDocumentId().toId(InvoiceId::ofRepoId))
				: Optional.empty();
	}
}
