package de.metas.ui.web.picking.packageable;

import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.picking.requests.CloseForShipmentSchedulesRequest;
import de.metas.i18n.ITranslatableString;
import de.metas.inout.ShipmentScheduleId;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.picking.packageable.filters.ProductBarcodeFilterData;
import de.metas.ui.web.picking.pickingslot.PickingSlotView;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;



/**
 * Picking editor's view left-hand side view which lists one or more {@link PackageableRow} records to be picked.
 * <p>
 * Note that technically this view also contains the right-hand side {@link PickingSlotView}.
 *
 *
 */
public class PackageableView extends AbstractCustomView<PackageableRow>
{
	public static PackageableView cast(final IView view)
	{
		return (PackageableView)view;
	}

	private final PickingCandidateService pickingCandidateService;

	@Getter
	private final ProductBarcodeFilterData barcodeFilterData;
	private final ConcurrentHashMap<DocumentId, PickingSlotView> pickingSlotsViewByRowId = new ConcurrentHashMap<>();

	@Builder
	private PackageableView(
			@NonNull final PickingCandidateService pickingCandidateService,
			@NonNull final ViewId viewId,
			@Nullable final ITranslatableString description,
			@NonNull final PackageableRowsData rowsData,
			@Nullable final ProductBarcodeFilterData barcodeFilterData)
	{
		super(viewId, description, rowsData, NullDocumentFilterDescriptorsProvider.instance);

		this.pickingCandidateService = pickingCandidateService;
		this.barcodeFilterData = barcodeFilterData;
	}

	@Override
	protected PackageableRowsData getRowsData()
	{
		return PackageableRowsData.cast(super.getRowsData());
	}

	/**
	 * @return {@link I_M_Packageable_V#Table_Name}.
	 */
	@Override
	public String getTableNameOrNull(@Nullable final DocumentId ignored)
	{
		return I_M_Packageable_V.Table_Name;
	}

	@Override
	public void close(final ViewCloseAction action)
	{
		if (action.isDone())
		{
			closePickingCandidatesFromRackSystemPickingSlots();
		}
	}

	private void closePickingCandidatesFromRackSystemPickingSlots()
	{
		final Set<ShipmentScheduleId> shipmentScheduleIds = getRows()
				.stream()
				.map(PackageableRow::getShipmentScheduleId)
				.collect(ImmutableSet.toImmutableSet());

		// Close all picking candidates which are on a rack system picking slot (gh2740)
		pickingCandidateService.closeForShipmentSchedules(CloseForShipmentSchedulesRequest.builder()
																  .shipmentScheduleIds(shipmentScheduleIds)
																  .pickingSlotIsRackSystem(true)
																  .failOnError(false) // close as much candidates as it's possible
																  .build());
	}

	public void setPickingSlotView(@NonNull final DocumentId rowId, @NonNull final PickingSlotView pickingSlotView)
	{
		pickingSlotsViewByRowId.put(rowId, pickingSlotView);
	}

	public void removePickingSlotView(@NonNull final DocumentId rowId, @NonNull final ViewCloseAction viewCloseAction)
	{
		final PickingSlotView view = pickingSlotsViewByRowId.remove(rowId);
		if (view != null)
		{
			view.close(viewCloseAction);
		}
	}

	public PickingSlotView getPickingSlotViewOrNull(@NonNull final DocumentId rowId)
	{
		return pickingSlotsViewByRowId.get(rowId);
	}

	public PickingSlotView computePickingSlotViewIfAbsent(@NonNull final DocumentId rowId, @NonNull final Supplier<PickingSlotView> pickingSlotViewFactory)
	{
		return pickingSlotsViewByRowId.computeIfAbsent(rowId, id -> pickingSlotViewFactory.get());
	}

	public void invalidatePickingSlotViews()
	{
		pickingSlotsViewByRowId.values().forEach(PickingSlotView::invalidateAll);
	}
}
