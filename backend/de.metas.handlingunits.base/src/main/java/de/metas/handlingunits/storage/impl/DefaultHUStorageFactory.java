package de.metas.handlingunits.storage.impl;

import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_Item;
import de.metas.handlingunits.storage.IHUItemStorage;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.handlingunits.storage.IHUStorage;
import de.metas.handlingunits.storage.IHUStorageDAO;
import de.metas.handlingunits.storage.IHUStorageFactory;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@ToString
public class DefaultHUStorageFactory implements IHUStorageFactory
{
	private final IHUStorageDAO storageDAO;

	public DefaultHUStorageFactory()
	{
		this(new HUStorageDAO());
	}

	public DefaultHUStorageFactory(@NonNull final IHUStorageDAO storageDAO)
	{
		this.storageDAO = storageDAO;
	}

	@Override
	public IHUStorage getStorage(@NonNull final I_M_HU hu)
	{
		return new HUStorage(this, hu);
	}

	@Override
	public IHUItemStorage getStorage(final I_M_HU_Item item)
	{
		return new HUItemStorage(this, item);
	}

	@Override
	public IHUStorageDAO getHUStorageDAO()
	{
		return storageDAO;
	}

	@Override
	@NonNull
	public List<IHUProductStorage> getHUProductStorages(@NonNull final List<I_M_HU> hus, final ProductId productId)
	{
		return hus.stream()
				.map(this::getStorage)
				.map(huStorage -> huStorage.getProductStorageOrNull(productId))
				.filter(Objects::nonNull)
				.collect(ImmutableList.toImmutableList());
	}

	@Override
	public Stream<IHUProductStorage> streamHUProductStorages(@NonNull final List<I_M_HU> hus)
	{
		return hus.stream()
				.map(this::getStorage)
				.flatMap(IHUStorage::streamProductStorages);
	}

	@Override
	public boolean isSingleProductWithQtyEqualsTo(@NonNull final I_M_HU hu, @NonNull final ProductId productId, @NonNull final Quantity qty)
	{
		final List<IHUProductStorage> productStorages = getStorage(hu).getProductStorages();
		return productStorages.size() == 1
				&& ProductId.equals(productStorages.get(0).getProductId(), productId)
				&& productStorages.get(0).getQty(qty.getUOM()).compareTo(qty) == 0;
	}
}
