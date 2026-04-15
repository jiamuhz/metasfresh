package de.metas.ui.web.bankstatement_reconciliation;

import java.util.Collection;

import com.google.common.collect.ImmutableSet;

import de.metas.banking.BankStatementLineId;
import de.metas.payment.PaymentId;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;


@Value
public class BanksStatementReconciliationViewCreateRequest
{
	ImmutableSet<BankStatementLineId> bankStatementLineIds;
	ImmutableSet<PaymentId> paymentIds;

	@Builder
	private BanksStatementReconciliationViewCreateRequest(
			@NonNull @Singular final Collection<BankStatementLineId> bankStatementLineIds,
			@NonNull @Singular final Collection<PaymentId> paymentIds)
	{
		Check.assumeNotEmpty(bankStatementLineIds, "bankStatementLineIds is not empty");
		Check.assumeNotEmpty(paymentIds, "paymentIds is not empty");

		this.bankStatementLineIds = ImmutableSet.copyOf(bankStatementLineIds);
		this.paymentIds = ImmutableSet.copyOf(paymentIds);
	}
}
