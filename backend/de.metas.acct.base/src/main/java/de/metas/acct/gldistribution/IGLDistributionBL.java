package de.metas.acct.gldistribution;

import org.compiere.model.I_GL_Distribution;

import de.metas.util.ISingletonService;

/** */

public interface IGLDistributionBL extends ISingletonService
{
	/**
	 * Validates given {@link I_GL_Distribution}, sets PercentTotal, IsValid.
	 * 
	 * @param glDistribution
	 * @throws GLDistributionNotValidException in case it's not valid.
	 */
	void validate(I_GL_Distribution glDistribution) throws GLDistributionNotValidException;
}
