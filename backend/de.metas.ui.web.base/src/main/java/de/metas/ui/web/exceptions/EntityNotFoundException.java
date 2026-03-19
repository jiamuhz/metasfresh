package de.metas.ui.web.exceptions;

import de.metas.i18n.ITranslatableString;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

  
/**
 * Exception thrown when some entity (document, process etc) was not found.
 * <p>
 * NOTE: this exceptions binds to HTTP 404
 *
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends AdempiereException
{
	public static final EntityNotFoundException wrapIfNeeded(@NonNull final Throwable throwable)
	{
		if (throwable instanceof EntityNotFoundException)
		{
			return (EntityNotFoundException)throwable;
		}

		final Throwable cause = extractCause(throwable);
		if (cause != throwable)
		{
			return wrapIfNeeded(cause);
		}

		return new EntityNotFoundException(extractMessage(throwable), cause);
	}

	public EntityNotFoundException(final String message)
	{
		super(message);
	}

	public EntityNotFoundException(final ITranslatableString message)
	{
		super(message);
	}

	public EntityNotFoundException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
