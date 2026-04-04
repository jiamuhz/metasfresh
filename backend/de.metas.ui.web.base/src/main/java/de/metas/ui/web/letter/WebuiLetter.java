package de.metas.ui.web.letter;

import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.user.UserId;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Value;

 

@Builder(toBuilder = true)
@Value
public class WebuiLetter
{
	@NonNull
	private final String letterId;
	private final UserId ownerUserId;

	private final boolean processed;
	/** PDF data; set when the letter is marked as processed too */
	private final byte[] temporaryPDFData;

	@Default
	private final int textTemplateId = -1;
	private final String content;
	private final String subject;

	// Context
	@NonNull
	private final DocumentPath contextDocumentPath;
	private final int adOrgId;
	private final int bpartnerId;
	private final int bpartnerLocationId;
	private final String bpartnerAddress;
	private final int bpartnerContactId;
}
