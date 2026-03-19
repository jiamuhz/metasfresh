package de.metas.ui.web.view;

import de.metas.ui.web.pattribute.ASIDocument;
import de.metas.ui.web.pattribute.ASILayout;
import de.metas.ui.web.view.descriptor.ViewRowAttributesLayout;
import de.metas.ui.web.view.json.JSONViewRowAttributes;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentField;
import de.metas.ui.web.window.datatypes.json.JSONLayoutWidgetType;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.IDocumentFieldView;

import java.util.List;
import java.util.stream.Collectors;



class ASIViewRowAttributes implements IViewRowAttributes
{
	private final DocumentPath documentPath;
	private final ASIDocument asiDoc;
	private final ViewRowAttributesLayout layout;

	ASIViewRowAttributes(final ASIDocument asiDoc, final ASILayout asiLayout)
	{
		final DocumentId asiDocId = asiDoc.getDocumentId();
		documentPath = DocumentPath.rootDocumentPath(DocumentType.ViewRecordAttributes, asiDocId, asiDocId);
		
		this.asiDoc = asiDoc;
		this.layout = ViewRowAttributesLayout.of(asiLayout.getElements());
	}

	@Override
	public ViewRowAttributesLayout getLayout()
	{
		return layout;
	}

	@Override
	public void processChanges(final List<JSONDocumentChangedEvent> events)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public LookupValuesList getAttributeTypeahead(final String attributeName, final String query)
	{
		throw new UnsupportedOperationException();
//		return asiDoc.getFieldLookupValuesForQuery(attributeName, query);
	}

	@Override
	public LookupValuesList getAttributeDropdown(final String attributeName)
	{
		throw new UnsupportedOperationException();
//		return asiDoc.getFieldLookupValues(attributeName);
	}

	@Override
	public JSONViewRowAttributes toJson(final JSONOptions jsonOpts)
	{
		final JSONViewRowAttributes jsonDocument = new JSONViewRowAttributes(documentPath);

		final List<JSONDocumentField> jsonFields = asiDoc.getFieldViews()
				.stream()
				.map(field -> toJSONDocumentField(field, jsonOpts))
				.collect(Collectors.toList());

		jsonDocument.setFields(jsonFields);

		return jsonDocument;
	}

	private JSONDocumentField toJSONDocumentField(final IDocumentFieldView field, final JSONOptions jsonOpts)
	{
		final String fieldName = field.getFieldName();
		final Object jsonValue = field.getValueAsJsonObject(jsonOpts);
		final DocumentFieldWidgetType widgetType = field.getWidgetType();
		return JSONDocumentField.ofNameAndValue(fieldName, jsonValue)
				.setDisplayed(true)
				.setMandatory(false)
				.setReadonly(true)
				.setWidgetType(JSONLayoutWidgetType.fromNullable(widgetType));
	}

}
