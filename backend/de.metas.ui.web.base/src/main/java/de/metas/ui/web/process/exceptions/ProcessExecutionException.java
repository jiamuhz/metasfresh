package de.metas.ui.web.process.exceptions;

import org.adempiere.exceptions.AdempiereException;

@SuppressWarnings("serial")
public class ProcessExecutionException extends AdempiereException
{
	public ProcessExecutionException(final String message)
	{
		super(message);
	}
}
