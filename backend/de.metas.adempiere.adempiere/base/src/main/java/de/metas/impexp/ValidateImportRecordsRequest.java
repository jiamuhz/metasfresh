package de.metas.impexp;

import org.adempiere.service.ClientId;
import org.adempiere.util.api.Params;

import de.metas.process.PInstanceId;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class ValidateImportRecordsRequest
{
	@NonNull
	String importTableName;

	@NonNull
	PInstanceId selectionId;

	@NonNull
	ClientId clientId;

	@NonNull
	@Default
	Params additionalParameters = Params.EMPTY;
}
