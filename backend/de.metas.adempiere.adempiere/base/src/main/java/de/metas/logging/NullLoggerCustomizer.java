package de.metas.logging;

import org.slf4j.Logger;

/** */

/**
 * Do nothing {@link ILoggerCustomizer} implementation.
 *
 * @author tsa
 *
 */
public final class NullLoggerCustomizer implements ILoggerCustomizer
{
	public static final transient NullLoggerCustomizer instance = new NullLoggerCustomizer();

	private NullLoggerCustomizer()
	{
		super();
	}

	@Override
	public void customize(final Logger logger)
	{
		// do nothing
	}

	@Override
	public String dumpConfig()
	{
		return getClass().getName() + " has no config to dump";
	}

}
