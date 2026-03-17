package org.adempiere.ad.dao.impl;

import java.util.List;

import org.adempiere.ad.wrapper.POJOLookupMap;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.DB;

import de.metas.util.Check;

/** */

class PrimaryKeyQueryInsertFromColumn implements IQueryInsertFromColumn
{
	private final String tableName;

	public PrimaryKeyQueryInsertFromColumn(final String tableName)
	{
		super();
		Check.assumeNotEmpty(tableName, "tableName not empty");
		this.tableName = tableName;
	}

	@Override
	public String getSql(final List<Object> sqlParams)
	{
		return DB.TO_TABLESEQUENCE_NEXTVAL(tableName);
	}

	@Override
	public boolean update(Object toModel, String toColumnName, Object fromModel)
	{
		final int id = POJOLookupMap.get().nextId(toColumnName);
		InterfaceWrapperHelper.setValue(toModel, toColumnName, id);
		return true;
	}

}
