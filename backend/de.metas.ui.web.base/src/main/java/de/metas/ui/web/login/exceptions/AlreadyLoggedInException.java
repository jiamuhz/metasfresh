package de.metas.ui.web.login.exceptions;

import org.adempiere.exceptions.AdempiereException;

 

@SuppressWarnings("serial")
public class AlreadyLoggedInException
		// extends org.springframework.security.core.AuthenticationException // TODO: use AuthenticationException when spring security will be used
		extends AdempiereException
{
	public AlreadyLoggedInException()
	{
		super("User already logged in");
	}
}
