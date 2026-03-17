package org.adempiere.ad.expression.api;

/** */

@FunctionalInterface
public interface IStringExpressionWrapper
{
	IStringExpression wrap(IStringExpression expression);
}
