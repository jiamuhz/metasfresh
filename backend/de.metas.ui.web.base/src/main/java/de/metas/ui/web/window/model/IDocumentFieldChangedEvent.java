package de.metas.ui.web.window.model;

import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;

public interface IDocumentFieldChangedEvent
{
	DocumentPath getDocumentPath();

	String getFieldName();

	DocumentFieldWidgetType getWidgetType();

	boolean isValueSet();

	Object getValue();
}
