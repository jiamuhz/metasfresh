 

package de.metas.ui.web.payment_allocation.process;

import de.metas.bpartner.BPartnerId;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.ui.web.payment_allocation.PaymentsViewFactory;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import org.compiere.SpringContextHolder;

public class PaymentView_Launcher_From_BPartnerSingleDocument extends JavaProcess
{
	private final IViewsRepository viewsFactory;

	public PaymentView_Launcher_From_BPartnerSingleDocument()
	{
		viewsFactory = SpringContextHolder.instance.getBean(IViewsRepository.class);
	}

	@Override
	protected String doIt()
	{
		final BPartnerId bPartnerId = BPartnerId.ofRepoId(getRecord_ID());

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
