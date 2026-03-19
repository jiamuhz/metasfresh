package de.metas.ui.web.handlingunits;

import de.metas.handlingunits.HuUnitType;
import org.adempiere.exceptions.AdempiereException;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import de.metas.handlingunits.model.X_M_HU_PI_Version;
import de.metas.ui.web.view.IViewRowType;
import de.metas.ui.web.view.ViewRowTypeIconNames;

 

public enum HUEditorRowType implements IViewRowType
{
	LU(ViewRowTypeIconNames.ICONNAME_LU, true) //
	, TU(ViewRowTypeIconNames.ICONNAME_TU, true) //
	, VHU(ViewRowTypeIconNames.ICONNAME_CU, true) //
	, HUStorage(ViewRowTypeIconNames.ICONNAME_CU, false) //
	;

	private final String name;
	private final boolean pureHU;

	HUEditorRowType(final String name, final boolean pureHU)
	{
		this.name = name;
		this.pureHU = pureHU;
	}

	@Override
	@JsonValue
	public String getName()
	{
		return name;
	}

	/** @return true if it's a pure HU (i.e. not {@link #HUStorage}) */
	public boolean isPureHU()
	{
		return pureHU;
	}

	public boolean isCU()
	{
		return this == VHU || this == HUStorage;
	}

	public static HUEditorRowType ofHU_UnitType(final String huUnitType)
	{
		final HUEditorRowType type = huUnitType2type.get(huUnitType);
		if (type == null)
		{
			throw new IllegalArgumentException("Cannot convert HU_UnitType '" + huUnitType + "' to " + HUEditorRowType.class);
		}
		return type;
	}

	public HuUnitType toHUUnitTypeOrNull()
	{
		if (this == HUStorage)
		{
			return HuUnitType.VHU;
		}
		return HuUnitType.ofNullableCode(huUnitType2type.inverse().get(this));

	}

	public HuUnitType toHUUnitType()
	{
		final HuUnitType unitType = toHUUnitTypeOrNull();
		if (unitType == null)
		{
			throw new AdempiereException("Cannot convert " + this + " to HU_UnitType");
		}
		return unitType;
	}

	private static final BiMap<String, HUEditorRowType> huUnitType2type = ImmutableBiMap.<String, HUEditorRowType> builder()
			.put(X_M_HU_PI_Version.HU_UNITTYPE_LoadLogistiqueUnit, LU)
			.put(X_M_HU_PI_Version.HU_UNITTYPE_TransportUnit, TU)
			.put(X_M_HU_PI_Version.HU_UNITTYPE_VirtualPI, VHU)
			.build();
}
