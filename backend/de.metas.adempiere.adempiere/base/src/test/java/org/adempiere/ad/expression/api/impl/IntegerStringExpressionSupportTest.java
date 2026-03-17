package org.adempiere.ad.expression.api.impl;

import java.util.Map;

import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IExpressionFactory;
import org.adempiere.ad.expression.api.impl.IntegerStringExpressionSupport.IntegerStringExpression;
import org.compiere.util.Evaluatees;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

import de.metas.util.Services;

/** */

public class IntegerStringExpressionSupportTest
{
	private IExpressionFactory expressionFactory;

	@Before
	public void init()
	{
		expressionFactory = Services.get(IExpressionFactory.class);
	}

	@Test
	public void test_NullExpression()
	{
		final IntegerStringExpression expr = expressionFactory.compile("", IntegerStringExpression.class);
		Assert.assertTrue("Expect null expression: " + expr, expr.isNullExpression());
	}

	@Test
	public void test_ConstantExpression()
	{
		final IntegerStringExpression expr = expressionFactory.compile("123456", IntegerStringExpression.class);
		final Integer value = expr.evaluate(Evaluatees.empty(), OnVariableNotFound.Fail);
		Assert.assertEquals((Integer)123456, value);
	}

	@Test
	public void test_SingleParameterExpression()
	{
		final IntegerStringExpression expr = expressionFactory.compile("@ValueBD@", IntegerStringExpression.class);
		final Integer value = expr.evaluate(Evaluatees.ofSingleton("ValueBD", "123456"), OnVariableNotFound.Fail);
		Assert.assertEquals((Integer)123456, value);
	}

	@Test
	public void test_GeneralExpression()
	{
		final IntegerStringExpression expr = expressionFactory.compile("@DecimalPart1@@DecimalPart2@", IntegerStringExpression.class);
		final Map<String, ? extends Object> ctx = ImmutableMap.of("DecimalPart1", 12, "DecimalPart2", "3456");
		final Integer value = expr.evaluate(Evaluatees.ofMap(ctx), OnVariableNotFound.Fail);
		Assert.assertEquals((Integer)123456, value);
	}

}
