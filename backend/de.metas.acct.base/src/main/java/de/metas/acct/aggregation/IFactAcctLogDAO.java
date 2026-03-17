package de.metas.acct.aggregation;

import de.metas.acct.model.I_Fact_Acct_Log;
import de.metas.acct.model.I_Fact_Acct_Summary;
import de.metas.util.ISingletonService;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.QueryLimit;

import java.util.Properties;

/** */

/**
 * {@link I_Fact_Acct_Log} DAO.
 * 
 *
 *
 */
public interface IFactAcctLogDAO extends ISingletonService
{
	String PROCESSINGTAG_NULL = null;

	/**
	 * Retrieves an iterable stream of {@link I_Fact_Acct_Log}s which were not already tagged.
	 * 
	 * This method is also tagging those logs, to prevent other business logic to consider them.
	 * 
	 * @param ctx
	 * @param limit
	 * @return
	 */
	IFactAcctLogIterable tagAndRetrieve(Properties ctx, final QueryLimit limit);

	/**
	 * Retrieves the {@link I_Fact_Acct_Summary} in which the given {@link IFactAcctSummaryKey} shall be aggregated.
	 * 
	 * @param ctx
	 * @param key
	 * @return {@link I_Fact_Acct_Summary} or <code>null</code>
	 */
	I_Fact_Acct_Summary retrieveLastMatchingFactAcctSummary(Properties ctx, IFactAcctSummaryKey key);

	IQueryBuilder<I_Fact_Acct_Summary> retrieveCurrentAndNextMatchingFactAcctSummaryQuery(Properties ctx, IFactAcctSummaryKey key);

	/**
	 * @param ctx
	 * @param processingTag
	 * @return true if there are any {@link I_Fact_Acct_Log}s tagged with given tag.
	 */
	boolean hasLogs(Properties ctx, String processingTag);

	void updateFactAcctEndingBalanceForTag(String processingTag);
}
