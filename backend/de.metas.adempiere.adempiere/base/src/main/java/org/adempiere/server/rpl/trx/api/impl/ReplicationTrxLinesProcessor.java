package org.adempiere.server.rpl.trx.api.impl;

/** */


import java.util.ArrayList;
import java.util.List;

import org.adempiere.ad.trx.processor.api.ITrxItemProcessorContext;
import org.adempiere.ad.trx.processor.spi.ITrxItemChunkProcessor;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.process.rpl.model.I_EXP_ReplicationTrxLine;
import org.adempiere.process.rpl.model.X_EXP_ReplicationTrxLine;
import org.adempiere.server.rpl.trx.api.IReplicationIssueSolverDAO;
import org.adempiere.server.rpl.trx.api.IReplicationIssueSolverParams;
import org.adempiere.server.rpl.trx.api.IReplicationTrxLinesProcessorResult;
import org.adempiere.server.rpl.trx.spi.IReplicationIssueAware;
import org.adempiere.server.rpl.trx.spi.IReplicationIssueSolver;

import de.metas.util.Services;
import lombok.NonNull;

public class ReplicationTrxLinesProcessor implements ITrxItemChunkProcessor<I_EXP_ReplicationTrxLine, IReplicationTrxLinesProcessorResult>
{
	private final ReplicationTrxLinesProcessorResult result = new ReplicationTrxLinesProcessorResult();

	private IReplicationIssueSolverParams params = null;
	private IReplicationIssueSolver<? extends IReplicationIssueAware> issueSolver;

	private List<I_EXP_ReplicationTrxLine> currentTrxLines;

	public void setParams(@NonNull final IReplicationIssueSolverParams params)
	{
		this.params = params;
	}

	public void setReplicationIssueSolver(final IReplicationIssueSolver<? extends IReplicationIssueAware> issueSolver)
	{
		this.issueSolver = issueSolver;
	}

	@Override
	public void setTrxItemProcessorCtx(final ITrxItemProcessorContext processorCtx)
	{
		// this.processorCtx = processorCtx;
	}

	@Override
	public ReplicationTrxLinesProcessorResult getResult()
	{
		return result;
	}

	@Override
	public boolean isSameChunk(final I_EXP_ReplicationTrxLine item)
	{
		return false;
	}

	@Override
	public void newChunk(final I_EXP_ReplicationTrxLine item)
	{
		currentTrxLines = new ArrayList<I_EXP_ReplicationTrxLine>();
	}

	@Override
	public void process(final I_EXP_ReplicationTrxLine item) throws Exception
	{
		final IReplicationIssueAware issueAware = Services.get(IReplicationIssueSolverDAO.class).retrieveReplicationIssueAware(item);
		issueSolver.solveIssues(issueAware, params);

		currentTrxLines.add(item);
		result.addReplicationIssueAware(issueAware);
	}

	@Override
	public void completeChunk()
	{
		for (final I_EXP_ReplicationTrxLine line : currentTrxLines)
		{
			line.setReplicationTrxStatus(X_EXP_ReplicationTrxLine.REPLICATIONTRXSTATUS_Vollstaendig);
			InterfaceWrapperHelper.save(line);
		}
	}

	@Override
	public void cancelChunk()
	{
		currentTrxLines = null;
	}

}
