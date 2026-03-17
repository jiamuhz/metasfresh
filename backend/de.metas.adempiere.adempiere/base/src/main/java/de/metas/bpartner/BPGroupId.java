package de.metas.bpartner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.Value;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

/** */

@Value
public class BPGroupId implements RepoIdAware
{
	public static final BPGroupId STANDARD = new BPGroupId(1000000);

	int repoId;

	private BPGroupId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "C_BP_Group_ID");
	}

	@JsonCreator
	public static BPGroupId ofRepoId(final int repoId)
	{
		final BPGroupId id = ofRepoIdOrNull(repoId);
		if (id == null)
		{
			throw new AdempiereException("Invalid C_BP_Group_ID: " + repoId);
		}
		return id;
	}

	@Nullable
	public static BPGroupId ofRepoIdOrNull(final int repoId)
	{
		if (repoId == STANDARD.repoId)
		{
			return STANDARD;
		}
		else if (repoId <= 0)
		{
			return null;
		}
		else
		{
			return new BPGroupId(repoId);
		}
	}

	public static Optional<BPGroupId> optionalOfRepoId(final int repoId) {return Optional.ofNullable(ofRepoIdOrNull(repoId));}

	public static int toRepoId(@Nullable final BPGroupId bpGroupId)
	{
		return bpGroupId != null ? bpGroupId.getRepoId() : -1;
	}

	@JsonValue
	public int toJson()
	{
		return getRepoId();
	}

	public static boolean equals(@Nullable final BPGroupId o1, @Nullable final BPGroupId o2)
	{
		return Objects.equals(o1, o2);
	}
}
