package org.adempiere.ad.callout.spi;

/** */

import org.adempiere.ad.callout.api.ICalloutInstance;
import org.adempiere.ad.modelvalidator.AbstractModuleInterceptor;

import de.metas.util.ISingletonService;

/**
 * Instances can be used to register callouts "programatically", i.e. not by database.
 * The preferred way to obtain an instance of this interface is to subclass {@link AbstractModuleInterceptor} and override its {@link AbstractModuleInterceptor#registerCallouts(IProgramaticCalloutProvider) registerCallouts} method.<br>
 * In other words, programatic callouts should be registered per-module and at one place within each module.<br>
 * However, since this interface extends {@link ISingletonService}, an instance can also be obtained via {@link de.metas.util.Services#get(Class)}.
 *
 *
 *
 */
public interface IProgramaticCalloutProvider extends ICalloutProvider, ISingletonService
{
	boolean registerCallout(String tableName, String columnName, ICalloutInstance callout);

	boolean registerAnnotatedCallout(Object annotatedCalloutObj);
}
