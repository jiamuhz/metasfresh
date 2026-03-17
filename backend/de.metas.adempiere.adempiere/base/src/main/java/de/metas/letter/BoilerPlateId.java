/**
 *
 */
package de.metas.letter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.Objects;

/** */

/**
 *
 *
 */
@Value
public class BoilerPlateId implements RepoIdAware
{
	int repoId;

	@JsonCreator
	public static BoilerPlateId ofRepoId(final int repoId)
	{
		return new BoilerPlateId(repoId);
	}

	public static BoilerPlateId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? ofRepoId(repoId) : null;
	}

	private BoilerPlateId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "AD_BoilerPlate_ID");
	}

	public static int toRepoId(final BoilerPlateId id)
	{
		return id != null ? id.getRepoId() : -1;
	}

	public static boolean equals(@Nullable final BoilerPlateId id1, @Nullable final BoilerPlateId id2)
	{
		return Objects.equals(id1, id2);
	}

	@Override
	@JsonValue
	public int getRepoId()
	{
		return repoId;
	}
}
