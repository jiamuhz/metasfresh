package de.metas.ui.web.address.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import de.metas.ui.web.address.AddressLayout;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElement;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;

  
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public final class JSONAddressLayout implements Serializable
{
	public static JSONAddressLayout of(final AddressLayout layout, final JSONDocumentLayoutOptions options)
	{
		return new JSONAddressLayout(layout, options);
	}

	@JsonProperty("elements")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final List<JSONDocumentLayoutElement> elements;

	public JSONAddressLayout(final AddressLayout layout, final JSONDocumentLayoutOptions options)
	{
		super();
		elements = JSONDocumentLayoutElement.ofList(layout.getElements(), options);
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
