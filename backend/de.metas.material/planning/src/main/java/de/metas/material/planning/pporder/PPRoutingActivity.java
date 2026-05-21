package de.metas.material.planning.pporder;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Range;
import de.metas.bpartner.BPartnerId;
import de.metas.product.ResourceId;
import de.metas.util.lang.Percent;
import de.metas.workflow.WFDurationUnit;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

/**
 * PP Routing : 生产工艺路线
 * PP Routing Activity : 生产工艺路线的工序
 */

@Value
@Builder(toBuilder = true)
public class PPRoutingActivity
{
	@NonNull PPRoutingActivityId id;

	@NonNull PPRoutingActivityType type;

	@NonNull String code;
	@NonNull String name;

	@NonNull @Default Range<Instant> validDates = Range.all();

	@NonNull ResourceId resourceId;

	@NonNull WFDurationUnit durationUnit;

	@NonNull Duration queuingTime;
	@NonNull Duration setupTime;
	@NonNull Duration waitingTime;
	@NonNull Duration movingTime;
	@NonNull Duration durationPerOneUnit;
	int overlapUnits;
	/**
	 * how many items can be manufactured on a production line in given duration unit.
	 */
	int unitsPerCycle;
	/**
	 * how many units are produced in a batch
	 */
	@NonNull BigDecimal qtyPerBatch;

	/**
	 * The Yield is the percentage of a lot that is expected to be of acceptable quality may fall below 100 percent
	 */
	@NonNull Percent yield;

	boolean subcontracting;
	BPartnerId subcontractingVendorId;

	boolean milestone;
	@NonNull PPAlwaysAvailableToUser alwaysAvailableToUser;
	@Nullable UserInstructions userInstructions;

	@NonNull @Default ImmutableSet<PPRoutingActivityId> nextActivityIds = ImmutableSet.of();

	@Nullable PPRoutingActivityTemplateId activityTemplateId;

	public boolean isValidAtDate(final Instant dateTime)
	{
		return validDates.contains(dateTime);
	}
}
