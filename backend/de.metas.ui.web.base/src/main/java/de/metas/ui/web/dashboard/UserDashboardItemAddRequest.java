package de.metas.ui.web.dashboard;

import de.metas.ui.web.dashboard.json.JsonUserDashboardItemAddRequest;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

 

@Builder
@Value
public class UserDashboardItemAddRequest
{
	public static final UserDashboardItemAddRequest of(final JsonUserDashboardItemAddRequest json, final DashboardWidgetType widgetType, final String adLanguage)
	{
		final UserDashboardItemChangeRequest changeRequest = UserDashboardItemChangeRequest.builder()
				.itemId(null) // new
				.widgetType(widgetType)
				.adLanguage(adLanguage)
				.caption(json.getCaption())
				.interval(json.getInterval())
				.when(json.getWhen())
				.build();

		return UserDashboardItemAddRequest.builder()
				.widgetType(widgetType)
				.kpiId(json.getKpiId())
				.position(json.getPosition())
				.changeRequest(!changeRequest.isEmpty() ? changeRequest : null)
				.build();
	}
	
	DashboardWidgetType widgetType;

	int kpiId;
	@Default int position = -1;

	UserDashboardItemChangeRequest changeRequest;
}
