package de.metas.material.planning;

import de.metas.quantity.Quantity;
import de.metas.util.time.DurationUtils;
import de.metas.workflow.WFDurationUnit;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

 
@Value
public class WorkingTime
{
	@NonNull Duration duration;
	int cycles;

	@NonNull WFDurationUnit activityTimeUnit;
	@NonNull Duration durationPerOneUnit;
	int unitsPerCycle;
	@NonNull Quantity qty;

	@Builder
	public WorkingTime(
			@NonNull final Duration durationPerOneUnit,
			final int unitsPerCycle,
			@NonNull final Quantity qty,
			@NonNull final WFDurationUnit activityTimeUnit)
	{
		this.durationPerOneUnit = durationPerOneUnit;
		this.unitsPerCycle = unitsPerCycle;
		this.qty = qty;
		this.activityTimeUnit = activityTimeUnit;

		this.cycles = calculateCycles(unitsPerCycle, qty.toBigDecimal());
		this.duration = durationPerOneUnit.multipliedBy(cycles);

	}

	/**
	 * @return how many cycles are needed for given qty and units per cycle
	 */
	private static int calculateCycles(final int unitsPerCycle, final BigDecimal qty)
	{
		if (unitsPerCycle > 0)
		{
			final BigDecimal unitsCycleBD = BigDecimal.valueOf(unitsPerCycle);
			return qty.divide(unitsCycleBD, 0, RoundingMode.UP).intValueExact();
		}
		else
		{
			// consider unitsPerCycle = 1
			return qty.setScale(0, RoundingMode.UP).intValueExact();
		}
	}

	public BigDecimal toBigDecimalUsingActivityTimeUnit()
	{
		return activityTimeUnit.toBigDecimal(getDuration());
	}
}
