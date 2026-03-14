package org.adempiere.model;

/** */

import com.google.common.base.Function;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;

import javax.annotation.Nullable;

/**
 * Class used to describe a column in a particular model.
 * <p>
 * Please note all COLUMN_ fields from generated interfaces.
 *
 * @param <MT>  table model class
 * @param <CMT> column's model class
 * @author tsa
 */
@Value
public final class ModelColumn<MT, CMT>
{
	/**
	 * Model class.
	 * e.g. if we are talking about C_Invoice.C_BPartner_ID field then this method will return I_C_Invoice.class
	 */
	private final Class<MT> modelClass;

	/**
	 * Column Name.
	 * e.g. if we are talking about C_Invoice.C_BPartner_ID field then this method will return "C_BPartner_ID"
	 */
	private final String columnName;

	/**
	 * Column's model class or null.
	 * e.g. if we are talking about C_Invoice.C_BPartner_ID field then this method will return I_C_BPartner.class
	 */
	private final Class<CMT> columnModelType;

	@NonFinal
	private transient String _modelTableName = null; // lazy loaded on demand

	public ModelColumn(
			@NonNull final Class<MT> modelClass,
			@NonNull final String columnName,
			@Nullable final Class<CMT> columnModelType)
	{
		this.modelClass = modelClass;
		this.columnName = columnName;
		this.columnModelType = columnModelType;
	}

	/**
	 * Gets model table name.
	 * e.g. if we are talking about I_C_Invoice model class then this method will return "C_Invoice" (i.e. I_C_Invoice.Table_Name)
	 */
	public String getTableName()
	{
		String modelTableName = _modelTableName;
		if (modelTableName == null)
		{
			modelTableName = _modelTableName = InterfaceWrapperHelper.getTableName(modelClass);
		}
		return modelTableName;
	}

	/**
	 * @return function which gets the column value from model.
	 */
	public final <ValueType> Function<MT, ValueType> asValueFunction()
	{
		// NOTE: we are returning a new instance each time, because this method is not used so offen
		// and because storing an instace for each ModelColumn will consume way more memory.

		final Function<MT, Object> valueFunction = new ValueFunction();
		@SuppressWarnings("unchecked")
		final Function<MT, ValueType> valueFunctionCasted = (Function<MT, ValueType>)valueFunction;
		return valueFunctionCasted;
	}

	/**
	 * Function which gets the Value of this model column.
	 *
	 * @author tsa
	 */
	private final class ValueFunction implements Function<MT, Object>
	{

		@Override
		public Object apply(final MT model)
		{
			final Object value = InterfaceWrapperHelper.getValue(model, columnName).orElse(null);
			return value;
		}

		@Override
		public String toString()
		{
			return "ValueFunction[" + modelClass + " -> " + columnName + "]";
		}
	}
}
