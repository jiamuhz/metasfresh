package de.metas.ui.web.session;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.compiere.util.DisplayType;

import de.metas.cache.CCache;
import de.metas.i18n.Language;
import lombok.NonNull;
import lombok.Value;



@Value
public class UserSessionLocale
{
	public static final UserSessionLocale get(@NonNull final String adLanguage)
	{
		return cache.getOrLoad(adLanguage, () -> new UserSessionLocale(adLanguage));
	}

	private static final CCache<String, UserSessionLocale> cache = CCache.newCache(UserSessionLocale.class.getName(), 10, 0);

	private final String adLanguage;
	private final char numberDecimalSeparator;
	private final char numberGroupingSeparator;

	private UserSessionLocale(final String adLanguage)
	{
		final Language language = Language.getLanguage(adLanguage);
		if (language == null)
		{
			throw new IllegalArgumentException("No language found for " + adLanguage);
		}
		this.adLanguage = language.getAD_Language();

		final DecimalFormat decimalFormat = DisplayType.getNumberFormat(DisplayType.Amount, language);
		final DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
		numberDecimalSeparator = decimalFormatSymbols.getDecimalSeparator();
		numberGroupingSeparator = decimalFormatSymbols.getGroupingSeparator();
	}
}
