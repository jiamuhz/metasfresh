package de.metas.ui.web.dashboard;

import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.kpi.KPITimeRangeDefaults;
import de.metas.ui.web.kpi.descriptor.KPI;
import de.metas.ui.web.kpi.descriptor.KPIId;
import de.metas.ui.web.kpi.descriptor.KPISupplier;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;


@Value
public class UserDashboardItem
{
	@NonNull UserDashboardItemId id;
	@NonNull ITranslatableString caption;
	String url;
	int seqNo;
	DashboardWidgetType widgetType;
	@Nullable KPISupplier kpiSupplier;
	@NonNull KPITimeRangeDefaults timeRangeDefaults;

	@Builder
	private UserDashboardItem(
			@NonNull final UserDashboardItemId id,
			@NonNull final ITranslatableString caption,
			final String url,
			final int seqNo,
			final DashboardWidgetType widgetType,
			@Nullable final KPISupplier kpiSupplier,
			@Nullable final KPITimeRangeDefaults timeRangeDefaults)
	{
		this.id = id;
		this.caption = caption;
		this.url = url;
		this.seqNo = seqNo;
		this.widgetType = widgetType;
		this.kpiSupplier = kpiSupplier;
		this.timeRangeDefaults = timeRangeDefaults != null ? timeRangeDefaults : KPITimeRangeDefaults.DEFAULT;
	}

	public String getCaption(final String adLanguage)
	{
		return caption.translate(adLanguage);
	}

	public KPIId getKPIId()
	{
		if (kpiSupplier == null)
		{
			throw new EntityNotFoundException("No KPI defined for " + this);
		}
		else
		{
			return kpiSupplier.getKpiId();
		}
	}

	public KPI getKPI()
	{
		final KPI kpi = kpiSupplier == null ? null : kpiSupplier.get();
		if (kpi == null)
		{
			throw new EntityNotFoundException("No KPI defined for " + this);
		}
		return kpi;
	}

	public KPITimeRangeDefaults getTimeRangeDefaults()
	{
		final KPI kpi = getKPI();
		return timeRangeDefaults.compose(kpi.getTimeRangeDefaults());
	}
}
