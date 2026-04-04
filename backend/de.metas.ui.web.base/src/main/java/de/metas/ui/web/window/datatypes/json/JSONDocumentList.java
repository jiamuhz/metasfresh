package de.metas.ui.web.window.datatypes.json;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.ToString;

 

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE) // cannot use it because of "otherProperties"
@ToString
public class JSONDocumentList
{
	private final List<JSONDocument> result;
	private final Set<DocumentId> missingIds;

	@Builder
	private JSONDocumentList(
			final List<JSONDocument> result,
			final Set<DocumentId> missingIds)
	{
		this.result = result != null ? ImmutableList.copyOf(result) : ImmutableList.of();
		this.missingIds = missingIds != null ? ImmutableSet.copyOf(missingIds) : ImmutableSet.of();
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class JSONDocumentListBuilder
	{
	}
}
