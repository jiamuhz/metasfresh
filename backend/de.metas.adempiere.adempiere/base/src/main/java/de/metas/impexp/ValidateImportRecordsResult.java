package de.metas.impexp;

import java.time.Duration;
import java.util.OptionalInt;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value
@Builder
public class ValidateImportRecordsResult
{
	@NonNull
	String importTableName;

	@NonNull
	@Default
	@With
	Duration duration = Duration.ZERO;

	int countImportRecordsDeleted;

	@NonNull
	OptionalInt countImportRecordsWithValidationErrors;

	public boolean hasErrors()
	{
		return countImportRecordsWithValidationErrors.orElse(-1) > 0;
	}

	public String getErrorMessage()
	{
		int errorsCount = countImportRecordsWithValidationErrors.orElse(-1);
		if (errorsCount <= 0)
		{
			throw new IllegalStateException("no errors expected");
		}

		return "" + errorsCount + " row(s) have validation errors";
	}
}
