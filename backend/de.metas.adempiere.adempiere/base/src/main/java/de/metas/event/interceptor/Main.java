package de.metas.event.interceptor;

import com.google.common.collect.ImmutableSet;
import de.metas.event.model.I_AD_EventLog;
import de.metas.event.model.I_AD_EventLog_Entry;
import de.metas.event.model.X_AD_EventLog;
import de.metas.event.model.X_AD_EventLog_Entry;
import org.adempiere.ad.modelvalidator.AbstractModuleInterceptor;
import org.adempiere.ad.persistence.TableModelClassLoader;

import java.util.Set;

/** */

public class Main extends AbstractModuleInterceptor
{
	public static final Main INSTANCE = new Main();

	private Main()
	{
	}

	@Override
	protected void onAfterInit()
	{
		TableModelClassLoader.instance.registerSpecialClassName(I_AD_EventLog.Table_Name, X_AD_EventLog.class.getName());
		TableModelClassLoader.instance.registerSpecialClassName(I_AD_EventLog_Entry.Table_Name, X_AD_EventLog_Entry.class.getName());
	}

	@Override
	protected Set<String> getTableNamesToSkipOnMigrationScriptsLogging()
	{
		return ImmutableSet.of(
				I_AD_EventLog.Table_Name,
				I_AD_EventLog_Entry.Table_Name
		);
	}
}
