package de.metas.acct.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.Objects;

/** */

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class AcctSchemaId implements RepoIdAware
{
	@JsonCreator
	@NonNull
	public static AcctSchemaId ofRepoId(final int repoId)
	{
		return new AcctSchemaId(repoId);
	}

	@Nullable
	public static AcctSchemaId ofRepoIdOrNull(final int repoId)
	{
		if (repoId <= 0)
		{
			return null;
		}
		else
		{
			return ofRepoId(repoId);
		}
	}

	public static int toRepoId(@Nullable final AcctSchemaId id)
	{
		return id != null ? id.getRepoId() : -1;
	}

	int repoId;

	private AcctSchemaId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "C_AcctSchema_ID");
	}

	@Override
	@JsonValue
	public int getRepoId()
	{
		return repoId;
	}

	public static boolean equals(
			@Nullable final AcctSchemaId id1,
			@Nullable final AcctSchemaId id2)
	{
		return Objects.equals(id1, id2);
	}
}
