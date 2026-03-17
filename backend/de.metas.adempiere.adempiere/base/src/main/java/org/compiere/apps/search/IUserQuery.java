package org.compiere.apps.search;

import java.util.List;

import de.metas.i18n.ITranslatableString;

/** */

public interface IUserQuery
{
	int getId();
	
	int getAD_User_ID();
	
	ITranslatableString getCaption();
	
	List<IUserQueryRestriction> getRestrictions();
}
