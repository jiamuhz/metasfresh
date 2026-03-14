package org.adempiere.ad.validationRule.impl;

import lombok.NonNull;

/** */

import org.adempiere.ad.validationRule.IValidationContext;
import org.compiere.util.Evaluatee;

public class EvaluateeValidationContext implements IValidationContext
{
	private final Evaluatee evaluatee;

	public EvaluateeValidationContext(@NonNull final Evaluatee evaluatee)
	{
		this.evaluatee = evaluatee;
	}

	/**
	 * @return null
	 */
	@Override
	public String getTableName()
	{
		return null;
	}

	@Override
	public String get_ValueAsString(final String variableName)
	{
		return evaluatee.get_ValueAsString(variableName);
	}

	@Override
	public Integer get_ValueAsInt(final String variableName, final Integer defaultValue)
	{
		return evaluatee.get_ValueAsInt(variableName, defaultValue);
	}

	@Override
	public Boolean get_ValueAsBoolean(final String variableName, final Boolean defaultValue)
	{
		return evaluatee.get_ValueAsBoolean(variableName, defaultValue);
	}

}
