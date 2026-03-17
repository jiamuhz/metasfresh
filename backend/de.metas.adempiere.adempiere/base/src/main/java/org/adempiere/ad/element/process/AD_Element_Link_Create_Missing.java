package org.adempiere.ad.element.process;

import org.adempiere.ad.element.api.IElementLinkBL;

import de.metas.process.JavaProcess;
import de.metas.util.Services;

/** */

public class AD_Element_Link_Create_Missing extends JavaProcess
{

	@Override
	protected String doIt() throws Exception
	{
		Services.get(IElementLinkBL.class).createMissingADElementLinks();

		return MSG_OK;
	}

}
