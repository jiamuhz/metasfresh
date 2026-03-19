package de.metas.ui.web.dashboard.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.kpi.descriptor.KPIField;



@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JsonKPIFieldLayout
{
	public static JsonKPIFieldLayout field(final KPIField kpiField, final KPIJsonOptions jsonOpts)
	{
		final boolean isOffsetField = false;
		return new JsonKPIFieldLayout(kpiField, isOffsetField, jsonOpts);
	}

	public static JsonKPIFieldLayout offsetField(final KPIField kpiField, final KPIJsonOptions jsonOpts)
	{
		final boolean isOffsetField = true;
		return new JsonKPIFieldLayout(kpiField, isOffsetField, jsonOpts);
	}

	@JsonProperty("caption")
	private final String caption;

	@JsonProperty("description")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final String description;

	@JsonProperty("unit")
	private final String unit;

	@JsonProperty("fieldName")
	private final String fieldName;

	// NOTE: not needed because we are providing a separate groupByField in JsonKPILayout
	// @JsonProperty("groupBy")
	// private final boolean groupBy;

	@JsonProperty("dataType")
	private final String dataType;

	@JsonProperty("color")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final String color;

	public JsonKPIFieldLayout(final KPIField kpiField, final boolean isOffsetField, final KPIJsonOptions jsonOpts)
	{
		final String adLanguage = jsonOpts.getAdLanguage();

		// Caption
		if (isOffsetField)
		{
			caption = kpiField.getOffsetCaption(adLanguage);
		}
		else
		{
			caption = kpiField.getCaption(adLanguage);
		}

		// FieldName
		if (isOffsetField)
		{
			fieldName = kpiField.getOffsetFieldName();
		}
		else
		{
			fieldName = kpiField.getFieldName();
		}

		description = kpiField.getDescription(adLanguage);
		unit = kpiField.getUnit(adLanguage).orElse(null);

		// groupBy = kpiField.isGroupBy();
		dataType = kpiField.getValueType().toJson();

		color = kpiField.getColor();
	}

}
