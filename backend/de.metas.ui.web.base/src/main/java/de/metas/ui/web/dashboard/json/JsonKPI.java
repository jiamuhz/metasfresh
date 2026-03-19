package de.metas.ui.web.dashboard.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.dashboard.DashboardWidgetType;
import de.metas.ui.web.kpi.data.KPIDataResult;
import de.metas.ui.web.kpi.descriptor.KPI;
import de.metas.ui.web.kpi.descriptor.KPIChartType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.List;



@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Builder
@Value
public class JsonKPI
{
	int kpiId;
	String caption;
	KPIChartType chartType;
	ImmutableSet<DashboardWidgetType> widgetTypes;

	// layout
	@JsonInclude(JsonInclude.Include.NON_NULL) JsonKPIFieldLayout groupByField;
	List<JsonKPIFieldLayout> fields;

	@Nullable JsonKPIDataResult sampleData;

	public static JsonKPI of(
			@NonNull final KPI kpi,
			@Nullable final KPIDataResult sampleData,
			@NonNull final KPIJsonOptions jsonOpts)
	{
		return JsonKPI.builder()
				.kpiId(kpi.getId().getRepoId())
				.caption(kpi.getCaption(jsonOpts.getAdLanguage()))
				.chartType(kpi.getChartType())
				.widgetTypes(ImmutableSet.copyOf(kpi.getSupportedWidgetTypes()))
				.groupByField(JsonKPILayout.extractGroupByField(kpi, jsonOpts))
				.fields(JsonKPILayout.extractFields(kpi, jsonOpts))
				.sampleData(sampleData != null ? JsonKPIDataResult.of(sampleData, jsonOpts) : null)
				.build();
	}
}
