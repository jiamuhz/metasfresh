 

package de.metas.ui.web.print.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class JSONDocumentPrintingOptions
{
	@NonNull
	String caption;

	@NonNull
	String okButtonCaption;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	List<JSONDocumentPrintingOption> options;

	@Builder
	private JSONDocumentPrintingOptions(
			@NonNull final String caption,
			@NonNull final String okButtonCaption,
			@NonNull final List<JSONDocumentPrintingOption> options)
	{
		this.caption = caption;
		this.okButtonCaption = okButtonCaption;
		this.options = ImmutableList.copyOf(options);
	}

}
