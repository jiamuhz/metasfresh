package de.metas.currency;

/** */

import de.metas.money.CurrencyConversionTypeId;
import de.metas.money.CurrencyId;
import de.metas.money.Money;
import de.metas.organization.OrgId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.service.ClientId;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * The result of a currency conversion.
 *
 *
 */
@Value
@Builder
public class CurrencyConversionResult
{
	@NonNull BigDecimal amount;
	@NonNull CurrencyId currencyId;

	@NonNull BigDecimal sourceAmount;
	@NonNull CurrencyId sourceCurrencyId;

	// NOTE: it might be null when sourceAmount is ZERO and API decided to not fetch the conversionRate because it's pointless
	@Nullable BigDecimal conversionRateOrNull;

	@NonNull Instant conversionDate;
	@NonNull CurrencyConversionTypeId conversionTypeId;
	@NonNull ClientId clientId;
	@NonNull OrgId orgId;

	public Money getAmountAsMoney()
	{
		return Money.of(amount, currencyId);
	}
}
