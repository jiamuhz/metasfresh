package org.adempiere.ad.dao;

import org.adempiere.ad.dao.impl.NotQueryFilter;

/** */

@FunctionalInterface
public interface IQueryFilter<T>
{
	boolean accept(T model);

	default IQueryFilter<T> negate()
	{
		return NotQueryFilter.of(this);
	}
}
