package de.metas.currency;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.adempiere.exceptions.AdempiereException;

import com.google.common.collect.ImmutableMap;

import de.metas.money.CurrencyId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import javax.annotation.Nullable;

@ToString
@EqualsAndHashCode
final class FixedConversionRateMap
{
	public static final FixedConversionRateMap EMPTY = new FixedConversionRateMap(ImmutableMap.of());

	private final ImmutableMap<FixedConversionRateKey, FixedConversionRate> rates;

	private FixedConversionRateMap(final Map<FixedConversionRateMap.FixedConversionRateKey, FixedConversionRate> rates)
	{
		this.rates = ImmutableMap.copyOf(rates);
	}

	public FixedConversionRateMap addingConversionRate(@NonNull final FixedConversionRate rate)
	{
		final HashMap<FixedConversionRateMap.FixedConversionRateKey, FixedConversionRate> newRates = new HashMap<>(rates);
		newRates.put(extractKey(rate), rate);
		return new FixedConversionRateMap(newRates);
	}

	private static FixedConversionRateMap.FixedConversionRateKey extractKey(@NonNull final FixedConversionRate rate)
	{
		return FixedConversionRateKey.builder()
				.fromCurrencyId(rate.getFromCurrencyId())
				.toCurrencyId(rate.getToCurrencyId())
				.build();
	}

	public BigDecimal getMultiplyRate(
			@NonNull final CurrencyId fromCurrencyId,
			@NonNull final CurrencyId toCurrencyId)
	{
		final BigDecimal multiplyRate = getMultiplyRateOrNull(fromCurrencyId, toCurrencyId);
		if (multiplyRate == null)
		{
			throw new AdempiereException("No fixed conversion rate found from " + fromCurrencyId + " to " + toCurrencyId + "."
					+ " Available rates are: " + rates.values());
		}
		return multiplyRate;
	}

	public boolean hasMultiplyRate(
			@NonNull final CurrencyId fromCurrencyId,
			@NonNull final CurrencyId toCurrencyId)
	{
		return getMultiplyRateOrNull(fromCurrencyId, toCurrencyId) != null;
	}

	@Nullable
	private BigDecimal getMultiplyRateOrNull(
			@NonNull final CurrencyId fromCurrencyId,
			@NonNull final CurrencyId toCurrencyId)
	{
		final FixedConversionRate rate = rates.get(FixedConversionRateKey.builder()
				.fromCurrencyId(fromCurrencyId)
				.toCurrencyId(toCurrencyId)
				.build());
		return rate != null ? rate.getMultiplyRate() : null;
	}

	@Value
	@Builder
	private static class FixedConversionRateKey
	{
		@NonNull
		CurrencyId fromCurrencyId;

		@NonNull
		CurrencyId toCurrencyId;
	}

}
