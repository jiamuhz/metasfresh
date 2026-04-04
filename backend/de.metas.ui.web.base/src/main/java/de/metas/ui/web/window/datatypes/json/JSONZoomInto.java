package de.metas.ui.web.window.datatypes.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Builder;
import lombok.Value;

 

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
@Builder
public class JSONZoomInto
{
	/** ZoomInto document path */
	private final JSONDocumentPath documentPath;
	/** Source field for whom we retrieved the ZoomInto document path */
	private final JSONDocumentPath source;
}
