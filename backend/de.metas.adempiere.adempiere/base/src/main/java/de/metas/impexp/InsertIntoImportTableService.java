package de.metas.impexp;

/**
 * Reads the external data stream and inserts it to import table
 */
public interface InsertIntoImportTableService
{
	InsertIntoImportTableResult insertData(InsertIntoImportTableRequest request);
}
