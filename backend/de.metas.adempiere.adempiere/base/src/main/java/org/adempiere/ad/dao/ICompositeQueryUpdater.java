package org.adempiere.ad.dao;

/** */

import org.adempiere.ad.dao.impl.ModelColumnNameValue;

import javax.annotation.Nullable;
import java.math.BigDecimal;

/**
 * Use {@link IQueryBL#createCompositeQueryUpdater(Class)} to get an instance.
 *
 *
 *
 * @param <T> model class of the table to be updated.
 */
public interface ICompositeQueryUpdater<T> extends ISqlQueryUpdater<T>
{

	ICompositeQueryUpdater<T> addQueryUpdater(IQueryUpdater<T> updater);

	ICompositeQueryUpdater<T> addSetColumnValue(String columnName, @Nullable Object value);

	ICompositeQueryUpdater<T> addSetColumnFromColumn(String columnName, ModelColumnNameValue<T> fromColumnName);

	ICompositeQueryUpdater<T> addAddValueToColumn(String columnName, BigDecimal valueToAdd);

	ICompositeQueryUpdater<T> addAddValueToColumn(String columnName, BigDecimal valueToAdd, IQueryFilter<T> onlyWhenFilter);
}
