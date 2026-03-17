package de.metas.acct.gldistribution.impl;

import java.math.BigDecimal;
import java.util.List;

import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_GL_Distribution;
import org.compiere.model.I_GL_DistributionLine;
import org.compiere.util.Env;

import de.metas.acct.gldistribution.GLDistributionNotValidException;
import de.metas.acct.gldistribution.IGLDistributionBL;
import de.metas.acct.gldistribution.IGLDistributionDAO;
import de.metas.util.Services;

/** */

public class GLDistributionBL implements IGLDistributionBL
{
	@Override
	public void validate(final I_GL_Distribution glDistribution)
	{
		final List<I_GL_DistributionLine> glDistributionLines = Services.get(IGLDistributionDAO.class).retrieveLines(glDistribution);
		String errmsg = null;
		if (glDistributionLines.isEmpty())
		{
			errmsg = "@NoLines@";
		}
		else
		{
			//	More then one line with 0
			BigDecimal percentTotal = BigDecimal.ZERO;
			I_GL_DistributionLine lineWithZeroPercent = null;
			for (final I_GL_DistributionLine line : glDistributionLines)
			{
				final BigDecimal percent = line.getPercent();
				
				if (percent.signum() == 0)
				{
					if (lineWithZeroPercent != null)
					{
						errmsg = "@Line@ " + line.getLine() + ": == 0";
						break;
					}
					lineWithZeroPercent = line;
				}
				
				percentTotal = percentTotal.add(percent);
			}

			// If we have a line with zero percentage, we shall consider that line as "put the rest there"
			// so it's fine to have the total percentage as 100%.
			if (lineWithZeroPercent != null)
			{
				percentTotal = Env.ONEHUNDRED;
			}
			
			if (percentTotal.compareTo(Env.ONEHUNDRED) != 0)
			{
				errmsg = "@PercentTotal@ <> 100";
			}
			
			glDistribution.setPercentTotal(percentTotal);
		}
		
		glDistribution.setIsValid (errmsg == null);
		InterfaceWrapperHelper.save(glDistribution);
		
		if (!glDistribution.isValid())
		{
			throw new GLDistributionNotValidException(errmsg);
		}
	}
}
