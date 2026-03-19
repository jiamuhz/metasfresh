package de.metas.ui.web.handlingunits.util;

import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_LUTU_Configuration;
import de.metas.handlingunits.model.X_M_HU_PI_Version;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;

 

public final class HUPackingInfos
{
	private HUPackingInfos()
	{
	}

	public static IHUPackingInfo of(@NonNull final I_M_HU_LUTU_Configuration lutuConfig)
	{
		return new LUTUConfigAsPackingInfo(lutuConfig);
	}

	public static IHUPackingInfo of(@NonNull final I_M_HU hu)
	{
		final IHandlingUnitsBL handlingUnitsBL = Services.get(IHandlingUnitsBL.class);
		
		if (handlingUnitsBL.isAggregateHU(hu))
		{
			return new AggregatedTUPackingInfo(hu);
		}

		final String huUnitType = handlingUnitsBL.getHU_UnitType(hu);
		if (X_M_HU_PI_Version.HU_UNITTYPE_LoadLogistiqueUnit.equals(huUnitType))
		{
			return new LUPIPackingInfo(handlingUnitsBL.getPI(hu));
		}
		else if (X_M_HU_PI_Version.HU_UNITTYPE_TransportUnit.equals(huUnitType))
		{
			return new TUPackingInfo(hu);
		}
		else if (X_M_HU_PI_Version.HU_UNITTYPE_VirtualPI.equals(huUnitType))
		{
			return new VHUPackingInfo(hu);
		}

		throw new IllegalArgumentException("HU type not supported: " + huUnitType
				+ "\n HU: " + hu);
	}

	public IHUPackingInfo of(final IHUProductStorage huProductStorage)
	{
		return new VHUPackingInfo(huProductStorage);
	}
}
