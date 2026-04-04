package de.metas.ui.web.window.datatypes.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.i18n.IMsgBL;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.compiere.util.ValueNamePairValidationInformation;

import javax.annotation.Nullable;

 

@Value
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public final class JSONLookupValueValidationInformation
{
	@JsonProperty("title")
	private final String title;

	@JsonProperty("question")
	private final String question;

	@JsonProperty("answerYes")
	private final String answerYes;

	@JsonProperty("answerNo")
	private final String answerNo;

	@Builder
	private JSONLookupValueValidationInformation(
			@JsonProperty("title") final String title,
			@JsonProperty("question") final String question,
			@JsonProperty("answerYes") final String answerYes,
			@JsonProperty("answerNo") final String answerNo)
	{
		this.title = title;
		this.question = question;
		this.answerYes = answerYes;
		this.answerNo = answerNo;
	}

	public static JSONLookupValueValidationInformation ofNullable(
			@Nullable final ValueNamePairValidationInformation validationInformation,
			@NonNull final String adLanguage)
	{
		if (validationInformation == null)
		{
			return null;
		}

		final IMsgBL msgBL = Services.get(IMsgBL.class);

		return JSONLookupValueValidationInformation.builder()
				.title(msgBL.translate(adLanguage, validationInformation.getTitle().toAD_Message()))
				.question(msgBL.translate(adLanguage, validationInformation.getQuestion().toAD_Message()))
				.answerYes(msgBL.translate(adLanguage, validationInformation.getAnswerYes().toAD_Message()))
				.answerNo(msgBL.translate(adLanguage, validationInformation.getAnswerNo().toAD_Message()))
				.build();
	}
}
