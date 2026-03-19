package de.metas.ui.web.handlingunits.process;

import java.util.List;

import de.metas.handlingunits.movement.generate.HUMovementGeneratorResult;
import org.adempiere.exceptions.AdempiereException;

import com.google.common.collect.ImmutableList;

import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_Warehouse;
import de.metas.handlingunits.movement.api.IHUMovementBL;
import de.metas.printing.esb.base.util.Check;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.util.Services;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import org.adempiere.warehouse.WarehouseId;

 

/**
 * HU editor: Move selected HUs to a given quality issues warehouse
 *
 *
 * Task initial task https://github.com/metasfresh/metasfresh-webui-api/issues/396
 */
public class WEBUI_M_HU_MoveToQualityWarehouse extends HUEditorProcessTemplate implements IProcessPrecondition
{
	private final transient IHUMovementBL huMovementBL = Services.get(IHUMovementBL.class);

	@Param(parameterName = I_M_Warehouse.COLUMNNAME_M_Warehouse_ID, mandatory = true)
	private I_M_Warehouse warehouse;

	private HUMovementGeneratorResult movementResult = null;

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if(!isHUEditorView())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
		}

		if (!streamSelectedHUIds(Select.ONLY_TOPLEVEL).findAny().isPresent())
		{
			return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_HU_Constants.MSG_WEBUI_ONLY_TOP_LEVEL_HU));
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt() throws Exception
	{
		Check.assume(warehouse.isQualityReturnWarehouse(), "not a quality returns warehouse");

		final List<I_M_HU> selectedTopLevelHUs = streamSelectedHUs(Select.ONLY_TOPLEVEL).collect(ImmutableList.toImmutableList());
		if (selectedTopLevelHUs.isEmpty())
		{
			throw new AdempiereException("@NoSelection@");
		}

		movementResult = huMovementBL.moveHUsToWarehouse(selectedTopLevelHUs, WarehouseId.ofRepoId(warehouse.getM_Warehouse_ID()));

		return MSG_OK;
	}

	@Override
	protected void postProcess(final boolean success)
	{
		if (movementResult != null)
		{
			getView().invalidateAll();
		}
	}
}
