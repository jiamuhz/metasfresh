package org.adempiere.ad.callout.api.impl;

/** */

import java.util.List;
import java.util.Properties;

import org.adempiere.ad.callout.api.ICalloutFactory;
import org.adempiere.ad.callout.api.TableCalloutsMap;
import org.adempiere.ad.callout.spi.CompositeCalloutProvider;
import org.adempiere.ad.callout.spi.ICalloutProvider;
import org.adempiere.ad.callout.spi.IDefaultCalloutProvider;
import org.adempiere.ad.callout.spi.impl.NullCalloutProvider;
import org.slf4j.Logger;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;

import de.metas.logging.LogManager;
import de.metas.util.Services;

public class CalloutFactory implements ICalloutFactory
{
	private static final transient Logger logger = LogManager.getLogger(CalloutFactory.class);
	private ICalloutProvider providers = ICalloutProvider.NULL;

	public CalloutFactory()
	{
		// Register standard providers
		registerCalloutProvider(Services.get(IDefaultCalloutProvider.class));

		// NOTE: IProgramaticCalloutProvider will be registered just in time
		// registerCalloutProvider(Services.get(IProgramaticCalloutProvider.class));
	}

	@Override
	public void registerCalloutProvider(final ICalloutProvider providerToAdd)
	{
		final ICalloutProvider providersOld = providers;
		final ICalloutProvider providersNew = CompositeCalloutProvider.compose(providersOld, providerToAdd);

		if (providersNew == providersOld)
		{
			return;
		}

		providers = providersNew;
		logger.info("Registered provider: {}", providerToAdd);
	}

	/**
	 *
	 * @return registered providers
	 */
	@VisibleForTesting
	final List<ICalloutProvider> getCalloutProvidersList()
	{
		final ICalloutProvider providers = this.providers;
		if (providers instanceof CompositeCalloutProvider)
		{
			return ((CompositeCalloutProvider)providers).getProvidersList();
		}
		else if (NullCalloutProvider.isNull(providers))
		{
			return ImmutableList.of();
		}
		else
		{
			return ImmutableList.of(providers);
		}
	}

	@Override
	public ICalloutProvider getProvider()
	{
		return providers;
	}

	@Override
	public TableCalloutsMap getCallouts(final Properties ctx, final String tableName)
	{
		return providers.getCallouts(ctx, tableName);
	}
}
