package de.metas.ui.web.mail;

import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.user.UserId;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Value;

 

@Builder(toBuilder = true)
@Value
public class WebuiEmail
{
	@NonNull
	String emailId;
	UserId ownerUserId;

	LookupValue from;
	@Default
	LookupValuesList to = LookupValuesList.EMPTY;
	String subject;
	String message;
	@Default
	LookupValuesList attachments = LookupValuesList.EMPTY;

	boolean sent;

	DocumentPath contextDocumentPath;
}
