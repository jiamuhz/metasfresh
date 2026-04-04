

package de.metas.servicerepair.customerreturns.process;

import de.metas.document.engine.DocStatus;
import de.metas.inout.IInOutDAO;
import de.metas.inout.InOutId;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.servicerepair.customerreturns.RepairCustomerReturnsService;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.util.Services;
import lombok.NonNull;
import org.compiere.SpringContextHolder;
import org.compiere.model.I_M_InOut;

abstract class CustomerReturnsBasedProcess extends JavaProcess
{
	protected final IInOutDAO inOutDAO = Services.get(IInOutDAO.class);
	protected final IViewsRepository viewsRepo = SpringContextHolder.instance.getBean(IViewsRepository.class);
	protected final RepairCustomerReturnsService repairCustomerReturnsService = SpringContextHolder.instance.getBean(RepairCustomerReturnsService.class);

	protected ProcessPreconditionsResolution checkSingleDraftedServiceRepairReturns(final @NonNull IProcessPreconditionsContext context)
	{
		if (!context.isSingleSelection())
		{
			return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
		}

		final I_M_InOut customerReturns = inOutDAO.getById(InOutId.ofRepoId(context.getSingleSelectedRecordId()));
		final DocStatus docStatus = DocStatus.ofCode(customerReturns.getDocStatus());
		if (!docStatus.isDrafted())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("document status should be Drafted");
		}

		if (!repairCustomerReturnsService.isRepairCustomerReturns(customerReturns))
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("not a Repair Customer Return");
		}

		return ProcessPreconditionsResolution.accept();
	}

	protected InOutId getCustomerReturnId()
	{
		return InOutId.ofRepoId(getRecord_ID());
	}

}
