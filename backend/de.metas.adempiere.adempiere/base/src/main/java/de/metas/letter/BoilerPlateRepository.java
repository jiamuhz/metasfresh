package de.metas.letter;

import de.metas.i18n.Language;
import de.metas.letters.model.I_AD_BoilerPlate;
import lombok.NonNull;
import org.adempiere.ad.expression.api.impl.StringExpressionCompiler;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;

import static org.adempiere.model.InterfaceWrapperHelper.loadOutOfTrx;
import static org.adempiere.model.InterfaceWrapperHelper.translate;

/** */

/**
 *
 *
 */
@Repository
public class BoilerPlateRepository
{
	@NonNull
	public BoilerPlate getByBoilerPlateId(
			@NonNull final BoilerPlateId boilerPlateId,
			@Nullable final Language language)
	{
		final I_AD_BoilerPlate boilerPlateRecord = loadOutOfTrx(boilerPlateId, I_AD_BoilerPlate.class);
		return toBoilerPlate(boilerPlateRecord, language);
	}

	private BoilerPlate toBoilerPlate(
			@NonNull final I_AD_BoilerPlate boilerPlateRecord,
			@Nullable final Language language)
	{
		final I_AD_BoilerPlate recordToUse;
		final Language languageEffective;
		if (language != null)
		{
			recordToUse = translate(boilerPlateRecord, I_AD_BoilerPlate.class, language.getAD_Language());
			languageEffective = language;
		}
		else
		{
			recordToUse = boilerPlateRecord;
			languageEffective = Language.getBaseLanguage();
		}

		return BoilerPlate.builder()
				.id(BoilerPlateId.ofRepoId(recordToUse.getAD_BoilerPlate_ID()))
				.language(languageEffective)
				.subject(StringExpressionCompiler.instance.compile(recordToUse.getSubject()))
				.textSnippet(StringExpressionCompiler.instance.compile(recordToUse.getTextSnippet()))
				.build();
	}
}
