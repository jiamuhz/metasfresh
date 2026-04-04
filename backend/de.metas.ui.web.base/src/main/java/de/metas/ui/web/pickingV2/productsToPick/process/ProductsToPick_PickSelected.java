 

package de.metas.ui.web.pickingV2.productsToPick.process;

import com.google.common.collect.ImmutableList;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRowsService;
import de.metas.ui.web.pickingV2.productsToPick.rows.WebuiPickHUResult;
import org.compiere.SpringContextHolder;

public class ProductsToPick_PickSelected extends ProductsToPickViewBasedProcess
{
	private final ProductsToPickRowsService rowsService = SpringContextHolder.instance.getBean(ProductsToPickRowsService.class);

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (!isPickerProfile())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("only picker shall pick");
		}

		if (rowsService.noRowsEligibleForPicking(getSelectedRows()))
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("select only rows that can be picked");
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	@RunOutOfTrx
	protected String doIt()
	{
		final ImmutableList<WebuiPickHUResult> result = rowsService.pick(getSelectedRows());

		updateViewRowFromPickingCandidate(result);

		invalidateView();

		return MSG_OK;
	}
}
