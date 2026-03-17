package org.adempiere.ad.callout.spi;

import de.metas.util.ISingletonService;

/** */

/**
 * Internal. Please don't use it!
 * 
 *
 *
 */
public interface IDefaultCalloutProvider extends ICalloutProvider, ISingletonService
{
	// NOTE: the only reason why we have this interface here is to have all @Cached methods wired.
}
