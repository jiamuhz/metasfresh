package de.metas.ui.web.material.cockpit.rowfactory;

import de.metas.material.cockpit.QtyDemandQtySupply;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.product.IProductBL;
import de.metas.product.ResourceId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.material.cockpit.MaterialCockpitRow;
import de.metas.uom.IUOMDAO;
import de.metas.util.Services;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.compiere.model.I_C_UOM;
import org.compiere.util.TimeUtil;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static de.metas.quantity.Quantity.addToNullable;
import static de.metas.util.Check.assumeNotNull;



/**
 * Mutable row representation that is used during the rows' loading
 *
 *
 *
 */
@EqualsAndHashCode(of = "plantId")
@ToString
public class CountingSubRowBucket
{
	@Getter(AccessLevel.NONE)
	private final transient IProductBL productBL = Services.get(IProductBL.class);
	@Getter(AccessLevel.NONE)
	private final transient IUOMDAO uomDAO = Services.get(IUOMDAO.class);

	public static CountingSubRowBucket create(final int plantId)
	{
		return new CountingSubRowBucket(plantId);
	}

	private final ResourceId plantId;

	// Zaehlbestand
	private Quantity qtyStockEstimateCountAtDate;

	@Nullable
	private Instant qtyStockEstimateTimeAtDate;

	private Quantity qtyInventoryCountAtDate;

	@Nullable
	private Instant qtyInventoryTimeAtDate;

	private Quantity qtyStockCurrentAtDate;

	private Quantity qtyOnHandStock;
	
	private Quantity qtySupplyPurchaseOrder;
	
	private Quantity qtyDemandSalesOrder;

	private final Set<Integer> cockpitRecordIds = new HashSet<>();

	private final Set<Integer> stockRecordIds = new HashSet<>();

	public CountingSubRowBucket(final int plantIdInt)
	{
		this.plantId = ResourceId.ofRepoIdOrNull(plantIdInt);
	}

	public void addCockpitRecord(@NonNull final I_MD_Cockpit cockpitRecord)
	{
		final I_C_UOM uom = productBL.getStockUOM(cockpitRecord.getM_Product_ID());

		qtyStockEstimateCountAtDate = addToNullable(qtyStockEstimateCountAtDate, cockpitRecord.getQtyStockEstimateCount_AtDate(), uom);
		qtyStockEstimateTimeAtDate = TimeUtil.max(qtyStockEstimateTimeAtDate, TimeUtil.asInstant(cockpitRecord.getQtyStockEstimateTime_AtDate()));

		qtyInventoryCountAtDate = addToNullable(qtyInventoryCountAtDate, cockpitRecord.getQtyInventoryCount_AtDate(), uom);
		qtyInventoryTimeAtDate = TimeUtil.max(qtyInventoryTimeAtDate, TimeUtil.asInstant(cockpitRecord.getQtyInventoryTime_AtDate()));

		qtyStockCurrentAtDate = addToNullable(qtyStockCurrentAtDate, cockpitRecord.getQtyStockCurrent_AtDate(), uom);

		cockpitRecordIds.add(cockpitRecord.getMD_Cockpit_ID());
	}

	public void addStockRecord(@NonNull final I_MD_Stock stockRecord)
	{
		final I_C_UOM uom = productBL.getStockUOM(stockRecord.getM_Product_ID());

		qtyOnHandStock = addToNullable(qtyOnHandStock, stockRecord.getQtyOnHand(), uom);

		stockRecordIds.add(stockRecord.getMD_Stock_ID());
	}

	public void addQuantitiesRecord(@NonNull final QtyDemandQtySupply quantitiesRecord)
	{
		final I_C_UOM uom = uomDAO.getById(quantitiesRecord.getUomId());

		qtyDemandSalesOrder = addToNullable(qtyDemandSalesOrder, quantitiesRecord.getQtyReserved(), uom);
		qtySupplyPurchaseOrder = addToNullable(qtySupplyPurchaseOrder, quantitiesRecord.getQtyToMove(), uom);
	}

	@NonNull
	public MaterialCockpitRow createIncludedRow(@NonNull final MainRowWithSubRows mainRowBucket)
	{
		final MainRowBucketId productIdAndDate = assumeNotNull(
				mainRowBucket.getProductIdAndDate(),
				"productIdAndDate may not be null; mainRowBucket={}", mainRowBucket);

		return MaterialCockpitRow.countingSubRowBuilder()
				.date(productIdAndDate.getDate())
				.productId(productIdAndDate.getProductId().getRepoId())
				.plantId(plantId)
				.qtyDemandSalesOrder(qtyDemandSalesOrder)
				.qtySupplyPurchaseOrder(qtySupplyPurchaseOrder)
				.qtyStockEstimateCountAtDate(qtyStockEstimateCountAtDate)
				.qtyStockEstimateTimeAtDate(qtyStockEstimateTimeAtDate)
				.qtyInventoryCountAtDate(qtyInventoryCountAtDate)
				.qtyInventoryTimeAtDate(qtyInventoryTimeAtDate)
				.qtyStockCurrentAtDate(qtyStockCurrentAtDate)
				.qtyOnHandStock(qtyOnHandStock)
				.allIncludedCockpitRecordIds(cockpitRecordIds)
				.allIncludedStockRecordIds(stockRecordIds)
				.build();
	}
}
