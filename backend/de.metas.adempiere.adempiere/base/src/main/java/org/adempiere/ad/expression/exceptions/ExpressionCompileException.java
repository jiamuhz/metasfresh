package org.adempiere.ad.expression.exceptions;

/** */



/**
 * Exception thrown when we have an expression compilation failure
 * 
 * @author tsa
 * 
 */
public class ExpressionCompileException extends ExpressionException
{
	private static final long serialVersionUID = 7284538289150002848L;

	public ExpressionCompileException(final String msg)
	{
		super(msg);
	}
}
