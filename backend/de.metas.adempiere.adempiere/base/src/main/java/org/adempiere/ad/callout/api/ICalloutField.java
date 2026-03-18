package org.adempiere.ad.callout.api;

/** */

import java.util.Properties;

import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;

import de.metas.util.Check;

/**
 * Callout aware field
 *
 * @author tsa
 *
 */
public interface ICalloutField
{

	boolean isTriggerCalloutAllowed();

	ICalloutRecord getCalloutRecord();

	Properties getCtx();

	String getTableName();

	Object getValue();

	/**
	 * @return old/previous value (from when the underlying model was checked out for changing)
	 */
	Object getOldValue();

	String getColumnName();

	/**
	 * @see ICalloutRecord#getModel(Class)
	 */
	default <T> T getModel(final Class<T> modelClass)
	{
		final ICalloutRecord calloutRecord = getCalloutRecord();
		final T model = calloutRecord.getModel(modelClass);
		Check.assumeNotNull(model, "model not null");
		return model;
	}

	/**
	 * @see ICalloutRecord#getModelBeforeChanges(Class)
	 */
	default <T> T getModelBeforeChanges(final Class<T> modelClass)
	{
		final ICalloutRecord calloutRecord = getCalloutRecord();
		final T model = calloutRecord.getModelBeforeChanges(modelClass);
		Check.assumeNotNull(model, "model not null");
		return model;
	}

	int getWindowNo();

	/**
	 * @return true if we are currently creating this record by copying (with or without details) from another record
	 */
	boolean isRecordCopyingMode();

	/**
	 * @return true if we are currently creating this record by copying (with details) from another record
	 */
	boolean isRecordCopyingModeIncludingDetails();

	ICalloutExecutor getCurrentCalloutExecutor();

	/**
	 * Create and fire Data Status Error Event
	 *
	 * @param AD_Message message
	 * @param info that is shown to the user when hovering over the actual message
	 * @param isError if not true, it is a Warning
	 */
	void fireDataStatusEEvent(String AD_Message, String info, boolean isError);

	/**
	 * Create and fire Data Status Error Event (from Error Log)
	 *
	 * @param errorLog log
	 */
	@Deprecated
	void fireDataStatusEEvent(ValueNamePair errorLog);

	/**
	 * Put to window context.
	 */
	default void putContext(final String name, final String value)
	{
		Env.setContextItem(getCtx(), name, value);
	}

	/**
	 * Put to window context.
	 */
	default void putWindowContext(final String name, final String value)
	{
		Env.setContextItem(getCtx(), getWindowNo(), name, value);
	}

	default void putContext(final String name, final boolean value)
	{
		Env.setContextItem(getCtx(), getWindowNo(), name, value);
	}

	/**
	 * Put to window context.
	 */
	default void putWindowContext(final String name, final boolean value)
	{
		Env.setContextItem(getCtx(), getWindowNo(), name, value);
	}

	default void putContext(final String name, final java.util.Date value)
	{
		Env.setContextItem(getCtx(), getWindowNo(), name, value);
	}

	/**
	 * Put to window context.
	 */
	default void putContext(final String name, final int value)
	{
		Env.setContextItem(getCtx(), getWindowNo(), name, value);
	}

	default int getGlobalContextAsInt(final String name)
	{
		return Env.getContextItemAsInt(getCtx(), name);
	}

	default int getTabInfoContextAsInt(final String name)
	{
		return Env.getContextItemAsInt(getCtx(), getWindowNo(), Env.TAB_INFO, name);
	}

	default boolean getContextAsBoolean(final String name)
	{
		return DisplayType.toBoolean(Env.getContextItem(getCtx(), getWindowNo(), name));
	}

	boolean isLookupValuesContainingId(@NonNull RepoIdAware id);
}
