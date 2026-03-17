package org.adempiere.ad.element.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.Value;

/** */

@Value
public class AdUISectionId implements RepoIdAware
{
	@JsonCreator
	public static AdUISectionId ofRepoId(final int repoId)
	{
		return new AdUISectionId(repoId);
	}

	public static AdUISectionId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? ofRepoId(repoId) : null;
	}

	int repoId;

	private AdUISectionId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "AD_UI_Section_ID");
	}

	@Override
	@JsonValue
	public int getRepoId()
	{
		return repoId;
	}
}
