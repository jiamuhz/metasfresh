package de.metas.acct.gldistribution;

import org.adempiere.exceptions.AdempiereException;

/** */

/**
 * Exception thrown when {@link IGLDistributionBL#validate(org.compiere.model.I_GL_Distribution)} fails.
 * 
 * 
 *
 */
public class GLDistributionNotValidException extends AdempiereException
{
	private static final long serialVersionUID = -1707655052772443217L;

	public GLDistributionNotValidException(final String message)
	{
		super(message);
	}
}
