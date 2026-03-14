package org.adempiere.ad.dao.impl;

/** */

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class LpadQueryFilterModifierTest
{
	@Test
	public void test_convertValue()
	{
		final LpadQueryFilterModifier modifier = new LpadQueryFilterModifier(10, "0");
		testConvertValue(null, null, modifier);
		testConvertValue("0000000000", "", modifier);
		testConvertValue("0000000000", "         ", modifier);
		testConvertValue("0000000001", "1", modifier);
		testConvertValue("0000123456", "123456", modifier);
		testConvertValue("0000123456", "    123456    ", modifier);
		testConvertValue("1234567890", "1234567890", modifier);
		testConvertValue("1234567890", "12345678901234567890", modifier);
	}

	@Test
	public void test_convertValuePadIsSpace()
	{
		final LpadQueryFilterModifier modifier = new LpadQueryFilterModifier(10, " ");
		testConvertValue(null, null, modifier);
		testConvertValue("          ", "", modifier);
		testConvertValue("          ", "         ", modifier);
		testConvertValue("         1", "1", modifier);
		testConvertValue("    123456", "123456", modifier);
		testConvertValue("    123456", "    123456    ", modifier);
		testConvertValue("1234567890", "1234567890", modifier);
		testConvertValue("1234567890", "12345678901234567890", modifier);
	}

	private void testConvertValue(final String resultExpected, final String value, final LpadQueryFilterModifier modifier)
	{
		final String columnName = "Dummy"; // N/A
		final Object model = null; // N/A
		String resultActual = (String)modifier.convertValue(columnName, value, model);
		Assert.assertEquals("Invalid result for: " + value, resultExpected, resultActual);

	}
}
