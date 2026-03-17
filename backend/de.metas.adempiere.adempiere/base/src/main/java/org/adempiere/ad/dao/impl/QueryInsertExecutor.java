package org.adempiere.ad.dao.impl;

/** */

import com.google.common.base.MoreObjects;
import de.metas.util.Check;
import lombok.NonNull;
import org.adempiere.ad.dao.IQueryInsertExecutor;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.Adempiere;
import org.compiere.model.POInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class QueryInsertExecutor<ToModelType, FromModelType> implements IQueryInsertExecutor<ToModelType, FromModelType>
{
	private final Class<ToModelType> toModelClass;
	private final String toTableName;

	private final AbstractTypedQuery<FromModelType> fromQuery;
	private final Class<FromModelType> fromModelClass;

	private final String fromTableName;

	// Mapping
	private final Map<String, IQueryInsertFromColumn> toColumn2fromColumn = new HashMap<>();
	private final Map<String, IQueryInsertFromColumn> toColumn2fromColumnRO = Collections.unmodifiableMap(toColumn2fromColumn);

	// Options
	private boolean createSelectionOfInsertedRows = false;

	QueryInsertExecutor(@NonNull final Class<ToModelType> toModelClass, @NonNull final AbstractTypedQuery<FromModelType> fromQuery)
	{
		this.toModelClass = toModelClass;
		this.toTableName = InterfaceWrapperHelper.getTableName(toModelClass);

		this.fromQuery = fromQuery;
		this.fromModelClass = fromQuery.getModelClass();
		this.fromTableName = InterfaceWrapperHelper.getTableName(fromModelClass);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("toTableName", toTableName)
				.add("fromModelClass", fromModelClass)
				.add("fromQuery", fromQuery)
				.add("mapping", toColumn2fromColumnRO)
				.add("createSelectionOfInsertedRows", createSelectionOfInsertedRows ? Boolean.TRUE : null)
				.toString();
	}

	@Override
	public QueryInsertExecutorResult execute()
	{
		return fromQuery.executeInsert(this);
	}

	@Override
	public QueryInsertExecutor<ToModelType, FromModelType> mapCommonColumns()
	{
		final Set<String> fromColumnNames = InterfaceWrapperHelper.getModelPhysicalColumnNames(fromModelClass);
		final Set<String> toColumnNames = new HashSet<>(InterfaceWrapperHelper.getModelPhysicalColumnNames(toModelClass));
		final Set<String> toColumnsWithoutAPhysicalFromColumn = new HashSet<>(toColumnNames);
		toColumnNames.retainAll(fromColumnNames);
		for (final String toColumnName : toColumnNames)
		{
			final String fromColumnName = toColumnName;
			final IQueryInsertFromColumn from = new QueryInsertFromColumn(fromColumnName);
			mapColumn(toColumnName, from);
		}

		if (toColumnsWithoutAPhysicalFromColumn.removeAll(fromColumnNames))
		{
			mapVirtualColumnsInSourceTable(toColumnsWithoutAPhysicalFromColumn);
		}

		return this;
	}

	/**
	 * Attempt to map physical columns in the {@code toTableName} that do not map to physical columns in the {@code fromTableName}.
	 * Reasons include:
	 * <ul>
	 *    <li>column does not exist in the {@code fromTableName} => nothing to copy</li>
	 *    <li>column in {@code fromTableName} is virtual => try to retrieve value from {@code AD_Column.ColumnSQL}</li>
	 * <ul/>
	 */
	private void mapVirtualColumnsInSourceTable(final Set<String> toColumnsWithoutAPhysicalFromColumn)
	{
		if (Adempiere.isUnitTestMode())
		{
			return;
		}
		final POInfo fromTablePOInfo = POInfo.getPOInfo(fromTableName);
		Check.assumeNotNull(fromTablePOInfo, "cannot find POInfo for table name: {}", fromTableName);
		toColumnsWithoutAPhysicalFromColumn.stream()
				.filter(fromTablePOInfo::isVirtualColumn)
				.forEach(fromColumnName -> {
					final String toColumnName = fromColumnName;
					final String columnSql = fromTablePOInfo.getColumnSql(fromColumnName);
					Check.assumeNotEmpty(columnSql, "columnSQL unexpectedly null for {}.{}", fromTableName, fromColumnName);
					mapColumnToSql(toColumnName, columnSql);
				});
	}

	@Override
	public QueryInsertExecutor<ToModelType, FromModelType> mapColumn(final String toColumnName, final String fromColumnName)
	{
		final IQueryInsertFromColumn from = new QueryInsertFromColumn(fromColumnName);
		mapColumn(toColumnName, from);
		return this;
	}

	private final QueryInsertExecutor<ToModelType, FromModelType> mapColumn(final String toColumnName, final IQueryInsertFromColumn from)
	{
		this.toColumn2fromColumn.put(toColumnName, from);
		return this;
	}

	@Override
	public QueryInsertExecutor<ToModelType, FromModelType> mapColumnToConstant(final String toColumnName, final Object constantValue)
	{
		final IQueryInsertFromColumn from = new ConstantQueryInsertFromColumn(constantValue);
		mapColumn(toColumnName, from);
		return this;
	}

	@Override
	public QueryInsertExecutor<ToModelType, FromModelType> mapColumnToSql(final String toColumnName, final String fromSql)
	{
		final IQueryInsertFromColumn from = new SqlQueryInsertFromColumn(fromSql);
		mapColumn(toColumnName, from);
		return this;
	}

	@Override
	public QueryInsertExecutor<ToModelType, FromModelType> mapPrimaryKey()
	{
		final String toColumnName = getToKeyColumnName();
		final IQueryInsertFromColumn from = new PrimaryKeyQueryInsertFromColumn(getToTableName());
		mapColumn(toColumnName, from);
		return this;
	}

	@Override
	public QueryInsertExecutor<ToModelType, FromModelType> creatingSelectionOfInsertedRows()
	{
		this.createSelectionOfInsertedRows = true;
		return this;
	}

	/* package */ boolean isCreateSelectionOfInsertedRows()
	{
		return createSelectionOfInsertedRows;
	}

	/* package */String getToKeyColumnName()
	{
		return InterfaceWrapperHelper.getKeyColumnName(toModelClass);
	}

	/**
	 * @return "To ColumnName" to "From Column" map
	 */
	/* package */ Map<String, IQueryInsertFromColumn> getColumnMapping()
	{
		return toColumn2fromColumnRO;
	}

	/* package */ boolean isEmpty()
	{
		return toColumn2fromColumn.isEmpty();
	}

	/* package */ String getToTableName()
	{
		return toTableName;
	}

	/* package */ Class<ToModelType> getToModelClass()
	{
		return toModelClass;
	}
}
