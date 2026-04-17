package de.metas.ui.web.window.exceptions;

import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import org.adempiere.exceptions.AdempiereException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown by {@link DocumentDescriptorFactory} on any layout building issue.
 *
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DocumentLayoutBuildException extends AdempiereException
{
	public static DocumentLayoutBuildException wrapIfNeeded(final Throwable throwable)
	{
		if (throwable == null)
		{
			return null;
		}
		else if (throwable instanceof DocumentLayoutBuildException)
		{
			return (DocumentLayoutBuildException)throwable;
		}

		final Throwable cause = extractCause(throwable);
		if (cause != throwable)
		{
			return wrapIfNeeded(cause);
		}

		// default
		return new DocumentLayoutBuildException(cause.getLocalizedMessage(), cause);
	}

	public static Throwable extractCause(final Throwable throwable)
	{
		if (throwable instanceof final DocumentLayoutBuildException documentLayoutBuildException)
		{
			final Throwable cause = documentLayoutBuildException.getCause();
			return cause != null ? cause : documentLayoutBuildException;
		}
		else
		{
			return AdempiereException.extractCause(throwable);
		}
	}

	public DocumentLayoutBuildException(final String message)
	{
		super(message);
	}

	private DocumentLayoutBuildException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

}
