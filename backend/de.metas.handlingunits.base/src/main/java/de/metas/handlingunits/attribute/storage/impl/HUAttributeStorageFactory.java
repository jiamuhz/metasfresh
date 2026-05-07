package de.metas.handlingunits.attribute.storage.impl;

import java.util.Properties;

import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.Util;
import org.compiere.util.Util.ArrayKey;

import de.metas.cache.annotation.CacheCtx;
import de.metas.cache.annotation.CacheIgnore;
import de.metas.cache.annotation.CacheTrx;
import de.metas.handlingunits.model.I_M_HU;

public class HUAttributeStorageFactory extends AbstractModelAttributeStorageFactory<I_M_HU, HUAttributeStorage>
{
	@Override
	public boolean isHandled(final Object model)
	{
		if (model == null)
		{
			return false;
		}

		return InterfaceWrapperHelper.isInstanceOf(model, I_M_HU.class);
	}

	@Override
	protected I_M_HU getModelFromObject(final Object modelObj)
	{
		final I_M_HU hu = InterfaceWrapperHelper.create(modelObj, I_M_HU.class);
		return hu;
	}

	@Override
	protected final ArrayKey mkKey(final I_M_HU model)
	{
		return Util.mkKey(model.getClass().getName(), model.getM_HU_ID());
	}

	@Override
	protected HUAttributeStorage createAttributeStorage(final I_M_HU model)
	{
		final Properties ctx = InterfaceWrapperHelper.getCtx(model);
		final String trxName = InterfaceWrapperHelper.getTrxName(model);
		final int huId = model.getM_HU_ID();
		return createAttributeStorageCached(ctx, huId, trxName, model);
	}

	// @Cached // commented out because it's not applied anyways
	/* package */HUAttributeStorage createAttributeStorageCached(
			@CacheCtx final Properties ctx,
			final int huId,
			@CacheTrx final String trxName,
			@CacheIgnore final I_M_HU hu)
	{
		final HUAttributeStorage storage = new HUAttributeStorage(this, hu);
		return storage;
	}
}
