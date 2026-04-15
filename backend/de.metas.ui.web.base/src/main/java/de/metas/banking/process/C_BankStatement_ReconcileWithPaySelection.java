package de.metas.banking.process;

import java.util.Set;

import de.metas.banking.PaySelectionId;
import de.metas.banking.payment.IPaySelectionBL;
import de.metas.payment.PaymentId;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.util.Services;
import lombok.NonNull;


public class C_BankStatement_ReconcileWithPaySelection extends BankStatementBasedProcess
{
	private final IPaySelectionBL paySelectionBL = Services.get(IPaySelectionBL.class);

	@Param(parameterName = "C_PaySelection_ID", mandatory = true)
	private PaySelectionId paySelectionId;

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable(@NonNull final IProcessPreconditionsContext context)
	{
		return checkBankStatementIsDraftOrInProcessOrCompleted(context)
				.and(() -> checkSingleLineSelectedWhichIsNotReconciled(context));
	}

	@Override
	protected String doIt()
	{
		final Set<PaymentId> paymentIds = paySelectionBL.getPaymentIds(paySelectionId);
		openBankStatementReconciliationView(paymentIds);
		return MSG_OK;
	}
}
