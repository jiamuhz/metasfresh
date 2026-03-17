package org.adempiere.ad.dao.impl;

/** */

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.ISqlQueryFilter;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.DisplayType;

public class ActiveRecordQueryFilter<T> implements IQueryFilter<T>, ISqlQueryFilter
{
	public static <T> IQueryFilter<T> getInstance()
	{
		@SuppressWarnings("unchecked")
		final ActiveRecordQueryFilter<T> instanceCasted = instance;
		return instanceCasted;
	}

	public static <T> IQueryFilter<T> getInstance(final Class<T> clazz)
	{
		return getInstance();
	}

	@SuppressWarnings("rawtypes")
	private static final ActiveRecordQueryFilter instance = new ActiveRecordQueryFilter();

	private static final String COLUMNNAME_IsActive = "IsActive";

	private final String sql;
	private final List<Object> sqlParams;

	private ActiveRecordQueryFilter()
	{
		this.sql = COLUMNNAME_IsActive + "=?";
		this.sqlParams = Arrays.asList((Object)true);
	}

	@Override
	public String toString()
	{
		return "Active";
	}

	@Override
	public String getSql()
	{
		return sql;
	}

	@Override
	public List<Object> getSqlParams(final Properties ctx)
	{
		return sqlParams;
	}

	@Override
	public boolean accept(final T model)
	{
		final Object isActiveObj = InterfaceWrapperHelper.getValueOrNull(model, COLUMNNAME_IsActive);
		final boolean isActive = DisplayType.toBoolean(isActiveObj);
		return isActive;
	}

}
