package de.metas.websocket.sender;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import de.metas.websocket.WebsocketTopicName;
import org.slf4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import de.metas.logging.LogManager;

import javax.annotation.Nullable;

 
final class WebsocketEventsLog
{
	private static final Logger logger = LogManager.getLogger(WebsocketEventsLog.class);

	private final AtomicBoolean logEventsEnabled = new AtomicBoolean(false);
	private final AtomicInteger logEventsMaxSize = new AtomicInteger(500);
	private final List<WebsocketEventLogRecord> loggedEvents = new LinkedList<>();

	public void logEvent(final WebsocketTopicName destination, @Nullable final Object event)
	{
		if (!logEventsEnabled.get())
		{
			return;
		}

		synchronized (loggedEvents)
		{
			logger.info("{}: {}", destination, event);

			loggedEvents.add(new WebsocketEventLogRecord(destination, event));
			final int maxSize = logEventsMaxSize.get();
			while (loggedEvents.size() > maxSize)
			{
				loggedEvents.remove(0);
			}
		}
	}

	public void setLogEventsEnabled(final boolean enabled)
	{
		final boolean enabledOld = logEventsEnabled.getAndSet(enabled);
		logger.info("Changed logEventsEnabled from {} to {}", enabledOld, enabled);
	}

	public void setLogEventsMaxSize(final int logEventsMaxSizeNew)
	{
		Preconditions.checkArgument(logEventsMaxSizeNew > 0, "logEventsMaxSize > 0");
		final int logEventsMaxSizeOld = logEventsMaxSize.getAndSet(logEventsMaxSizeNew);
		logger.info("Changed logEventsMaxSize from {} to {}", logEventsMaxSizeOld, logEventsMaxSizeNew);
	}

	public List<WebsocketEventLogRecord> getLoggedEvents()
	{
		synchronized (loggedEvents)
		{
			return new ArrayList<>(loggedEvents);
		}
	}

	public List<WebsocketEventLogRecord> getLoggedEvents(final String destinationFilter)
	{
		return getLoggedEvents()
				.stream()
				.filter(websocketEvent -> websocketEvent.isDestinationMatching(destinationFilter))
				.collect(ImmutableList.toImmutableList());
	}
}
