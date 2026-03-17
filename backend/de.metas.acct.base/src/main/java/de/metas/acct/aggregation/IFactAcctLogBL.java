package de.metas.acct.aggregation;

import de.metas.acct.model.I_Fact_Acct_Log;
import de.metas.util.ISingletonService;
import org.adempiere.ad.dao.QueryLimit;
import org.compiere.model.IQuery;

import java.util.Properties;

/** */

/**
 * {@link I_Fact_Acct_Log} BL.
 * 
 * 
 *
 */
public interface IFactAcctLogBL extends ISingletonService
{

	/**
	 * Process all pending {@link I_Fact_Acct_Log}s.
	 * 
	 * @param limit maximum amount of logs to process or {@link IQuery#NO_LIMIT}.
	 */
	FactAcctLogProcessResult processAll(Properties ctx, QueryLimit limit);
}
