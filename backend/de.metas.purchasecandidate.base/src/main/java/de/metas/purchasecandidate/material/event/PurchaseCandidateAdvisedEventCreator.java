package de.metas.purchasecandidate.material.event;

import de.metas.material.event.commons.EventDescriptor;
import de.metas.material.event.commons.MaterialDescriptor;
import de.metas.material.event.commons.SupplyRequiredDescriptor;
import de.metas.material.event.purchase.PurchaseCandidateAdvisedEvent;
import de.metas.material.planning.IMaterialPlanningContext;
import de.metas.material.planning.event.SupplyRequiredHandlerUtils;
import de.metas.organization.OrgId;
import de.metas.product.ProductId;
import de.metas.purchasecandidate.VendorProductInfo;
import de.metas.purchasecandidate.VendorProductInfoService;
import de.metas.util.Loggables;
import lombok.NonNull;
import org.eevolution.model.I_PP_Product_Planning;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static org.eevolution.model.X_PP_Order_Candidate.ISLOTFORLOT_No;
import static org.eevolution.model.X_PP_Order_Candidate.ISLOTFORLOT_Yes;

 
@Service
public class PurchaseCandidateAdvisedEventCreator
{
	private final PurchaseOrderDemandMatcher purchaseOrderDemandMatcher;
	private final VendorProductInfoService vendorProductInfoService;

	public PurchaseCandidateAdvisedEventCreator(
			@NonNull final PurchaseOrderDemandMatcher purchaseOrderDemandMatcher,
			@NonNull final VendorProductInfoService vendorProductInfoService)
	{
		this.vendorProductInfoService = vendorProductInfoService;
		this.purchaseOrderDemandMatcher = purchaseOrderDemandMatcher;
	}

	public Optional<PurchaseCandidateAdvisedEvent> createPurchaseAdvisedEvent(
			@NonNull final SupplyRequiredDescriptor supplyRequiredDescriptor,
			@NonNull final IMaterialPlanningContext mrpContext)
	{
		if (!purchaseOrderDemandMatcher.matches(mrpContext))
		{
			return Optional.empty();
		}

		// temporary workaround to avoid creating 2 PurchaseCandidates, one for each PPOrderCandidate and PPOrder
		final I_PP_Product_Planning productPlanning = mrpContext.getProductPlanning();
		final BigDecimal requiredQty = supplyRequiredDescriptor.getMaterialDescriptor().getQuantity();
		if(!productPlanning.isLotForLot() && requiredQty.signum() <= 0)
		{
			Loggables.addLog("Didn't create PurchaseCandidateAdvisedEvent because LotForLot=false and requiredQty={}", requiredQty);
			return Optional.empty();
		}

		final I_PP_Product_Planning ppOrderProductPlanning = mrpContext.getPpOrderProductPlanning();
		if(productPlanning.isLotForLot()
				&& supplyRequiredDescriptor.getPpOrderLineCandidateId() > 0
				&& ppOrderProductPlanning != null
				&& ppOrderProductPlanning.isCreatePlan())
		{
			Loggables.addLog("Didn't create PurchaseCandidateAdvisedEvent because it should be created by PPOrder");
			return Optional.empty();
		}
		if(!productPlanning.isLotForLot()
				&& supplyRequiredDescriptor.getPpOrderId() > 0
				&& ppOrderProductPlanning != null
				&& !ppOrderProductPlanning.isCreatePlan())
		{
			Loggables.addLog("Didn't create PurchaseCandidateAdvisedEvent because it should already be created by PPOrderCandidate");
			return Optional.empty();
		}

		final ProductId productId = ProductId.ofRepoId(supplyRequiredDescriptor.getMaterialDescriptor().getProductId());
		final OrgId orgId = supplyRequiredDescriptor.getEventDescriptor().getOrgId();

		final Optional<VendorProductInfo> defaultVendorProductInfo = vendorProductInfoService.getDefaultVendorProductInfo(productId, orgId);
		if (defaultVendorProductInfo.isEmpty())
		{
			Loggables.addLog("Found no VendorProductInfo for productId={} and orgId={}", productId.getRepoId(), orgId.getRepoId());
			return Optional.empty();
		}

		final PurchaseCandidateAdvisedEvent.PurchaseCandidateAdvisedEventBuilder eventBuilder = PurchaseCandidateAdvisedEvent.builder()
				.eventDescriptor(EventDescriptor.ofEventDescriptor(supplyRequiredDescriptor.getEventDescriptor()))
				.directlyCreatePurchaseCandidate(productPlanning.isCreatePlan())
				.productPlanningId(productPlanning.getPP_Product_Planning_ID())
				.vendorId(defaultVendorProductInfo.get().getVendorId().getRepoId());

		if(productPlanning.isLotForLot())
		{
			final BigDecimal usedQty;
			if(!supplyRequiredDescriptor.isUpdated())
			{
				usedQty = supplyRequiredDescriptor.getFullDemandQty();
				Loggables.addLog("Using fullDemandQty={}, because of LotForLot=true and updated=false", usedQty);
			}
			// we don't reduce Quantity of PurchaseCandidates atm
			else if(supplyRequiredDescriptor.getDeltaQuantity().signum() > 0)
			{
				usedQty = supplyRequiredDescriptor.getDeltaQuantity();
				Loggables.addLog("Using deltaQty={}, because of LotForLot=true and updated=true", usedQty);
			}
			else
			{
				Loggables.addLog("Didn't create PurchaseCandidateAdvisedEvent because LotForLot=true and updated=true, but deltaQty={}", supplyRequiredDescriptor.getDeltaQuantity());
				return Optional.empty();
			}
			final MaterialDescriptor updatedMaterialDescriptor = supplyRequiredDescriptor.getMaterialDescriptor().withQuantity(usedQty);
			eventBuilder.supplyRequiredDescriptor(supplyRequiredDescriptor.toBuilder()
												   .materialDescriptor(updatedMaterialDescriptor)
												   .isLotForLot(ISLOTFORLOT_Yes)
												   .build());
		}
		else
		{
			eventBuilder.supplyRequiredDescriptor(supplyRequiredDescriptor.toBuilder().isLotForLot(ISLOTFORLOT_No).build());
		}

		final PurchaseCandidateAdvisedEvent event = eventBuilder.build();
		final BigDecimal finalQtyUsed = event.getSupplyRequiredDescriptor().getMaterialDescriptor().getQuantity();
		if(requiredQty.compareTo(finalQtyUsed) != 0)
		{
			final BigDecimal deltaToApply = finalQtyUsed.subtract(requiredQty);
			SupplyRequiredHandlerUtils.updateMainDataWithQty(supplyRequiredDescriptor, deltaToApply);
		}


		Loggables.addLog("Created PurchaseCandidateAdvisedEvent");
		return Optional.of(event);
	}
}
