 

package de.metas.servicerepair.customerreturns.process;

import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.HuId;
import de.metas.inout.InOutId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.servicerepair.customerreturns.HUsToReturnViewContext;
import de.metas.servicerepair.customerreturns.RepairCustomerReturnsService;
import de.metas.ui.web.handlingunits.HUEditorRow;
import org.compiere.SpringContextHolder;

public class HUsToReturn_SelectHU extends HUsToReturnViewBasedProcess implements IProcessPrecondition
{
	private final RepairCustomerReturnsService repairCustomerReturnsService = SpringContextHolder.instance.getBean(RepairCustomerReturnsService.class);

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		final HUEditorRow row = getSingleSelectedRowOrNull();
		if (row == null)
		{
			return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection().toInternal();
		}

		if (!row.isTopLevel())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("not a top level HU");
		}
		final HUsToReturnViewContext viewContext = getHUsToReturnViewContext();

		final HuId huId = row.getHuId();
		final InOutId customerReturnsId = viewContext.getCustomerReturnsId();
		if (!isValidHuForInOut(customerReturnsId, huId))
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("already added");
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt()
	{
		final HUEditorRow row = getSingleSelectedRow();
		final HUsToReturnViewContext viewContext = getHUsToReturnViewContext();

		final HuId huId = row.getHuId();
		final InOutId customerReturnsId = viewContext.getCustomerReturnsId();
		if (isValidHuForInOut(customerReturnsId, huId))
		{
			repairCustomerReturnsService.prepareCloneHUAndCreateCustomerReturnLine()
					.customerReturnId(customerReturnsId)
					.productId(row.getProductId())
					.qtyReturned(row.getQtyCUAsQuantity())
					.cloneFromHuId(huId)
					.build();
		}

		getResult().setCloseWebuiModalView(true);

		getView().removeHUIdsAndInvalidate(ImmutableList.of(huId));

		return MSG_OK;
	}

	private boolean isValidHuForInOut(final InOutId inOutId, final HuId huId)
	{
		return repairCustomerReturnsService.isValidHuForInOut(inOutId, huId);
	}

}
