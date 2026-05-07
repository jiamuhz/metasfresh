package de.metas.handlingunits.materialtracking.process;

import de.metas.bpartner.BPartnerLocationId;
import de.metas.bpartner.service.IBPartnerOrgBL;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.distribution.ddorder.movement.schedule.DDOrderMoveScheduleService;
import de.metas.distribution.ddorder.producer.HUs2DDOrderProducer;
import de.metas.distribution.ddorder.producer.HUToDistribute;
import de.metas.handlingunits.materialtracking.IHUMaterialTrackingBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.organization.OrgId;
import de.metas.process.JavaProcess;
import de.metas.process.RunOutOfTrx;
import de.metas.util.Services;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.util.api.IRangeAwareParams;
import org.adempiere.warehouse.LocatorId;
import org.adempiere.warehouse.WarehouseId;
import org.adempiere.warehouse.api.IWarehouseBL;
import org.compiere.SpringContextHolder;

import java.util.Iterator;

/**
 * Process used to generate DD_Orders to move the HUs flagged as "scheduled from Quality Inspection" from a given warehouse to a given warehouse.
 *
 *
 * @implNote task 08639
 */
public class DD_Order_GenerateForQualityInspectionFlaggedHUs extends JavaProcess
{
	// services
	private final DDOrderMoveScheduleService ddOrderMoveScheduleService = SpringContextHolder.instance.getBean(DDOrderMoveScheduleService.class);
	private final IHandlingUnitsDAO handlingUnitsDAO = Services.get(IHandlingUnitsDAO.class);
	private final IBPartnerOrgBL bpartnerOrgBL = Services.get(IBPartnerOrgBL.class);
	private final IWarehouseBL warehouseBL = Services.get(IWarehouseBL.class);

	// Parameters
	private WarehouseId warehouseFromId;
	private WarehouseId warehouseToId;

	@Override
	protected void prepare()
	{
		final IRangeAwareParams params = getParameterAsIParams();
		warehouseFromId = getWarehouseId(params, "M_Warehouse_ID");
		warehouseToId = getWarehouseId(params, "M_Warehouse_Dest_ID");

		// make sure user selected different warehouses
		if (WarehouseId.equals(warehouseFromId, warehouseToId))
		{
			throw new AdempiereException("@M_Warehouse_ID@ = @M_Warehouse_Dest_ID@");
		}
	}

	private WarehouseId getWarehouseId(final IRangeAwareParams params, final String warehouseParameterName)
	{
		final WarehouseId warehouseId = WarehouseId.ofRepoIdOrNull(params.getParameterAsInt(warehouseParameterName, -1));
		if (warehouseId == null)
		{
			throw new FillMandatoryException(warehouseParameterName);
		}
		return warehouseId;
	}

	@Override
	@RunOutOfTrx
	protected String doIt()
	{
		final LocatorId locatorToId = warehouseBL.getOrCreateDefaultLocatorId(warehouseToId);
		final OrgId orgId = warehouseBL.getWarehouseOrgId(warehouseToId);
		final BPartnerLocationId orgBPLocationId = bpartnerOrgBL.retrieveOrgBPLocationId(orgId);

		HUs2DDOrderProducer.newProducer(ddOrderMoveScheduleService)
				.setLocatorToId(locatorToId)
				.setBpartnerLocationId(orgBPLocationId)
				.setHUs(retrieveHUs())
				.process();

		return MSG_OK;
	}

	private Iterator<HUToDistribute> retrieveHUs()
	{
		return handlingUnitsDAO.createHUQueryBuilder()
				.setContext(getCtx(), ITrx.TRXNAME_ThreadInherited)
				.setOnlyTopLevelHUs()
				.addOnlyInWarehouseId(warehouseFromId)
				.addOnlyWithAttribute(IHUMaterialTrackingBL.ATTRIBUTENAME_IsQualityInspection, IHUMaterialTrackingBL.ATTRIBUTEVALUE_IsQualityInspection_Yes)
				.addHUStatusToInclude(X_M_HU.HUSTATUS_Active)
				.addFilter(ddOrderMoveScheduleService.getHUsNotAlreadyScheduledToMoveFilter())
				//
				.createQuery()
				.stream(I_M_HU.class)
				.map(HUToDistribute::ofHU)
				.iterator();
	}
}
