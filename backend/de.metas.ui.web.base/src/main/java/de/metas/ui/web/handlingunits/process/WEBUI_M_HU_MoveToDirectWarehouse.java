package de.metas.ui.web.handlingunits.process;

import de.metas.common.util.time.SystemTime;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.DocumentCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;



/**
 * HU Editor: Move selected HU to direct warehouse (aka Materialentnahme)
 *
 *
 *
 */
public class WEBUI_M_HU_MoveToDirectWarehouse extends HUEditorProcessTemplate implements IProcessPrecondition
{
	@Autowired
	private DocumentCollection documentsCollection;

	private static final HUEditorRowFilter rowsFilter = HUEditorRowFilter.builder().select(Select.ONLY_TOPLEVEL).onlyActiveHUs().build();

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if(!isHUEditorView())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
		}

		final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
		if (selectedRowIds.isEmpty())
		{
			return ProcessPreconditionsResolution.rejectBecauseNoSelection();
		}

		if (!streamSelectedHUIds(rowsFilter).findAny().isPresent())
		{
			return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_HU_Constants.MSG_WEBUI_ONLY_TOP_LEVEL_HU));
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	@RunOutOfTrx
	protected String doIt()
	{
		final Stream<I_M_HU> selectedTopLevelHUs = streamSelectedHUs(rowsFilter);
		HUMoveToDirectWarehouseService.newInstance()
				.setDocumentsCollection(documentsCollection)
				.setHUView(getView())
				.setMovementDate(SystemTime.asInstant()) // now
				// .setDescription(description) // none
				.setFailOnFirstError(true)
				.setFailIfNoHUs(true)
				.setLoggable(this)
				.move(selectedTopLevelHUs.iterator());

		return MSG_OK;
	}
}
