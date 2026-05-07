package com.mchange.v2.resourcepool;

import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.compiere.util.Trace;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource;
import com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource_MetashfreshObserver;
import com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool;
import com.mchange.v2.c3p0.impl.C3P0PooledConnectionPoolManager;
import com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool_MetasfreshObserver;
import com.mchange.v2.resourcepool.BasicResourcePool.PunchCard;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BasicResourcePool_MetasfreshObserver
{
	public static List<String> getAquiredConnectionInfos(final ComboPooledDataSource dataSource) throws Exception
	{
		final AbstractPoolBackedDataSource poolBackedDataSource = AbstractPoolBackedDataSource.class.cast(dataSource);
		return getAquiredConnectionInfos(poolBackedDataSource);
	}

	private static List<String> getAquiredConnectionInfos(final AbstractPoolBackedDataSource poolBackedDataSource) throws SQLException
	{
		final C3P0PooledConnectionPoolManager poolManager = AbstractPoolBackedDataSource_MetashfreshObserver.getPoolManager(poolBackedDataSource);
		final C3P0PooledConnectionPool pool = poolManager.getPool();
		final ResourcePool resourcePool = C3P0PooledConnectionPool_MetasfreshObserver.getResourcePool(pool);
		final BasicResourcePool basicResourcePool = BasicResourcePool.class.cast(resourcePool);
		return getAquiredConnectionInfos(basicResourcePool);
	}

	private List<String> getAquiredConnectionInfos(@NonNull final BasicResourcePool pool)
	{
		synchronized (pool)
		{
			final ArrayList<String> result = new ArrayList<>();

			@SuppressWarnings("unchecked")
			final Map<Object, PunchCard> managed = new HashMap<>(pool.managed);
			@SuppressWarnings("unchecked")
			final List<Object> unused = new ArrayList<>(pool.unused);

			for (final Map.Entry<Object, PunchCard> e : managed.entrySet())
			{
				final Object resource = e.getKey();
				if (unused.contains(resource))
				{
					continue;
				}

				final PunchCard punchCard = e.getValue();
				result.add(toInfoString(punchCard));
			}

			return result;
		}
	}

	private static String toInfoString(final PunchCard connectionPunchCard)
	{
		final StringBuilder info = new StringBuilder();

		final Instant checkoutTime = Instant.ofEpochMilli(connectionPunchCard.checkout_time);
		info.append("checkout=").append(checkoutTime);

		// final Instant acquisitionTime = Instant.ofEpochMilli(connectionPunchCard.acquisition_time);
		// info.append(", acquired=").append(acquisitionTime);

		final Exception checkoutStackTraceException = connectionPunchCard.checkoutStackTraceException;
		if (checkoutStackTraceException != null)
		{
			final String stackTraceString = Trace.toOneLineStackTraceString(checkoutStackTraceException.getStackTrace());
			info.append(", stacktrace=").append(stackTraceString);
		}

		return info.toString();
	}

}
