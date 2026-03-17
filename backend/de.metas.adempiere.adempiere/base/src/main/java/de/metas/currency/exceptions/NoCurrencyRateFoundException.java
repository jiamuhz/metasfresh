package de.metas.currency.exceptions;

/** */

import de.metas.currency.ConversionTypeMethod;
import de.metas.currency.CurrencyCode;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;
import java.time.temporal.Temporal;

/**
 * Exception thrown when there was no currency rate found.
 *
 *
 */
public class NoCurrencyRateFoundException extends AdempiereException
{
	private static final AdMessageKey MSG = AdMessageKey.of("NoCurrencyConversion");

	public NoCurrencyRateFoundException(
			@NonNull final CurrencyCode currencyFrom,
			@NonNull final CurrencyCode currencyTo,
			@Nullable final Temporal conversionDate,
			@Nullable final ConversionTypeMethod conversionTypeMethod)
	{
		super(buildMsg(currencyFrom, currencyTo, conversionDate, conversionTypeMethod));
	}

	private static ITranslatableString buildMsg(
			@NonNull final CurrencyCode currencyFrom,
			@NonNull final CurrencyCode currencyTo,
			@Nullable final Temporal conversionDate,
			@Nullable final ConversionTypeMethod conversionTypeMethod)
	{
		return TranslatableStrings.builder()
				.appendADMessage(MSG).append(" ")
				.appendObj(currencyFrom).appendObj("->").appendObj(currencyTo)
				.append(", ").appendADElement("ConversionDate").append(": ").appendTemporal(conversionDate, "?")
				.append(", ").appendADElement("C_ConversionType_ID").append(": ").append(conversionTypeMethod != null ? conversionTypeMethod.name() : "?")
				.build();
	}
}
