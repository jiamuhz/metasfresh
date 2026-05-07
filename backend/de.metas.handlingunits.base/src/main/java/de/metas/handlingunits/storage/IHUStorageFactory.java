package de.metas.handlingunits.storage;

import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.IHUContext;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_Item;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Stream;

/**
 * Factory for HU related quantities.<br>
 * Use {@link IHandlingUnitsBL#getStorageFactory()}, unless you have a {@link IHUContext} to get it from.
 *
 *
 *
 */
public interface IHUStorageFactory
{
	IHUStorage getStorage(I_M_HU hu);

	IHUItemStorage getStorage(I_M_HU_Item item);

	IHUStorageDAO getHUStorageDAO();

	/**
	 * Iterate all <code>hus</code> and collect the {@link IHUProductStorage} storages from them.
	 *
	 * NOTE: Collect the product storages directly from given HUs. Don't navigate them to collect the product storages from possible included HUs.
	 *
	 * @return product storages; never return {@code null}. Only return items for existing storages. E.g. if none of the given {@code hus} has a storage, return an empty list.
	 */
	List<IHUProductStorage> getHUProductStorages(List<I_M_HU> hus, ProductId productId);

	Stream<IHUProductStorage> streamHUProductStorages(List<I_M_HU> hus);

	default Stream<IHUProductStorage> streamHUProductStorages(@NonNull final I_M_HU hu)
	{
		return streamHUProductStorages(ImmutableList.of(hu));
	}

	boolean isSingleProductWithQtyEqualsTo(I_M_HU hu, ProductId productId, Quantity qty);

}
