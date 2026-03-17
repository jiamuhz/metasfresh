package org.adempiere.ad.dao.impl;

/** */

import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.model.ModelColumn;

import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public final class ModelColumnNameValue<T>
{
	public static <ModelType> ModelColumnNameValue<ModelType> forColumn(@NonNull final ModelColumn<ModelType, ?> column)
	{
		return new ModelColumnNameValue<>(column.getColumnName());
	}

	public static <ModelType> ModelColumnNameValue<ModelType> forColumnName(final String columnName)
	{
		return new ModelColumnNameValue<>(columnName);
	}

	/**
	 * Creates a fully qualified column name
	 */
	public static <ModelType> ModelColumnNameValue<ModelType> forColumnName(final String tableName, final String columnName)
	{
		Check.assumeNotEmpty(tableName, "tableName not empty");
		Check.assumeNotEmpty(columnName, "columnName not empty");

		final String columnNameFQ = tableName + "." + columnName;
		return new ModelColumnNameValue<>(columnNameFQ);
	}

	@Getter
	private final String columnName;

	private ModelColumnNameValue(final String columnName)
	{
		Check.assumeNotEmpty(columnName, "columnName not empty");
		this.columnName = columnName;
	}

	/**
	 * <b>Might return <code>null</code>!</b>
	 * 
	 * @param model
	 * @return
	 */
	public Object getValue(@NonNull final T model)
	{
		final String columnName = getColumnName();
		if (InterfaceWrapperHelper.isNull(model, columnName))
		{
			return null;
		}
		return InterfaceWrapperHelper.getValue(model, columnName).orElse(null);
	}
}
