package de.metas.ui.web.bankstatement_reconciliation;

import java.util.List;

import javax.annotation.Nullable;

import de.metas.i18n.TranslatableStrings;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IViewRowOverrides;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;


public class BankStatementReconciliationView extends AbstractCustomView<BankStatementLineRow> implements IViewRowOverrides
{
	public static BankStatementReconciliationView cast(final IView view)
	{
		return (BankStatementReconciliationView)view;
	}

	@Getter
	private final PaymentsToReconcileView paymentsToReconcileView;

	@Builder
	private BankStatementReconciliationView(
			@NonNull final ViewId bankStatementViewId,
			final BankStatementLineAndPaymentsRows rows,
			@Nullable final List<RelatedProcessDescriptor> paymentToReconcilateProcesses)
	{
		super(bankStatementViewId,
				TranslatableStrings.empty(),
				rows.getBankStatementLineRows(),
				NullDocumentFilterDescriptorsProvider.instance);

		paymentsToReconcileView = PaymentsToReconcileView.builder()
				.bankStatementViewId(bankStatementViewId)
				.rows(rows.getPaymentToReconcileRows())
				.processes(paymentToReconcilateProcesses)
				.build();
	}

	@Override
	public String getTableNameOrNull(final DocumentId documentId)
	{
		return null;
	}

	@Override
	public ViewId getIncludedViewId(final IViewRow row_NOTUSED)
	{
		return paymentsToReconcileView.getViewId();
	}

	@Override
	protected BankStatementLineRows getRowsData()
	{
		return BankStatementLineRows.cast(super.getRowsData());
	}
}
