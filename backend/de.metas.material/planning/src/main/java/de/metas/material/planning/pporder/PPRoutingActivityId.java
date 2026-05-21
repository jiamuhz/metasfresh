package de.metas.material.planning.pporder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;

 
/**
 * Routing activity ID.
 * i.e. {@link PPRoutingId} / AD_WF_Node_ID
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class PPRoutingActivityId implements RepoIdAware
{
	public static PPRoutingActivityId ofRepoId(final PPRoutingId routingId, final int repoId)
	{
		return new PPRoutingActivityId(routingId, repoId);
	}

	public static PPRoutingActivityId ofRepoId(final int AD_Workflow_ID, final int AD_WF_Node_ID)
	{
		return new PPRoutingActivityId(PPRoutingId.ofRepoId(AD_Workflow_ID), AD_WF_Node_ID);
	}

	@Nullable
	public static PPRoutingActivityId ofRepoIdOrNull(final int AD_Workflow_ID, final int AD_WF_Node_ID)
	{
		if (AD_WF_Node_ID <= 0)
		{
			return null;
		}
		final PPRoutingId routingId = PPRoutingId.ofRepoIdOrNull(AD_Workflow_ID);
		if (routingId == null)
		{
			return null;
		}

		return new PPRoutingActivityId(routingId, AD_WF_Node_ID);
	}

	public static int toRepoId(final PPRoutingActivityId id)
	{
		return id != null ? id.getRepoId() : -1;
	}

	PPRoutingId routingId;
	int repoId;

	private PPRoutingActivityId(@NonNull final PPRoutingId routingId, final int repoId)
	{
		this.routingId = routingId;
		this.repoId = Check.assumeGreaterThanZero(repoId, "AD_WF_Node_ID");
	}

	@JsonValue
	public int toJson()
	{
		return getRepoId();
	}
}
