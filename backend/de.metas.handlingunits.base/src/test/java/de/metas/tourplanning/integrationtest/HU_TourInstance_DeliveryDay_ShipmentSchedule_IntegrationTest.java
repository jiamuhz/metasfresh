package de.metas.tourplanning.integrationtest;

import de.metas.ad_reference.ADReferenceService;
import de.metas.distribution.ddorder.DDOrderService;
import de.metas.distribution.ddorder.lowlevel.DDOrderLowLevelDAO;
import de.metas.distribution.ddorder.lowlevel.DDOrderLowLevelService;
import de.metas.distribution.ddorder.movement.schedule.DDOrderMoveScheduleRepository;
import de.metas.distribution.ddorder.movement.schedule.DDOrderMoveScheduleService;
import de.metas.handlingunits.attribute.impl.HUUniqueAttributesRepository;
import de.metas.handlingunits.attribute.impl.HUUniqueAttributesService;
import de.metas.handlingunits.model.I_M_ShipmentSchedule;
import de.metas.handlingunits.reservation.HUReservationRepository;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.handlingunits.tourplanning.model.I_M_DeliveryDay_Alloc;
import de.metas.handlingunits.tourplanning.spi.impl.HUShipmentScheduleDeliveryDayHandlerTest;
import de.metas.inoutcandidate.picking_bom.PickingBOMService;
import de.metas.resource.ResourceService;
import de.metas.tourplanning.model.I_M_DeliveryDay;
import org.adempiere.model.InterfaceWrapperHelper;

import java.math.BigDecimal;

public class HU_TourInstance_DeliveryDay_ShipmentSchedule_IntegrationTest extends TourInstance_DeliveryDay_ShipmentSchedule_IntegrationTest
{
	@Override
	protected void afterInit()
	{
		super.afterInit();

		final DDOrderLowLevelDAO ddOrderLowLevelDAO = new DDOrderLowLevelDAO();
		final HUReservationService huReservationService = new HUReservationService(new HUReservationRepository());
		final DDOrderMoveScheduleService ddOrderMoveScheduleService = new DDOrderMoveScheduleService(
				ddOrderLowLevelDAO,
				new DDOrderMoveScheduleRepository(),
				ADReferenceService.newMocked(),
				huReservationService);
		final DDOrderLowLevelService ddOrderLowLevelService = new DDOrderLowLevelService(ddOrderLowLevelDAO, ResourceService.newInstanceForJUnitTesting());
		final DDOrderService ddOrderService = new DDOrderService(ddOrderLowLevelDAO, ddOrderLowLevelService, ddOrderMoveScheduleService);
		final HUUniqueAttributesService huUniqueAttributesService = new HUUniqueAttributesService(new HUUniqueAttributesRepository());
		new de.metas.handlingunits.model.validator.Main(
				ddOrderMoveScheduleService,
				ddOrderService,
				new PickingBOMService(),
				huUniqueAttributesService).setupTourPlanning();
	}

	@Override
	protected boolean performTourPlanningRelevantChange(final de.metas.tourplanning.model.I_M_ShipmentSchedule shipmentSchedule)
	{
		super.performTourPlanningRelevantChange(shipmentSchedule);

		final I_M_ShipmentSchedule huShipmentSchedule = InterfaceWrapperHelper.create(shipmentSchedule, I_M_ShipmentSchedule.class);

		// Increase QryOrdered_LU by 10
		huShipmentSchedule.setQtyOrdered_LU(huShipmentSchedule.getQtyOrdered_LU().add(BigDecimal.valueOf(10)));

		// we expect that changing QtyOrdered_LU to be a releavant change for tour planning
		return true;
	}

	@Override
	protected I_M_DeliveryDay_Alloc assertDeliveryDayAlloc(final I_M_DeliveryDay deliveryDayExpected,
														   final de.metas.tourplanning.model.I_M_ShipmentSchedule shipmentSchedule)
	{
		final de.metas.tourplanning.model.I_M_DeliveryDay_Alloc alloc = super.assertDeliveryDayAlloc(
				deliveryDayExpected,
				InterfaceWrapperHelper.create(shipmentSchedule, de.metas.tourplanning.model.I_M_ShipmentSchedule.class));

		final I_M_DeliveryDay_Alloc huAlloc = InterfaceWrapperHelper.create(alloc, I_M_DeliveryDay_Alloc.class);
		final I_M_ShipmentSchedule huShipmentSchedule = InterfaceWrapperHelper.create(shipmentSchedule, I_M_ShipmentSchedule.class);
		HUShipmentScheduleDeliveryDayHandlerTest.assertEquals(huShipmentSchedule, huAlloc);

		return huAlloc;
	}

}
