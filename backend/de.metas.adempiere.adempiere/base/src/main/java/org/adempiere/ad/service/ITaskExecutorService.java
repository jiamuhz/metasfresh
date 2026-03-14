package org.adempiere.ad.service;

/** */

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import de.metas.util.ISingletonService;

/**
 * Service responsible for executing asynchronous tasks. Maintain a map of thread pool executors, one for each thread name prefix.
 *
 * @author tsa
 *
 */
public interface ITaskExecutorService extends ISingletonService
{
	/**
	 * Shut down and destroy all the internal thread pool executors. Successive invocations of a <code>submit</code> or <code>schedule</code> method shall recreate them.
	 */
	void destroy();

	/**
	 * Submit a task to be executed asynchronously, "as soon as possible".
	 *
	 * @param task
	 * @param theadNamePrefix if there is no thread pool executor for the given theadNamePrefix, create one and store it for future use.
	 * @return
	 */
	<T> Future<T> submit(Callable<T> task, String theadNamePrefix);

	/**
	 * Submit a task to be executed asynchronously, "as soon as possible".
	 *
	 * @param task
	 * @param theadNamePrefix if there is no thread pool executor for the given theadNamePrefix, create one and store it for future use.
	 * @return
	 */
	Future<?> submit(Runnable task, String theadNamePrefix);

	/**
	 * Submit a task to be executed asynchronously, after the given time interval has passed.
	 *
	 * @param task
	 * @param time
	 * @param timeUnit
	 * @param theadNamePrefix if there is no thread pool executor for the given theadNamePrefix, create one and store it for future use.
	 * @return
	 */
	<T> ScheduledFuture<T> schedule(Callable<T> task, int time, TimeUnit timeUnit, String theadNamePrefix);
}
