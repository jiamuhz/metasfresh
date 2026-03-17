package org.adempiere.exceptions;

/** */

/**
 * Exception thrown when a database connection acquiring was timed out.
 * 
 *
 *
 */
public class DBConnectionAcquireTimeoutException extends DBNoConnectionException
{
	private static final long serialVersionUID = -6558012776228675922L;

	public DBConnectionAcquireTimeoutException(final Throwable timeoutException)
	{
		super(timeoutException.getLocalizedMessage(), timeoutException);
	}
}
