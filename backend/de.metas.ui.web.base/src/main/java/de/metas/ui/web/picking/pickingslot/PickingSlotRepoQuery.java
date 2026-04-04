package de.metas.ui.web.picking.pickingslot;
 

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableSet;
import de.metas.inout.ShipmentScheduleId;
import de.metas.picking.qrcode.PickingSlotQRCode;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * Used in the repo services, to specify which data we want to be retrieved.
 * 
 *
 *
 */
@Value
public class PickingSlotRepoQuery
{
	@VisibleForTesting
	public static PickingSlotRepoQuery of(@NonNull final ShipmentScheduleId shipmentScheduleId)
	{
		return builder().currentShipmentScheduleId(shipmentScheduleId).shipmentScheduleId(shipmentScheduleId).build();
	}

	@NonNull ShipmentScheduleId currentShipmentScheduleId;

	@NonNull ImmutableSet<ShipmentScheduleId> shipmentScheduleIds;

	boolean onlyNotClosedOrNotRackSystem;

	@Nullable PickingSlotQRCode pickingSlotQRCode;

	@Builder
	private PickingSlotRepoQuery(
			@NonNull final ShipmentScheduleId currentShipmentScheduleId,
			@Singular final Set<ShipmentScheduleId> shipmentScheduleIds,
			@Nullable final Boolean onlyNotClosedOrNotRackSystem,
			@Nullable PickingSlotQRCode pickingSlotQRCode)
	{
		if (!Check.isEmpty(shipmentScheduleIds) && !shipmentScheduleIds.contains(currentShipmentScheduleId))
		{
			throw new AdempiereException("Current shipment schedule " + currentShipmentScheduleId + " is not in all shipment schedules list: " + shipmentScheduleIds);
		}

		this.currentShipmentScheduleId = currentShipmentScheduleId;
		this.shipmentScheduleIds = ImmutableSet.copyOf(shipmentScheduleIds);
		this.onlyNotClosedOrNotRackSystem = onlyNotClosedOrNotRackSystem != null ? onlyNotClosedOrNotRackSystem : true;
		this.pickingSlotQRCode = pickingSlotQRCode;
	}
}
