package de.metas.ui.web.address.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.window.datatypes.json.JSONDocumentPath;
import lombok.Value;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONCreateAddressRequest
{
	@JsonProperty("templateId") int templateId;

	//
	// Source
	@JsonProperty("source")
	@JsonInclude(JsonInclude.Include.NON_ABSENT) JSONDocumentPath source;

	@JsonCreator
	private JSONCreateAddressRequest(
			@JsonProperty("templateId") final int templateId,
			@JsonProperty("source") final JSONDocumentPath source)
	{
		this.templateId = templateId;
		this.source = source;
	}
}
