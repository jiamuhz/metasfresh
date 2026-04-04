

package de.metas.ui.web.pickingV2.productsToPick.process;

import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.HuPackingInstructionsId;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.handlingunits.picking.PackToSpec;
import de.metas.i18n.AdMessageKey;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRow;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRowsService;
import de.metas.ui.web.pickingV2.productsToPick.rows.WebuiPickHUResult;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.SpringContextHolder;

import java.util.List;

public class ProductsToPick_PickAndPackSelected extends ProductsToPickViewBasedProcess
{
	private final ProductsToPickRowsService rowsService = SpringContextHolder.instance.getBean(ProductsToPickRowsService.class);

	private final AdMessageKey MSG_SET_DEFAULT_PACKING_INSTRUCTION = AdMessageKey.of("de.metas.ui.web.pickingV2.productsToPick.process.ProductsToPick_PickAndPackSelected.SetDefaultPackingInstruction");
	private final IHandlingUnitsDAO handlingUnitsDAO = Services.get(IHandlingUnitsDAO.class);

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (!isPickerProfile())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("only picker shall pick");
		}

		if (rowsService.noRowsEligibleForPicking(getValidRowsForPickAndPack()))
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("select only rows that can be picked");
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	@RunOutOfTrx
	protected String doIt()
	{
		ensureDefaultPackingInstructionExists();
		pick();
		pack();

		invalidateView();

		return MSG_OK;
	}

	private void pick()
	{
		final ImmutableList<WebuiPickHUResult> result = rowsService.pick(getValidRowsForPickAndPack());

		updateViewRowFromPickingCandidate(result);
	}

	private void pack()
	{
		final ImmutableList<WebuiPickHUResult> result = rowsService.setPackingInstruction(getValidRowsForPickAndPack(), getPackToSpec());

		updateViewRowFromPickingCandidate(result);
	}

	private List<ProductsToPickRow> getValidRowsForPickAndPack()
	{
		return getSelectedRows()
				.stream()
				.filter(row -> !row.getQtyEffective().isZero())
				.collect(ImmutableList.toImmutableList());
	}

	@NonNull
	private PackToSpec getPackToSpec()
	{
		final I_M_HU_PI defaultPIForPicking = handlingUnitsDAO.retrievePIDefaultForPicking();
		if (defaultPIForPicking == null)
		{
			throw new AdempiereException(MSG_SET_DEFAULT_PACKING_INSTRUCTION);
		}

		return PackToSpec.ofGenericPackingInstructionsId(HuPackingInstructionsId.ofRepoId(defaultPIForPicking.getM_HU_PI_ID()));
	}

	private void ensureDefaultPackingInstructionExists()
	{
		getPackToSpec();
	}

}
