package de.metas.handlingunits.impl;

import de.metas.handlingunits.HUConstants;
import de.metas.handlingunits.IHUContext;
import de.metas.handlingunits.IHUContextFactory;
import de.metas.handlingunits.IMutableHUContext;
import de.metas.handlingunits.attribute.impl.SaveOnCommitHUAttributesDAO;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactory;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactoryService;
import de.metas.handlingunits.storage.IHUStorageDAO;
import de.metas.handlingunits.storage.impl.DefaultHUStorageFactory;
import de.metas.handlingunits.storage.impl.SaveOnCommitHUStorageDAO;
import de.metas.organization.ClientAndOrgId;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.model.PlainContextAware;
import org.adempiere.util.lang.IContextAware;
import org.compiere.util.Env;

import java.util.Properties;

public class HUContextFactory implements IHUContextFactory
{
	@Override
	public IMutableHUContext createMutableHUContextForProcessing(final IContextAware contextProvider)
	{
		final MutableHUContext huContext = new MutableHUContext(contextProvider);

		configureProcessingContext(huContext);

		return huContext;
	}

	@Override
	public IMutableHUContext createMutableHUContextForProcessing(
			@NonNull final Properties ctx,
			@NonNull final ClientAndOrgId clientAndOrgId)
	{
		final Properties ctxEffective = Env.copyCtx(ctx);
		Env.setClientId(ctxEffective, clientAndOrgId.getClientId());
		Env.setOrgId(ctxEffective, clientAndOrgId.getOrgId());
		final IContextAware contextProvider = PlainContextAware.newOutOfTrxAllowThreadInherited(ctxEffective);
		return createMutableHUContextForProcessing(contextProvider);
	}

	private void configureProcessingContext(final IMutableHUContext huContext)
	{
		if (!HUConstants.isUseCacheWhenCreatingHUs())
		{
			return;
		}

		final Boolean configured = huContext.getProperty(IHUContext.PROPERTY_Configured);
		if (configured != null && configured)
		{
			return;
		}

		final IHUStorageDAO storageDAO = new SaveOnCommitHUStorageDAO();
		final DefaultHUStorageFactory huStorageFactory = new DefaultHUStorageFactory(storageDAO);
		huContext.setHUStorageFactory(huStorageFactory);

		final SaveOnCommitHUAttributesDAO huAttributesDAO = new SaveOnCommitHUAttributesDAO();
		final IAttributeStorageFactoryService attributeStorageFactoryService = Services.get(IAttributeStorageFactoryService.class);

		final IAttributeStorageFactory attributesStorageFactory = attributeStorageFactoryService
				.prepareHUAttributeStorageFactory(huAttributesDAO);

		huContext.setHUAttributeStorageFactory(attributesStorageFactory);
	}

	@Override
	public IMutableHUContext createMutableHUContext(
			@NonNull final Properties ctx,
			@NonNull final ClientAndOrgId clientAndOrgId)
	{
		final Properties ctxEffective = Env.copyCtx(ctx);
		Env.setClientId(ctxEffective, clientAndOrgId.getClientId());
		Env.setOrgId(ctxEffective, clientAndOrgId.getOrgId());
		return new MutableHUContext(ctxEffective, ITrx.TRXNAME_None);
	}

	// TODO: probably will have to add ClientAndOrgId here as well. I don't have time for this now
	@Override
	public IMutableHUContext createMutableHUContext(final Properties ctx, @NonNull final String trxName)
	{
		final PlainContextAware contextProvider = PlainContextAware.newWithTrxName(ctx, trxName);
		return new MutableHUContext(contextProvider);
	}

	@Override
	public IMutableHUContext createMutableHUContext(final IContextAware contextProvider)
	{
		return new MutableHUContext(contextProvider);
	}

	@Override
	public final IHUContext deriveWithTrxName(final IHUContext huContext, final String trxNameNew)
	{
		// FIXME: handle the case of SaveOnCommit Storage and Attributes when changing transaction name

		//
		// Check: if transaction name was not changed, do nothing, return the old context
		final String contextTrxName = huContext.getTrxName();//  // TODO tbp: here the context has wrong org. WHYYYYY
		final ITrxManager trxManager = Services.get(ITrxManager.class);
		if (trxManager.isSameTrxName(contextTrxName, trxNameNew))
		{
			return huContext;
		}

		final IMutableHUContext huContextNew = huContext.copyAsMutable();
		huContextNew.setTrxName(trxNameNew);

		configureProcessingContext(huContextNew);

		return huContextNew;
	}

	@Override
	public String toString()
	{
		return "HUContextFactory []";
	}
}
