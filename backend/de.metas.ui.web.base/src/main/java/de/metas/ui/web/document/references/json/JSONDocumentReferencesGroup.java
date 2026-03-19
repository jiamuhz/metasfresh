package de.metas.ui.web.document.references.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;



@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONDocumentReferencesGroup
{
	@JsonProperty("caption")
	String caption;

	@JsonProperty("references")
	List<JSONDocumentReference> references;

	@JsonProperty("miscGroup")
	boolean miscGroup;

	@JsonCreator
	@Builder
	private JSONDocumentReferencesGroup(
			@JsonProperty("caption") final String caption,
			@JsonProperty("miscGroup") final boolean isMiscGroup,
			@JsonProperty("references") @Singular final List<JSONDocumentReference> references)
	{
		this.caption = caption;
		this.miscGroup = isMiscGroup;
		this.references = references == null || references.isEmpty() ? ImmutableList.of() : ImmutableList.copyOf(references);
	}

	public boolean isEmpty()
	{
		return getReferences().isEmpty();
	}
}
