package de.metas.process;

/** */

import de.metas.util.ISingletonService;
import lombok.NonNull;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.I_AD_PInstance;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface IADPInstanceDAO extends ISingletonService
{
	/**
	 * Saves {@link ProcessInstanceInfo} together with it's parameters.
	 *
	 * @see #saveProcessInfoOnly(ProcessInstanceInfo)
	 * @see #saveParameterToDB(PInstanceId, List)
	 */
	void saveProcessInfo(ProcessInstanceInfo pi);

	/**
	 * Saves {@link ProcessInstanceInfo} only, excluding depending records like process parameters.
	 *
	 * Also, in case the {@link ProcessInstanceInfo#getAdProcessId()} is missing, this method will create it and it will set it to {@link ProcessInstanceInfo}.
	 */
	void saveProcessInfoOnly(ProcessInstanceInfo pi);

	/**
	 * Saves process parameters.
	 *
	 * @param pinstanceId existing AD_PInstance_ID (mandatory)
	 */
	void saveParameterToDB(PInstanceId pinstanceId, List<ProcessInfoParameter> piParams);

	/**
	 * @param pinstanceId AD_PInstance_ID
	 * @return process parameters for given AD_PInstance_ID
	 */
	List<ProcessInfoParameter> retrieveProcessInfoParameters(PInstanceId pinstanceId);

	/**
	 * Locks underlying AD_PInstance.
	 */
	void lock(PInstanceId pinstanceId);

	/**
	 * Unlocks underlying AD_PInstance, saves the result and logs.
	 */
	void unlockAndSaveResult(ProcessExecutionResult result);

	void loadResultSummary(ProcessExecutionResult result);

	/** @return process info logs, ordered chronologically */
	List<ProcessInfoLog> retrieveProcessInfoLogs(PInstanceId pinstanceId);

	/**
	 * Creates a new selection ID (AD_PInstance_ID).
	 *
	 * IMPORTANT: <b>this method is NOT creating an {@link I_AD_PInstance} record.</b>
	 * If you want to create an {@link I_AD_PInstance}, please use {@link #createAD_PInstance(AdProcessId)}.
	 *
	 * @return new AD_PInstance_ID
	 */
	PInstanceId createSelectionId();

	/**
	 * Creates and saves a new AD_PInstance.
	 */
	I_AD_PInstance createAD_PInstance(AdProcessId adProcessId);

	/**
	 * @return process instance; never returns null
	 */
	I_AD_PInstance getById(PInstanceId pinstanceId);

	I_AD_PInstance getByIdOrNull(@NonNull final PInstanceId pinstanceId);

	Set<TableRecordReference> retrieveSelectedIncludedRecords(PInstanceId pinstanceId);

	void saveSelectedIncludedRecords(PInstanceId pinstanceId, Set<TableRecordReference> recordRefs);

	PInstanceId createADPinstanceAndADPInstancePara(PInstanceRequest pinstanceRequest);

	Timestamp getLastRunDate(@NonNull AdProcessId adProcessId, @Nullable PInstanceId pinstanceToExclude);

	void saveProcessInfoLogs(PInstanceId pinstanceId, List<ProcessInfoLog> logs);
}
