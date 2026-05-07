package de.metas.handlingunits.storage;

import java.math.BigDecimal;
import java.util.Comparator;

import org.compiere.model.I_C_UOM;

import de.metas.handlingunits.allocation.IAllocationRequest;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.uom.IUOMDAO;
import de.metas.uom.UomId;
import de.metas.util.Services;
import lombok.NonNull;

public interface IProductStorage
{
	/** Comparator used to sort {@link IProductStorage}s by Product Name */
	Comparator<IProductStorage> COMPARATOR_ByProductName = new Comparator<IProductStorage>()
	{

		@Override
		public int compare(final IProductStorage productStorage1, final IProductStorage productStorage2)
		{
			final String productName1 = getProductName(productStorage1);
			final String productName2 = getProductName(productStorage2);
			return productName1.compareTo(productName2);
		}

		private final String getProductName(final IProductStorage productStorage)
		{
			if (productStorage == null)
			{
				return "";
			}

			return Services.get(IProductBL.class).getProductName(productStorage.getProductId());
		}
	};

	ProductId getProductId();

	I_C_UOM getC_UOM();

	BigDecimal getQtyFree();

	Quantity getQty();

	default BigDecimal getQtyAsBigDecimal()
	{
		return getQty().toBigDecimal();
	}

	default Quantity getQty(@NonNull final UomId uomId)
	{
		final I_C_UOM uomRecord = Services.get(IUOMDAO.class).getById(uomId);
		return getQty(uomRecord);
	}

	/**
	 * Gets storage Qty, converted to given UOM.
	 *
	 * @return Qty converted to given UOM.
	 */
	Quantity getQty(I_C_UOM uom);

	/**
	 * @return storage capacity
	 */
	BigDecimal getQtyCapacity();

	IAllocationRequest removeQty(IAllocationRequest request);

	IAllocationRequest addQty(IAllocationRequest request);

	void markStaled();

	boolean isEmpty();

	/**
	 * @return true if this storage allows negative storages
	 */
	boolean isAllowNegativeStorage();

}
