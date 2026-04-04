package de.metas.ui.web.window.model;

import java.util.Set;

 

public interface IDocumentFieldValueProvider
{
	Set<String> getDependsOnFieldNames();

	Object calculateValue(final Document document);
}
