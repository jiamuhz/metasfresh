package de.metas.ui.web.handlingunits.process;

import de.metas.Profiles;
import de.metas.handlingunits.report.HUToReport;
import de.metas.handlingunits.report.labels.HULabelPrintRequest;
import de.metas.handlingunits.report.labels.HULabelService;
import de.metas.handlingunits.report.labels.HULabelSourceDocType;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.report.PrintCopies;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import org.compiere.SpringContextHolder;
import org.springframework.context.annotation.Profile;


@Profile(Profiles.PROFILE_Webui)
public class WEBUI_M_HU_PrintReceiptLabel
		extends HUEditorProcessTemplate
		implements IProcessPrecondition
{
	private final HULabelService huLabelService = SpringContextHolder.instance.getBean(HULabelService.class);

	@Param(mandatory = true, parameterName = "Copies")
	private int p_copies = 1;

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (!isHUEditorView())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
		}

		if (!getSelectedRowIds().isSingleDocumentId())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("No (single) row selected");
		}

		final HUToReport hu = getSingleSelectedRow().getAsHUToReportOrNull();
		if (hu == null)
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("No (single) HU selected");
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	@RunOutOfTrx
	protected String doIt()
	{
		final HUToReport hu = getSingleSelectedRow().getAsHUToReport();

		huLabelService.print(HULabelPrintRequest.builder()
				.sourceDocType(HULabelSourceDocType.MaterialReceipt)
				.hu(hu)
				.printCopiesOverride(PrintCopies.ofInt(p_copies))
				.failOnMissingLabelConfig(true)
				.build());

		return MSG_OK;
	}
}
