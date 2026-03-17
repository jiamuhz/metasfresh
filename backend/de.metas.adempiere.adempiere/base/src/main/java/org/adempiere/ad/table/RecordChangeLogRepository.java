package org.adempiere.ad.table;

import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableListMultimap;

import lombok.NonNull;

/** */

@Repository
public class RecordChangeLogRepository implements LogEntriesRepository
{
	public RecordChangeLog getByRecord(@NonNull final TableRecordReference recordRef)
	{
		final String tableName = recordRef.getTableName();
		final int recordId = recordRef.getRecord_ID();

		final String singleKeyColumnName = InterfaceWrapperHelper.getKeyColumnName(tableName);

		return RecordChangeLogLoader.ofAdTableId(recordRef.getAD_Table_ID())
				.getByRecordId(ComposedRecordId.singleKey(singleKeyColumnName, recordId));
	}

	public RecordChangeLog getByRecord(final int adTableId, final ComposedRecordId recordId)
	{
		return RecordChangeLogLoader
				.ofAdTableId(adTableId)
				.getByRecordId(recordId);
	}

	public RecordChangeLog getSummaryByRecord(@NonNull final TableRecordReference recordRef)
	{
		final String tableName = recordRef.getTableName();
		final int recordId = recordRef.getRecord_ID();

		final int adTableId = recordRef.getAD_Table_ID();
		final String singleKeyColumnName = InterfaceWrapperHelper.getKeyColumnName(tableName);

		return RecordChangeLogLoader.ofAdTableId(adTableId)
				.getSummaryByRecordId(ComposedRecordId.singleKey(singleKeyColumnName, recordId));
	}

	@Override
	public ImmutableListMultimap<TableRecordReference, RecordChangeLogEntry> getLogEntriesForRecordReferences(
			@NonNull final LogEntriesQuery logEntriesQuery)
	{
		return RecordChangeLogEntryLoader.retrieveLogEntries(logEntriesQuery);
	}
}
