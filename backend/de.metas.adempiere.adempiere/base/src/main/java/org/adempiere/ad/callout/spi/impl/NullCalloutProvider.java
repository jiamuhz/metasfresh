package org.adempiere.ad.callout.spi.impl;

import java.util.Properties;

import org.adempiere.ad.callout.api.TableCalloutsMap;
import org.adempiere.ad.callout.spi.ICalloutProvider;

/** */

/**
 * A {@link ICalloutProvider} which supplies no callouts.
 * 
 *
 *
 */
public final class NullCalloutProvider implements ICalloutProvider
{
	public static final transient NullCalloutProvider instance = new NullCalloutProvider();

	public static final boolean isNull(final ICalloutProvider provider)
	{
		return provider == null || provider == instance;
	}

	private NullCalloutProvider()
	{
		super();
	}

	@Override
	public TableCalloutsMap getCallouts(Properties ctx, String tableName)
	{
		return TableCalloutsMap.EMPTY;
	}

}
