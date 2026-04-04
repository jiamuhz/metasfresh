 

package de.metas.ui.web.pickingV2.productsToPick.process;

import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.HuPackingInstructionsId;
import de.metas.handlingunits.picking.PackToSpec;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRowsService;
import de.metas.ui.web.pickingV2.productsToPick.rows.WebuiPickHUResult;
import org.compiere.SpringContextHolder;

public class ProductsToPick_SetPackingInstructions extends ProductsToPickViewBasedProcess
{
	private final ProductsToPickRowsService rowsService = SpringContextHolder.instance.getBean(ProductsToPickRowsService.class);

	@Param(parameterName = "M_HU_PI_ID", mandatory = true)
	private HuPackingInstructionsId p_M_HU_PI_ID;

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (!isPickerProfile())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("only picker shall pack");
		}

		if (!rowsService.anyRowsEligibleForPacking(getSelectedRows()))
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("no eligible rows were selected");
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	@RunOutOfTrx
	protected String doIt()
	{
		final ImmutableList<WebuiPickHUResult> result = rowsService.setPackingInstruction(getSelectedRows(), getPackToSpec());

		updateViewRowFromPickingCandidate(result);

		invalidateView();

		return MSG_OK;
	}

	private PackToSpec getPackToSpec() {return PackToSpec.ofGenericPackingInstructionsId(p_M_HU_PI_ID);}

}
