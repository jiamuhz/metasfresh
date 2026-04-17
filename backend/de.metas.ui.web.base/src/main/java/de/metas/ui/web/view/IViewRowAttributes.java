package de.metas.ui.web.view;

import de.metas.ui.web.view.descriptor.ViewRowAttributesLayout;
import de.metas.ui.web.view.json.JSONViewRowAttributes;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONOptions;

import java.util.List;

public interface IViewRowAttributes
{
	ViewRowAttributesLayout getLayout();

	void processChanges(List<JSONDocumentChangedEvent> events);

	LookupValuesList getAttributeTypeahead(String attributeName, final String query);

	LookupValuesList getAttributeDropdown(String attributeName);

	JSONViewRowAttributes toJson(final JSONOptions jsonOpts);

}
