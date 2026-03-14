package org.adempiere.ad.dao.impl;

/** */


import java.sql.Timestamp;

import org.adempiere.ad.dao.impl.CompareQueryFilter.Operator;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.test.AdempiereTestHelper;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link AddHoursQueryFilterModifier} by using an {@link CompareQueryFilter}.
 *
 * @author tsa
 *
 */
public class AddHoursQueryFilterModifierTest
{
	public static interface ITestModel
	{
		String Table_Name = "TestModel";

		String COLUMNNAME_Date = "Date";

		Timestamp getDate();

		void setDate(Timestamp date);

		String COLUMNNAME_HoursToAdd = "HoursToAdd";

		int getHoursToAdd();

		void setHoursToAdd(int hoursToAdd);
	}

	@Before
	public void init()
	{
		AdempiereTestHelper.get().init();
	}

	private ITestModel createTestModel(final Timestamp date, final int hoursToAdd)
	{
		final ITestModel testModel = InterfaceWrapperHelper.create(Env.getCtx(), ITestModel.class, ITrx.TRXNAME_None);
		testModel.setDate(date);
		testModel.setHoursToAdd(hoursToAdd);
		InterfaceWrapperHelper.save(testModel);

		return testModel;
	}

	/**
	 * @param operand1_date
	 * @param operand1_hoursToAdd
	 * @param operator
	 * @param operand2
	 */
	private void assertComparation(final boolean matchedExpected,
			final Timestamp operand1_date, final int operand1_hoursToAdd,
			final Operator operator,
			final Timestamp operand2)
	{
		final ITestModel testModel = createTestModel(operand1_date, operand1_hoursToAdd);

		final CompareQueryFilter<ITestModel> filter = new CompareQueryFilter<>(ITestModel.COLUMNNAME_Date,
				operator,
				operand2,
				new AddHoursQueryFilterModifier(ITestModel.COLUMNNAME_HoursToAdd));

		assertSqlValid(operand1_date, operand1_hoursToAdd, operator, operand2);
		// TODO: validate SQL

		final boolean matchedActual = filter.accept(testModel);
		final String message = "Filter shall match: " + filter
				+ "\nOperand1: Date=" + operand1_date + ", HoursToAdd=" + operand1_hoursToAdd
				+ "\nOperator: " + operator
				+ "\nOperand2: " + operand2;
		Assert.assertEquals(message, matchedExpected, matchedActual);
	}

	private void assertSqlValid(Timestamp operand1_date, int operand1_hoursToAdd, Operator operator, Timestamp operand2)
	{
		// TODO Auto-generated method stub

	}

	@Test
	public void test_NoHoursToAdd()
	{
		final Timestamp date1 = TimeUtil.getDay(2014, 8, 10, 10, 30, 21); // 2014-08-10 10:30:21.0
		final Timestamp date2 = TimeUtil.getDay(2014, 8, 10, 14, 30, 21); // 2014-08-10 14:30:21.0

		final int operand1_hoursToAdd = 0;
		assertComparation(true, date1, operand1_hoursToAdd, Operator.LESS_OR_EQUAL, date2);
		assertComparation(true, date1, operand1_hoursToAdd, Operator.LESS, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.EQUAL, date2);
		assertComparation(true, date1, operand1_hoursToAdd, Operator.NOT_EQUAL, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.GREATER_OR_EQUAL, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.GREATER, date2);
	}

	@Test
	public void test_NoHoursToAdd_SameDate()
	{
		final Timestamp date1 = TimeUtil.getDay(2014, 8, 10, 10, 30, 21);
		final Timestamp date2 = TimeUtil.getDay(2014, 8, 10, 10, 30, 21);

		final int operand1_hoursToAdd = 0;
		assertComparation(true, date1, operand1_hoursToAdd, Operator.LESS_OR_EQUAL, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.LESS, date2);
		assertComparation(true, date1, operand1_hoursToAdd, Operator.EQUAL, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.NOT_EQUAL, date2);
		assertComparation(true, date1, operand1_hoursToAdd, Operator.GREATER_OR_EQUAL, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.GREATER, date2);
	}

	@Test
	public void test_SmallHoursToAdd_DontChangeTheResult()
	{
		final Timestamp date1 = TimeUtil.getDay(2014, 8, 10, 10, 30, 21);
		final Timestamp date2 = TimeUtil.getDay(2014, 8, 10, 14, 30, 21);

		final int operand1_hoursToAdd = 3;
		assertComparation(true, date1, operand1_hoursToAdd, Operator.LESS_OR_EQUAL, date2);
		assertComparation(true, date1, operand1_hoursToAdd, Operator.LESS, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.EQUAL, date2);
		assertComparation(true, date1, operand1_hoursToAdd, Operator.NOT_EQUAL, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.GREATER_OR_EQUAL, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.GREATER, date2);
	}

	@Test
	public void test_HoursToAdd_MakesTheDatesEqual()
	{
		final Timestamp date1 = TimeUtil.getDay(2014, 8, 10, 10, 30, 21);
		final Timestamp date2 = TimeUtil.getDay(2014, 8, 10, 14, 30, 21);

		final int operand1_hoursToAdd = 4;
		assertComparation(true, date1, operand1_hoursToAdd, Operator.LESS_OR_EQUAL, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.LESS, date2);
		assertComparation(true, date1, operand1_hoursToAdd, Operator.EQUAL, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.NOT_EQUAL, date2);
		assertComparation(true, date1, operand1_hoursToAdd, Operator.GREATER_OR_EQUAL, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.GREATER, date2);
	}

	@Test
	public void test_HoursToAdd_MakesTheDate2GreatherThanDate1()
	{
		final Timestamp date1 = TimeUtil.getDay(2014, 8, 10, 10, 30, 21);
		final Timestamp date2 = TimeUtil.getDay(2014, 8, 10, 14, 30, 21);

		final int operand1_hoursToAdd = 5;
		assertComparation(false, date1, operand1_hoursToAdd, Operator.LESS_OR_EQUAL, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.LESS, date2);
		assertComparation(false, date1, operand1_hoursToAdd, Operator.EQUAL, date2);
		assertComparation(true, date1, operand1_hoursToAdd, Operator.NOT_EQUAL, date2);
		assertComparation(true, date1, operand1_hoursToAdd, Operator.GREATER_OR_EQUAL, date2);
		assertComparation(true, date1, operand1_hoursToAdd, Operator.GREATER, date2);
	}

}
