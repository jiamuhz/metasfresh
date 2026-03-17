/**
 *
 */
package de.metas.impexp.processing;

/** */

import java.util.Properties;

import org.adempiere.service.ClientId;
import org.adempiere.util.api.IParams;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;

import de.metas.process.PInstanceId;
import de.metas.util.ILoggable;

/**
 * All processes that are importing data should implement this interface.
 *
 * NOTE to developers: instead of implementing this class, please consider extending {@link ImportProcessTemplate}.
 *
 * @author Teo Sarca, www.arhipac.ro
 *         <li>FR [ 2788276 ] Data Import Validator https://sourceforge.net/tracker/?func=detail&aid=2788276&group_id=176962&atid=879335
 */
public interface IImportProcess<ImportRecordType>
{
	String PARAM_DeleteOldImported = "DeleteOldImported";
	String PARAM_IsValidateOnly = "IsValidateOnly";
	String PARAM_Selection_ID = "Selection_ID";
	String PARAM_IsInsertOnly = "IsInsertOnly";
	String PARAM_IsDocComplete = "IsDocComplete";

	/** Sets the processing context */
	IImportProcess<ImportRecordType> setCtx(Properties ctx);

	IImportProcess<ImportRecordType> clientId(ClientId clientId);

	/** Sets process parameters to be used */
	IImportProcess<ImportRecordType> setParameters(IParams params);

	/** Sets the {@link ILoggable} where status notices shall be reported */
	IImportProcess<ImportRecordType> setLoggable(ILoggable loggable);

	IImportProcess<ImportRecordType> selectedRecords(TableRecordReferenceSet selectedRecordRefs);

	IImportProcess<ImportRecordType> selectedRecords(PInstanceId selectionId);

	IImportProcess<ImportRecordType> validateOnly(boolean validateOnly);

	IImportProcess<ImportRecordType> completeDocuments(boolean completeDocuments);

	/** @return import table model class */
	Class<ImportRecordType> getImportModelClass();

	/** @return The Name of Import Table (e.g. I_BPartner) */
	String getImportTableName();

	/**
	 * Run the import.
	 */
	ImportProcessResult run();

	/**
	 * Delete import records
	 * 
	 * @return how many rows were deleted
	 */
	int deleteImportRecords(ImportDataDeleteRequest request);
}
