package org.adempiere.ad.element.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

/** */

@Value
public class AdWindowId implements RepoIdAware
{
	@NonNull @JsonCreator
	public static AdWindowId ofRepoId(final int repoId)
	{
		return new AdWindowId(repoId);
	}

	@Nullable public static AdWindowId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? new AdWindowId(repoId) : null;
	}

	public static Optional<AdWindowId> optionalOfRepoId(final int repoId)
	{
		return Optional.ofNullable(ofRepoIdOrNull(repoId));
	}

	public static int toRepoId(@Nullable final AdWindowId id)
	{
		return id != null ? id.getRepoId() : -1;
	}

	int repoId;

	private AdWindowId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "AD_Window_ID");
	}

	@Override
	@JsonValue
	public int getRepoId()
	{
		return repoId;
	}

	public static boolean equals(@Nullable final AdWindowId id1, @Nullable final AdWindowId id2)
	{
		return Objects.equals(id1, id2);
	}
}
