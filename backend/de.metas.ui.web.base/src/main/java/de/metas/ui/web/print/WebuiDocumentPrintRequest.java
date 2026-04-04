

package de.metas.ui.web.print;

import de.metas.report.DocumentPrintOptions;
import de.metas.report.DocumentReportFlavor;
import de.metas.security.RoleId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.user.UserId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class WebuiDocumentPrintRequest
{
	@NonNull
	DocumentPath documentPath;

	@NonNull
	UserId userId;
	@NonNull
	RoleId roleId;

	@NonNull
	@Builder.Default
	DocumentReportFlavor flavor = DocumentReportFlavor.PRINT;

	@NonNull
	@Builder.Default
	DocumentPrintOptions printOptions = DocumentPrintOptions.NONE;
}
