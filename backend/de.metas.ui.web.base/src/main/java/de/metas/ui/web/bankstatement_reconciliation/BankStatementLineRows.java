package de.metas.ui.web.bankstatement_reconciliation;

import java.util.List;

import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.model.I_C_BankStatementLine;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import de.metas.banking.BankStatementLineId;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.view.template.SynchronizedRowsIndexHolder;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.Builder;
import lombok.NonNull;



public class BankStatementLineRows implements IRowsData<BankStatementLineRow>
{
	public static BankStatementLineRows cast(final IRowsData<BankStatementLineRow> rowsData)
	{
		return (BankStatementLineRows)rowsData;
	}

	private final BankStatementLineAndPaymentsToReconcileRepository repository;
	private final SynchronizedRowsIndexHolder<BankStatementLineRow> rowsHolder;

	@Builder
	private BankStatementLineRows(
			@NonNull final BankStatementLineAndPaymentsToReconcileRepository repository,
			@NonNull final List<BankStatementLineRow> rows)
	{
		this.repository = repository;
		this.rowsHolder = SynchronizedRowsIndexHolder.of(rows);
	}

	@Override
	public ImmutableMap<DocumentId, BankStatementLineRow> getDocumentId2TopLevelRows()
	{
		return rowsHolder.getDocumentId2TopLevelRows();
	}

	@Override
	public DocumentIdsSelection getDocumentIdsToInvalidate(@NonNull final TableRecordReferenceSet recordRefs)
	{
		return recordRefs.streamIds(I_C_BankStatementLine.Table_Name, BankStatementLineId::ofRepoId)
				.map(BankStatementLineRow::convertBankStatementLineIdToDocumentId)
				.filter(rowsHolder.isRelevantForRefreshingByDocumentId())
				.collect(DocumentIdsSelection.toDocumentIdsSelection());
	}

	@Override
	public void invalidateAll()
	{
		invalidate(DocumentIdsSelection.ALL);
	}

	@Override
	public void invalidate(final DocumentIdsSelection rowIds)
	{
		final ImmutableSet<BankStatementLineId> bankStatementLineIds = rowsHolder
				.getRecordIdsToRefresh(rowIds, BankStatementLineRow::convertDocumentIdToBankStatementLineId);

		final List<BankStatementLineRow> newRows = repository.getBankStatementLineRowsByIds(bankStatementLineIds);
		rowsHolder.compute(rows -> rows.replacingRows(rowIds, newRows));
	}
}
