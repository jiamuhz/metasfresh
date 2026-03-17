package org.adempiere.server.rpl.trx.api.impl;

import static org.adempiere.model.InterfaceWrapperHelper.newInstance;
import static org.adempiere.model.InterfaceWrapperHelper.save;

/** */

import java.util.Properties;
import java.util.concurrent.CopyOnWriteArraySet;

import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.PlainContextAware;
import org.adempiere.process.rpl.model.I_EXP_ReplicationTrx;
import org.adempiere.process.rpl.model.I_EXP_ReplicationTrxLine;
import org.adempiere.server.rpl.trx.api.IReplicationTrxBL;
import org.adempiere.server.rpl.trx.api.IReplicationTrxDAO;

import de.metas.util.Check;
import de.metas.util.Services;

public class ReplicationTrxBL implements IReplicationTrxBL
{
	private final CopyOnWriteArraySet<String> excludeTableNames = new CopyOnWriteArraySet<>();

	private int getCreateReplicationTrx(final Properties ctx, final String replicationTrxName, final String trxName)
	{
		final I_EXP_ReplicationTrx replicationTrx = Services.get(IReplicationTrxDAO.class).retrieveReplicationTrxByName(ctx, replicationTrxName, ITrx.TRXNAME_None);
		if (replicationTrx != null)
		{
			return replicationTrx.getEXP_ReplicationTrx_ID();
		}

		final I_EXP_ReplicationTrx replicationTrxNew = newInstance(
				I_EXP_ReplicationTrx.class,
				PlainContextAware.newWithTrxName(ctx, trxName));

		replicationTrxNew.setName(replicationTrxName);
		save(replicationTrxNew);

		return replicationTrxNew.getEXP_ReplicationTrx_ID();
	}

	@Override
	public I_EXP_ReplicationTrxLine createAndMatchVoidReplicationTrxLine(final Properties ctx, final String tableName, final String replicationTrxName, final String trxName)
	{
		Check.assume(!isTableIgnored(tableName), "tableName not ignored");

		final int replicationTrxId = getCreateReplicationTrx(ctx, replicationTrxName, trxName);

		//
		// Create a new ReplicationTrxLine
		final I_EXP_ReplicationTrxLine replicationTrxLine = newInstance(
				I_EXP_ReplicationTrxLine.class,
				PlainContextAware.newWithTrxName(ctx, trxName));
		replicationTrxLine.setEXP_ReplicationTrx_ID(replicationTrxId);
		return replicationTrxLine;
	}

	@Override
	public void addTableToIgnoreList(final String tableName)
	{
		Check.assumeNotEmpty(tableName, "tableName not empty");
		excludeTableNames.add(tableName);
	}

	@Override
	public boolean isTableIgnored(final String tableName)
	{
		return excludeTableNames.contains(tableName);
	}
}
