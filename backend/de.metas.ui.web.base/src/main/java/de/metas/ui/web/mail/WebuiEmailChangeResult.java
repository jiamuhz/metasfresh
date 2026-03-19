package de.metas.ui.web.mail;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;



@Value
@Builder
class WebuiEmailChangeResult
{
	@NonNull
	private final WebuiEmail email;
	@NonNull
	private final WebuiEmail originalEmail;
}
