package de.metas.handlingunits.storage;

import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import org.compiere.model.I_C_UOM;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Generic Handling Unit Storage.
 *
 * Implementations of this interface can be at any level of a given HU (e.g. HU Level, HU Item level etc).
 *
 * @author tsa
 *
 */
public interface IGenericHUStorage
{
	/**
	 *
	 * @return parent storage or null
	 */
	IGenericHUStorage getParentStorage();

	/**
	 * Add or removed given <code>qty</code> to storage.
	 *
	 * @param productId
	 * @param qty the qty to add (or to remove, if negative)
	 * @param uom qty's UOM
	 */
	void addQty(ProductId productId, BigDecimal qty, I_C_UOM uom);

	/**
	 * @return storage qty for <code>product</code> in <code>uom</code> unit of measure
	 */
	BigDecimal getQty(ProductId productId, I_C_UOM uom);

	default Quantity getQuantity(ProductId productId, I_C_UOM uom)
	{
		return Quantity.of(getQty(productId, uom), uom);
	}

	Optional<Quantity> getQuantity(ProductId productId);

	/**
	 *
	 * @return true if storage is empty
	 */
	boolean isEmpty();

	/**
	 *
	 * @param productId
	 * @return true if storage is empty for given product
	 */
	boolean isEmpty(ProductId productId);

	/**
	 * @return true if this is a virtual storage (i.e. a storage for a virtual HU, HU Item etc)
	 */
	boolean isVirtual();
}
