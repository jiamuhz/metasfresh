package de.metas.ui.web.window.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.util.Check;

public class MutableDocumentFieldChangedEvent implements IDocumentFieldChangedEvent
{
	public static final MutableDocumentFieldChangedEvent of(final DocumentPath documentPath, final String fieldName, DocumentFieldWidgetType widgetType)
	{
		return new MutableDocumentFieldChangedEvent(documentPath, fieldName, widgetType);
	}

	private final DocumentPath documentPath;
	private final String fieldName;
	private final DocumentFieldWidgetType widgetType;

	private Object value;
	private boolean valueSet = false;

	private MutableDocumentFieldChangedEvent(final DocumentPath documentPath, final String fieldName, DocumentFieldWidgetType widgetType)
	{
		super();

		Check.assumeNotNull(documentPath, "Parameter documentPath is not null");
		this.documentPath = documentPath;

		Check.assumeNotEmpty(fieldName, "fieldName is not empty");
		this.fieldName = fieldName;
		
		Check.assumeNotNull(widgetType, "Parameter widgetType is not null");
		this.widgetType = widgetType;
	}

	@Override
	public String toString()
	{
		final ToStringHelper helper = MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("documentPath", documentPath)
				.add("fieldName", fieldName);

		if (valueSet)
		{
			helper.add("value", value == null ? "<NULL>" : value);
		}

		return helper.toString();
	}

	@Override
	public DocumentPath getDocumentPath()
	{
		return documentPath;
	}

	@Override
	public String getFieldName()
	{
		return fieldName;
	}

	@Override
	public DocumentFieldWidgetType getWidgetType()
	{
		return widgetType;
	}

	@Override
	public boolean isValueSet()
	{
		return valueSet;
	}

	@Override
	public Object getValue()
	{
		return value;
	}

	public MutableDocumentFieldChangedEvent setValue(final Object value)
	{
		this.value = value;
		valueSet = true;
		return this;
	}

}
