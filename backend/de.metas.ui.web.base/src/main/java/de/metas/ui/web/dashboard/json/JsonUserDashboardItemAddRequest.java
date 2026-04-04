 

package de.metas.ui.web.dashboard.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Value;

@Builder
@Value
@JsonDeserialize(builder = JsonUserDashboardItemAddRequest.JsonUserDashboardItemAddRequestBuilder.class)
public class JsonUserDashboardItemAddRequest
{
	int kpiId;
	@Default
	int position = -1;

	//
	// Optional params
	String caption;
	JSONInterval interval;
	JSONWhen when;

	@AllArgsConstructor
	@Getter
	public static enum JSONInterval
	{
		week("P-7D");

		private final String esTimeRange;
	}

	@AllArgsConstructor
	@Getter
	public enum JSONWhen
	{
		now(null), lastWeek("P-7D");

		private final String esTimeRangeEnd;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class JsonUserDashboardItemAddRequestBuilder
	{
	}

}
