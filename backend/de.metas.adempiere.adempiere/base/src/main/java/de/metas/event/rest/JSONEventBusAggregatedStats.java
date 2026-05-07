package de.metas.event.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonDeserialize(builder = JSONEventBusAggregatedStats.JSONEventBusAggregatedStatsBuilder.class)
public class JSONEventBusAggregatedStats
{
	long eventBusInstancesCount;

	@Singular
	List<JSONEventBusStats> eventBusInstances;

	@JsonPOJOBuilder(withPrefix = "")
	public static class JSONEventBusAggregatedStatsBuilder
	{
	}
}
