package de.metas.handlingunits.attribute.storage.impl;

import java.util.ArrayList;

import java.util.List;

import org.adempiere.mm.attributes.spi.IAttributeValueContext;

import de.metas.handlingunits.attribute.IAttributeValue;
import de.metas.handlingunits.attribute.storage.IAttributeStorage;
import de.metas.handlingunits.attribute.storage.IAttributeStorageListener;
import lombok.NonNull;

/**
 *
 *
 *
 */
public class CompositeAttributeStorageListener implements IAttributeStorageListener
{
	private final List<IAttributeStorageListener> listeners;

	public CompositeAttributeStorageListener()
	{
		listeners = new ArrayList<>();
	}

	/**
	 * Registers given listener.
	 *
	 * NOTEs:
	 * <ul>
	 * <li>listeners will be added weakly
	 * <li>a listener won't be added if was already added before
	 * </ul>
	 *
	 * @param listener
	 */
	public void addAttributeStorageListener(@NonNull final IAttributeStorageListener listener)
	{
		if (listeners.contains(listener))
		{
			return;
		}
		listeners.add(listener);
	}

	public void removeAttributeStorageListener(final IAttributeStorageListener listener)
	{
		if (listener == null)
		{
			return;
		}
		listeners.remove(listener);
	}

	public void clear()
	{
		listeners.clear();
	}

	@Override
	public void onAttributeValueCreated(final IAttributeValueContext attributeValueContext, final IAttributeStorage storage, final IAttributeValue attributeValue)
	{
		for (final IAttributeStorageListener listener : listeners)
		{
			listener.onAttributeValueCreated(attributeValueContext, storage, attributeValue);
		}
	}

	@Override
	public void onAttributeValueChanged(final IAttributeValueContext attributeValueContext, final IAttributeStorage storage, final IAttributeValue attributeValue, final Object valueOld)
	{
		for (final IAttributeStorageListener listener : listeners)
		{
			listener.onAttributeValueChanged(attributeValueContext, storage, attributeValue, valueOld);
		}
	}

	@Override
	public void onAttributeValueDeleted(final IAttributeValueContext attributeValueContext, final IAttributeStorage storage, final IAttributeValue attributeValue)
	{
		for (final IAttributeStorageListener listener : listeners)
		{
			listener.onAttributeValueDeleted(attributeValueContext, storage, attributeValue);
		}
	}

	@Override
	public void onAttributeStorageDisposed(final IAttributeStorage storage)
	{
		// if a listener gets notified about this event, it might well remove itself from this composite.
		// In order to prevent a ConcurrentModificationException, we iterate a copy
		final ArrayList<IAttributeStorageListener> listenersToIterate = new ArrayList<>(listeners);

		for (final IAttributeStorageListener listener : listenersToIterate)
		{
			listener.onAttributeStorageDisposed(storage);
		}
	}

	@Override
	public String toString()
	{
		return "CompositeAttributeStorageListener [listeners=" + listeners + "]";
	}

}
