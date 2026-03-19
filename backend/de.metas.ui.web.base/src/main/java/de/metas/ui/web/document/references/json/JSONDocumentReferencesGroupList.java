package de.metas.ui.web.document.references.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

import lombok.Value;



@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONDocumentReferencesGroupList
{
	public static final JSONDocumentReferencesGroupList EMPTY = new JSONDocumentReferencesGroupList(ImmutableList.of());

	@JsonProperty("groups")
	private final List<JSONDocumentReferencesGroup> groups;

	@JsonCreator
	public JSONDocumentReferencesGroupList(@JsonProperty("groups") final List<JSONDocumentReferencesGroup> groups)
	{
		this.groups = groups == null || groups.isEmpty() ? ImmutableList.of() : ImmutableList.copyOf(groups);
	}

	public boolean isEmpty()
	{
		return groups.isEmpty();
	}
}
