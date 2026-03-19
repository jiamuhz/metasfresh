package de.metas.ui.web.invoicecandidate.process;

import de.metas.invoicecandidate.model.I_C_Invoice_Candidate;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Services;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.IQuery;

import java.util.Set;

 

/**
 * Process used to approve (for invoicing) the selected billing candidates.
 * <p>
 * This process is the webui alternative for swing's side action: de.metas.invoicecandidate.callout.IC_ApproveForInvoicing_Action
 *
 *
 * task https://github.com/metasfresh/metasfresh/issues/2361
 */
public class C_Invoice_Candidate_ApproveForInvoicing extends C_Invoice_Candidate_ProcessHelper
{
	@Override
	protected boolean isApproveForInvoicing()
	{
		return true;
	}

	/**
	 * Implementation detail: during `checkPreconditionsApplicable` `getProcessInfo` throws exception because it is not configured for the Process, so we ignore it.
	 */
	@Override
	protected IQuery<I_C_Invoice_Candidate> retrieveQuery(final boolean includeProcessInfoFilters)
	{
		final IQueryBuilder<I_C_Invoice_Candidate> queryBuilder = Services.get(IQueryBL.class).createQueryBuilder(I_C_Invoice_Candidate.class);
		if (includeProcessInfoFilters)
		{
			queryBuilder.filter(getProcessInfo().getQueryFilterOrElseFalse());
		}

		queryBuilder.addOnlyActiveRecordsFilter()
				.addNotEqualsFilter(I_C_Invoice_Candidate.COLUMN_Processed, true) // not processed
				.addNotEqualsFilter(I_C_Invoice_Candidate.COLUMN_ApprovalForInvoicing, true) // not already approved
		;

		// Only selected rows
		final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
		if (!selectedRowIds.isAll())
		{
			final Set<Integer> invoiceCandidateIds = selectedRowIds.toIntSet();
			if (invoiceCandidateIds.isEmpty())
			{
				// shall not happen
				throw new AdempiereException("@NoSelection@");
			}

			queryBuilder.addInArrayFilter(I_C_Invoice_Candidate.COLUMN_C_Invoice_Candidate_ID, invoiceCandidateIds);
		}

		return queryBuilder.create();
	}
}
