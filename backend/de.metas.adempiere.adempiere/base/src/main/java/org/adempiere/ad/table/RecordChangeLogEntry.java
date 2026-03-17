package org.adempiere.ad.table;

import java.time.Instant;

import javax.annotation.Nullable;

import de.metas.i18n.ITranslatableString;
import de.metas.user.UserId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/** */

@Value
@Builder(toBuilder = true)
public class RecordChangeLogEntry
{
	@NonNull
	String columnName;

	@NonNull
	ITranslatableString columnDisplayName;

	int displayType;

	Object valueNew;
	Object valueOld;

	@NonNull
	Instant changedTimestamp;

	@Nullable
	UserId changedByUserId;
}
