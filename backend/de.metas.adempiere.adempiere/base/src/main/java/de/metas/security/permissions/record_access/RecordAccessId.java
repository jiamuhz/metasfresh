package de.metas.security.permissions.record_access;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.Value;

@Value
public class RecordAccessId implements RepoIdAware
{
	@JsonCreator
	public static RecordAccessId ofRepoId(final int repoId)
	{
		return new RecordAccessId(repoId);
	}

	public static RecordAccessId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? new RecordAccessId(repoId) : null;
	}

	int repoId;

	private RecordAccessId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "AD_User_Record_Access");
	}

	@JsonValue
	@Override
	public int getRepoId()
	{
		return repoId;
	}

	public static int toRepoId(final RecordAccessId id)
	{
		return id != null ? id.getRepoId() : -1;
	}
}
