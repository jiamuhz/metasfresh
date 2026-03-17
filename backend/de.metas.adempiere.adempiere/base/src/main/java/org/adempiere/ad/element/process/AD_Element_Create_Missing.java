package org.adempiere.ad.element.process;

import org.adempiere.ad.element.api.IADElementDAO;

import de.metas.process.JavaProcess;
import de.metas.process.RunOutOfTrx;
import de.metas.translation.api.IElementTranslationBL;
import de.metas.util.Services;

/** */

public class AD_Element_Create_Missing extends JavaProcess
{
	@Override
	@RunOutOfTrx
	protected String doIt() throws Exception
	{
		Services.get(IElementTranslationBL.class).createAndAssignElementsToApplicationDictionaryEntries();

		return MSG_OK;
	}

	@Override
	protected void postProcess(final boolean success)
	{
		if (success)
		{
			Services.get(IADElementDAO.class).makeElementMandatoryInApplicationDictionaryTables();
		}
	}

}
