package org.adempiere.ad.expression.api.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.adempiere.ad.expression.api.ExpressionContext;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IExpressionFactory;
import org.adempiere.ad.expression.api.impl.DateStringExpressionSupport.DateStringExpression;
import org.compiere.util.Evaluatees;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

import de.metas.util.Services;

/** */

public class DateStringExpressionSupportTest
{
	private IExpressionFactory expressionFactory;
	private ExpressionContext context;

	@Before
	public void init()
	{
		expressionFactory = Services.get(IExpressionFactory.class);

		context = ExpressionContext.builder()
				.putContext(DateStringExpression.PARAM_DateFormat, new SimpleDateFormat("yyyy-MM-dd"))
				.build();
	}

	@Test
	public void test_NullExpression()
	{
		final DateStringExpression expr = expressionFactory.compile("", DateStringExpression.class, context);
		Assert.assertTrue("Expect null expression: " + expr, expr.isNullExpression());
	}

	@Test
	public void test_ConstantExpression()
	{
		final Date valueExpected = new GregorianCalendar(2016, Calendar.JANUARY, 31).getTime();
		final DateStringExpression expr = expressionFactory.compile("2016-01-31", DateStringExpression.class, context);
		final Date value = expr.evaluate(Evaluatees.empty(), OnVariableNotFound.Fail);
		Assert.assertEquals(valueExpected, value);
	}

	@Test
	public void test_SingleParameterExpression()
	{
		final Date valueExpected = new GregorianCalendar(2016, Calendar.JANUARY, 31).getTime();
		final DateStringExpression expr = expressionFactory.compile("@Date@", DateStringExpression.class, context);
		final Date value = expr.evaluate(Evaluatees.ofSingleton("Date", "2016-01-31"), OnVariableNotFound.Fail);
		Assert.assertEquals(valueExpected, value);

	}

	@Test
	public void test_GeneralExpression()
	{
		final Date valueExpected = new GregorianCalendar(2016, Calendar.JANUARY, 31).getTime();
		final DateStringExpression expr = expressionFactory.compile("@Y@-@M@-@D@", DateStringExpression.class);
		final Map<String, ? extends Object> ctx = ImmutableMap.of("Y", "2016", "M", "01", "D", 31);
		final Date value = expr.evaluate(Evaluatees.ofMap(ctx), OnVariableNotFound.Fail);
		Assert.assertEquals(valueExpected, value);
	}

}
