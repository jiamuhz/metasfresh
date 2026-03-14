package org.adempiere.pos.impl;

/** */


import java.util.List;
import java.util.Properties;

import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.pos.IPOSKeyKayoutDAO;
import org.adempiere.util.proxy.Cached;
import org.compiere.model.I_C_POSKey;
import org.compiere.model.I_C_POSKeyLayout;

import de.metas.cache.annotation.CacheCtx;
import de.metas.cache.annotation.CacheTrx;
import de.metas.util.Check;
import de.metas.util.Services;

public class POSKeyKayoutDAO implements IPOSKeyKayoutDAO
{
	@Override
	public List<I_C_POSKey> retrievePOSKeys(final I_C_POSKeyLayout keyLayout)
	{
		Check.assumeNotNull(keyLayout, "keyLayout not null");
		final Properties ctx = InterfaceWrapperHelper.getCtx(keyLayout);
		final String trxName = InterfaceWrapperHelper.getTrxName(keyLayout);
		final int posKeyLayoutId = keyLayout.getC_POSKeyLayout_ID();

		return retrievePOSKeys(ctx, posKeyLayoutId, trxName);
	}

	@Cached(cacheName = I_C_POSKey.Table_Name + "#by#" + I_C_POSKey.COLUMNNAME_C_POSKeyLayout_ID)
	List<I_C_POSKey> retrievePOSKeys(@CacheCtx Properties ctx, int posKeyLayoutId, @CacheTrx String trxName)
	{
		final IQueryBL queryBL = Services.get(IQueryBL.class);
		final IQueryBuilder<I_C_POSKey> queryBuilder = queryBL.createQueryBuilder(I_C_POSKey.class, ctx, trxName)
				.addEqualsFilter(I_C_POSKey.COLUMNNAME_C_POSKeyLayout_ID, posKeyLayoutId)
				.addOnlyActiveRecordsFilter();

		queryBuilder.orderBy()
				.addColumn(I_C_POSKey.COLUMNNAME_SeqNo);

		return queryBuilder
				.create()
				.list(I_C_POSKey.class);
	}
}
