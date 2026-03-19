package de.metas.handlingunits.process;

import de.metas.process.AdProcessId;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import lombok.NonNull;



/**
 * It takes M_HU_IDs from T_Selection, gets/generates QR-Codes for them
 * and then generate the PDF that will contain the QR Code and more detailed product infos.
 */

public class M_HU_Report_Print_Labels extends M_HU_Report_Print_Template
{
	@Param(mandatory = true, parameterName = "AD_Process_ID")
	private AdProcessId p_AD_Process_ID;

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
	protected AdProcessId getPrintFormatProcessId() {return p_AD_Process_ID;}
}
