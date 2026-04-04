 

package de.metas.ui.web.issues;

import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.serviceprovider.model.I_S_Issue;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.dao.ICompositeQueryUpdater;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.exceptions.AdempiereException;

public class WEBUI_ServicesCompanies_Toggle_Processed extends JavaProcess implements IProcessPrecondition
{
	private final IQueryBL queryBL = Services.get(IQueryBL.class);


	private static final String PARAM_PROCESSED = "Processed";
	@Param(parameterName = PARAM_PROCESSED, mandatory = true)
	private boolean processed;

	@Override
	protected String doIt() throws Exception
	{
		final IQueryBuilder<I_S_Issue> updateProcessedQuery = queryBL
				.createQueryBuilder(I_S_Issue.class)
				.addEqualsFilter(I_S_Issue.COLUMNNAME_Processed, !processed);

		final IQueryFilter<I_S_Issue> selectionFilter = getProcessInfo().getQueryFilterOrElse(null);
		if (selectionFilter == null)
		{
			throw new AdempiereException("@NoSelection@");
		}
		updateProcessedQuery.filter(selectionFilter);

		final ICompositeQueryUpdater<I_S_Issue> processedUpdater = queryBL
				.createCompositeQueryUpdater(I_S_Issue.class)
				.addSetColumnValue(I_S_Issue.COLUMNNAME_Processed, processed);

		updateProcessedQuery.create().update(processedUpdater);

		return MSG_OK;
	}

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable(final @NonNull IProcessPreconditionsContext context)
	{
		if (context.getSelectionSize().isNoSelection())
		{
			return ProcessPreconditionsResolution.rejectBecauseNoSelection();
		}

		return ProcessPreconditionsResolution.accept();
	}
}
