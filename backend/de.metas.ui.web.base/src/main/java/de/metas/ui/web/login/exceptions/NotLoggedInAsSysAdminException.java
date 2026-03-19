package de.metas.ui.web.login.exceptions;

import org.adempiere.exceptions.AdempiereException;

 

public class NotLoggedInAsSysAdminException extends AdempiereException
{
	private static final long serialVersionUID = -2528134231184643520L;

	public NotLoggedInAsSysAdminException()
	{
		super("not logged in as System Administrator");
	}
}
