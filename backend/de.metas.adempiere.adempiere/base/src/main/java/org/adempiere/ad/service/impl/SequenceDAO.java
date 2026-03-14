package org.adempiere.ad.service.impl;

/** */

import java.util.Properties;

import org.adempiere.ad.dao.ICompositeQueryFilter;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.impl.UpperCaseQueryFilterModifier;
import org.adempiere.ad.service.ISequenceDAO;
import org.adempiere.ad.service.ITableSequenceChecker;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.service.IClientDAO;
import org.compiere.model.I_AD_Sequence;
import org.compiere.util.DB;

import de.metas.util.Services;

public class SequenceDAO implements ISequenceDAO
{
	@Override
	public I_AD_Sequence retrieveTableSequenceOrNull(Properties ctx, String tableName, String trxName)
	{
		final IQueryBuilder<I_AD_Sequence> queryBuilder = Services.get(IQueryBL.class)
				.createQueryBuilder(I_AD_Sequence.class, ctx, trxName);

		final ICompositeQueryFilter<I_AD_Sequence> filters = queryBuilder.getCompositeFilter();
		filters.addEqualsFilter(I_AD_Sequence.COLUMNNAME_Name, tableName, UpperCaseQueryFilterModifier.instance);
		filters.addEqualsFilter(I_AD_Sequence.COLUMNNAME_IsTableID, true);
		filters.addEqualsFilter(I_AD_Sequence.COLUMNNAME_AD_Client_ID, IClientDAO.SYSTEM_CLIENT_ID);

		final I_AD_Sequence sequence = queryBuilder.create()
				.firstOnly(I_AD_Sequence.class);

		return sequence;
	}

	@Override
	public I_AD_Sequence retrieveTableSequenceOrNull(Properties ctx, String tableName)
	{
		final String trxName = ITrx.TRXNAME_None;
		return retrieveTableSequenceOrNull(ctx, tableName, trxName);
	}

	@Override
	public ITableSequenceChecker createTableSequenceChecker(final Properties ctx)
	{
		return new TableSequenceChecker(ctx);
	}

	@Override
	public void renameTableSequence(final Properties ctx, final String tableNameOld, final String tableNameNew)
	{
		//
		// Rename the AD_Sequence
		final I_AD_Sequence adSequence = retrieveTableSequenceOrNull(ctx, tableNameOld, ITrx.TRXNAME_ThreadInherited);
		if (adSequence != null)
		{
			adSequence.setName(tableNameNew);
			InterfaceWrapperHelper.save(adSequence);
		}

		//
		// Rename the database native sequence
		{
			final String dbSequenceNameOld = DB.getTableSequenceName(tableNameOld);
			final String dbSequenceNameNew = DB.getTableSequenceName(tableNameNew);
			DB.getDatabase().renameSequence(dbSequenceNameOld, dbSequenceNameNew);
		}
	}
}
