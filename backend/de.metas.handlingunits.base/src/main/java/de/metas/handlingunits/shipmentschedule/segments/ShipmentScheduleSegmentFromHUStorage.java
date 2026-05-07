package de.metas.handlingunits.shipmentschedule.segments;

import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_Storage;
import de.metas.inoutcandidate.invalidation.segments.IShipmentScheduleSegment;
import de.metas.inoutcandidate.invalidation.segments.ShipmentScheduleAttributeSegment;
import de.metas.util.Services;
import de.metas.util.collections.CollectionUtils;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.Env;

import java.util.Collections;
import java.util.Set;

@ToString(exclude = "huStorage")
public class ShipmentScheduleSegmentFromHUStorage implements IShipmentScheduleSegment
{
	private final I_M_HU_Storage huStorage;

	private Set<Integer> productIds = Collections.emptySet();
	private Set<Integer> bpartnerIds = Collections.emptySet();
	private Set<Integer> locatorIds = Collections.emptySet();
	private boolean loaded = false;

	public ShipmentScheduleSegmentFromHUStorage(@NonNull final I_M_HU_Storage huStorage)
	{
		this.huStorage = huStorage;
	}

	private void loadIfNeeded()
	{
		if (loaded)
		{
			return;
		}

		load();

		loaded = true;
	}

	private void load()
	{
		//
		// Load the HU
		// NOTE: instead of getting the HU by using huStorage.getM_HU() we are loading it directly because the huStorage's transaction is already closed,
		// and our ModelCacheService will log a WARNING about this.
		// see ModelCacheService (line ~194): "No transaction was found for " + trxName + ". Skip cache."
		final int huId = huStorage.getM_HU_ID();
		if (huId <= 0)
		{
			loaded = true;
			return;
		}
		final I_M_HU hu = InterfaceWrapperHelper.create(Env.getCtx(), huId, I_M_HU.class, ITrx.TRXNAME_ThreadInherited);
		if (hu == null)
		{
			return;
		}

		// Fire only for top-level HUs to minimize the number of events
		if (!Services.get(IHandlingUnitsBL.class).isTopLevel(hu))
		{
			return;
		}

		final ShipmentScheduleSegmentFromHU huSegment = new ShipmentScheduleSegmentFromHU(hu);

		// If this HU does not contain QtyOnHand storages, there is no point to go forward
		// because actually nothing changed from QOH perspective
		if (!huSegment.hasQtyOnHandChanges())
		{
			return;
		}

		productIds = CollectionUtils.asSet(huStorage.getM_Product_ID());
		bpartnerIds = huSegment.getBpartnerIds();
		locatorIds = huSegment.getLocatorIds();
	}

	@Override
	public Set<Integer> getProductIds()
	{
		loadIfNeeded();
		return productIds;
	}

	@Override
	public Set<Integer> getBpartnerIds()
	{
		loadIfNeeded();
		return bpartnerIds;
	}

	@Override
	public Set<Integer> getLocatorIds()
	{
		loadIfNeeded();
		return locatorIds;
	}

	@Override
	public Set<Integer> getBillBPartnerIds()
	{
		return ImmutableSet.of();
	}

	@Override
	public Set<ShipmentScheduleAttributeSegment> getAttributes()
	{
		return ImmutableSet.of();
	}
}
