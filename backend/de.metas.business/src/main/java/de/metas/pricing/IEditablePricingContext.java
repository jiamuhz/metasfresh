package de.metas.pricing;

/** */

import de.metas.bpartner.BPartnerId;
import de.metas.order.SOTrx;
import de.metas.location.CountryId;
import de.metas.money.CurrencyId;
import de.metas.organization.OrgId;
import de.metas.pricing.conditions.PricingConditionsBreak;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.uom.UomId;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A {@link IPricingContext} which also have setters.
 *
 * This object is used for creating the pricing context.
 */
@SuppressWarnings("UnusedReturnValue")
public interface IEditablePricingContext extends IPricingContext
{
	IEditablePricingContext setOrgId(OrgId orgId);

	/**
	 * Set's this context's referenced object to the given {@code referencedObject}, and set's this context's trxName to be the given referencedObject's trxName.
	 */
	IEditablePricingContext setReferencedObject(Object referencedObject);

	IEditablePricingContext setSOTrx(SOTrx soTrx);

	IEditablePricingContext setQty(BigDecimal qty);

	IEditablePricingContext setUomId(@Nullable UomId uomId);

	default IEditablePricingContext setQty(@NonNull final Quantity qty)
	{
		setQty(qty.toBigDecimal());
		setUomId(qty.getUomId());
		return this;
	}

	IEditablePricingContext setBPartnerId(BPartnerId bpartnerId);

	IEditablePricingContext setCurrencyId(@Nullable CurrencyId currencyId);

	IEditablePricingContext setPriceDate(LocalDate priceDate);

	IEditablePricingContext setPricingSystemId(PricingSystemId pricingSystemId);

	IEditablePricingContext setPriceListId(@Nullable PriceListId priceListId);

	IEditablePricingContext setPriceListVersionId(@Nullable PriceListVersionId priceListVersionId);

	IEditablePricingContext setProductId(ProductId productId);

	/**
	 * Set this to <code>true</code> to indicate to the pricing engine that discounts shall <b>not</b> be computed and applied to the result.
	 */
	IEditablePricingContext setDisallowDiscount(boolean disallowDiscount);

	IEditablePricingContext setForcePricingConditionsBreak(PricingConditionsBreak forcePricingConditionsBreak);

	IEditablePricingContext setTrxName(String trxName);

	/**
	 * @see IPricingContext#isConvertPriceToContextUOM()
	 */
	IEditablePricingContext setConvertPriceToContextUOM(boolean convertPriceToContextUOM);

	IEditablePricingContext setProperty(String propertyName, Object value);

	default IEditablePricingContext setProperty(final String propertyName)
	{
		return setProperty(propertyName, Boolean.TRUE);
	}

	IEditablePricingContext setManualPriceEnabled(boolean manualPriceEnabled);

	/**
	 * Note that either countryId or priceListId need to be provided.
	 */
	IEditablePricingContext setCountryId(@Nullable CountryId countryId);

	IEditablePricingContext setFailIfNotCalculated();

	IEditablePricingContext setSkipCheckingPriceListSOTrxFlag(boolean skipCheckingPriceListSOTrxFlag);

	IEditablePricingContext setManualPrice(@Nullable final BigDecimal manualPrice);
}
