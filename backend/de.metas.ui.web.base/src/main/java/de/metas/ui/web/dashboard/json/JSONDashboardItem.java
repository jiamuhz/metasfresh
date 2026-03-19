package de.metas.ui.web.dashboard.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.metas.ui.web.dashboard.UserDashboardItem;
import de.metas.ui.web.dashboard.UserDashboardItemDataResponse;
import de.metas.ui.web.kpi.descriptor.KPI;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;

  
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONDashboardItem
{
	public static JSONDashboardItem of(
			@NonNull final UserDashboardItem item,
			@Nullable final UserDashboardItemDataResponse itemData,
			@NonNull final KPIJsonOptions jsonOpts)
	{
		return new JSONDashboardItem(item, itemData, jsonOpts);
	}

	int id;
	String caption;
	int seqNo;
	@JsonInclude(JsonInclude.Include.NON_EMPTY) String url;
	@JsonInclude(JsonInclude.Include.NON_NULL) JsonKPILayout kpi;
	@JsonInclude(JsonInclude.Include.NON_NULL) JsonKPIDataResult data;

	private JSONDashboardItem(
			@NonNull final UserDashboardItem item,
			@Nullable final UserDashboardItemDataResponse itemData,
			@NonNull final KPIJsonOptions jsonOpts)
	{
		this.id = item.getId().getRepoId();
		this.caption = extractCaption(item, item.getKPI(), jsonOpts);
		this.seqNo = item.getSeqNo();
		this.url = item.getUrl();
		this.kpi = JsonKPILayout.of(item.getKPI(), jsonOpts);
		this.data = itemData != null ? JsonKPIDataResult.of(itemData, jsonOpts) : null;
	}

	private static String extractCaption(final @NonNull UserDashboardItem item, @NonNull final KPI kpi, final @NonNull KPIJsonOptions jsonOpts)
	{
		String caption = item.getCaption(jsonOpts.getAdLanguage());
		if (jsonOpts.isDebugShowColumnNamesForCaption())
		{
			caption = caption + " (" + item.getId() + ", kpiId=" + kpi.getId() + ")";
		}

		return caption;
	}
}
