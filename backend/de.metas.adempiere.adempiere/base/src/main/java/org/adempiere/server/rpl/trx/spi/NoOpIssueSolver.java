package org.adempiere.server.rpl.trx.spi;

/** */

import org.adempiere.server.rpl.trx.api.IReplicationIssueSolverParams;
import org.slf4j.Logger;

import ch.qos.logback.classic.Level;
import de.metas.logging.LogManager;
import de.metas.util.Loggables;
import lombok.ToString;

/**
 * This implementation does nothing with the given {@link IReplicationIssueAware}.
 */
@ToString
public class NoOpIssueSolver<T extends IReplicationIssueAware> implements IReplicationIssueSolver<T>
{
	private static final Logger logger = LogManager.getLogger(NoOpIssueSolver.class);

	/**
	 * Does nothing; we just want to clear the record for further processing.
	 */
	@Override
	public void solveIssues(final IReplicationIssueAware recordWithIssues, final IReplicationIssueSolverParams params)
	{
		Loggables.withLogger(logger, Level.DEBUG).addLog("NoOpIssueSolver is called with IReplicationIssueAware={}", recordWithIssues);
	}
}
