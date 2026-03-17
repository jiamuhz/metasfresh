package org.adempiere.ad.table.process;

import de.metas.process.JavaProcess;
import de.metas.process.RunOutOfTrx;
import de.metas.util.Services;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.model.I_AD_Tab;
import org.compiere.model.I_AD_Table;

import java.util.List;

/** */

public class AD_Table_CreatePK_ForWindow extends JavaProcess
{
	// services
	private final transient IQueryBL queryBL = Services.get(IQueryBL.class);

	@Override
	@RunOutOfTrx
	protected String doIt()
	{
		final TablePrimaryKeyGenerator generator = TablePrimaryKeyGenerator.newInstance();
		generator.generateForTablesIfPossible(retrieveTables());
		return generator.getSummary();
	}

	private List<I_AD_Table> retrieveTables()
	{
		final int adWindowId = getRecord_ID();
		if (adWindowId <= 0)
		{
			throw new FillMandatoryException("AD_Window_ID");
		}

		return queryBL.createQueryBuilder(I_AD_Tab.class, getCtx(), ITrx.TRXNAME_ThreadInherited)
				.addEqualsFilter(I_AD_Tab.COLUMN_AD_Window_ID, adWindowId)
				//
				.andCollect(I_AD_Tab.COLUMNNAME_AD_Table_ID, I_AD_Table.class)
				.addOnlyActiveRecordsFilter()
				.addEqualsFilter(I_AD_Table.COLUMNNAME_IsView, false)
				.orderBy(I_AD_Table.COLUMNNAME_AD_Table_ID)
				.create()
				.list(I_AD_Table.class);
	}

}
