package org.adempiere.ad.element.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.Objects;

/** */
@Value
public class AdTabId implements RepoIdAware
{
	@JsonCreator
	public static AdTabId ofRepoId(final int repoId)
	{
		return new AdTabId(repoId);
	}

	@Nullable
	public static AdTabId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? new AdTabId(repoId) : null;
	}

	public static int toRepoId(@Nullable final AdTabId id)
	{
		return id != null ? id.getRepoId() : -1;
	}

	int repoId;

	private AdTabId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "AD_Tab_ID");
	}

	@Override
	@JsonValue
	public int getRepoId()
	{
		return repoId;
	}

	public static boolean equals(@Nullable final AdTabId id1, @Nullable final AdTabId id2) { return Objects.equals(id1, id2); }
}
