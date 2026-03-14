package org.adempiere.mm.attributes.api.impl;

/** */


import java.util.ArrayList;
import java.util.List;

import org.adempiere.ad.modelvalidator.IModelInterceptorRegistry;
import org.adempiere.mm.attributes.api.IModelAttributeSetInstanceListener;
import org.adempiere.mm.attributes.api.IModelAttributeSetInstanceListenerService;

import de.metas.util.Services;
import lombok.NonNull;

public class ModelAttributeSetInstanceListenerService implements IModelAttributeSetInstanceListenerService
{
	private final List<IModelAttributeSetInstanceListener> listeners = new ArrayList<>();

	@Override
	public void registerListener(@NonNull final IModelAttributeSetInstanceListener listener)
	{
		if (listeners.contains(listener))
		{
			return;
		}
		listeners.add(listener);

		final ModelAttributeSetInstanceListenerInterceptor listenerInterceptor = new ModelAttributeSetInstanceListenerInterceptor(listener);
		Services.get(IModelInterceptorRegistry.class)
				.addModelInterceptor(listenerInterceptor);
	}

}
