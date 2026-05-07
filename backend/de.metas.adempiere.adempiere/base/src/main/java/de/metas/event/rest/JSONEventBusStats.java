package de.metas.event.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import de.metas.event.Type;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonDeserialize(builder = JSONEventBusStats.JSONEventBusStatsBuilder.class)
public class JSONEventBusStats
{
	String topicName;
	Type type;
	boolean async;
	boolean destroyed;

	long eventsEnqueued;
	long eventsDequeued;
	long eventsToDequeue;

	@JsonPOJOBuilder(withPrefix = "")
	public static class JSONEventBusStatsBuilder
	{
	}
}
