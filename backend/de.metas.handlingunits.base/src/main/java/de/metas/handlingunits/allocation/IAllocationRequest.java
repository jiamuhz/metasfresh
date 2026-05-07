package de.metas.handlingunits.allocation;

import de.metas.handlingunits.ClearanceStatusInfo;
import de.metas.handlingunits.IHUContext;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.I_C_UOM;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Allocation Request describes what we need to allocate/deallocate. Implementors are immutable. Instead of modifying it (split, create partial requests etc), use {@link IAllocationRequestBuilder} to
 * get new instances.
 *
 * @author tsa
 */
public interface IAllocationRequest
{
	BigDecimal QTY_INFINITE = Quantity.QTY_INFINITE;

	IHUContext getHuContext();

	/**
	 * @return allocation date
	 */
	ZonedDateTime getDate();

	ProductId getProductId();

	BigDecimal getQty();

	/**
	 * @return true if this request is asking about infinite quantity
	 */
	boolean isInfiniteQty();

	/**
	 * @return true if this request is asking about ZERO quantity
	 */
	boolean isZeroQty();

	I_C_UOM getC_UOM();

	/**
	 * @return quantity/uom (source quantity/uom)
	 */
	Quantity getQuantity();

	/**
	 * Gets referenced model.
	 * <p>
	 * In case you are doing allocations/deallocations, creating new HUs, the Qty changes or newly created HUs can be linked to this model. This is done in
	 * {@link de.metas.handlingunits.hutransaction.IHUTrxListener} implementations and those implementation decide when and how this is made.
	 *
	 * @return referenced model (e.g. a document line)
	 */
	TableRecordReference getReference();

	/**
	 * @return <code>true</code> if we shall allocate the qty even if the destination is already full
	 */
	boolean isForceQtyAllocation();

	@Nullable
	ClearanceStatusInfo getClearanceStatusInfo();
}
