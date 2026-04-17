package de.metas.ui.web.view.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.base.MoreObjects;

import de.metas.ui.web.view.descriptor.ViewRowAttributesLayout;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElement;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;

@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONViewRowAttributesLayout implements Serializable
{
	public static JSONViewRowAttributesLayout of(final ViewRowAttributesLayout layout, final JSONDocumentLayoutOptions jsonOpts)
	{
		return new JSONViewRowAttributesLayout(layout, jsonOpts);
	}

	private final List<JSONDocumentLayoutElement> elements;

	private JSONViewRowAttributesLayout(final ViewRowAttributesLayout layout, final JSONDocumentLayoutOptions jsonOpts)
	{
		super();
		elements = JSONDocumentLayoutElement.ofList(layout.getElements(), jsonOpts);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.add("elements", elements)
				.toString();
	}

	public List<JSONDocumentLayoutElement> getElements()
	{
		return elements;
	}
}
