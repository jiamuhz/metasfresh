package de.metas.ui.web.exceptions;

import org.adempiere.exceptions.AdempiereException;

 

@SuppressWarnings("serial")
public class InvalidDocumentVersionException extends AdempiereException
{
	public InvalidDocumentVersionException(final int expectedVersion, final int actualVersion)
	{
		super("Invalid version, expected " + expectedVersion + " but it was " + actualVersion);
	}
}
