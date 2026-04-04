package de.metas.ui.web.pickingslotsClearing.process;

import java.util.List;

import de.metas.handlingunits.IHUShipperTransportationBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.shipping.model.I_M_ShipperTransportation;
import de.metas.shipping.model.ShipperTransportationId;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.util.Services;

 

public class WEBUI_PackingHUsView_AddHUsToShipperTransportation extends PackingHUsViewBasedProcess implements IProcessPrecondition
{
	private final transient IHUShipperTransportationBL huShipperTransportationBL = Services.get(IHUShipperTransportationBL.class);

	@Param(parameterName = I_M_ShipperTransportation.COLUMNNAME_M_ShipperTransportation_ID, mandatory = true)
	private int shipperTransportationId;

	@Override
	protected final ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (getSelectedRowIds().isEmpty())
		{
			return ProcessPreconditionsResolution.rejectBecauseNoSelection();
		}

		final boolean eligibleHUsFound = streamEligibleHURows()
				.findAny()
				.isPresent();
		if (!eligibleHUsFound)
		{
			return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_HU_Constants.MSG_WEBUI_ONLY_TOP_LEVEL_HU));
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected final String doIt() throws Exception
	{
		final List<I_M_HU> hus = retrieveEligibleHUs();
		huShipperTransportationBL.addHUsToShipperTransportation(ShipperTransportationId.ofRepoId(shipperTransportationId), hus);

		return MSG_OK;
	}

	@Override
	protected final void postProcess(final boolean success)
	{
		if (!success)
		{
			return;
		}

		getView().invalidateAll();
	}
}
