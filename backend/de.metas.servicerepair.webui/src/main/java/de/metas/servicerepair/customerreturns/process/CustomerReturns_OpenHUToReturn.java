 

package de.metas.servicerepair.customerreturns.process;

import de.metas.inout.InOutId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessExecutionResult;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.servicerepair.customerreturns.HUsToReturnViewFactory;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import lombok.NonNull;
import org.compiere.SpringContextHolder;

public class CustomerReturns_OpenHUToReturn extends CustomerReturnsBasedProcess implements IProcessPrecondition
{
	private final transient IViewsRepository viewsRepo = SpringContextHolder.instance.getBean(IViewsRepository.class);

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable(final @NonNull IProcessPreconditionsContext context)
	{
		return checkSingleDraftedServiceRepairReturns(context);
	}

	@Override
	protected String doIt()
	{
		final ProcessExecutionResult.WebuiViewToOpen viewToOpen = openServiceHUEditorView();
		getResult().setWebuiViewToOpen(viewToOpen);
		return MSG_OK;
	}

	private ProcessExecutionResult.WebuiViewToOpen openServiceHUEditorView()
	{
		final InOutId customerReturnsId = getCustomerReturnId();
		final IView view = viewsRepo.createView(HUsToReturnViewFactory.createViewRequest(customerReturnsId));

		return ProcessExecutionResult.WebuiViewToOpen.builder()
				.viewId(view.getViewId().getViewId())
				.target(ProcessExecutionResult.ViewOpenTarget.ModalOverlay)
				.build();
	}
}
