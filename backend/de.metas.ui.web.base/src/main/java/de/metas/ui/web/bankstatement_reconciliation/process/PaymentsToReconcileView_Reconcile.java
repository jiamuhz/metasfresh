package de.metas.ui.web.bankstatement_reconciliation.process;

import de.metas.banking.payment.IBankStatementPaymentBL;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.bankstatement_reconciliation.actions.ReconcilePaymentsCommand;
import de.metas.ui.web.bankstatement_reconciliation.actions.ReconcilePaymentsRequest;
import de.metas.util.Services;


public class PaymentsToReconcileView_Reconcile extends PaymentsToReconcileViewBasedProcess
{
	private final IBankStatementPaymentBL bankStatmentPaymentBL = Services.get(IBankStatementPaymentBL.class);

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		return reconcilePayments().checkCanExecute();
	}

	@Override
	protected String doIt()
	{
		reconcilePayments().execute();

		// NOTE: usually this would not be needed but it seems frontend has some problems to refresh the left side view
		invalidateBankStatementReconciliationView();

		return MSG_OK;
	}

	private ReconcilePaymentsCommand reconcilePayments()
	{
		return ReconcilePaymentsCommand.builder()
				.msgBL(msgBL)
				.bankStatmentPaymentBL(bankStatmentPaymentBL)
				//
				.request(ReconcilePaymentsRequest.builder()
						.selectedBankStatementLine(getSingleSelectedBankStatementRowOrNull())
						.selectedPaymentsToReconcile(getSelectedPaymentToReconcileRows())
						.build())
				//
				.build();
	}
}
