package de.metas.handlingunits.model.validator;

import de.metas.handlingunits.model.I_C_OrderLine;
import de.metas.handlingunits.model.I_M_ShipmentSchedule;
import de.metas.handlingunits.shipmentschedule.api.IHUShipmentScheduleBL;
import de.metas.inoutcandidate.api.IShipmentScheduleBL;
import de.metas.inoutcandidate.api.IShipmentScheduleEffectiveBL;
import de.metas.inoutcandidate.api.impl.ShipmentScheduleUpdater;
import de.metas.inoutcandidate.invalidation.impl.ShipmentScheduleInvalidateBL;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.modelvalidator.ModelChangeType;
import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.ModelValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Interceptor(I_M_ShipmentSchedule.class)
@Component
public class M_ShipmentSchedule
{
	private final IHUShipmentScheduleBL huShipmentScheduleBL = Services.get(IHUShipmentScheduleBL.class);
	private final IShipmentScheduleBL shipmentScheduleBL = Services.get(IShipmentScheduleBL.class);
	private final IShipmentScheduleEffectiveBL shipmentScheduleEffectiveBL = Services.get(IShipmentScheduleEffectiveBL.class);

	private final ShipmentScheduleInvalidateBL invalidSchedulesService;
	private final ShipmentScheduleUpdater shipmentScheduleUpdater;

	public M_ShipmentSchedule(
			@NonNull final ShipmentScheduleInvalidateBL invalidSchedulesService,
			@NonNull final ShipmentScheduleUpdater shipmentScheduleUpdater)
	{
		this.invalidSchedulesService = invalidSchedulesService;
		this.shipmentScheduleUpdater = shipmentScheduleUpdater;
	}

	@ModelChange(timings = ModelValidator.TYPE_BEFORE_CHANGE, //
			ifColumnsChanged = I_M_ShipmentSchedule.COLUMNNAME_IsClosed)
	public void updateHURelatedValuesFromOrderLineBeforeOpened(@NonNull final I_M_ShipmentSchedule shipmentSchedule)
	{
		if (shipmentScheduleBL.isJustOpened(shipmentSchedule))
		{
			huShipmentScheduleBL.updateHURelatedValuesFromOrderLine(shipmentSchedule);
			huShipmentScheduleBL.updateEffectiveValues(shipmentSchedule);
		}
		}

	@ModelChange(timings = ModelValidator.TYPE_BEFORE_NEW)
	public void updateHURelatedValuesFromOrderLineBeforeNew(
			@NonNull final I_M_ShipmentSchedule shipmentSchedule,
			@NonNull final ModelChangeType type)
	{
		huShipmentScheduleBL.updateHURelatedValuesFromOrderLine(shipmentSchedule);
		huShipmentScheduleBL.updateEffectiveValues(shipmentSchedule);
	}

	@ModelChange(//
			timings = ModelValidator.TYPE_BEFORE_CHANGE, //
			ifColumnsChanged = {
			I_M_ShipmentSchedule.COLUMNNAME_QtyTU_Calculated,
			I_M_ShipmentSchedule.COLUMNNAME_QtyTU_Override,
			I_M_ShipmentSchedule.COLUMNNAME_QtyOrdered_TU,
			I_M_ShipmentSchedule.COLUMNNAME_M_HU_PI_Item_Product_Calculated_ID,
			I_M_ShipmentSchedule.COLUMNNAME_M_HU_PI_Item_Product_Override_ID,
			I_M_ShipmentSchedule.COLUMNNAME_M_HU_PI_Item_Product_ID
	})
	public void updateEffectiveValues(final I_M_ShipmentSchedule shipmentSchedule)
	{
		if (shipmentSchedule.getC_OrderLine_ID() > 0)
		{
			huShipmentScheduleBL.updateHURelatedValuesFromOrderLine(shipmentSchedule);
		}

		huShipmentScheduleBL.updateEffectiveValues(shipmentSchedule);

		if (shipmentSchedule.getC_OrderLine_ID() > 0)
		{
		// update orderLine
			final I_C_OrderLine orderLine = InterfaceWrapperHelper.create(shipmentSchedule.getC_OrderLine(), I_C_OrderLine.class);

		// task 09005: make sure the correct qtyOrdered is taken from the shipmentSchedule
			final BigDecimal qtyOrderedEffective = shipmentScheduleEffectiveBL.computeQtyOrdered(shipmentSchedule);
		orderLine.setQtyOrdered(qtyOrderedEffective);

		InterfaceWrapperHelper.save(orderLine);
		}

	}

	/**
	 * Note: it's important that the schedule is only invalidated on certain value changes.<br>
	 * For example, a change of lock status or valid status may not cause an invalidation.<br>
	 */
	@ModelChange(timings = ModelValidator.TYPE_AFTER_CHANGE, //
			ifColumnsChanged = {
					I_M_ShipmentSchedule.COLUMNNAME_QtyOrdered_Override,
					I_M_ShipmentSchedule.COLUMNNAME_QtyTU_Calculated,
					I_M_ShipmentSchedule.COLUMNNAME_QtyTU_Override,
					I_M_ShipmentSchedule.COLUMNNAME_QtyOrdered_Calculated,
					I_M_ShipmentSchedule.COLUMNNAME_M_HU_PI_Item_Product_Override_ID,
					I_M_ShipmentSchedule.COLUMNNAME_M_HU_PI_Item_Product_ID,
					I_M_ShipmentSchedule.COLUMNNAME_M_HU_PI_Item_Product_Calculated_ID,
					I_M_ShipmentSchedule.COLUMNNAME_QtyOrdered
			})
	public void invalidate(final I_M_ShipmentSchedule shipmentSchedule)
	{
		// If shipment schedule updater is currently running in this thread, it means that updater changed this record so there is NO need to invalidate it again.
		if (shipmentScheduleUpdater.isRunning())
		{
			return;
		}
		if (shipmentScheduleBL.isDoNotInvalidateOnChange(shipmentSchedule))
		{
			return;
		}

		invalidSchedulesService.notifySegmentChangedForShipmentScheduleInclSched(shipmentSchedule); // 08746: make sure that at any rate, the schedule itself is invalidated, even if it has delivery
	}
}
