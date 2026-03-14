package org.adempiere.ad.dao.impl;

import java.util.Optional;

/** */


import org.adempiere.model.InterfaceWrapperHelper;

import de.metas.util.TypedAccessor;

public class ModelAccessor<T> implements TypedAccessor<T>
{
	private final String columnName;

	public ModelAccessor(String columnName)
	{
		this.columnName = columnName;
	}

	/**
	 * Note: might as well return <code>null</code>!
	 */
	@Override
	public T getValue(final Object model)
	{
		final Optional<T> value = InterfaceWrapperHelper.getValue(model, columnName);
		return value.orElse(null);
	}

	@Override
	public String toString()
	{
		return "ModelAccessor [columnName=" + columnName + "]";
	}
}
