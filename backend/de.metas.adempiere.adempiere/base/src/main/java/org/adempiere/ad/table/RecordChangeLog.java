package org.adempiere.ad.table;

import com.google.common.collect.ImmutableList;
import de.metas.user.UserId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.Objects;

/** */

/**
 * Both {@code createdByUserId} and {@code lastChangedByUserId} can be {@code null} if the respective DB columns have a value less than zero.
 * This happens if there is no user-id in the context while a DB record is saved.
 */
@Value
@Builder
public class RecordChangeLog
{
	@NonNull String tableName;
	@NonNull ComposedRecordId recordId;
	@Nullable UserId createdByUserId;
	@NonNull Instant createdTimestamp;
	@Nullable UserId lastChangedByUserId;
	@NonNull Instant lastChangedTimestamp;
	@NonNull @Singular ImmutableList<RecordChangeLogEntry> entries;

	public boolean hasChanges()
	{
		return !Objects.equals(createdByUserId, lastChangedByUserId)
				|| !Objects.equals(createdTimestamp, lastChangedTimestamp)
				|| !entries.isEmpty();
	}

}
