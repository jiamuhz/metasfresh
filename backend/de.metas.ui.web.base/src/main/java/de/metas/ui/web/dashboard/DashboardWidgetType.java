package de.metas.ui.web.dashboard;

import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.ui.web.base.model.X_WEBUI_DashboardItem;
import de.metas.util.GuavaCollectors;

public enum DashboardWidgetType
{
	TargetIndicator(X_WEBUI_DashboardItem.WEBUI_DASHBOARDWIDGETTYPE_Target) //
	, KPI(X_WEBUI_DashboardItem.WEBUI_DASHBOARDWIDGETTYPE_KPI) //
	;

	private final String code;

	DashboardWidgetType(final String code)
	{
		this.code = code;
	}

	@JsonValue
	public String toJson()
	{
		return name();
	}

	public String getCode()
	{
		return code;
	}

	public static DashboardWidgetType ofCode(final String code)
	{
		final DashboardWidgetType type = code2type.get(code);
		if (type == null)
		{
			throw new IllegalArgumentException("No " + DashboardWidgetType.class + " found for " + code);
		}
		return type;
	}

	private static final Map<String, DashboardWidgetType> code2type = Stream.of(values())
			.collect(GuavaCollectors.toImmutableMapByKey(DashboardWidgetType::getCode));
}
