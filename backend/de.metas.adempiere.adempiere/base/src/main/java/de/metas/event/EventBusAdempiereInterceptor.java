package de.metas.event;

/** */


import org.adempiere.ad.modelvalidator.AbstractModuleInterceptor;
import org.adempiere.ad.session.MFSession;
import org.compiere.util.Ini;

import de.metas.util.Services;

/**
 * Module activator which is initializing the {@link IEventBus} infrastructure.
 *
 * @author tsa
 *
 */
public final class EventBusAdempiereInterceptor extends AbstractModuleInterceptor
{
	public static final transient EventBusAdempiereInterceptor instance = new EventBusAdempiereInterceptor();

	private EventBusAdempiereInterceptor()
	{
		super();
	}

	@Override
	public void onUserLogin(int AD_Org_ID, int AD_Role_ID, int AD_User_ID)
	{
		if (Ini.isSwingClient())
		{
			Services.get(IEventBusFactory.class).initEventBussesWithGlobalListeners();
		}
	}

	@Override
	public void beforeLogout(final MFSession session)
	{
		if (Ini.isSwingClient())
		{
			Services.get(IEventBusFactory.class).destroyAllEventBusses();
		}
	}

}
