package de.metas.menu;

import java.util.Set;

import org.compiere.model.I_AD_Menu;

import de.metas.util.ISingletonService;

/** */

public interface IADMenuDAO extends ISingletonService
{
	Set<AdMenuId> retrieveMenuIdsWithMissingADElements();

	I_AD_Menu getById(AdMenuId menuId);
}
