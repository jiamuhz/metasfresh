package de.metas.handlingunits.attribute.storage.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.adempiere.mm.attributes.AttributeId;
import org.adempiere.mm.attributes.spi.IAttributeValueContext;
import org.junit.Ignore;

import com.google.common.base.MoreObjects.ToStringHelper;

import de.metas.handlingunits.attribute.IAttributeValue;
import de.metas.handlingunits.attribute.storage.IAttributeStorage;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactory;
import de.metas.handlingunits.hutransaction.MutableHUTransactionAttribute;
import de.metas.uom.UOMType;

@Ignore
public class ListAttributeStorage extends AbstractAttributeStorage
{
	private final String id;
	private final List<IAttributeValue> initalAttributeValues;

	public ListAttributeStorage(final IAttributeStorageFactory storageFactory, final List<IAttributeValue> initalAttributeValues)
	{
		super(storageFactory);
		this.initalAttributeValues = initalAttributeValues;

		// generate a random ID
		id = UUID.randomUUID().toString();
	}

	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public IAttributeStorage getParentAttributeStorage()
	{
		return null;
	}

	/**
	 * Always returns an empty list.
	 */
	@Override
	public final List<IAttributeStorage> getChildAttributeStorages(final boolean loadIfNeeded_IGNORED)
	{
		return Collections.emptyList();
	}

	@Override
	public void updateHUTrxAttribute(final MutableHUTransactionAttribute huTrxAttribute, final IAttributeValue fromAttributeValue)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	protected void toString(final ToStringHelper stringHelper)
	{
		stringHelper
				.add("id", id)
				.add("initalAttributeValues", initalAttributeValues);
	}

	@Override
	protected List<IAttributeValue> loadAttributeValues()
	{
		return initalAttributeValues;
	}

	@Override
	protected List<IAttributeValue> generateAndGetInitialAttributes(final IAttributeValueContext attributesCtx, final Map<AttributeId, Object> defaultAttributesValue)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not supported.
	 *
	 * @throws UnsupportedOperationException
	 */
	@Override
	protected void addChildAttributeStorage(final IAttributeStorage childAttributeStorage)
	{
		throw new UnsupportedOperationException("Child attribute storages are not supported for " + this);
	}

	/**
	 * Method not supported.
	 *
	 * @throws UnsupportedOperationException
	 */
	@Override
	protected IAttributeStorage removeChildAttributeStorage(final IAttributeStorage childAttributeStorage)
	{
		throw new UnsupportedOperationException("Child attribute storages are not supported for " + this);
	}

	@Override
	public void saveChangesIfNeeded()
	{
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void setSaveOnChange(final boolean saveOnChange)
	{
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public UOMType getQtyUOMTypeOrNull()
	{
		throw new UnsupportedOperationException();
	}
}
