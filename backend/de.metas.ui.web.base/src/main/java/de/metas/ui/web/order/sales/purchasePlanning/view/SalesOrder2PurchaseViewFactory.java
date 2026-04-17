package de.metas.ui.web.order.sales.purchasePlanning.view;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_Order;

import com.google.common.annotations.VisibleForTesting;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import de.metas.document.engine.DocStatus;
import de.metas.order.IOrderDAO;
import de.metas.order.OrderAndLineId;
import de.metas.order.OrderId;
import de.metas.order.OrderLineId;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.purchasecandidate.PurchaseCandidate;
import de.metas.purchasecandidate.PurchaseCandidateId;
import de.metas.purchasecandidate.PurchaseCandidateRepository;
import de.metas.purchasecandidate.PurchaseDemand;
import de.metas.purchasecandidate.PurchaseDemandWithCandidatesService;
import de.metas.purchasecandidate.SalesOrderLine;
import de.metas.purchasecandidate.SalesOrderLineRepository;
import de.metas.purchasecandidate.async.C_PurchaseCandidates_GeneratePurchaseOrders;
import de.metas.purchasecandidate.availability.AvailabilityCheckService;
import de.metas.quantity.Quantity;
import de.metas.ui.web.order.sales.purchasePlanning.process.WEBUI_SalesOrder_Apply_Availability_Row;
import de.metas.ui.web.order.sales.purchasePlanning.process.WEBUI_SalesOrder_PurchaseView_Launcher;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.NonNull;



@ViewFactory(windowId = SalesOrder2PurchaseViewFactory.WINDOW_ID_STRING)
public class SalesOrder2PurchaseViewFactory extends PurchaseViewFactoryTemplate
{
	public static final String WINDOW_ID_STRING = "SO2PO";
	public static final WindowDocumentTypeId WINDOW_ID = WindowDocumentTypeId.fromJson(WINDOW_ID_STRING);

	// services
	private final SalesOrderLineRepository salesOrderLineRepository;
	private final PurchaseCandidateRepository purchaseCandidatesRepo;

	public SalesOrder2PurchaseViewFactory(
			@NonNull final PurchaseDemandWithCandidatesService purchaseDemandWithCandidatesService,
			@NonNull final AvailabilityCheckService availabilityCheckService,
			@NonNull final PurchaseCandidateRepository purchaseCandidatesRepo,
			@NonNull final PurchaseRowFactory purchaseRowFactory,
			@NonNull final SalesOrderLineRepository salesOrderLineRepository)
	{
		super(WINDOW_ID,
				WEBUI_SalesOrder_PurchaseView_Launcher.class, // launcherProcessClass
				purchaseDemandWithCandidatesService,
				availabilityCheckService,
				purchaseRowFactory);

		this.salesOrderLineRepository = salesOrderLineRepository;
		this.purchaseCandidatesRepo = purchaseCandidatesRepo;
	}

	@Override
	protected List<RelatedProcessDescriptor> getAdditionalProcessDescriptors()
	{
		return ImmutableList.of(
				createProcessDescriptor(WEBUI_SalesOrder_Apply_Availability_Row.class));
	}

	@Override
	protected List<PurchaseDemand> getDemands(@NonNull final CreateViewRequest request)
	{
		final Set<OrderLineId> salesOrderLineIds = extractSalesOrderLineIds(request);
		Check.assumeNotEmpty(salesOrderLineIds, "salesOrderLineIds is not empty");

		return getDemands(salesOrderLineIds);
	}

	private List<PurchaseDemand> getDemands(final Collection<OrderLineId> salesOrderLineIds)
	{
		return salesOrderLineRepository.getByIds(salesOrderLineIds)
				.stream()
				.sorted(Comparator.comparing(SalesOrderLine::getLine))
				.map(salesOrderLine -> createDemand(salesOrderLine))
				.collect(ImmutableList.toImmutableList());
	}

	private static Set<OrderLineId> extractSalesOrderLineIds(final CreateViewRequest request)
	{
		return request.getFilterOnlyIds()
				.stream()
				.map(OrderLineId::ofRepoId)
				.collect(ImmutableSet.toImmutableSet());
	}

