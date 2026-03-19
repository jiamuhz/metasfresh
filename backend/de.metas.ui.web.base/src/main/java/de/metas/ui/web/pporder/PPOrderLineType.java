package de.metas.ui.web.pporder;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_Storage;
import de.metas.ui.web.handlingunits.HUEditorRowType;
import de.metas.ui.web.view.IViewRowType;
import de.metas.ui.web.view.ViewRowTypeIconNames;
import de.metas.util.GuavaCollectors;
import lombok.NonNull;
import org.eevolution.model.I_PP_Order;
import org.eevolution.model.I_PP_Order_BOMLine;

import java.util.stream.Stream;

 

public enum PPOrderLineType implements IViewRowType
{
	MainProduct("MP", true, false, I_PP_Order.Table_Name),
	BOMLine_Component("CO", false, false, I_PP_Order_BOMLine.Table_Name),
	BOMLine_Component_Service("CO", false, true, I_PP_Order_BOMLine.Table_Name),
	BOMLine_ByCoProduct("BY", true, false, I_PP_Order_BOMLine.Table_Name),

	HU_LU(HUEditorRowType.LU, I_M_HU.Table_Name),
	HU_TU(HUEditorRowType.TU, I_M_HU.Table_Name),
	HU_VHU(HUEditorRowType.VHU, I_M_HU.Table_Name),
	HU_Storage(HUEditorRowType.HUStorage, I_M_HU_Storage.Table_Name);

	private final String name;
	private final String iconName;
	private final HUEditorRowType huType;

	private final String tableName;

	private final boolean canReceive;
	private final boolean canIssue;

	PPOrderLineType(
			@NonNull final String name,
			final boolean canReceive,
			final boolean serviceProduct,
			@NonNull final String tableName)
	{
		this.name = name;
		this.huType = null;
		this.tableName = tableName;

		this.canReceive = canReceive;
		this.canIssue = !canReceive;

		if (canReceive)
		{
			this.iconName = ViewRowTypeIconNames.ICONNAME_PP_Order_Receive;
		}
		else if (serviceProduct)
		{
			this.iconName = ViewRowTypeIconNames.ICONNAME_PP_Order_Issue_Service;
		}
		else
		{
			this.iconName = ViewRowTypeIconNames.ICONNAME_PP_Order_Issue;
		}
	}

	PPOrderLineType(
			@NonNull final HUEditorRowType huType,
			@NonNull final String tableName)
	{
		this.name = huType.getName();
		this.iconName = huType.getIconName();
		this.huType = huType;
		this.tableName = tableName;

		canReceive = false;
		canIssue = false;
	}

	@JsonValue
	@Override
	public String getName()
	{
		return name;
	}

	public String getTableName()
	{
		return tableName;
	}

	@Override
	public String getIconName()
	{
		return iconName;
	}

	public static PPOrderLineType cast(final IViewRowType type)
	{
		return (PPOrderLineType)type;
	}

	public boolean canReceive()
	{
		return canReceive;
	}

	public boolean canIssue()
	{
		return canIssue;
	}

	public boolean isBOMLine()
	{
		return this == BOMLine_Component
				|| this == BOMLine_Component_Service
				|| this == BOMLine_ByCoProduct;
	}

	public boolean isMainProduct()
	{
		return this == MainProduct;
	}
	
	
	public boolean isHUOrHUStorage()
	{
		return this == HU_LU
				|| this == HU_TU
				|| this == HU_VHU
				|| this == HU_Storage;
	}

	public static PPOrderLineType ofHUEditorRowType(final HUEditorRowType huType)
	{
		final PPOrderLineType type = huType2type.get(huType);
		if (type == null)
		{
			throw new IllegalArgumentException("No type found for " + huType);
		}
		return type;
	}

	private static final ImmutableMap<HUEditorRowType, PPOrderLineType> huType2type = Stream.of(values())
			.filter(type -> type.huType != null)
			.collect(GuavaCollectors.toImmutableMapByKey(type -> type.huType));
}
