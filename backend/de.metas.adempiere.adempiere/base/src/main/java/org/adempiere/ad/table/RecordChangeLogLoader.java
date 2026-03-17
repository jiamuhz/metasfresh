package org.adempiere.ad.table;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import de.metas.user.UserId;
import de.metas.util.Check;
import lombok.NonNull;
import org.adempiere.ad.table.LogEntriesRepository.LogEntriesQuery;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.POInfo;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

import java.util.List;

/** */

final class RecordChangeLogLoader
{
	public static RecordChangeLogLoader ofAdTableId(final int adTableId)
	{
		return new RecordChangeLogLoader(adTableId);
	}

	private final POInfo poInfo;

	private RecordChangeLogLoader(final int adTableId)
	{
		Check.assumeGreaterThanZero(adTableId, "adTableId");

		poInfo = POInfo.getPOInfo(adTableId);
		Check.assumeNotNull(poInfo, "poInfo is not null for adTableId={}", adTableId);

	}

	public RecordChangeLog getByRecordId(@NonNull final ComposedRecordId recordId)
	{
		final RecordChangeLog.RecordChangeLogBuilder changeLogsBuilder = RecordChangeLog.builder()
				.tableName(poInfo.getTableName())
				.recordId(recordId);

		loadRecordSummary(changeLogsBuilder, recordId);

		final List<RecordChangeLogEntry> logEntries = retrieveLogEntries(recordId);
		changeLogsBuilder.entries(logEntries);

		return changeLogsBuilder.build();
	}

	public RecordChangeLog getSummaryByRecordId(@NonNull final ComposedRecordId recordId)
	{
		final RecordChangeLog.RecordChangeLogBuilder changeLogsBuilder = RecordChangeLog.builder()
				.tableName(poInfo.getTableName())
				.recordId(recordId);

		loadRecordSummary(changeLogsBuilder, recordId);

		return changeLogsBuilder.build();
	}

	private void loadRecordSummary(final RecordChangeLog.RecordChangeLogBuilder changeLogsBuilder, final ComposedRecordId recordId)
	{
		DB.forFirstRowIfAny(
				"SELECT Created, CreatedBy, Updated, UpdatedBy FROM " + poInfo.getTableName() + " WHERE " + poInfo.getSqlWhereClauseByKeys(),
				recordId.getKeysAsList(poInfo.getKeyColumnNames()),
				rs -> changeLogsBuilder.createdByUserId(UserId.ofRepoIdOrNull(rs.getInt("CreatedBy")))
						.createdTimestamp(rs.getTimestamp("Created").toInstant())
						.lastChangedByUserId(UserId.ofRepoIdOrNull(rs.getInt("UpdatedBy")))
						.lastChangedTimestamp(rs.getTimestamp("Updated").toInstant()));
	}

	private List<RecordChangeLogEntry> retrieveLogEntries(@NonNull final ComposedRecordId recordId)
	{
		if (!recordId.isSingleKey())
		{
			return ImmutableList.of();
		}

		final int singleRecordId = recordId.getSingleRecordId().orElse(-1);

		final TableRecordReference recordRef = TableRecordReference.of(poInfo.getAD_Table_ID(), singleRecordId);

		final ImmutableListMultimap<TableRecordReference, RecordChangeLogEntry> //
				logEntries = RecordChangeLogEntryLoader.retrieveLogEntries(LogEntriesQuery.of(recordRef));

		return (logEntries.get(recordRef));
	}
}
