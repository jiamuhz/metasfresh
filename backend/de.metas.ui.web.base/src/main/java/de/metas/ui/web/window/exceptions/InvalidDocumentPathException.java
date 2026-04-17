package de.metas.ui.web.window.exceptions;

import org.adempiere.exceptions.AdempiereException;

import de.metas.ui.web.window.datatypes.DocumentPath;

@SuppressWarnings("serial")
public class InvalidDocumentPathException extends AdempiereException
{
	public InvalidDocumentPathException(final DocumentPath documentPath)
	{
		super(buildMsg(documentPath, null));
	}

	public InvalidDocumentPathException(final DocumentPath documentPath, final String reason)
	{
		super(buildMsg(documentPath, reason));
	}

	public InvalidDocumentPathException(final String message)
	{
		super(message);
	}

	private static String buildMsg(final DocumentPath documentPath, final String reason)
	{
		final StringBuilder msg = new StringBuilder();
		msg.append("Invalid document path ").append(documentPath).append(".");
		if (reason != null)
		{
			msg.append(" Reason: ").append(reason);
		}
		return msg.toString();
	}
}
