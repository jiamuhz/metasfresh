package de.metas.purchasecandidate.material.event;

import de.metas.bpartner.service.impl.BPartnerBL;
import de.metas.common.util.time.SystemTime;
import de.metas.material.event.commons.EventDescriptor;
import de.metas.material.event.commons.MaterialDescriptor;
import de.metas.material.event.commons.ProductDescriptor;
import de.metas.material.event.commons.SupplyRequiredDescriptor;
import de.metas.material.event.purchase.PurchaseCandidateAdvisedEvent;
import de.metas.material.planning.IMaterialPlanningContext;
import de.metas.material.planning.impl.MaterialPlanningContext;
import de.metas.organization.IOrgDAO;
import de.metas.pricing.conditions.BreakValueType;
import de.metas.purchasecandidate.VendorProductInfoService;
import de.metas.user.UserRepository;
import de.metas.util.Services;
import org.adempiere.test.AdempiereTestHelper;
import org.adempiere.warehouse.WarehouseId;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_M_DiscountSchema;
import org.compiere.model.I_M_Product;
import org.compiere.model.X_M_DiscountSchema;
import org.eevolution.model.I_PP_Product_Planning;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.adempiere.model.InterfaceWrapperHelper.newInstance;
import static org.adempiere.model.InterfaceWrapperHelper.save;
import static org.assertj.core.api.Assertions.*;
import static org.eevolution.model.X_PP_Order_Candidate.ISLOTFORLOT_No;
import static org.eevolution.model.X_PP_Order_Candidate.ISLOTFORLOT_Yes;

 
public class PurchaseCandidateAdvisedEventCreatorTest
{
	private I_PP_Product_Planning productPlanningRecord;
	private IOrgDAO orgDAO;

	@Before
	public void init()
	{
		AdempiereTestHelper.get().init();

		productPlanningRecord = newInstance(I_PP_Product_Planning.class);
		productPlanningRecord.setIsPurchased("Y");
		save(productPlanningRecord);

		orgDAO = Mockito.mock(IOrgDAO.class);
		Services.registerService(IOrgDAO.class, orgDAO);
	}

	@Test
	public void createPurchaseAdvisedEvent()
	{
		final I_M_DiscountSchema discountSchemaRecord = newInstance(I_M_DiscountSchema.class);
		discountSchemaRecord.setDiscountType(X_M_DiscountSchema.DISCOUNTTYPE_Breaks);
		discountSchemaRecord.setBreakValueType(BreakValueType.QUANTITY.getCode());
		save(discountSchemaRecord);

		final I_C_BPartner bPartnerVendorRecord = newInstance(I_C_BPartner.class);
		bPartnerVendorRecord.setPO_DiscountSchema(discountSchemaRecord); // note that right now we don't need to have an actual price
		save(bPartnerVendorRecord);

		Mockito.when(orgDAO.getTimeZone(Mockito.any()))
				.thenReturn(SystemTime.zoneId());

		SupplyRequiredDescriptor supplyRequiredDescriptor = SupplyRequiredDescriptor.builder()
				.eventDescriptor(EventDescriptor.ofClientAndOrg(10, 20))
				.materialDescriptor(createMaterialDescriptor())
				.demandCandidateId(50)
				.fullDemandQty(BigDecimal.TEN)
				.build();

		final IMaterialPlanningContext mrpContext = new MaterialPlanningContext();
		mrpContext.setProductPlanning(productPlanningRecord);

		final PurchaseCandidateAdvisedEventCreator purchaseCandidateAdvisedEventCreator = new PurchaseCandidateAdvisedEventCreator(
				new PurchaseOrderDemandMatcher(),
				new VendorProductInfoService(new BPartnerBL(new UserRepository())));

		// invoke the method under test
		final Optional<PurchaseCandidateAdvisedEvent> purchaseAdvisedEvent = purchaseCandidateAdvisedEventCreator
				.createPurchaseAdvisedEvent(
						supplyRequiredDescriptor,
						mrpContext);

		supplyRequiredDescriptor = supplyRequiredDescriptor.toBuilder().isLotForLot(ISLOTFORLOT_No).build();

		assertThat(purchaseAdvisedEvent).isPresent();
		assertThat(purchaseAdvisedEvent.get().getProductPlanningId()).isEqualTo(productPlanningRecord.getPP_Product_Planning_ID());
		assertThat(purchaseAdvisedEvent.get().getVendorId()).isEqualTo(bPartnerVendorRecord.getC_BPartner_ID());
		assertThat(purchaseAdvisedEvent.get().getSupplyRequiredDescriptor()).isEqualTo(supplyRequiredDescriptor);

		productPlanningRecord.setIsLotForLot(true);
		save(productPlanningRecord);
		supplyRequiredDescriptor = supplyRequiredDescriptor.toBuilder().isLotForLot(null).build();

		// invoke the method under test
		final Optional<PurchaseCandidateAdvisedEvent> purchaseAdvisedEvent2 = purchaseCandidateAdvisedEventCreator
				.createPurchaseAdvisedEvent(
						supplyRequiredDescriptor,
						mrpContext);

		supplyRequiredDescriptor = supplyRequiredDescriptor.toBuilder()
				.isLotForLot(ISLOTFORLOT_Yes)
				.materialDescriptor(supplyRequiredDescriptor.getMaterialDescriptor().withQuantity(new BigDecimal("10")))
				.build();

		assertThat(purchaseAdvisedEvent2).isPresent();
		assertThat(purchaseAdvisedEvent2.get().getProductPlanningId()).isEqualTo(productPlanningRecord.getPP_Product_Planning_ID());
		assertThat(purchaseAdvisedEvent2.get().getVendorId()).isEqualTo(bPartnerVendorRecord.getC_BPartner_ID());
		assertThat(purchaseAdvisedEvent2.get().getSupplyRequiredDescriptor()).isEqualTo(supplyRequiredDescriptor);
	}

	static MaterialDescriptor createMaterialDescriptor()
	{
		final I_M_Product product = newInstance(I_M_Product.class);
		product.setM_Product_Category_ID(60);
		product.setValue("Value");
		product.setName("Name");
		save(product);

		return MaterialDescriptor.builder()
				.productDescriptor(ProductDescriptor.completeForProductIdAndEmptyAttribute(product.getM_Product_ID()))
				.warehouseId(WarehouseId.ofRepoId(40))
				.quantity(BigDecimal.ONE)
				.date(SystemTime.asInstant())
				.build();
	}
}
