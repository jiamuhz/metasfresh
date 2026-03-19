package de.metas.ui.web.handlingunits.process;

import org.adempiere.util.lang.MutableInt;

import java.util.Objects;

 

import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;

/**
 * Common base class to dedupliate code.
 *
 *
 *
 */
public abstract class WEBUI_M_HU_Receipt_Base
		extends ViewBasedProcessTemplate
		implements IProcessPrecondition
{
	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (!isViewClass(HUEditorView.class))
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("The current view is not an HUEditorView");
		}

		final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
		if (selectedRowIds.isEmpty())
		{
			return ProcessPreconditionsResolution.rejectBecauseNoSelection();
		}

		final MutableInt checkedDocumentsCount = new MutableInt(0);
		final ProcessPreconditionsResolution firstRejection = getView(HUEditorView.class)
				.streamByIds(selectedRowIds)
				.filter(document -> document.isPureHU())

				.peek(document -> checkedDocumentsCount.incrementAndGet()) // count checked documents
				.map(document -> rejectResolutionOrNull(document)) // create reject resolution if any
				.filter(Objects::nonNull) // filter out those which are not errors
				.findFirst()
				.orElse(null);
		if (firstRejection != null)
		{
			// found a record which is not eligible => don't run the process
			return firstRejection;
		}
		if (checkedDocumentsCount.getValue() <= 0)
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("no eligible rows");
		}

		return ProcessPreconditionsResolution.accept();
	}

	/**
	 * Check the individual given row, to find out if this process can be applied to it or not.
	 *
	 * @return {@code null} if there is no reason to reject the given {@code document}.
	 */
	abstract ProcessPreconditionsResolution rejectResolutionOrNull(HUEditorRow document);

}
