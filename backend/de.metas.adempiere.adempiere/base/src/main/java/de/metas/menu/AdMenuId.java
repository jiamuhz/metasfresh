package de.metas.menu;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.Value;

/** */
@Value
public class AdMenuId implements RepoIdAware
{
	@JsonCreator
	public static AdMenuId ofRepoId(final int repoId)
	{
		return new AdMenuId(repoId);
	}

	public static AdMenuId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? new AdMenuId(repoId) : null;
	}

	public static int toRepoId(final AdMenuId id)
	{
		return id != null ? id.getRepoId() : -1;
	}

	int repoId;

	private AdMenuId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "AD_Menu_ID");
	}

	@Override
	@JsonValue
	public int getRepoId()
	{
		return repoId;
	}
}
