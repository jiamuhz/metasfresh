package org.adempiere.ad.element.process;

import org.adempiere.ad.element.api.IADElementDAO;
import org.adempiere.ad.housekeeping.spi.IStartupHouseKeepingTask;
import org.springframework.stereotype.Component;

import de.metas.translation.api.IElementTranslationBL;
import de.metas.util.Services;

/** */

@Component
public class AD_Element_CreateMissing_HousekeepingTask implements IStartupHouseKeepingTask
{

	@Override
	public void executeTask()
	{
		Services.get(IElementTranslationBL.class).createAndAssignElementsToApplicationDictionaryEntries();
		Services.get(IADElementDAO.class).makeElementMandatoryInApplicationDictionaryTables();
	}
}
