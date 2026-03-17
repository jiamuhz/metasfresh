package de.metas.menu.impl;

import static org.adempiere.model.InterfaceWrapperHelper.load;

import java.util.Set;

import org.adempiere.ad.dao.IQueryBL;
import org.compiere.model.I_AD_Menu;

import de.metas.menu.AdMenuId;
import de.metas.menu.IADMenuDAO;
import de.metas.util.Services;

/** */

public class ADMenuDAO implements IADMenuDAO
{

	@Override
	public Set<AdMenuId> retrieveMenuIdsWithMissingADElements()
	{
		return Services.get(IQueryBL.class)
				.createQueryBuilder(I_AD_Menu.class)
				.addEqualsFilter(I_AD_Menu.COLUMN_AD_Element_ID, null)
				.create()
				.listIds(AdMenuId::ofRepoId);
	}

	@Override
	public I_AD_Menu getById(final AdMenuId menuId)
	{
		return load(menuId, I_AD_Menu.class);
	}

}
