/**
 *
 */
package de.metas.currency;

import de.metas.money.CurrencyConversionTypeId;
import de.metas.money.CurrencyId;
import de.metas.organization.OrgId;
import de.metas.util.ISingletonService;
import lombok.NonNull;
import org.adempiere.service.ClientId;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Instant;

/** */

/**
 *
 */
public interface ICurrencyDAO extends ISingletonService
{
	CurrencyPrecision DEFAULT_PRECISION = CurrencyPrecision.TWO;

	Currency getById(CurrencyId currencyId);

	/**
	 * retrieves currency by ISO code
	 *
	 * @return currency or <code>null</code>
	 */
	Currency getByCurrencyCode(CurrencyCode currencyCode);

	CurrencyCode getCurrencyCodeById(CurrencyId currencyId);

	CurrencyPrecision getStdPrecision(CurrencyId currencyId);

	CurrencyPrecision getCostingPrecision(CurrencyId currencyId);

	@NonNull CurrencyConversionTypeId getDefaultConversionTypeId(ClientId adClientId, OrgId adOrgId, Instant date);

	CurrencyConversionTypeId getConversionTypeId(@NonNull ConversionTypeMethod type);

	@NonNull ConversionTypeMethod getConversionTypeMethodById(@NonNull CurrencyConversionTypeId id);

	@Nullable
	BigDecimal retrieveRateOrNull(CurrencyConversionContext conversionCtx, CurrencyId currencyFromId, CurrencyId currencyToId);
}
