package org.adempiere.ad.callout.api;

/** */

import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;

/**
 * Callout record. This is the underlying record for which a field callout or tab callout is invoked.
 * 
 *
 *
 */
public interface ICalloutRecord
{
	String getTableName();

	int getAD_Tab_ID();

	/**
	 * @return underlying model
	 */
	<T> T getModel(Class<T> modelClass);
	
	/**
	 * @return underlying model as it was before starting to change it
	 * @see #getModel(Class)
	 */
	<T> T getModelBeforeChanges(Class<T> modelClass);

	Object getValue(String columnName);

	/**
	 * Set New Value & call Callout
	 *
	 * @param columnName database column name
	 * @param value value
	 * @return error message or ""
	 */
	String setValue(String columnName, final Object value);

	boolean dataSave(boolean manualCmd);

	void dataRefresh();

	void dataRefreshAll();

	void dataRefreshRecursively();

	boolean isLookupValuesContainingId(@NonNull String columnName, @NonNull RepoIdAware id);
}
