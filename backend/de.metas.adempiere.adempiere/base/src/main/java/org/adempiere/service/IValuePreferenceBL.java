package org.adempiere.service;

import java.util.Collection;

/** */

import java.util.Properties;

import org.adempiere.ad.element.api.AdWindowId;

import de.metas.util.ISingletonService;

import javax.annotation.Nullable;

public interface IValuePreferenceBL extends ISingletonService
{
	public interface IUserValuePreference
	{
		@Nullable AdWindowId getAdWindowId();

		String getName();

		String getValue();

		<T> T getValue(Class<T> clazz);
	}

	public interface IUserValuePreferences
	{
		@Nullable AdWindowId getAdWindowId();

		String getValue(String name);

		<T> T getValue(String name, Class<T> clazz);

		Collection<IUserValuePreference> values();
	}

	IUserValuePreferences getWindowPreferences(Properties ctx, AdWindowId adWindowId);

	Collection<IUserValuePreferences> getAllWindowPreferences(int AD_Client_ID, int AD_Org_ID, int AD_User_ID);

}
