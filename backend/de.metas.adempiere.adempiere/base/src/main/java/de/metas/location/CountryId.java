package de.metas.location;

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
public class CountryId implements RepoIdAware
{
	public static final CountryId SWITZERLAND = new CountryId(107);

	@JsonCreator
	@NonNull
	public static CountryId ofRepoId(final int repoId)
	{
		return new CountryId(repoId);
	}

	@Nullable
	public static CountryId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? new CountryId(repoId) : null;
	}

	public static int toRepoId(@Nullable final CountryId id)
	{
		return id != null ? id.getRepoId() : -1;
	}

	int repoId;

	private CountryId(final int repoId)
	{
		this.repoId = Check.assumeGreaterOrEqualToZero(repoId, "C_Country_ID");
	}

	@Override
	@JsonValue
	public int getRepoId()
	{
		return repoId;
	}

	public static boolean equals(@Nullable final CountryId countryId1, @Nullable final CountryId countryId2)
	{
		return Objects.equals(countryId1, countryId2);
	}

	public boolean equalsToRepoId(final int repoId)
	{
		return this.repoId == repoId;
	}
}
