package de.metas.impexp;

import java.util.OptionalInt;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import de.metas.error.AdIssueId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
public class ActualImportRecordsResult
{
	/** target table name, where the records were imported (e.g. C_BPartner) */
	@NonNull
	String targetTableName;
	/** import table name, FROM where the records are imported (e.g. I_BPartner) */
	@NonNull
	String importTableName;

	@NonNull
	OptionalInt countImportRecordsConsidered;
	@NonNull
	OptionalInt countInsertsIntoTargetTable;
	@NonNull
	OptionalInt countUpdatesIntoTargetTable;

	@NonNull
	@Singular
	ImmutableList<Error> errors;

	public String getCountInsertsIntoTargetTableString()
	{
		return counterToString(getCountInsertsIntoTargetTable());
	}

	public String getCountUpdatesIntoTargetTableString()
	{
		return counterToString(getCountUpdatesIntoTargetTable());
	}

	private static String counterToString(final OptionalInt counter)
	{
		return counter.isPresent() ? String.valueOf(counter.getAsInt()) : "N/A";
	}

	public boolean hasErrors()
	{
		return !getErrors().isEmpty();
	}

	public int getCountErrors()
	{
		return getErrors().size();
	}

	@Value
	@Builder
	public static class Error
	{
		@NonNull
		private String message;

		@NonNull
		AdIssueId adIssueId;
		@Nullable
		transient Throwable exception;

		private int affectedImportRecordsCount;
	}
}
