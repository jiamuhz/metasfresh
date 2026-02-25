package org.adempiere.ad.housekeeping.spi;

import de.metas.util.ILoggable;
import de.metas.util.Loggables;

/**
 * IStartupHouseKeepingTask 是一个系统启动时的维护任务接口，用于定义在应用程序启动完成后需要执行的初始化或清理工作
 */

@FunctionalInterface
public interface IStartupHouseKeepingTask
{
	/**
	 * Execute the housekeeping-task. Please log to an {@link ILoggable} obtained via {@link Loggables#get()}. The housekeeping engine provides one.
	 */
	void executeTask();
}
