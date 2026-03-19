package de.metas.ui.web.window.datatypes.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementLineGroupDescriptor;
import de.metas.util.GuavaCollectors;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

  
@Schema(description = "elementGroup")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@ToString
public final class JSONDocumentLayoutElementGroup
{
	static List<JSONDocumentLayoutElementGroup> ofList(
			@NonNull final List<DocumentLayoutElementLineGroupDescriptor> elementGroups,
			@NonNull final JSONDocumentLayoutOptions jsonOpts)
	{
		return elementGroups.stream()
				.map(elementGroup -> of(elementGroup, jsonOpts))
				.filter(group -> !group.isEmpty())
				.collect(GuavaCollectors.toImmutableList());
	}

	public static JSONDocumentLayoutElementGroup of(
			@NonNull final DocumentLayoutElementLineGroupDescriptor elementGroup,
			@NonNull final JSONDocumentLayoutOptions jsonOpts)
	{
		return new JSONDocumentLayoutElementGroup(elementGroup, jsonOpts);
	}

	@Schema
	@JsonProperty("type")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Getter
	private final JSONLayoutType type;

	@Schema(description = "Number of equal-width-columns into which the included elementsLines shall be displayed:\n"
			+ "Notes:\n"
			+ "* one element line per cell"
			+ "* an empty element line shall be rendered as empty cell"
			+ "* if you have e.g. columnCount=3 and four element lines, then the rightmost two cells of the last line shall be empty"
			+ "* if this property is missing, then <code>1</code> should be assumed")
	@JsonProperty("columnCount")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Getter
	private final Integer columnCount;

	@JsonProperty("internalName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Getter
	private final String internalName;

	@Schema(description = "Container for elementy that are supposed to be displayed next to each other\n"
			+ "Notes:"
			+ "* individual element lines might be empty for layout purposes; see <code>columnCount</code>\n"
			+ "* in most of the cases, each elementLine has one element")
	@JsonProperty("elementsLine")
	@JsonInclude(JsonInclude.Include.ALWAYS)
	@Getter
	private final List<JSONDocumentLayoutElementLine> elementLines;

	private JSONDocumentLayoutElementGroup(
			@NonNull final DocumentLayoutElementLineGroupDescriptor elementGroup,
			@NonNull final JSONDocumentLayoutOptions jsonOpts)
	{
		this.type = JSONLayoutType.fromNullable(elementGroup.getLayoutType());
		this.columnCount = elementGroup.getColumnCount();
		this.internalName = elementGroup.getInternalName();
		this.elementLines = JSONDocumentLayoutElementLine.ofList(elementGroup.getElementLines(), jsonOpts);
	}

	@JsonCreator
	private JSONDocumentLayoutElementGroup(
			@JsonProperty("type") final JSONLayoutType type,
			@JsonProperty("columnCount") @Nullable final Integer columnCount,
			@JsonProperty("internalName") @Nullable final String internalName,
			@JsonProperty("elementsLine") @Nullable final List<JSONDocumentLayoutElementLine> elementLines)
	{
		this.type = type;
		this.columnCount = columnCount;
		this.internalName = internalName;
		this.elementLines = elementLines == null ? ImmutableList.of() : ImmutableList.copyOf(elementLines);
	}

	private boolean isEmpty()
	{
		final boolean atLeastOneLineIsFilled = elementLines
				.stream()
				.anyMatch(line -> !line.isEmpty());
		return !atLeastOneLineIsFilled;
	}

	Stream<JSONDocumentLayoutElement> streamInlineTabElements()
	{
		return getElementLines().stream().flatMap(JSONDocumentLayoutElementLine::streamInlineTabElements);
	}
}
