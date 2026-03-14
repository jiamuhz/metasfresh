package org.adempiere.mmovement.api;

/** */

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.distribution.ddorder.DDOrderLineId;
import de.metas.document.DocTypeId;
import de.metas.organization.ClientAndOrgId;
import de.metas.quantity.Quantity;
import de.metas.uom.UomId;
import de.metas.util.ISingletonService;
import de.metas.util.collections.MultiValueMap;
import lombok.NonNull;
import org.adempiere.mmovement.MovementId;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Movement;
import org.compiere.model.I_M_MovementLine;

import java.util.List;
import java.util.Map;

public interface IMovementBL extends ISingletonService
{

	DocTypeId getDocTypeId(@NonNull ClientAndOrgId clientAndOrgId);

	I_C_UOM getC_UOM(I_M_MovementLine movementLine);

	/**
	 * Gets movement qty converted to given UOM
	 */
	Quantity getMovementQty(I_M_MovementLine movementLine, I_C_UOM uom);

	void setMovementQty(I_M_MovementLine movementLine, Quantity movementQty);

	/**
	 * Set the correct activities (from and to) in the movement line This is, usually, the activity of the warehouses
	 * <p>
	 * Fall back: if the warehouses don't have a c_activity, pick the one from of the product
	 * <p>
	 * NOTE: The movement line is saved
	 */
	void setC_Activities(I_M_MovementLine movementLine);

	/**
	 * Checks if given movement is a true reversal (and not the original document which was reversed).
	 *
	 * @return true if given movement is the true reversal
	 */
	boolean isReversal(I_M_Movement movement);

	void complete(@NonNull I_M_Movement movement);

	void voidMovement(I_M_Movement movement);

	void save(I_M_Movement movement);

	void save(@NonNull I_M_MovementLine movementLine);

	@NonNull
	ImmutableList<I_M_MovementLine> retrieveLines(@NonNull MovementId movementId);

	@NonNull
	Quantity getMovementQty(@NonNull I_M_MovementLine movementLine);

	@NonNull
	Map<DDOrderLineId, List<I_M_MovementLine>> retrieveCompletedMovementLinesForDDOrderLines(@NonNull ImmutableSet<DDOrderLineId> ddOrderLineIds);
}
