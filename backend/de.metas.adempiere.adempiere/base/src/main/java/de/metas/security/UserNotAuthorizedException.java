package de.metas.security;

import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;

/** */

public class UserNotAuthorizedException extends AdempiereException
{
	public UserNotAuthorizedException(
			@SuppressWarnings("unused") @Nullable final String authTokenString,
			@Nullable final Throwable cause)
	{
		super(buildMsg(cause), cause);
		// setParameter("authTokenString", authTokenString); // NOTE: don't include token in message because it might be a security issue
	}

	private static String buildMsg(@Nullable final Throwable cause)
	{
		final StringBuilder msg = new StringBuilder();
		msg.append("Token not authorized.");
		// NOTE: don't include token in message because it might be a security issue

		if (cause != null)
		{
			msg.append("Cause: ").append(extractMessage(cause));
		}
		return msg.toString();
	}
}
