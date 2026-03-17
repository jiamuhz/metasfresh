package org.adempiere.ad.element.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.Optional;

/** */

@Value
public class AdElementId implements RepoIdAware
{
	@JsonCreator
	public static AdElementId ofRepoId(final int repoId)
	{
		return new AdElementId(repoId);
	}

	@Nullable
	public static AdElementId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? new AdElementId(repoId) : null;
	}

	public static Optional<AdElementId> optionalOfRepoId(final int repoId)
	{
		return Optional.ofNullable(ofRepoIdOrNull(repoId));
	}

	public static int toRepoId(final AdElementId id)
	{
		return id != null ? id.getRepoId() : -1;
	}

	int repoId;

	private AdElementId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "AD_Element_ID");
	}

	@Override
	@JsonValue
	public int getRepoId()
	{
		return repoId;
	}
}
