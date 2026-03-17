package de.metas.event.log.process;

import de.metas.event.model.I_AD_EventLog;
import de.metas.process.JavaProcess;
import de.metas.process.Param;
import de.metas.util.Services;
import org.adempiere.ad.dao.ICompositeQueryFilter;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.impl.CompareQueryFilter.Operator;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

import java.sql.Timestamp;

/** */

public class AD_EventLog_DeleteOldRecords extends JavaProcess
{

	@Param(mandatory = true, parameterName = "LastUpdatedDaysBack")
	private int p_lastUpdatedDaysBack;

	@Override
	protected String doIt() throws Exception
	{
		final Timestamp maxUpdated = TimeUtil.addDays(Env.getDate(), -p_lastUpdatedDaysBack);

		final IQueryBL queryBL = Services.get(IQueryBL.class);
		final ICompositeQueryFilter<I_AD_EventLog> noErrorFilter = queryBL.createCompositeQueryFilter(I_AD_EventLog.class)
				.setJoinOr()
				.addEqualsFilter(I_AD_EventLog.COLUMN_IsError, false)
				.addEqualsFilter(I_AD_EventLog.COLUMN_IsErrorAcknowledged, true);

		final int deleted = queryBL.createQueryBuilder(I_AD_EventLog.class)
				.addCompareFilter(I_AD_EventLog.COLUMN_Updated, Operator.LESS_OR_EQUAL, maxUpdated)
				.filter(noErrorFilter)
				.create()
				.deleteDirectly(); // this also lets the DB delete the AD_EventLog_Entries, via an FK constraint with "ON DELETE CASCADE"

		addLog("Deleted {} records with a Updated up to {}", deleted, maxUpdated);
		return MSG_OK;
	}

}
