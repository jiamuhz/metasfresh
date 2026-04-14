package de.metas.ui.web.ztest.process;

import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessExecutionResult;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.ztest.myview.MyViewFactory;


public class WEBUI_MyProcess4IncludedViewLauncher extends ViewBasedProcessTemplate implements IProcessPrecondition
{
	private static final int MAX_ROWS_ALLOWED = 50;

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		return verifySelectedDocuments();
	}

	@Override
	protected String doIt()
	{
    addLog("######");
		return MSG_OK;
	}

	private ProcessPreconditionsResolution verifySelectedDocuments()
	{
/*
		final DocumentIdsSelection selectedRowIds = getSelectedRootDocumentIds();
		if (selectedRowIds.isEmpty())
		{
			return ProcessPreconditionsResolution.rejectBecauseNoSelection();
		}

		final long selectionSize = getSelectionSize(selectedRowIds);
		if (selectionSize > MAX_ROWS_ALLOWED)
		{
			return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_TOO_MANY_PACKAGEABLES_1P, MAX_ROWS_ALLOWED));
		}

		// Make sure that they all have the same C_BPartner and location.
		if (selectionSize > 1)
		{
			final Set<Integer> bpartnerLocationIds = getView().streamByIds(selectedRowIds)
					.flatMap(selectedRow -> selectedRow.getIncludedRows().stream())
					.map(WEBUI_MyProcess_Launcher::getBPartnerLocationId)
					.collect(Collectors.toSet());
			if (bpartnerLocationIds.size() > 1)
			{
				return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_DIVERGING_LOCATIONS));
			}
		}
*/

		return ProcessPreconditionsResolution.accept();
	}
}
