package org.adempiere.ad.expression.api;

import org.adempiere.ad.expression.exceptions.ExpressionEvaluationException;
import org.compiere.util.Evaluatee;

/** */

/**
 * Logic expression evaluator
 * 
 *
 *
 */
public interface ILogicExpressionEvaluator extends IExpressionEvaluator<ILogicExpression, Boolean>
{
	@Override
	Boolean evaluate(Evaluatee ctx, ILogicExpression expression, IExpressionEvaluator.OnVariableNotFound onVariableNotFound) throws ExpressionEvaluationException;

	/**
	 * Evaluates given expression and returns {@link LogicExpressionResult}.
	 * 
	 * Use this method if you need more informations about the evaluation (e.g. which were the parameters used etc).
	 * 
	 * If you are just interested about the boolean result, please use {@link #evaluate(Evaluatee, OnVariableNotFound)}.
	 * 
	 * @param ctx
	 * @param onVariableNotFound
	 * @return
	 * @throws ExpressionEvaluationException
	 */
	LogicExpressionResult evaluateToResult(Evaluatee ctx, ILogicExpression expression, OnVariableNotFound onVariableNotFound) throws ExpressionEvaluationException;

	ILogicExpression evaluatePartial(Evaluatee params, ILogicExpression expr);
}
