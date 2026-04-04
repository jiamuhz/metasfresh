package de.metas.banking.process;

import com.google.common.collect.ImmutableList;
import de.metas.banking.BankStatementLineId;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessPreconditionsResolution;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.I_C_BankStatement;
import org.compiere.model.I_C_BankStatementLine;

import java.util.Set;

 

public class C_BankStatement_UnReconcileLine extends BankStatementBasedProcess
{
	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable(@NonNull final IProcessPreconditionsContext context)
	{
		return checkBankStatementIsDraftOrInProcessOrCompleted(context)
				.and(() -> checkSingleLineSelectedWhichIsReconciled(context));
	}

	private ProcessPreconditionsResolution checkSingleLineSelectedWhichIsReconciled(@NonNull final IProcessPreconditionsContext context)
	{
		// there should be a single line selected
		final Set<TableRecordReference> bankStatemementLineRefs = context.getSelectedIncludedRecords();
		if (bankStatemementLineRefs.size() != 1)
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("a single line shall be selected");
		}

		final TableRecordReference bankStatemementLineRef = bankStatemementLineRefs.iterator().next();
		final BankStatementLineId bankStatementLineId = BankStatementLineId.ofRepoId(bankStatemementLineRef.getRecord_ID());
		final I_C_BankStatementLine line = bankStatementBL.getLineById(bankStatementLineId);
		if (!line.isReconciled())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("line shall be reconciled");
		}
		if (isReconciledByGLJournal(line))
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("GL Journal reconciliation");
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt()
	{
		final I_C_BankStatement bankStatement = getSelectedBankStatement();
		bankStatementBL.assertBankStatementIsDraftOrInProcessOrCompleted(bankStatement);

		final I_C_BankStatementLine bankStatementLine = getSingleSelectedBankStatementLine();
		if (isReconciledByGLJournal(bankStatementLine))
		{
			throw new AdempiereException("Clearing GL Journal reconciliation is not allowed. Consider reversing the GL Journal instead");
		}
		bankStatementBL.markAsNotReconciledAndDeleteReferences(ImmutableList.of(bankStatementLine));
		bankStatementBL.unpost(bankStatement);

		return MSG_OK;
	}

	private static boolean isReconciledByGLJournal(final I_C_BankStatementLine bankStatementLine)
	{
		return bankStatementLine.isReconciled() && bankStatementLine.getReconciledBy_SAP_GLJournalLine_ID() > 0;
	}

}
