package org.compiere.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.adempiere.ad.wrapper.POJOLookupMap;
import org.adempiere.test.AdempiereTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/** */

public class POUtilsTest
{
	private PO po;

	@BeforeEach
	public void init()
	{
		AdempiereTestHelper.get().init();
		
		po = Mockito.mock(PO.class);
	}

	@Test
	public void stripZerosAndLogIssueIfBigDecimalScaleTooBig_too_many_zeroes_strip_zeros()
	{
		final Object result = POUtils.stripZerosAndLogIssueIfBigDecimalScaleTooBig(new BigDecimal("1.123000000000000000000000000000000000"), po);
		assertThat(result).isEqualTo(new BigDecimal("1.123"));
		assertThat(POJOLookupMap.get().getRecords(I_AD_Issue.class)).hasSize(1);
	}

	@Test
	public void stripZerosAndLogIssueIfBigDecimalScaleTooBig_too_many_zeroes_dont_strip_nonzero()
	{
		final Object result = POUtils.stripZerosAndLogIssueIfBigDecimalScaleTooBig(new BigDecimal("1.123456789012345678901234567800000000"), po);
		assertThat(result).isEqualTo(new BigDecimal("1.1234567890123456789012345678"));
		assertThat(POJOLookupMap.get().getRecords(I_AD_Issue.class)).hasSize(1);
	}

	@Test
	public void stripZerosAndLogIssueIfBigDecimalScaleTooBig_dont_change_value()
	{
		final BigDecimal value = new BigDecimal("1.12345");
		final Object result = POUtils.stripZerosAndLogIssueIfBigDecimalScaleTooBig(value, po);
		assertThat(result).isSameAs(value);
		assertThat(POJOLookupMap.get().getRecords(I_AD_Issue.class)).isEmpty();
	}

	@Test
	public void stripZerosAndLogIssueIfBigDecimalScaleTooBig_null()
	{
		final Object result = POUtils.stripZerosAndLogIssueIfBigDecimalScaleTooBig(null, po);
		assertThat(result).isNull();
		assertThat(POJOLookupMap.get().getRecords(I_AD_Issue.class)).isEmpty();
	}

	@Test
	public void stripZerosAndLogIssueIfBigDecimalScaleTooBig_other_object()
	{
		final String someOtherValue = "not a BigDecimal instace";
		final Object result = POUtils.stripZerosAndLogIssueIfBigDecimalScaleTooBig(someOtherValue, po);
		assertThat(result).isSameAs(someOtherValue);
		assertThat(POJOLookupMap.get().getRecords(I_AD_Issue.class)).isEmpty();
	}
}
