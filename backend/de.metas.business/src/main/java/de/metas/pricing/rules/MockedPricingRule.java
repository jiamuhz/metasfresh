package de.metas.pricing.rules;

/** */

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import org.compiere.util.Env;

import de.metas.currency.CurrencyPrecision;
import de.metas.pricing.IPricingContext;
import de.metas.pricing.IPricingResult;
import de.metas.product.ProductId;
import de.metas.tax.api.TaxCategoryId;
import de.metas.uom.UomId;
import lombok.ToString;

/**
 * Mocked {@link IPricingRule} implementation to be used in testing.
 *
 * Make sure you have called {@link #reset()} before starting to use it.
 *
 * @author tsa
 *
 */
@ToString
public class MockedPricingRule implements IPricingRule
{
	public static final MockedPricingRule INSTANCE = new MockedPricingRule();

	public final BigDecimal priceToReturnInitial = Env.ONEHUNDRED;

	/** Default price to return */
	public BigDecimal priceToReturn = priceToReturnInitial;

	private CurrencyPrecision precision;

	private final Map<ProductId, I_C_UOM> productId2priceUOM = new HashMap<>();

	/** M_Product_ID to "price" to return" */
	private final Map<ProductId, BigDecimal> productId2price = new HashMap<>();

	/**
	 * Reset it to inital state.
	 */
	public void reset()
	{
		priceToReturn = priceToReturnInitial;
		productId2price.clear();
	}

	public void setC_UOM(final I_M_Product product, final I_C_UOM uom)
	{
		productId2priceUOM.put(ProductId.ofRepoId(product.getM_Product_ID()), uom);
	}

	public void setPrecision(CurrencyPrecision precision)
	{
		this.precision = precision;
	}

	public void setProductPrice(final I_M_Product product, final BigDecimal price)
	{
		productId2price.put(ProductId.ofRepoId(product.getM_Product_ID()), price);
	}

	@Override
	public boolean applies(IPricingContext pricingCtx, IPricingResult result)
	{
		return true;
	}

	@Override
	public void calculate(IPricingContext pricingCtx, IPricingResult result)
	{
		//
		// Check product price
		final ProductId productId = pricingCtx.getProductId();
		BigDecimal price = productId2price.get(productId);
		if (price == null)
		{
			price = priceToReturn;
		}

		result.setPriceLimit(price);
		result.setPriceList(price);
		result.setPriceStd(price);

		result.setPrecision(precision);

		result.setTaxCategoryId(TaxCategoryId.ofRepoId(100));

		final I_C_UOM priceUOM = productId2priceUOM.get(productId);
		if (priceUOM != null)
		{
			result.setPriceUomId(UomId.ofRepoId(priceUOM.getC_UOM_ID()));
		}

		result.setCalculated(true);
	}
}
