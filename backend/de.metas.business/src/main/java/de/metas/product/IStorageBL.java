package de.metas.product;

/** */


import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.I_M_Locator;

import de.metas.util.ISingletonService;

public interface IStorageBL extends ISingletonService
{
	void addQtyOrdered(Properties ctx,
			I_M_Locator locator,
			int productId, int attributeSetInstanceId,
			BigDecimal diffQtyOrdered,
			String trxName);

	void addQtyReserved(Properties ctx,
			I_M_Locator locator,
			int productId, int attributeSetInstanceId,
			BigDecimal diffQtyReserved,
			String trxName);

	/**
	 * Enqueue an async-workpackage to be processed by {@link org.adempiere.product.async.spi.impl.M_Storage_Add}, with the given parameters.
	 *
	 * @task http://dewiki908/mediawiki/index.php/08999_Lieferdisposition_a.frieden_%28104263801724%29
	 */
	void addAsync(Properties ctx,
			int M_Warehouse_ID,
			int M_Locator_ID,
			int M_Product_ID,
			int M_AttributeSetInstance_ID,
			int reservationAttributeSetInstance_ID,
			BigDecimal diffQtyOnHand,
			BigDecimal diffQtyReserved,
			BigDecimal diffQtyOrdered,
			String trxName);

	void add(Properties ctx,
			int M_Warehouse_ID,
			int M_Locator_ID,
			int M_Product_ID,
			int M_AttributeSetInstance_ID,
			int reservationAttributeSetInstance_ID,
			BigDecimal diffQtyOnHand,
			BigDecimal diffQtyReserved,
			BigDecimal diffQtyOrdered,
			String trxName);

}
