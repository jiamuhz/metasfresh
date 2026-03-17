package de.metas.acct.gldistribution;

import java.util.List;
import java.util.Properties;

import de.metas.acct.api.PostingType;
import de.metas.document.DocTypeId;
import org.compiere.model.I_GL_Distribution;
import org.compiere.model.I_GL_DistributionLine;

import de.metas.acct.api.AccountDimension;
import de.metas.util.ISingletonService;

/** */

public interface IGLDistributionDAO extends ISingletonService
{
	/**
	 * Retrieves those {@link I_GL_Distribution}s which are matching the given criteria.
	 *
	 */
	List<I_GL_Distribution> retrieve(Properties ctx, AccountDimension dimension, PostingType PostingType, DocTypeId C_DocType_ID);

	/**
	 * Retrieves {@link I_GL_DistributionLine}s of given {@link I_GL_Distribution}.
	 * 
	 * @return active {@link I_GL_DistributionLine}s.
	 */
	List<I_GL_DistributionLine> retrieveLines(I_GL_Distribution glDistribution);

	/**
	 * @return last {@link I_GL_DistributionLine#getLine()} of given {@link I_GL_Distribution}; if there are no lines, this method returns zero.
	 */
	int retrieveLastLineNo(I_GL_Distribution glDistribution);
}
