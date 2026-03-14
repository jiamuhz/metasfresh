package org.adempiere.ad.expression.exceptions;

/** */

import org.adempiere.exceptions.AdempiereException;

/**
 * Root of all expression exceptions
 *
 * @author tsa
 *
 */
public class ExpressionException extends AdempiereException
{
	private static final long serialVersionUID = -1795711700186090995L;

	protected ExpressionException(final String msg)
	{
		super(msg);
	}

	protected ExpressionException(final String msg, final Throwable cause)
	{
		super(msg, cause);
	}

}
