package de.metas.i18n;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.common.base.Strings;

import de.metas.i18n.impl.NullModelTranslation;
import lombok.NonNull;

import javax.annotation.Nullable;

/** */

/**
 * Contains all translated column values for a particular model (record) and for ALL languages.
 * 
 *
 *
 */
public interface IModelTranslationMap
{
	int getRecordId();
	
	/**
	 * @return all {@link IModelTranslation}s for AD_Language; if no translation was found then {@link NullModelTranslation} will be returned
	 */
	IModelTranslation getTranslation(String adLanguage);

	/**
	 * @return all {@link IModelTranslation}s indexed by AD_Language
	 */
	Map<String, IModelTranslation> getAllTranslations();

	/**
	 * @param defaultValue default value to be used in case a translation is missing
	 * @return {@link ITranslatableString} for given column name
	 */
	default ITranslatableString getColumnTrl(@NonNull final String columnName, @Nullable final String defaultValue)
	{
		final Map<String, String> columnTrls = new HashMap<>();
		for (final IModelTranslation modelTrl : getAllTranslations().values())
		{
			if (!modelTrl.isTranslated(columnName))
			{
				continue;
			}

			final String adLanguage = modelTrl.getAD_Language();
			final String columnTrl = modelTrl.getTranslation(columnName);
			columnTrls.put(adLanguage, Strings.nullToEmpty(columnTrl));
		}

		return TranslatableStrings.ofMap(columnTrls, defaultValue);
	}

	/**
	 * Translates columnName to given adLanguage. If the language or the column was not found, {@link Optional#empty()} will be returned.
	 */
	default Optional<String> translateColumn(final String columnName, final String adLanguage)
	{
		final IModelTranslation modelTrl = getTranslation(adLanguage);
		if (NullModelTranslation.isNull(modelTrl))
		{
			return Optional.empty();
		}

		final String columnTrl = modelTrl.getTranslation(columnName);
		if (columnTrl == null)
		{
			return Optional.empty();
		}

		return Optional.of(columnTrl);
	}
}
