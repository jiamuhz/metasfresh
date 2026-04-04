 

package de.metas.ui.web.print.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;

@Value
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class JSONDocumentPrintingOption
{
	@NonNull
	String caption;

	@Nullable
	String description;

	@NonNull
	String internalName;

	boolean value;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Nullable
	String debugSourceName;
}
