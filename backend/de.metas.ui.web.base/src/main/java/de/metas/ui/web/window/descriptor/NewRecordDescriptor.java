package de.metas.ui.web.window.descriptor;

import de.metas.document.NewRecordContext;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.model.Document;
import lombok.NonNull;
import lombok.Value;



/**
 * Describes which window to be used to capture the fields for quickly creating a record for a given BPartner.
 * <p>
 * task https://github.com/metasfresh/metasfresh/issues/1090
 */
@Value(staticConstructor = "of")
public class NewRecordDescriptor
{
	public interface NewRecordProcessor
	{
		int processNewRecordDocument(Document document,
				NewRecordContext newRecordContext);
	}

	@NonNull String tableName;
	@NonNull
	WindowDocumentTypeId newRecordWindowId;
	@NonNull NewRecordProcessor processor;
}
