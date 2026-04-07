package de.metas.ui.web.login.exceptions;

import org.adempiere.exceptions.AdempiereException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

 

/**
 * Exception thrown when user is not logged in.
 * 
 * IMPORTANT: this exception shall be mapped to HTTP 401 Unauthorized instead of 403 Forbidden. Webui frontend relies on that!
 * 
 *
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class NotLoggedInException
		// extends org.springframework.security.core.AuthenticationException // TODO: use AuthenticationException when spring security will be used
		extends AdempiereException
{
	public NotLoggedInException()
	{
		this("not logged in");
	}

	public NotLoggedInException(final String message)
	{
		super(message);
	}

}
