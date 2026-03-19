package de.metas.ui.web.window.exceptions;

import org.adempiere.exceptions.AdempiereException;

import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.model.Document;

  
@SuppressWarnings("serial")
public class InvalidDocumentStateException extends AdempiereException
{
	public InvalidDocumentStateException(final Document document, final String reason)
	{
		super(buildMsg(document.getDocumentPath(), reason));
	}

	public InvalidDocumentStateException(final DocumentPath documentPath, final String reason)
	{
		super(buildMsg(documentPath, reason));
	}

	private static String buildMsg(final DocumentPath documentPath, final String reason)
	{
		return "Document " + documentPath + " state is invalid: " + reason;
	}
}
