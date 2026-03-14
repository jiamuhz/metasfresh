package org.adempiere.ad.dao;

/** */


import java.util.Comparator;

public interface IQueryOrderBy
{
	public enum Direction
	{
		Ascending,
		Descending,
	}

	public enum Nulls
	{
		First,
		Last,
	};

	String getSql();

	Comparator<Object> getComparator();

	<T> Comparator<T> getComparator(Class<T> modelClass);
}