	@VisibleForTesting
	PurchaseDemand createDemand(final SalesOrderLine salesOrderLine)
	{
		final Quantity qtyOrdered = salesOrderLine.getOrderedQty();
		final Quantity qtyDelivered = salesOrderLine.getDeliveredQty();
		final Quantity qtyToPurchase = qtyOrdered.subtract(qtyDelivered);

		final OrderLineId ordeRLineId = salesOrderLine.getId().getOrderLineId();
		final List<PurchaseCandidateId> existingPurchaseCandidates = purchaseCandidatesRepo.getAllIdsBySalesOrderLineId(ordeRLineId);

		return PurchaseDemand.builder()
				.existingPurchaseCandidateIds(existingPurchaseCandidates)
				//
				.orgId(salesOrderLine.getOrgId())
				.warehouseId(salesOrderLine.getWarehouseId())
				//
				.productId(salesOrderLine.getProductId())
				.attributeSetInstanceId(salesOrderLine.getAsiId())
				//
				.qtyToDeliver(qtyToPurchase)
				//
				.currencyIdOrNull(salesOrderLine.getPriceActual().getCurrencyId())
				//
				.salesPreparationDate(salesOrderLine.getPreparationDate())
				//
				.salesOrderAndLineIdOrNull(salesOrderLine.getId())
				//
				.build();
	}

	@Override
	protected void onViewClosedByUser(final PurchaseView purchaseView)
	{
		saveRowsAndEnqueueIfOrderCompleted(purchaseView);
	}

	private void saveRowsAndEnqueueIfOrderCompleted(final PurchaseView purchaseView)
	{
		final List<PurchaseCandidate> purchaseCandidates = saveRows(purchaseView);
		if (purchaseCandidates.isEmpty())
		{
			return;
		}
		//
		// If the sales order was already completed, enqueue the purchase candidates
		if (isSalesOrderCompleted(purchaseCandidates))
		{
			final Set<PurchaseCandidateId> purchaseCandidateIds = purchaseCandidates.stream()
					.filter(purchaseCandidate -> !purchaseCandidate.isProcessedOrLocked())
					.filter(purchaseCandidate -> purchaseCandidate.getQtyToPurchase().signum() > 0)
					.map(PurchaseCandidate::getId)
					// note: no need to filter out nulls because we assume everything was saved
					.collect(ImmutableSet.toImmutableSet());
			if (!purchaseCandidateIds.isEmpty())
			{
				C_PurchaseCandidates_GeneratePurchaseOrders.enqueue(purchaseCandidateIds);
			}
		}
	}

	private List<PurchaseCandidate> saveRows(@NonNull final PurchaseView purchaseView)
	{
		final List<PurchaseRow> rows = purchaseView.getRows();

		return PurchaseRowsSaver.builder()
				.purchaseCandidatesRepo(purchaseCandidatesRepo)
				.build()
				.save(rows);
	}

	private boolean isSalesOrderCompleted(@NonNull final List<PurchaseCandidate> purchaseCandidates)
	{
		final IOrderDAO ordersRepo = Services.get(IOrderDAO.class);

		final OrderId salesOrderId = getSingleSalesOrderId(purchaseCandidates);
		final I_C_Order salesOrder = ordersRepo.getById(salesOrderId);
		final DocStatus docStatus = DocStatus.ofCode(salesOrder.getDocStatus());
		return docStatus.isCompleted();
	}

	private static final OrderId getSingleSalesOrderId(@NonNull final List<PurchaseCandidate> purchaseCandidates)
	{
		Check.assumeNotEmpty(purchaseCandidates, "purchaseCandidates not empty");

		return purchaseCandidates.stream()
				.map(PurchaseCandidate::getSalesOrderAndLineIdOrNull)
				.filter(Objects::nonNull)
				.map(OrderAndLineId::getOrderId)
				.distinct()
				.collect(GuavaCollectors.singleElementOrThrow(() -> new AdempiereException("More or less than one salesOrderId found in the given purchaseCandidates")
						.appendParametersToMessage()
						.setParameter("purchaseCandidates", purchaseCandidates)));
	}
}
