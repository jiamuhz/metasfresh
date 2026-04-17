package de.metas.ui.web.picking.packageable;

import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.inout.ShipmentScheduleId;
import de.metas.ui.web.picking.PickingConstants;
import de.metas.ui.web.picking.packageable.filters.ProductBarcodeFilterData;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.IncludedViewLayout;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAwares;
import lombok.Builder;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

 

/**
 * Factory class for {@link PackageableView} intances.
 *
 *
 */
@ViewFactory(windowId = PickingConstants.WINDOWID_PickingView_String, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class PackageableViewFactory implements IViewFactory
{
	private final PackageableRowsRepository pickingViewRepo;
	private final PickingCandidateService pickingCandidateService;

	private static final String VIEWPARAM_ProductBarcodeFilterData = "ProductBarcodeFilterData";

	/**
	 * @param pickingCandidateService when a new view is created, this stateless instance is given to that view
	 */
	public PackageableViewFactory(
			@NonNull final PackageableRowsRepository pickingViewRepo,
			@NonNull final PickingCandidateService pickingCandidateService)
	{
		this.pickingViewRepo = pickingViewRepo;
		this.pickingCandidateService = pickingCandidateService;
	}

	@Override
	public ViewLayout getViewLayout(
			@NonNull final WindowDocumentTypeId windowId,
			@NonNull final JSONViewDataType viewDataType,
			@Nullable final ViewProfileId profileId)
	{
		return ViewLayout.builder()
				.setWindowId(PickingConstants.WINDOWID_PickingView)
				.setCaption("Picking")
				//
				.setIncludedViewLayout(IncludedViewLayout.builder()
											   .openOnSelect(true)
											   .build())
				//
				.addElementsFromViewRowClass(PackageableRow.class, viewDataType)
				//
				.build();
	}

	/**
	 * @param request its {@code windowId} has to me {@link PickingConstants#WINDOWID_PickingView}
	 */
	@Override
	public IView createView(@NonNull final CreateViewRequest request)
	{
		final ViewId viewId = request.getViewId();
		if (!PickingConstants.WINDOWID_PickingView.equals(viewId.getWindowId()))
		{
			throw new IllegalArgumentException("Invalid request's windowId: " + request);
		}

		final Set<ShipmentScheduleId> shipmentScheduleIds = extractShipmentScheduleIds(request);
		final PackageableRowsData rowsData = pickingViewRepo.createRowsData(viewId, shipmentScheduleIds);

		return PackageableView.builder()
				.viewId(viewId)
				.rowsData(rowsData)
				.pickingCandidateService(pickingCandidateService)
				.barcodeFilterData(extractProductBarcodeFilterData(request).orElse(null))
				.build();
	}

	@Builder(builderMethodName = "createViewRequest", builderClassName = "$CreateViewRequestBuilder")
	private static CreateViewRequest createCreateViewRequest(
			@NonNull final List<ShipmentScheduleId> shipmentScheduleIds,
			@Nullable final ProductBarcodeFilterData barcodeFilterData)
	{
		Check.assumeNotEmpty(shipmentScheduleIds, "shipmentScheduleIds");

		return CreateViewRequest.builder(PickingConstants.WINDOWID_PickingView)
				.setFilterOnlyIds(RepoIdAwares.asRepoIds(shipmentScheduleIds))
				.setParameter(VIEWPARAM_ProductBarcodeFilterData, barcodeFilterData)
				.build();
	}

	private static Set<ShipmentScheduleId> extractShipmentScheduleIds(@NonNull final CreateViewRequest request)
	{
		return request.getFilterOnlyIds()
				.stream()
				.map(ShipmentScheduleId::ofRepoId)
				.collect(ImmutableSet.toImmutableSet());
	}

	private static Optional<ProductBarcodeFilterData> extractProductBarcodeFilterData(@NonNull final CreateViewRequest request)
	{
		return Optional.ofNullable(request.getParameterAs(VIEWPARAM_ProductBarcodeFilterData, ProductBarcodeFilterData.class));
	}

}
