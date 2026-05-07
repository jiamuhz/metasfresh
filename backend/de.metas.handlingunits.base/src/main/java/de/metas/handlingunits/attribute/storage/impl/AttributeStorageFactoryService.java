package de.metas.handlingunits.attribute.storage.impl;

import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;

import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.attribute.IHUAttributesDAO;
import de.metas.handlingunits.attribute.impl.HUAttributesDAO;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactory;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactoryService;
import de.metas.handlingunits.attribute.storage.IAttributeStorageListener;
import de.metas.handlingunits.storage.IHUStorageFactory;
import de.metas.logging.LogManager;
import de.metas.util.Services;
import lombok.NonNull;

public class AttributeStorageFactoryService implements IAttributeStorageFactoryService
{
	private static final Logger logger = LogManager.getLogger(AttributeStorageFactoryService.class);

	private final CopyOnWriteArrayList<Class<? extends IAttributeStorageFactory>> attributeStorageFactories = new CopyOnWriteArrayList<>();

	private final CopyOnWriteArrayList<IAttributeStorageListener> attributeStorageListeners = new CopyOnWriteArrayList<>();

	public AttributeStorageFactoryService()
	{
		// Setup Default Attribute Storage Factories
		addAttributeStorageFactory(HUAttributeStorageFactory.class);
		addAttributeStorageFactory(ASIAttributeStorageFactory.class);
		addAttributeStorageFactory(ASIAwareAttributeStorageFactory.class);
	}

	@Override
	public IAttributeStorageFactory createHUAttributeStorageFactory()
	{
		final IHUStorageFactory huStorageFactory = Services.get(IHandlingUnitsBL.class).getStorageFactory();
		return createHUAttributeStorageFactory(huStorageFactory, HUAttributesDAO.instance);
	}

	@Override
	public IAttributeStorageFactory createHUAttributeStorageFactory(@NonNull final IHUStorageFactory huStorageFactory)
	{
		return createHUAttributeStorageFactory(huStorageFactory, HUAttributesDAO.instance);
	}

	@Override
	public IAttributeStorageFactory createHUAttributeStorageFactory(
			@NonNull final IHUStorageFactory huStorageFactory,
			@NonNull final IHUAttributesDAO huAttributesDAO)
	{
		final IAttributeStorageFactory factory = prepareHUAttributeStorageFactory(huAttributesDAO);
		factory.setHUStorageFactory(huStorageFactory);

		return factory;
	}

	@Override
	public IAttributeStorageFactory prepareHUAttributeStorageFactory(@NonNull final IHUAttributesDAO huAttributesDAO)
	{
		final CompositeAttributeStorageFactory factory = new CompositeAttributeStorageFactory();
		factory.setHUAttributesDAO(huAttributesDAO);
		factory.addAttributeStorageFactoryClasses(attributeStorageFactories);

		for (final IAttributeStorageListener attributeStorageListener : attributeStorageListeners)
		{
			factory.addAttributeStorageListener(attributeStorageListener);
		}
		return factory;
	}

	@Override
	public void addAttributeStorageFactory(@NonNull final Class<? extends IAttributeStorageFactory> attributeStorageFactoryClass)
	{
		final boolean added = attributeStorageFactories.addIfAbsent(attributeStorageFactoryClass);

		if (added)
		{
			logger.info("Registered: {}", attributeStorageFactoryClass);
		}
		else
		{
			logger.warn("Already registered: {}", attributeStorageFactoryClass);
		}
	}

	@Override
	public void addAttributeStorageListener(@NonNull final IAttributeStorageListener attributeStorageListener)
	{
		attributeStorageListeners.add(attributeStorageListener);

		logger.info("Registered: {}", attributeStorageListener);
	}
}
