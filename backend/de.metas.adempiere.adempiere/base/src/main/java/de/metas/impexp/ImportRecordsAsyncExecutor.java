package de.metas.impexp;

public interface ImportRecordsAsyncExecutor
{
	AsyncImportRecordsResponse schedule(ImportRecordsRequest request);
}
