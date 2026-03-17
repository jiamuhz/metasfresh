package org.adempiere.ad.dao.housekeeping.spi.impl;

/** */

import org.adempiere.ad.housekeeping.spi.IStartupHouseKeepingTask;
import org.adempiere.ad.trx.api.ITrx;
import org.compiere.util.DB;
import org.slf4j.Logger;

import ch.qos.logback.classic.Level;
import de.metas.logging.LogManager;
import de.metas.util.Loggables;
import lombok.NonNull;
import org.springframework.stereotype.Component;

/**
 * Clears several temporary tables.
 */
@Component
public class ClearTemporaryTables implements IStartupHouseKeepingTask
{
	private static final Logger logger = LogManager.getLogger(ClearTemporaryTables.class);

	@Override
	public void executeTask()
	{
		truncateTable("T_Selection");
		truncateTable("T_Selection2");

		// handeled via QuerySelectionToDeleteHelper
		// truncateTable(I_T_Query_Selection.Table_Name);
		// truncateTable(I_T_Query_Selection_ToDelete.Table_Name);
	}

	private void truncateTable(@NonNull final String tableName)
	{
		try
		{
			DB.executeUpdateAndThrowExceptionOnFail("TRUNCATE TABLE " + tableName, ITrx.TRXNAME_None);
			Loggables.withLogger(logger, Level.DEBUG).addLog("Truncated table {}", tableName);
		}
		catch (final Exception ex)
		{
			logger.warn("Failed truncating {}", tableName, ex);
		}
	}

}
