package org.adempiere.ad.element.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.Value;

/** */

@Value
public class AdUIColumnId implements RepoIdAware
{
	@JsonCreator
	public static AdUIColumnId ofRepoId(final int repoId)
	{
		return new AdUIColumnId(repoId);
	}

	public static AdUIColumnId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? new AdUIColumnId(repoId) : null;
	}

	int repoId;

	private AdUIColumnId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "AD_UI_Column_ID");
	}

	@Override
	@JsonValue
	public int getRepoId()
	{
		return repoId;
	}
}
