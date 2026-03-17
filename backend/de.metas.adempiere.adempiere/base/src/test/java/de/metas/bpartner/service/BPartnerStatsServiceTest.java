package de.metas.bpartner.service;

import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.service.impl.CalculateCreditStatusRequest;
import de.metas.common.util.time.SystemTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static java.math.BigDecimal.ZERO;

/** */

public class BPartnerStatsServiceTest
{
	@Test
	public void build_CalculateSOCreditStatusRequest()
	{
		final BPartnerStats stat = BPartnerStats.builder()
				.repoId(20)
				.bpartnerId(BPartnerId.ofRepoId(10))
				.build();

		final CalculateCreditStatusRequest calculateCreditStatusRequest = CalculateCreditStatusRequest.builder()
				.date(SystemTime.asTimestamp())
				.additionalAmt(null)
				.stat(stat)
				.build();

		assertThat(calculateCreditStatusRequest.getAdditionalAmt()).isEqualByComparingTo(ZERO);
	}

}
