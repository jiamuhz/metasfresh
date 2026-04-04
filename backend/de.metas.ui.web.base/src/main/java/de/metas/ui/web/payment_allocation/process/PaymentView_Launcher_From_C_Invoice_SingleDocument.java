

package de.metas.ui.web.payment_allocation.process;

import de.metas.banking.payment.paymentallocation.PaymentAllocationRepository;
import de.metas.bpartner.BPartnerId;
import de.metas.invoice.InvoiceId;
import de.metas.invoice.service.IInvoiceDAO;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.ui.web.payment_allocation.PaymentsViewFactory;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.util.Services;
import org.compiere.SpringContextHolder;
import org.compiere.model.I_C_Invoice;

public class PaymentView_Launcher_From_C_Invoice_SingleDocument extends JavaProcess
{
	private final IViewsRepository viewsFactory;
	final IInvoiceDAO invoiceDAO;
	final PaymentAllocationRepository allocationRepository;

	public PaymentView_Launcher_From_C_Invoice_SingleDocument()
	{
		viewsFactory = SpringContextHolder.instance.getBean(IViewsRepository.class);
		allocationRepository = SpringContextHolder.instance.getBean(PaymentAllocationRepository.class);
		invoiceDAO = Services.get(IInvoiceDAO.class);
	}

	@Override
	protected String doIt()
	{
		final I_C_Invoice invoice = invoiceDAO.getByIdInTrx(InvoiceId.ofRepoId(getRecord_ID()));
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
}
