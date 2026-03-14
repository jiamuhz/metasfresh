package de.metas.logging;

import org.slf4j.Logger;

/** */


/**
 * Implementations of this interface are used to customize {@link Logger} when they are returned to callers.
 *
 * @author tsa
 *
 */
public interface ILoggerCustomizer
{
	void customize(Logger logger);

	/**
	 *
	 * @return information that is useful to understand the customizer's behavior
	 * @task https://github.com/metasfresh/metasfresh/issues/288
	 */
	String dumpConfig();
}
