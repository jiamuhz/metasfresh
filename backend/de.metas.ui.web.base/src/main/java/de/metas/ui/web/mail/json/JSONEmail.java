package de.metas.ui.web.mail.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.collect.ImmutableList;

import de.metas.ui.web.mail.WebuiEmail;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import lombok.Getter;
import lombok.NonNull;



@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Getter
public class JSONEmail
{
	public static JSONEmail of(@NonNull final WebuiEmail email, @NonNull final String adLanguage)
	{
		return new JSONEmail(email, adLanguage);
	}

	private final String emailId;
	private final JSONLookupValue from;
	private final List<JSONLookupValue> to;
	private final String subject;
	private final String message;
	private final List<JSONLookupValue> attachments;

	private JSONEmail(@NonNull final WebuiEmail email, @NonNull final String adLanguage)
	{
		emailId = email.getEmailId();
		from = JSONLookupValue.ofLookupValue(email.getFrom(), adLanguage);
		to = email.getTo()
				.stream()
				.map(lookupValue -> JSONLookupValue.ofLookupValue(lookupValue, adLanguage))
				.collect(ImmutableList.toImmutableList());
		subject = email.getSubject();
		message = email.getMessage();
		attachments = email.getAttachments()
				.stream()
				.map(lookupValue -> JSONLookupValue.ofLookupValue(lookupValue, adLanguage))
				.collect(ImmutableList.toImmutableList());
	}
}
