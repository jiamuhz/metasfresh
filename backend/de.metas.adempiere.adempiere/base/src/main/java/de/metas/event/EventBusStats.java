package de.metas.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EventBusStats
{
	long eventsEnqueued;
	long eventsDequeued;

	public long getEventsToDequeue()
	{
		return getEventsEnqueued() - getEventsDequeued();
	}
}
