package de.metas.handlingunits.attribute.storage;

import org.adempiere.mm.attributes.spi.IAttributeValueContext;

import de.metas.handlingunits.attribute.IAttributeValue;

/**
 * Note: currently this is used to collect attribute related events, so they can be persisted all at ones
 */
public interface IAttributeStorageListener
{
	default void onAttributeValueCreated(IAttributeValueContext attributeValueContext, IAttributeStorage storage, IAttributeValue attributeValue)
	{
	}

	default void onAttributeValueChanged(IAttributeValueContext attributeValueContext, IAttributeStorage storage, IAttributeValue attributeValue, Object valueOld)
	{
	}

	/** @deprecated this method is not used anymore */
	@Deprecated
	default void onAttributeValueDeleted(IAttributeValueContext attributeValueContext, IAttributeStorage storage, IAttributeValue attributeValue)
	{
	}

	default void onAttributeStorageDisposed(final IAttributeStorage storage)
	{
	}
}
