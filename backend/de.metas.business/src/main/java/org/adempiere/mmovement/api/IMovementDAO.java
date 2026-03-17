package org.adempiere.mmovement.api;

/** */

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.distribution.ddorder.DDOrderLineId;
import de.metas.inventory.InventoryId;
import de.metas.util.ISingletonService;
import lombok.NonNull;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.mmovement.MovementId;
import org.adempiere.mmovement.MovementLineId;
import org.compiere.model.I_M_Movement;
import org.compiere.model.I_M_MovementLine;

import java.util.List;
import java.util.Map;

public interface IMovementDAO extends ISingletonService
{
	I_M_MovementLine getLineById(MovementLineId movementLineId);

	/**
	 * Retrieves all {@link I_M_MovementLine}s (including inactive ones), ordered by "Line" column.
	 *
	 * @param movement
	 * @return movement lines
	 * @see #retrieveLines(I_M_Movement, Class)
	 */
	List<I_M_MovementLine> retrieveLines(I_M_Movement movement);

	/**
	 * Retrieves all {@link I_M_MovementLine}s (including inactive ones), ordered by "Line" column.
	 *
	 * @param movement
	 * @param movementLineClass
	 * @return movement lines
	 */
	<MovementLineType extends I_M_MovementLine> List<MovementLineType> retrieveLines(I_M_Movement movement, final Class<MovementLineType> movementLineClass);

	IQueryBuilder<I_M_Movement> retrieveMovementsForInventoryQuery(InventoryId inventoryId);

	void save(final I_M_Movement movement);

	void save(final I_M_MovementLine movementLine);

	List<I_M_Movement> retrieveMovementsForDDOrder(int ddOrderId);

	@NonNull
	I_M_Movement getById(@NonNull MovementId movementId);

	@NonNull
	ImmutableList<I_M_MovementLine> retrieveLines(@NonNull MovementId movementId);

	@NonNull
	Map<DDOrderLineId, List<I_M_MovementLine>> retrieveCompletedMovementLinesForDDOrderLines(@NonNull ImmutableSet<DDOrderLineId> ddOrderLineIds);
}
