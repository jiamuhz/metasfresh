package de.metas.ui.web.window.datatypes.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentLayoutUIColumnDescriptor;
import de.metas.util.GuavaCollectors;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

@Schema(description = "column")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@ToString
public final class JSONDocumentLayoutColumn
{
	static List<JSONDocumentLayoutColumn> ofList(final List<DocumentLayoutUIColumnDescriptor> columns, final JSONDocumentLayoutOptions jsonOpts)
	{
		return columns.stream()
				.map(column -> of(column, jsonOpts))
				.collect(GuavaCollectors.toImmutableList());
	}

	private static JSONDocumentLayoutColumn of(final DocumentLayoutUIColumnDescriptor column, final JSONDocumentLayoutOptions jsonOpts)
	{
		return new JSONDocumentLayoutColumn(column, jsonOpts);
	}

	@JsonProperty("elementGroups")
	@JsonInclude(Include.NON_EMPTY)
	@Getter
	private final List<JSONDocumentLayoutElementGroup> elementGroups;

	@JsonCreator
	private JSONDocumentLayoutColumn(
			@JsonProperty("elementGroups") @Nullable final List<JSONDocumentLayoutElementGroup> elementGroups)
	{
		this.elementGroups = elementGroups == null ? ImmutableList.of() : ImmutableList.copyOf(elementGroups);
	}

	private JSONDocumentLayoutColumn(final DocumentLayoutUIColumnDescriptor column, final JSONDocumentLayoutOptions jsonOpts)
	{
		elementGroups = JSONDocumentLayoutElementGroup.ofList(column.getElementGroups(), jsonOpts);
	}

	Stream<JSONDocumentLayoutElement> streamInlineTabElements()
	{
		return getElementGroups().stream().flatMap(JSONDocumentLayoutElementGroup::streamInlineTabElements);
	}
}
