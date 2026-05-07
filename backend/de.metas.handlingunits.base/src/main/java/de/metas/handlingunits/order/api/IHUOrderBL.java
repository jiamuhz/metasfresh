package de.metas.handlingunits.order.api;

import java.util.Date;
import java.util.Properties;
import java.util.function.Consumer;

import org.compiere.model.I_M_Forecast;

import de.metas.handlingunits.model.I_C_Order;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.interfaces.I_C_OrderLine;
import de.metas.product.ProductId;
import de.metas.util.ISingletonService;

/**
 * Order and Handling Units integration BL
 *
 * @author tsa
 *
 */
public interface IHUOrderBL extends ISingletonService
{
	/**
	 * Updates the HU relevant fields in order line. This method updates the order line based on the following columns:
	 * <ul>
	 * <li>M_Product_ID</li>: if changed or if inconsistent with the given <code>ol</code>'s M_HU_PI_Item_Product's M_Product_ID
	 * <li>C_BPartner_ID</li>
	 * <li>DateOrdered</li>
	 * <li>M_HU_PI_Item_Product_ID</li>
	 * </ul>
	 *
	 * Notes:
	 * <ul>
	 * <li>It does nothing if <code>M_Product_ID</code> is not set or if <code>ManualQtyItemCapacity=Y</code></li>
	 * <li>It might also update the prices</li>
	 * <li>It also set/resets <code>M_HU_PI_Item_Product_ID</code> from <code>C_BPartner_ID</code>, <code>M_Product_ID</code> and <code>DateOrdered</code>,
	 * if either BPartner or Product were changed, or - for new ols, as of FRESH-351 - if <code>M_Product_ID</code> is inconsistent with
	 * the given <code>ol</code>'s <code>M_HU_PI_Item_Product<code>'s <code>M_Product_ID</code>.</li>
	 * </ul>
	 *
	 * @param changedColumnName optional, may be <code>null</code>. Can be used to explicitly state a column has been changed (for using the method in invoice).
	 */
	void updateOrderLine(I_C_OrderLine ol, String changedColumnName);

	/**
	 * Update the product quantity (Qty CU) if the M_HU_PI_Item_Produc's qty (Qty TU) was changed
	 *
	 * @return true if the qty CU was modified, false otherwise
	 */
	boolean updateQtyCU(I_C_Order order);

	/**
	 * Update the M_HU_PI_Item_Product quantity(Qty TU) if the M_Product's qty (Qty CU) was changed
	 *
	 * @param order
	 * @return true if the qty TU was modified, false otherwise
	 */
	boolean updateQtyTU(I_C_Order order);

	/**
	 * Update quantities when the M_HU_PI_Item_Product, Menge TU or Menge CU changes
	 *
	 * @param order
	 * @param columnname
	 * @param modifying
	 */

	void updateQtys(I_C_Order order, String columnname);

	/**
	 * Check if the M_Product of an order in contained in any Transportation Unit
	 *
	 * @param order
	 * @return
	 */
	boolean hasTUs(I_C_Order order);

	/**
	 * Check if the M_Product of an order in contained in any Transportation Unit of the specified partner (or of no partner), that is valid in the specified date
	 *
	 * @param ctx
	 * @param bpartnerId
	 * @param productId
	 * @param date
	 * @return
	 */
	boolean hasTUs(Properties ctx, int bpartnerId, int productId, Date date);

	void findM_HU_PI_Item_ProductForForecast(I_M_Forecast forecast, ProductId productId, Consumer<I_M_HU_PI_Item_Product> pipConsumer);
}
