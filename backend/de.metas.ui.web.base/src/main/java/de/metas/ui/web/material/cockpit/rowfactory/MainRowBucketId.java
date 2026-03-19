package de.metas.ui.web.material.cockpit.rowfactory;

import de.metas.material.cockpit.QtyDemandQtySupply;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.product.ProductId;
import lombok.NonNull;
import lombok.Value;
import org.compiere.util.TimeUtil;

import java.math.BigDecimal;
import java.time.LocalDate;

 

@Value
public class MainRowBucketId
{
	public static MainRowBucketId createInstanceForCockpitRecord(
			@NonNull final I_MD_Cockpit dataRecord)
	{
		return new MainRowBucketId(
				ProductId.ofRepoId(dataRecord.getM_Product_ID()),
				TimeUtil.asLocalDate(dataRecord.getDateGeneral()));
	}

	public static MainRowBucketId createInstanceForStockRecord(
			@NonNull final I_MD_Stock stockRecord,
			@NonNull final LocalDate date)
	{
		return new MainRowBucketId(
				ProductId.ofRepoId(stockRecord.getM_Product_ID()),
				date);
	}

	@NonNull
	public static MainRowBucketId createInstanceForQuantitiesRecord(
			@NonNull final QtyDemandQtySupply qtyRecord,
			@NonNull final LocalDate date)
	{
		return new MainRowBucketId(qtyRecord.getProductId(), date);
	}

	public static MainRowBucketId createPlainInstance(@NonNull final ProductId productId, @NonNull final LocalDate date)
	{
		return new MainRowBucketId(productId, date);
	}

	ProductId productId;
	LocalDate date;
	BigDecimal pmmQtyPromised = BigDecimal.ZERO;
	BigDecimal qtyReserved = BigDecimal.ZERO;
	BigDecimal qtyOrdered = BigDecimal.ZERO;
	BigDecimal qtyMaterialentnahme = BigDecimal.ZERO;
	BigDecimal qtyMrp = BigDecimal.ZERO;
	BigDecimal qtyPromised = BigDecimal.ZERO;
	BigDecimal qtyOnHand = BigDecimal.ZERO;

	private MainRowBucketId(
			@NonNull final ProductId productId,
			@NonNull final LocalDate date)
	{
		this.productId = productId;
		this.date = date;
	}

}
