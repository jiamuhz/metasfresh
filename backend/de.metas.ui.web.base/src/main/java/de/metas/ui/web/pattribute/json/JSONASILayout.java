package de.metas.ui.web.pattribute.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import de.metas.ui.web.pattribute.ASILayout;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElement;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;

  
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public final class JSONASILayout implements Serializable
{
	public static JSONASILayout of(final ASILayout layout, final JSONDocumentLayoutOptions options)
	{
		return new JSONASILayout(layout, options);
	}

	@JsonProperty("id")
	private final String id;

	@JsonProperty("caption")
	private final String caption;
	@JsonProperty("description")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final String description;

	@JsonProperty("elements")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final List<JSONDocumentLayoutElement> elements;

	private JSONASILayout(final ASILayout layout, final JSONDocumentLayoutOptions options)
	{
		final String adLanguage = options.getAdLanguage();

		final DocumentId asiDescriptorId = layout.getASIDescriptorId();
		this.id = asiDescriptorId == null ? null : asiDescriptorId.toJson();

		caption = layout.getCaption(adLanguage);
		description = layout.getDescription(adLanguage);
		elements = JSONDocumentLayoutElement.ofList(layout.getElements(), options);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("id", id)
				.add("caption", caption)
				.add("description", description)
				.add("elements", elements)
				.toString();
	}

	public String getCaption()
	{
		return caption;
	}

	public String getDescription()
	{
		return description;
	}

	public List<JSONDocumentLayoutElement> getElements()
	{
		return elements;
	}
}
