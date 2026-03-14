package org.adempiere.ad.callout.annotations.api.impl;

/** */

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import lombok.NonNull;
import org.adempiere.ad.callout.annotations.api.ICalloutMethodPointcut;
import org.adempiere.ad.callout.api.ICalloutExecutor;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.callout.api.ICalloutInstance;
import org.adempiere.ad.callout.exceptions.CalloutExecutionException;
import org.slf4j.Logger;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ListMultimap;

import de.metas.logging.LogManager;
import de.metas.util.Check;

public final class AnnotatedCalloutInstance implements ICalloutInstance
{
	private static final transient Logger logger = LogManager.getLogger(AnnotatedCalloutInstance.class);

	private final String id;
	private final Object annotatedObject;
	private final String tableName;
	private final Set<String> columnNames;
	private final ListMultimap<CalloutMethodPointcutKey, CalloutMethodPointcut> mapPointcuts;

	/* package */AnnotatedCalloutInstance(final String id,
			final String tableName,
			final Collection<String> columnNames,
			@NonNull final Object annotatedObject,
			final ListMultimap<CalloutMethodPointcutKey, CalloutMethodPointcut> mapPointcuts)
	{
		Check.assumeNotEmpty(id, "id not empty");
		this.id = id;

		Check.assumeNotEmpty(tableName, "tableName not empty");
		this.tableName = tableName;

		Check.assumeNotEmpty(columnNames, "columnNames not empty");
		this.columnNames = ImmutableSet.copyOf(columnNames);

		this.annotatedObject = annotatedObject;

		Check.assume(mapPointcuts != null && !mapPointcuts.isEmpty(), "mapPointcuts not empty");
		this.mapPointcuts = ImmutableListMultimap.copyOf(mapPointcuts);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.addValue(annotatedObject)
				.toString();
	}

	public String getTableName()
	{
		return tableName;
	}

	/**
	 * Gets a set of column names on which this callout instance is listening
	 *
	 * @return set of column names
	 */
	public Set<String> getColumnNames()
	{
		return columnNames;
	}

	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public void execute(final ICalloutExecutor executor, final ICalloutField field)
	{
		final String columnName = field.getColumnName();
		final CalloutMethodPointcutKey pointcutKey = CalloutMethodPointcutKey.of(columnName);
		final List<CalloutMethodPointcut> pointcutsForKey = mapPointcuts.get(pointcutKey);
		if (pointcutsForKey.isEmpty())
		{
			return;
		}

		for (final ICalloutMethodPointcut pointcut : pointcutsForKey)
		{
			executePointcut(pointcut, executor, field);
		}
	}

	private void executePointcut(@NonNull final ICalloutMethodPointcut pointcut, @NonNull final ICalloutExecutor executor, @NonNull final ICalloutField field)
	{
		// Skip executing this callout if we were asked to skip when record copying mode is active
		if (pointcut.isSkipIfCopying() && field.isRecordCopyingMode())
		{
			logger.info("Skip invoking callout because we are in copying mode: {}", pointcut.getMethod());
			return;
		}
		if (pointcut.isSkipIfIndirectlyCalled() && executor.getActiveCalloutInstancesCount() > 1)
		{
			logger.info("Skip invoking callout because it is called via another callout: {}", pointcut.getMethod());
			return;
		}

		try
		{
			final Method method = pointcut.getMethod();
			final Object model = field.getModel(pointcut.getModelClass());

			// make sure the method can be executed
			if (!method.isAccessible())
			{
				method.setAccessible(true);
			}

			if (pointcut.isParamCalloutFieldRequired())
			{
				method.invoke(annotatedObject, model, field);
			}
			else
			{
				method.invoke(annotatedObject, model);
			}
		}
		catch (final CalloutExecutionException e)
		{
			throw e;
		}
		catch (final Exception e)
		{
			throw CalloutExecutionException.of(e)
					.setCalloutExecutor(executor)
					.setCalloutInstance(this)
					.setField(field);
		}
	}
}
