package de.metas.ui.web.bankstatement_reconciliation;

import java.util.List;

import com.google.common.collect.ImmutableList;

import de.metas.i18n.TranslatableStrings;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;


public class PaymentsToReconcileView extends AbstractCustomView<PaymentToReconcileRow>
{
	public static PaymentsToReconcileView cast(final IView view)
	{
		return (PaymentsToReconcileView)view;
	}

	@Getter
	private final ViewId bankStatementViewId;
	private final ImmutableList<RelatedProcessDescriptor> processes;

	@Builder
	private PaymentsToReconcileView(
			final ViewId bankStatementViewId,
			@NonNull final PaymentToReconcileRows rows,
			@NonNull final List<RelatedProcessDescriptor> processes)
	{
		super(
				bankStatementViewId.withWindowId(PaymentsToReconcileViewFactory.WINDOW_ID),
				TranslatableStrings.empty(),
				rows,
				NullDocumentFilterDescriptorsProvider.instance);

		this.bankStatementViewId = bankStatementViewId;
		this.processes = processes != null ? ImmutableList.copyOf(processes) : ImmutableList.of();
	}

	@Override
	public String getTableNameOrNull(final DocumentId documentId)
	{
		return null;
	}

	@Override
	public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors()
	{
		return processes;
	}

	@Override
	protected PaymentToReconcileRows getRowsData()
	{
		return PaymentToReconcileRows.cast(super.getRowsData());
	}

	@Override
	public ViewId getParentViewId()
	{
		return getBankStatementViewId();
	}
}
