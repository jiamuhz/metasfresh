package de.metas.ui.web.process.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.process.descriptor.ProcessLayout;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElement;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONProcessLayout
{
	public static JSONProcessLayout of(final ProcessLayout layout, final JSONDocumentLayoutOptions jsonOpts)
	{
		return new JSONProcessLayout(layout, jsonOpts);
	}

	@JsonProperty("layoutType")
	private final PanelLayoutType layoutType;

	@JsonProperty("barcodeScannerType")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final BarcodeScannerType barcodeScannerType;

	@JsonProperty("caption")
	private final String caption;
	@JsonProperty("description")
	private final String description;

	@JsonProperty("elements")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final List<JSONDocumentLayoutElement> elements;

	private JSONProcessLayout(final ProcessLayout layout, final JSONDocumentLayoutOptions jsonOpts)
	{
		layoutType = layout.getLayoutType();
		barcodeScannerType = layout.getBarcodeScannerType();

		final String adLanguage = jsonOpts.getAdLanguage();
		caption = layout.getCaption(adLanguage);
		description = layout.getDescription(adLanguage);

		elements = JSONDocumentLayoutElement.ofList(layout.getElements(), jsonOpts);
	}
}
