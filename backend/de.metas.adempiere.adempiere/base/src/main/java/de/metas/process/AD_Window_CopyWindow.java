package de.metas.process;

import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.window.api.ADWindowService;
import org.adempiere.ad.window.api.WindowCopyRequest;
import org.compiere.SpringContextHolder;

/** */

public class AD_Window_CopyWindow extends JavaProcess
{
	private static final String PARAM_Source_AD_Window_ID = "AD_Window_ID";
	@Param(parameterName = PARAM_Source_AD_Window_ID, mandatory = true)
	private AdWindowId sourceWindowId;

	@Param(parameterName = "IsCustomizationWindow", mandatory = true)
	private boolean isCustomizationWindow;

	private final ADWindowService adWindowService = SpringContextHolder.instance.getBean(ADWindowService.class);

	@Override
	protected String doIt()
	{
		adWindowService.copyWindow(WindowCopyRequest.builder()
				.sourceWindowId(sourceWindowId)
				.targetWindowId(AdWindowId.ofRepoId(getProcessInfo().getRecord_ID()))
				.isCustomizationWindow(isCustomizationWindow)
				.build());

		return MSG_OK;
	}

}
