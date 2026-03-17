package org.adempiere.ad.modelvalidator;

/** */

import org.adempiere.exceptions.AdempiereException;

import de.metas.util.Check;

@SuppressWarnings("serial")
public class ModelInterceptorInitException extends AdempiereException
{
	private final String modelInterceptorClassName;

	public ModelInterceptorInitException(final String modelInterceptorClassName, final Throwable error)
	{
		super(buildMsg(modelInterceptorClassName, error), error);
		this.modelInterceptorClassName = modelInterceptorClassName;
	}

	private final static String buildMsg(final String modelInterceptorClassName, final Throwable error)
	{
		final String message = (Check.isEmpty(modelInterceptorClassName, true) ? "(general)" : modelInterceptorClassName.trim())
				+ ": "
				+ (error == null ? "Unknown error" : error.toString());

		return message;
	}

	public String getModelInterceptorClassName()
	{
		return modelInterceptorClassName;
	}
}
