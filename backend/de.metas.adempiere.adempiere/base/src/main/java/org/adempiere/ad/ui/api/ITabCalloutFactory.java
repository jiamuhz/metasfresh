package org.adempiere.ad.ui.api;

import org.adempiere.ad.callout.api.ICalloutRecord;

/** */


import org.adempiere.ad.ui.spi.ITabCallout;
import org.compiere.model.StateChangeEvent;

import de.metas.util.ISingletonService;

/**
 * Creates and registers {@link ICalloutRecord}'s {@link ITabCallout}s.
 *
 * @author tsa
 *
 */
public interface ITabCalloutFactory extends ISingletonService
{
	/**
	 * Creates new {@link ITabCallout} instances from registered callouts of given tab.
	 *
	 * This method will make sure to intercept {@link ICalloutRecord}'s {@link StateChangeEvent}s and call the right callout methods.
	 *
	 * @return instantiated tab callouts.
	 */
	ITabCallout createAndInitialize(ICalloutRecord calloutRecord);

	/**
	 * Programmatically registers a {@link ITabCallout} to all {@link ICalloutRecord}s which are about given <code>tableName</code>.
	 */
	void registerTabCalloutForTable(String tableName, Class<? extends ITabCallout> tabCalloutClass);

	void unregisterTabCalloutForTable(String tableName, Class<? extends ITabCallout> tabCalloutClass);
}
