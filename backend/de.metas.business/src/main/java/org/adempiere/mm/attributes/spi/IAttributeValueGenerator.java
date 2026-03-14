package org.adempiere.mm.attributes.spi;

/** */


import java.math.BigDecimal;
import java.util.Date;
import java.util.Properties;

import org.adempiere.mm.attributes.AttributeListValue;
import org.adempiere.mm.attributes.api.IAttributeSet;
import org.compiere.model.I_M_Attribute;

/**
 * If possible, instead of implementing this interface, please consider extending the {@link AbstractAttributeValueGenerator}.
 */
public interface IAttributeValueGenerator extends IAttributeValueHandler
{
	/**
	 * See X_M_Attribute.ATTRIBUTEVALUETYPE_*.
	 *
	 * @return value type or null if information is not not available
	 */
	String getAttributeValueType();

	/**
	 * Check if this generator can generate a value in given context.
	 *
	 *
	 * If it cannot (i.e. this method returns <code>false</code>), and one of the following generate methods are called, they will throw {@link UnsupportedOperationException}:
	 * <ul>
	 * <li>{@link #generateStringValue(Properties, IAttributeSet, I_M_Attribute)}
	 * <li>{@link #generateNumericValue(Properties, IAttributeSet, I_M_Attribute)}
	 * <li>{@link #generateDateValue(Properties, IAttributeSet, I_M_Attribute)}
	 * </ul>
	 *
	 * NOTE: {@link #generateAttributeValue(Properties, int, int, boolean, String)} is NOT considered.
	 *
	 * @return true if a value can be generated
	 */
	boolean canGenerateValue(Properties ctx, IAttributeSet attributeSet, I_M_Attribute attribute);

	/**
	 * Generate a String value. Will throw {@link UnsupportedOperationException} for attributes of different types.
	 *
	 * @param attributeSet attribute set
	 * @param attribute attribute on which the value will be generated
	 *
	 * @return generated string value
	 * @throws UnsupportedOperationException if value cannot be generated
	 */
	String generateStringValue(Properties ctx, IAttributeSet attributeSet, I_M_Attribute attribute);

	/**
	 * Generate a numeric value. Will throw {@link UnsupportedOperationException} for attributes of different types.
	 *
	 * @param attributeSet attribute set
	 * @param attribute attribute on which the value will be generated
	 * @return generated numeric value
	 * @throws UnsupportedOperationException if value cannot be generated
	 */
	BigDecimal generateNumericValue(Properties ctx, IAttributeSet attributeSet, I_M_Attribute attribute);

	/**
	 * Generate a date value. Will throw {@link UnsupportedOperationException} for attributes of different types.
	 *
	 * @param attributeSet attribute set
	 * @param attribute attribute on which the value will be generated
	 * @return generated date value
	 * @throws UnsupportedOperationException if value cannot be generated
	 */
	Date generateDateValue(Properties ctx, IAttributeSet attributeSet, I_M_Attribute attribute);

	/**
	 * Generate an attribute value based on table and record ID.
	 *
	 * Will be specific for each implementation. Will throw {@link UnsupportedOperationException} for attributes of different types.
	 *
	 * @param isSOTrx implementors might return different values for sales and purchase transactions
	 *
	 * @throws UnsupportedOperationException if value cannot be generated
	 */
	AttributeListValue generateAttributeValue(Properties ctx, int tableId, int recordId, boolean isSOTrx, String trxName);
}
