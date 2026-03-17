package org.adempiere.model;

/** */

import java.util.Properties;

import org.adempiere.util.lang.IContextAware;

/**
 * Note: use {@link InterfaceWrapperHelper#getContextAware(Object)} to get an instance.
 *
 */
/* package */final class ModelContextAware implements IContextAware
{
	private final Object model;

	public ModelContextAware(final Object model)
	{
		// we allow null values because we want that InterfaceWrapperHelper.getContextAware
		// ... to not return null for nulls
		// Check.assumeNotNull(model, "model not null");
		this.model = model;
	}

	@Override
	public Properties getCtx()
	{
		return InterfaceWrapperHelper.getCtx(model);
	}

	public Properties getCtx(boolean useClientOrgFromModel)
	{
		return InterfaceWrapperHelper.getCtx(model, useClientOrgFromModel);
	}

	@Override
	public String getTrxName()
	{
		return InterfaceWrapperHelper.getTrxName(model);
	}

}
