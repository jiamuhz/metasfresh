package de.metas.ui.web.dashboard.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.kpi.descriptor.KPI;
import de.metas.ui.web.kpi.descriptor.KPIField;

import javax.annotation.Nullable;
import java.util.List;



@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JsonKPILayout
{
	public static JsonKPILayout of(final KPI kpi, final KPIJsonOptions jsonOpts)
	{
		return new JsonKPILayout(kpi, jsonOpts);
	}

	// private final int id; // don't exported because is useless and confusing

	// @JsonProperty("caption")
	// private final String caption; // don't exported because is useless and confusing; frontend shall display the dashboard item caption anyways

	@JsonProperty("description")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final String description;

	@JsonProperty("chartType")
	private final String chartType;

	@JsonProperty("groupByField")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final JsonKPIFieldLayout groupByField;

	@JsonProperty("fields")
	private final List<JsonKPIFieldLayout> fields;

	@JsonProperty("zoomToDetailsAvailable")
	private final boolean zoomToDetailsAvailable;

	public JsonKPILayout(final KPI kpi, final KPIJsonOptions jsonOpts)
	{
		// id = kpi.getId();
		// caption = kpi.getCaption(jsonOpts.getAdLanguage());
		description = Strings.emptyToNull(kpi.getDescription(jsonOpts.getAdLanguage()));
		chartType = kpi.getChartType().toJson();
		groupByField = extractGroupByField(kpi, jsonOpts);
		fields = extractFields(kpi, jsonOpts);
		zoomToDetailsAvailable = kpi.isZoomToDetailsAvailable();
	}

	@Nullable
	static JsonKPIFieldLayout extractGroupByField(final KPI kpi, final KPIJsonOptions jsonOpts)
	{
		final KPIField groupByField = kpi.getGroupByFieldOrNull();
		return groupByField != null ? JsonKPIFieldLayout.field(groupByField, jsonOpts) : null;
	}

	static ImmutableList<JsonKPIFieldLayout> extractFields(final KPI kpi, final KPIJsonOptions jsonOpts)
	{
		final ImmutableList.Builder<JsonKPIFieldLayout> jsonFields = ImmutableList.builder();
		final boolean hasCompareOffset = kpi.hasCompareOffset();
		for (final KPIField kpiField : kpi.getFields())
		{
			// Don't add the group by field to our fields list
			if (kpiField.isGroupBy())
			{
				continue;
			}

			jsonFields.add(JsonKPIFieldLayout.field(kpiField, jsonOpts));

			if (hasCompareOffset && !kpiField.isGroupBy())
			{
				jsonFields.add(JsonKPIFieldLayout.offsetField(kpiField, jsonOpts));
			}
		}
		return jsonFields.build();
	}

}
