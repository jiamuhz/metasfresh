package org.adempiere.ad.modelvalidator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ModelChangeTypeTest
{
	@Test
	public void test_valueOf()
	{
		for (final ModelChangeType changeType : ModelChangeType.values())
		{
			assertThat(ModelChangeType.valueOf(changeType.toInt())).isSameAs(changeType);
		}
	}

	@Test
	public void test_isBeforeSaveTrx()
	{
		assertThat(ModelChangeType.isBeforeSaveTrx(ModelChangeType.AFTER_NEW)).isFalse();
		assertThat(ModelChangeType.isBeforeSaveTrx(ModelChangeType.BEFORE_SAVE_TRX)).isTrue();

		assertThat(ModelChangeType.isBeforeSaveTrx(DocTimingType.BEFORE_COMPLETE)).isFalse();
	}
}
