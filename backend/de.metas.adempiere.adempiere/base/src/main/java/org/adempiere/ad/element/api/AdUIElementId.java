package org.adempiere.ad.element.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.Value;

/** */

@Value
public class AdUIElementId implements RepoIdAware
{
	@JsonCreator
	public static AdUIElementId ofRepoId(final int repoId)
	{
		return new AdUIElementId(repoId);
	}

	public static AdUIElementId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? ofRepoId(repoId) : null;
	}

	int repoId;

	private AdUIElementId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "AD_UI_Element_ID");
	}

	@Override
	@JsonValue
	public int getRepoId()
	{
		return repoId;
	}
}
