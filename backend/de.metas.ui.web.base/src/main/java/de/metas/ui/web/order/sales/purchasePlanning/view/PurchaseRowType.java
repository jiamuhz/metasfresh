package de.metas.ui.web.order.sales.purchasePlanning.view;

import java.util.Map;
import java.util.stream.Stream;

import org.adempiere.exceptions.AdempiereException;

import de.metas.ui.web.pporder.PPOrderLineType;
import de.metas.ui.web.view.IViewRowType;
import de.metas.util.GuavaCollectors;
import lombok.Getter;
import lombok.NonNull;



public enum PurchaseRowType implements IViewRowType
{
	GROUP("G", PPOrderLineType.MainProduct.getIconName()), //
	LINE("L", PPOrderLineType.BOMLine_Component.getIconName()), //
	AVAILABILITY_DETAIL("A", PPOrderLineType.BOMLine_ByCoProduct.getIconName());

	@Getter
	private final String code;
	private final String iconName;

	PurchaseRowType(@NonNull final String code, @NonNull final String iconName)
	{
		this.code = code;
		this.iconName = iconName;
	}

	@Override
	public String getName()
	{
		return iconName;
	}

	@Override
	public String getIconName()
	{
		return iconName;
	}

	public static PurchaseRowType ofCode(final String code)
	{
		final PurchaseRowType type = typesByCode.get(code);
		if (type == null)
		{
			throw new AdempiereException("No " + PurchaseRowType.class.getName() + " found for code: " + code);
		}
		return type;
	}

	private static final Map<String, PurchaseRowType> typesByCode = Stream.of(values())
			.collect(GuavaCollectors.toImmutableMapByKey(PurchaseRowType::getCode));
}
