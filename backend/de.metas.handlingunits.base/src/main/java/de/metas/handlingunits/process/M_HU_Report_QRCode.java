package de.metas.handlingunits.process;

import de.metas.global_qrcodes.service.QRCodePDFResource;
import de.metas.handlingunits.qrcodes.service.HUQRCodesService;
import de.metas.process.AdProcessId;
import de.metas.process.JavaProcess;
import de.metas.process.PInstanceId;
import de.metas.process.Param;
import de.metas.process.RunOutOfTrx;
import org.compiere.SpringContextHolder;



/**
 * Glue-code process to be called from M_HU_Report.
 * It takes M_HU_IDs from T_Selection, gets/generates QR-Codes for them
 * and then generate the PDF.
 */
public class M_HU_Report_QRCode extends JavaProcess
{
	private final HUQRCodesService huQRCodesService = SpringContextHolder.instance.getBean(HUQRCodesService.class);

	private static final String PARAM_AD_Process_ID = "AD_Process_ID";

	@Param(parameterName = PARAM_AD_Process_ID)
	private int processId;

	@Param(parameterName = "IsPrintPreview")
	private boolean isPrintPreview;

	@Override
	@RunOutOfTrx
	protected String doIt()
	{
		final PInstanceId selectionId = getPinstanceId();
		final AdProcessId qrCodeProcessId = AdProcessId.ofRepoId(processId);

		if (getProcessInfo().isPrintPreview())
		{
			final QRCodePDFResource pdf = huQRCodesService.createPdfForSelectionOfHUIds(selectionId, qrCodeProcessId);
			getResult().setReportData(pdf, pdf.getFilename(), pdf.getContentType());
		}
		else
		{
			huQRCodesService.printForSelectionOfHUIds(selectionId, qrCodeProcessId);
		}

		return MSG_OK;
	}
}
