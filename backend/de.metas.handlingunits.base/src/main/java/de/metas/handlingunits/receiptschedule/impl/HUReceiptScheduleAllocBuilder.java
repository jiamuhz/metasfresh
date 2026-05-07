package de.metas.handlingunits.receiptschedule.impl;

import java.math.BigDecimal;

import org.adempiere.model.InterfaceWrapperHelper;

import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_ReceiptSchedule_Alloc;
import de.metas.inoutcandidate.api.impl.ReceiptScheduleAllocBuilder;
import de.metas.inoutcandidate.model.I_M_ReceiptSchedule;
import de.metas.product.ProductId;
import de.metas.quantity.StockQtyAndUOMQty;
import de.metas.uom.IUOMConversionBL;
import de.metas.uom.UOMConversionContext;
import de.metas.uom.UomId;
import de.metas.util.Services;
import lombok.NonNull;

public class HUReceiptScheduleAllocBuilder extends ReceiptScheduleAllocBuilder
{
	// services
	private final transient IUOMConversionBL uomConversionBL = Services.get(IUOMConversionBL.class);

	private StockQtyAndUOMQty _huQtyAllocated;
	private I_M_HU _luHU;
	private I_M_HU _tuHU;
	private I_M_HU _vhu;

	@Override
	protected void build(final de.metas.inoutcandidate.model.I_M_ReceiptSchedule_Alloc rsa)
	{
		super.build(rsa);

		final I_M_ReceiptSchedule_Alloc rsaHU = InterfaceWrapperHelper.create(rsa, I_M_ReceiptSchedule_Alloc.class);

		//
		// HU_QtyAllocated
		final StockQtyAndUOMQty huQtyAllocatedSrc = getHU_QtyAllocated();
		final BigDecimal huQtyAllocated;
		if (huQtyAllocatedSrc != null)
		{
			//
			// Convert qty in Stock-UOM from given UOM to receipt schedule's UOM
			final I_M_ReceiptSchedule receiptSchedule = getM_ReceiptSchedule();
			final UomId uomIdTo = UomId.ofRepoId(receiptSchedule.getC_UOM_ID());
			final ProductId productId = ProductId.ofRepoId(receiptSchedule.getM_Product_ID());
			final UOMConversionContext uomConversionCtx = UOMConversionContext.of(productId);
			huQtyAllocated = uomConversionBL
					.convertQuantityTo(huQtyAllocatedSrc.getStockQty(), uomConversionCtx, uomIdTo)
					.toBigDecimal();
		}
		else
		{
			huQtyAllocated = null;
		}
		rsaHU.setHU_QtyAllocated(huQtyAllocated);

		//
		// LU/TU/VHU
		rsaHU.setM_LU_HU(getM_LU_HU());
		rsaHU.setM_TU_HU(getM_TU_HU());
		rsaHU.setVHU(getVHU());
	}

	public HUReceiptScheduleAllocBuilder setHU_QtyAllocated(@NonNull final StockQtyAndUOMQty huQtyAllocated)
	{
		_huQtyAllocated = huQtyAllocated;
		return this;
	}

	private StockQtyAndUOMQty getHU_QtyAllocated()
	{
		return _huQtyAllocated;
	}

	private I_M_HU getM_LU_HU()
	{
		return _luHU;
	}

	public HUReceiptScheduleAllocBuilder setM_LU_HU(final I_M_HU luHU)
	{
		_luHU = luHU;
		return this;
	}

	private I_M_HU getM_TU_HU()
	{
		return _tuHU;
	}

	public HUReceiptScheduleAllocBuilder setM_TU_HU(final I_M_HU tuHU)
	{
		_tuHU = tuHU;
		return this;
	}

	private I_M_HU getVHU()
	{
		return _vhu;
	}

	public HUReceiptScheduleAllocBuilder setVHU(final I_M_HU vhu)
	{
		_vhu = vhu;
		return this;
	}

}
