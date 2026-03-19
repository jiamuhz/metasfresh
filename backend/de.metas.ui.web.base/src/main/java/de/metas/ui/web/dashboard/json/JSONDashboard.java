package de.metas.ui.web.dashboard.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

import javax.annotation.Nullable;
import java.util.List;

  
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONDashboard
{
	public static final JSONDashboard EMPTY = new JSONDashboard();

	List<JSONDashboardItem> items;
	@Nullable String websocketEndpoint;

	@With
	@JsonInclude(JsonInclude.Include.NON_EMPTY) String noDashboardReason;

	@Builder
	private JSONDashboard(
			@NonNull final List<JSONDashboardItem> items,
			@Nullable final String websocketEndpoint,
			@Nullable final String noDashboardReason)
	{
		this.items = ImmutableList.copyOf(items);
		this.websocketEndpoint = websocketEndpoint;
		this.noDashboardReason = noDashboardReason;
	}

	private JSONDashboard()
	{
		this.items = ImmutableList.of();
		this.websocketEndpoint = null;
		this.noDashboardReason = null;
	}
}
