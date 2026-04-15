package de.metas.ui.web.view;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;


@EqualsAndHashCode
@ToString
public class ViewHeaderPropertiesIncrementalResult
{
	public static ViewHeaderPropertiesIncrementalResult computed(@NonNull final ViewHeaderProperties computedHeaderProperties)
	{
		return new ViewHeaderPropertiesIncrementalResult(
				Resolution.COMPUTED,
				computedHeaderProperties);
	}

	public static ViewHeaderPropertiesIncrementalResult computedAsEmpty()
	{
		return COMPUTED_AS_EMPTY;
	}

	public static ViewHeaderPropertiesIncrementalResult fullRecomputeRequired()
	{
		return FULL_RECOMPUTE_REQUIRED;
	}

	private static final ViewHeaderPropertiesIncrementalResult COMPUTED_AS_EMPTY = new ViewHeaderPropertiesIncrementalResult(Resolution.COMPUTED, ViewHeaderProperties.EMPTY);
	private static final ViewHeaderPropertiesIncrementalResult FULL_RECOMPUTE_REQUIRED = new ViewHeaderPropertiesIncrementalResult(Resolution.FULL_RECOMPUTE_REQUIRED, null);

	private enum Resolution
	{
		COMPUTED, FULL_RECOMPUTE_REQUIRED,
	}

	private final Resolution resolution;
	private final ViewHeaderProperties computedHeaderProperties;

	public ViewHeaderPropertiesIncrementalResult(
			@NonNull final Resolution resolution,
			final ViewHeaderProperties computedHeaderProperties)
	{
		this.resolution = resolution;
		this.computedHeaderProperties = computedHeaderProperties;
	}

	public boolean isComputed()
	{
		return resolution == Resolution.COMPUTED;
	}

	public ViewHeaderProperties getComputeHeaderProperties()
	{
		return computedHeaderProperties;
	}

	public boolean isFullRecomputeRequired()
	{
		return resolution == Resolution.FULL_RECOMPUTE_REQUIRED;
	}
}
