package de.metas.material.planning.pporder;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

 
@EqualsAndHashCode
@ToString
public final class PPRoutingActivityChangeRequest
{
	public static PPRoutingActivityChangeRequest newInstance(@NonNull final PPRoutingActivityId activityId)
	{
		return new PPRoutingActivityChangeRequest(activityId);
	}

	@NonNull
	private final PPRoutingActivityId activityId;
	@Getter
	private BigDecimal cost;

	private PPRoutingActivityChangeRequest(@NonNull final PPRoutingActivityId activityId)
	{
		this.activityId = activityId;
	}

	public void addCost(@NonNull final BigDecimal cost)
	{
		if (this.cost == null)
		{
			this.cost = cost;
		}
		else
		{
			this.cost = this.cost.add(cost);
		}
	}
}
