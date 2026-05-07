package de.metas.impexp;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class SqlInsertIntoImportTableService implements InsertIntoImportTableService
{

	@Override
	public InsertIntoImportTableResult insertData(@NonNull final InsertIntoImportTableRequest request)
	{
		final SqlInsertIntoImportTableCommand command = SqlInsertIntoImportTableCommand.builder()
				.importFormat(request.getImportFormat())
				.clientId(request.getClientId())
				.orgId(request.getOrgId())
				.userId(request.getUserId())
				.dataImportRunId(request.getDataImportRunId())
				.dataImportConfigId(request.getDataImportConfigId())
				.insertBatchSize(request.getInsertBatchSize())
				.linesStream(request.getStream())
				.overrideColumnValues(request.getOverrideColumnValues())
				.build();

		return command.execute();
	}

}
