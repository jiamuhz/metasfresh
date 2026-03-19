package de.metas.ui.web.window.datatypes.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentLayoutUISectionDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutUISectionDescriptor.CaptionMode;
import de.metas.ui.web.window.descriptor.DocumentLayoutUISectionDescriptor.ClosableMode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

  
@Schema(description = "section")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public final class JSONDocumentLayoutSection
{
	static List<JSONDocumentLayoutSection> ofSectionsList(final List<DocumentLayoutUISectionDescriptor> sections, final JSONDocumentLayoutOptions jsonOpts)
	{
		return sections.stream()
				.map(section -> new JSONDocumentLayoutSection(section, jsonOpts))
				.collect(ImmutableList.toImmutableList());
	}

	public enum JSONClosableMode
	{
		ALWAYS_OPEN,

		INITIALLY_OPEN,

		INITIALLY_CLOSED;

		public static JSONClosableMode ofClosableMode(@NonNull final ClosableMode closableMode)
		{
			switch (closableMode)
			{
				case ALWAYS_OPEN:
					return ALWAYS_OPEN;
				case INITIALLY_CLOSED:
					return INITIALLY_CLOSED;
				case INITIALLY_OPEN:
					return INITIALLY_OPEN;
				default:
					throw new AdempiereException("Unexpected closableMode=" + closableMode);
			}
		}
	}

	@JsonProperty("title")
	@JsonInclude(Include.NON_EMPTY)
	private final String title;

	@JsonProperty("description")
	@JsonInclude(Include.NON_EMPTY)
	private final String description;

	@JsonProperty("uiStyle")
	@JsonInclude(Include.NON_EMPTY)
	private final String uiStyle;

	@JsonProperty("columns")
	@JsonInclude(Include.NON_EMPTY)
	@Getter
	private final List<JSONDocumentLayoutColumn> columns;

	@JsonProperty("closableMode")
	@JsonInclude(Include.NON_EMPTY)
	private final JSONClosableMode closableMode;

	private JSONDocumentLayoutSection(
			final DocumentLayoutUISectionDescriptor section,
			final JSONDocumentLayoutOptions options)
	{
		this.title = extractTitle(section, options);
		this.uiStyle = section.getUiStyle();

		this.description = section.getDescription(options.getAdLanguage()).trim();
		this.columns = JSONDocumentLayoutColumn.ofList(section.getColumns(), options);
		this.closableMode = JSONClosableMode.ofClosableMode(section.getClosableMode());
	}

	@Nullable
	private static String extractTitle(
			@NonNull final DocumentLayoutUISectionDescriptor section,
			@NonNull final JSONDocumentLayoutOptions options)
	{
		if (CaptionMode.DISPLAY.equals(section.getCaptionMode()))
		{
			return section.getCaption(options.getAdLanguage()).trim();
		}
		else if (CaptionMode.DISPLAY_IN_ADV_EDIT.equals(section.getCaptionMode()))
		{
			if (options.isShowAdvancedFields())
			{
				return section.getCaption(options.getAdLanguage()).trim();
			}
			else
			{
				return null;
			}
		}
		else if (CaptionMode.DONT_DISPLAY.equals(section.getCaptionMode()))
		{
			return null;
		}

		throw new AdempiereException("Unexpected captionMode=" + section.getCaptionMode())
				.appendParametersToMessage()
				.setParameter("documentLayoutSectionDescriptor", section);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.add("columns", columns)
				.toString();
	}

	Stream<JSONDocumentLayoutElement> streamInlineTabElements()
	{
		return getColumns().stream().flatMap(JSONDocumentLayoutColumn::streamInlineTabElements);
	}
}
