package de.metas.ui.web.login.exceptions;

import org.adempiere.exceptions.AdempiereException;

 

@SuppressWarnings("serial")
public class NotAuthenticatedException
		// extends org.springframework.security.core.AuthenticationException // TODO: use AuthenticationException when spring security will be used
		extends AdempiereException
{
	public NotAuthenticatedException()
	{
		super("not authenticated");
	}
}
