package org.adempiere.ad.expression.api.impl;

/** */

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.exceptions.ExpressionCompileException;
import org.compiere.util.MockedEvaluatee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class StringExpressionCompilerTests
{
	private StringExpressionCompiler compiler;

	@Before
	public void init()
	{
		compiler = StringExpressionCompiler.instance;
	}

	@Test
	public void test_compileStringExpression_getParameters()
	{
		{
			final String sql = "C_BPartner_ID=@C_BPartner_Override_ID/-1@ AND C_BPartner_Location_ID=@C_BPartner_Location_Override_ID/-1@";
			final IStringExpression expression = compiler.compile(sql);
			final Set<String> dependsActual = expression.getParameterNames();
			final Set<String> dependsExpected = ImmutableSet.of("C_BPartner_Override_ID", "C_BPartner_Location_Override_ID");
			assertEquals(dependsExpected, dependsActual);
		}
		{
			// test if is also works with strings and with nested '-signs
			final String sql = "Type='@StringVar/'NONE'@' AND C_BPartner_Location_ID=@IntVar_ID/-1@ AND Type2='@NoDefaultStringVar@'";
			final IStringExpression expression = compiler.compile(sql);
			final Set<String> dependsActual = expression.getParameterNames();
			final Set<String> dependsExpected = ImmutableSet.of("StringVar", "IntVar_ID", "NoDefaultStringVar");
			assertEquals(dependsExpected, dependsActual);
		}
	}

	@Test
	public void test_compileStringExpression_emptyTag()
	{
		final String expressionStr = "C_BPartner_ID=@C_BPartner_ID@ AND Text='@@'";
		final IStringExpression expression = compiler.compile(expressionStr);
		final Set<String> expectedParams = ImmutableSet.of("C_BPartner_ID");
		assertEquals("Invalid params", expectedParams, expression.getParameterNames());

		assertEquals("Formated expression shall be equal to initial expression", expressionStr, expression.getFormatedExpressionString());

		// Try to evaluate it
		final MockedEvaluatee ctx = new MockedEvaluatee();
		ctx.put("C_BPartner_ID", "123");
	}

	@Test(expected = ExpressionCompileException.class)
	public void test_compileStringExpression_noClosingTag()
	{
		final String expressionStr = "C_BPartner_ID=@C_BPartner_ID and closing tag is missing";
		final IStringExpression expression = compiler.compile(expressionStr);
		Assert.fail("Expression '" + expressionStr + "' shall not be compiled to: " + expression);
	}

	@Test
	public void test_compileStringExpression_NullExpression()
	{
		Assert.assertSame(IStringExpression.NULL, compiler.compile(null));
		Assert.assertSame(IStringExpression.NULL, compiler.compile(""));

		// empty expressions with wildcards shall be compiled to a regular expression
		Assert.assertNotSame(IStringExpression.NULL, compiler.compile("   "));
	}

	@Test
	public void test_compileStringExpression_EmptyExpressionWithSpaces()
	{
		// empty expressions with wildcards shall be compiled to a regular expression
		final String expressionStr = "     ";
		final IStringExpression expression = compiler.compile(expressionStr);
		Assert.assertNotSame("Empty expressions with wildcards shall be compiled to a regular expression", IStringExpression.NULL, expression);
		Assert.assertEquals(expressionStr, expression.getFormatedExpressionString());
	}

	@Test
	public void test_compileStringExpression_WithNoParameters()
	{
		final String expressionStr = "C_BPartner_Location.C_BPartner_ID=bp.C_BPartner_ID and no parameters";
		final ConstantStringExpression expression = (ConstantStringExpression)compiler.compile(expressionStr);
		Assert.assertEquals(expressionStr, expression.getFormatedExpressionString());
	}

	@Test
	public void test_compileStringExpression_StartingWithParameter()
	{
		final String expressionStr = "@C_BPartner_ID@=C_BPartner_ID";
		final IStringExpression expression = compiler.compile(expressionStr);
		Assert.assertEquals(expressionStr, expression.getFormatedExpressionString());
	}

}
