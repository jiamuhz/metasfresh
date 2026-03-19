package de.metas.ui.web.handlingunits.process;

 

import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.ProcessPreconditionsResolution;
import java.util.List;

/**
 * #2144
 * HU editor: Move selected HUs to another warehouse
 *
 *
 * <p>
 * This process is completely similar with the basic structure for HU moving process.
 */
public class WEBUI_M_HU_MoveToAnotherWarehouse_InclQuarantined extends WEBUI_M_HU_MoveToAnotherWarehouse_Template
{

	@Override
	public ProcessPreconditionsResolution checkHUsEligible(final List<I_M_HU> hus)
	{
		// Nothing to do. This process includes also quarantine HUs
		return ProcessPreconditionsResolution.accept();
	}
}
