package org.adempiere.ad.expression.api;

import de.metas.logging.LogManager;
import org.slf4j.Logger;

/** */

/**
 * Expression compiler
 *
 * @author tsa
 *
 * @param <V> expression evaluation result type
 * @param <ET> expression type
 */
public interface IExpressionCompiler<V, ET extends IExpression<? extends V>>
{
	/**
	 * Compiles given expression.
	 * 
	 * @return compiled expression
	 */
	ET compile(ExpressionContext context, String expressionStr);

	/**
	 * Same as {@link #compile(ExpressionContext, String)} but using no context.
	 * 
	 * @return compiled expression
	 */
	default ET compile(final String expressionStr)
	{
		return compile(ExpressionContext.EMPTY, expressionStr);
	}
	
	/**
	 * Compiles given string expression
	 * 
	 * If the expression cannot be evaluated, returns the given default expression.
	 * 
	 * This method does not throw any exception, but in case of error that error will be logged.
	 * 
	 * @param expressionStr The expression to be compiled
	 * @return compiled expression or <code>defaultExpression</code>
	 */
	default ET compileOrDefault(final String expressionStr, final ET defaultExpression)
	{
		try
		{
			return compile(expressionStr);
		}
		catch (final Exception ex)
		{
			final Logger logger = LogManager.getLogger(getClass());
			logger.warn("Failed parsing '{}'. Returning default expression: {}", expressionStr, defaultExpression, ex);
			return defaultExpression;
		}
	}

}
