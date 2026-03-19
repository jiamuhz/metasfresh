package de.metas.handlingunits.process;

import de.metas.process.AdProcessId;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessPreconditionsResolution;
import lombok.NonNull;



/**
 * It takes M_HU_IDs (LUs) from T_Selection, gets/generates QR-Codes for them
 * and then generate the PDF that will contain the QR Code and more detailed product infos.
 */

public class M_HU_Report_LU_Label extends M_HU_Report_Print_Template
{
	/**
	 * Process Value: LU_Label_QRCode
	 * JasperReport: @PREFIX@de/metas/docs/label/label_lu.jasper
	 */
	private static final AdProcessId LU_QR_LABEL_PROCESS_ID = AdProcessId.ofRepoId(584998); // FIXME hard coded process id

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable(final @NonNull IProcessPreconditionsContext context)
	{
		if (context.isMoreThanOneSelected())
		{
			return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected AdProcessId getPrintFormatProcessId() {return LU_QR_LABEL_PROCESS_ID;}

}
