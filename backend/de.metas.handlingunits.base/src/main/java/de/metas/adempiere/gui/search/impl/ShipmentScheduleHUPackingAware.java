package de.metas.adempiere.gui.search.impl;

import java.math.BigDecimal;

import de.metas.adempiere.gui.search.IHUPackingAware;
import de.metas.handlingunits.model.I_M_ShipmentSchedule;
import de.metas.inoutcandidate.api.IShipmentScheduleEffectiveBL;
import de.metas.util.Services;
import lombok.NonNull;

public class ShipmentScheduleHUPackingAware implements IHUPackingAware
{

	private final I_M_ShipmentSchedule shipmentSchedule;
	private final PlainHUPackingAware values = new PlainHUPackingAware();

	public ShipmentScheduleHUPackingAware(@NonNull final I_M_ShipmentSchedule shipmentSchedule)
	{
		this.shipmentSchedule = shipmentSchedule;
	}

	@Override
	public int getM_Product_ID()
	{
		return shipmentSchedule.getM_Product_ID();
	}

	@Override
	public void setM_Product_ID(final int productId)
	{
		shipmentSchedule.setM_Product_ID(productId);
	}

	@Override
	public int getM_AttributeSetInstance_ID()
	{
		return shipmentSchedule.getM_AttributeSetInstance_ID();
	}

	@Override
	public void setM_AttributeSetInstance_ID(final int M_AttributeSetInstance_ID)
	{
		shipmentSchedule.setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
	}

	@Override
	public int getC_UOM_ID()
	{
		return shipmentSchedule.getC_UOM_ID();
	}

	@Override
	public void setC_UOM_ID(final int uomId)
	{
		// we assume inoutLine's UOM is correct
		if (uomId > 0)
		{
			shipmentSchedule.setC_UOM_ID(uomId);
		}
	}

	@Override
	public void setQty(final BigDecimal qty)
	{
		shipmentSchedule.setQtyOrdered_Override(qty);
	}

	@Override
	public BigDecimal getQty()
	{
		// task 09005: make sure the correct qtyOrdered is taken from the shipmentSchedule
		final BigDecimal qtyOrdered = Services.get(IShipmentScheduleEffectiveBL.class).computeQtyOrdered(shipmentSchedule);

		return qtyOrdered;
	}

	@Override
	public int getM_HU_PI_Item_Product_ID()
	{
		return shipmentSchedule.getM_HU_PI_Item_Product_ID();
	}

	@Override
	public void setM_HU_PI_Item_Product_ID(final int huPiItemProductId)
	{
		values.setM_HU_PI_Item_Product_ID(huPiItemProductId);
	}

	@Override
	public BigDecimal getQtyTU()
	{
		return shipmentSchedule.getQtyOrdered_TU();
	}

	@Override
	public void setQtyTU(final BigDecimal qtyPacks)
	{
		shipmentSchedule.setQtyOrdered_TU(qtyPacks);

	}

	@Override
	public void setC_BPartner_ID(final int partnerId)
	{
		values.setC_BPartner_ID(partnerId);

	}

	@Override
	public int getC_BPartner_ID()
	{
		return values.getC_BPartner_ID();
	}

	@Override
	public boolean isInDispute()
	{
		return values.isInDispute();
	}

	@Override
	public void setInDispute(final boolean inDispute)
	{
		values.setInDispute(inDispute);
	}
}
