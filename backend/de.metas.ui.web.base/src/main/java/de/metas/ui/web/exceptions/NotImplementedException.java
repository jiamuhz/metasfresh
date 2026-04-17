package de.metas.ui.web.exceptions;

import org.adempiere.exceptions.AdempiereException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when some feature is not implemented yet.
 *
 * NOTE:
 * <ul>
 * <li>this exceptions binds to HTTP 501
 * <li>usually "A 501 response is cacheable by default; i.e., unless otherwise indicated by the method definition or explicit cache controls", see https://tools.ietf.org/html/rfc7231#section-6.6.2
 * </ul>
 *
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
public class NotImplementedException extends AdempiereException
{
	public NotImplementedException(final String message)
	{
		super(message);
	}

}
