package org.compiere.util;

import lombok.NonNull;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Optional;

/** */

/**
 * Extend {@link Evaluatee} interface with more methods.
 * <p>
 * To create {@link Evaluatee2} instances, please use {@link Evaluatees}.
 *
 * @author tsa
 */
public interface Evaluatee2 extends Evaluatee
{
	/**
	 * Check if variableName exists.
	 * Note: that in case when is not sure if the variable exist, the implementation of this method should return true
	 *
	 * @return true if the variable exists
	 */
	boolean has_Variable(String variableName);

	/**
	 * Get Old Variable Value
	 *
	 * @param variableName name
	 * @return value
	 */
	@Nullable
	String get_ValueOldAsString(String variableName);

	@Nullable
	default Integer get_ValueOldAsInt(final String variableName, @Nullable final Integer defaultValue)
	{
		final String valueStr = get_ValueOldAsString(variableName);
		return Evaluatee.convertToInteger(variableName, valueStr, defaultValue);
	}

	@Nullable
	default Boolean get_ValueOldAsBoolean(final String variableName, @Nullable final Boolean defaultValue)
	{
		final String valueStr = get_ValueOldAsString(variableName);
		return DisplayType.toBoolean(valueStr, defaultValue);
	}

	@Nullable
	default BigDecimal get_ValueOldAsBigDecimal(final String variableName, @Nullable final BigDecimal defaultValue)
	{
		final String valueStr = get_ValueOldAsString(variableName);
		return Evaluatee.convertToBigDecimal(variableName, valueStr, defaultValue);
	}

	@Nullable
	default java.util.Date get_ValueOldAsDate(final String variableName, @Nullable final java.util.Date defaultValue)
	{
		final String valueStr = get_ValueOldAsString(variableName);
		return Evaluatee.convertToDate(variableName, valueStr, defaultValue);
	}

	@Override
	default Optional<Object> get_ValueIfExists(@NonNull final String variableName, @NonNull final Class<?> targetType)
	{
		return has_Variable(variableName)
				? Evaluatee.super.get_ValueIfExists(variableName, targetType)
				: Optional.empty();
	}

}
