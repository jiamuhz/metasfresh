package de.metas.acct.aggregation;

import java.util.Date;

import de.metas.acct.model.I_Fact_Acct_Log;
import de.metas.acct.model.I_Fact_Acct_Summary;

/** */

/**
 * The aggregation dimension used for aggregating {@link I_Fact_Acct_Log}s into {@link I_Fact_Acct_Summary} records.
 * 
 *
 *
 */
public interface IFactAcctSummaryKey
{
	/**
	 * @return string identifier of this key.
	 */
	String asString();

	int getAD_Client_ID();

	int getAD_Org_ID();

	int getC_Period_ID();
	
	Date getDateAcct();

	String getPostingType();

	int getC_AcctSchema_ID();

	int getC_ElementValue_ID();

	int getPA_ReportCube_ID();
}
