package de.metas.process;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.Value;

import javax.annotation.Nullable;

/** */

/**
 * Process Instance ID
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class PInstanceId implements RepoIdAware
{
	int repoId;

	@JsonCreator
	public static PInstanceId ofRepoId(final int repoId)
	{
		return new PInstanceId(repoId);
	}

	public static PInstanceId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? new PInstanceId(repoId) : null;
	}

	public static Optional<PInstanceId> optionalOfRepoId(final int repoId)
	{
		return Optional.ofNullable(ofRepoIdOrNull(repoId));
	}

	public static int toRepoId(@Nullable final PInstanceId id)
	{
		return toRepoIdOr(id, -1);
	}

	public static int toRepoIdOr(@Nullable final PInstanceId id, final int defaultValue)
	{
		return id != null ? id.getRepoId() : defaultValue;
	}

	private PInstanceId(final int adPInstanceId)
	{
		repoId = Check.assumeGreaterThanZero(adPInstanceId, "adPInstanceId");
	}

	@JsonValue
	public int toJson()
	{
		return getRepoId();
	}

	public static boolean equals(final PInstanceId o1, final PInstanceId o2)
	{
		return Objects.equals(o1, o2);
	}
}
