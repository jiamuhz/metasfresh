package org.adempiere.ad.element.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.Value;

import javax.annotation.Nullable;

/** */

@Value
public class AdFieldId implements RepoIdAware
{
	@JsonCreator
	public static AdFieldId ofRepoId(final int repoId)
	{
		return new AdFieldId(repoId);
	}

	@Nullable
	public static AdFieldId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? new AdFieldId(repoId) : null;
	}

	public static int toRepoId(@Nullable final AdFieldId id)
	{
		return id != null ? id.getRepoId() : -1;
	}

	int repoId;

	private AdFieldId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "AD_Field_ID");
	}

	@Override
	@JsonValue
	public int getRepoId()
	{
		return repoId;
	}
}
