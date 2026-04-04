

package de.metas.ui.web.pickingV2.productsToPick.rows.factory;

import de.metas.i18n.ITranslatableString;
import de.metas.product.IProductDAO;
import de.metas.product.ProductId;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductInfo;
import de.metas.uom.IUOMDAO;
import de.metas.uom.UomId;
import lombok.Builder;
import lombok.NonNull;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import org.compiere.util.Env;

import java.util.HashMap;
import java.util.Map;

final class ProductInfoSupplier
{
	private final IProductDAO productsRepo;
	private final IUOMDAO uomsRepo;

	private final Map<ProductId, ProductInfo> productInfos = new HashMap<>();

	@Builder
	private ProductInfoSupplier(final IProductDAO productsRepo, final IUOMDAO uomsRepo)
	{
		this.productsRepo = productsRepo;
		this.uomsRepo = uomsRepo;
	}

	public ProductInfo getByProductId(@NonNull final ProductId productId)
	{
		return productInfos.computeIfAbsent(productId, this::retrieveProductInfo);
	}

	private ProductInfo retrieveProductInfo(@NonNull final ProductId productId)
	{
		final I_M_Product productRecord = productsRepo.getById(productId);

		final UomId packageUOMId = UomId.ofRepoIdOrNull(productRecord.getPackage_UOM_ID());
		final String packageSizeUOM;
		if (packageUOMId != null)
		{
			final I_C_UOM packageUOM = uomsRepo.getById(packageUOMId);
			packageSizeUOM = packageUOM.getUOMSymbol();
		}
		else
		{
			packageSizeUOM = null;
		}

		final String stockUOM = uomsRepo.getName(UomId.ofRepoId(productRecord.getC_UOM_ID())).translate(Env.getAD_Language());

		final ITranslatableString productName = InterfaceWrapperHelper.getModelTranslationMap(productRecord)
				.getColumnTrl(I_M_Product.COLUMNNAME_Name, productRecord.getName());

		return ProductInfo.builder()
				.productId(productId)
				.code(productRecord.getValue())
				.name(productName)
				.packageSize(productRecord.getPackageSize())
				.packageSizeUOM(packageSizeUOM)
				.stockUOM(stockUOM)
				.build();
	}
}
