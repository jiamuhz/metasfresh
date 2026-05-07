package de.metas.handlingunits.inout;

import de.metas.handlingunits.HUConstants;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_InOutLine;
import de.metas.inout.InOutLineId;
import de.metas.util.ISingletonService;
import org.compiere.model.I_M_InOut;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IHUInOutDAO extends ISingletonService
{
	/**
	 * Retrieve the <b>top-level</b> HUs associated with the given {@code inout}'s lines.
	 */
	List<I_M_HU> retrieveHandlingUnits(I_M_InOut inOut);

	List<I_M_InOutLine> retrievePackingMaterialLines(I_M_InOut inOut);

	boolean hasPackingMaterialLines(I_M_InOut inOut);

	/**
	 * Returns the inoutline that is referenced by the given <code>hu</code>'s {@link HUConstants#ATTRIBUTE_VALUE_HU_ReceiptInOutLine_ID} value,<br>
	 * or <code>null</code> if there is no such (active!) inout line, <b>or</b> if the inOutline dies not belong to an <code>MInOut</code> that is completed or closed.
	 */
	@Nullable
	I_M_InOutLine retrieveCompletedReceiptLineOrNull(I_M_HU hu);
	
	List<I_M_InOutLine> retrieveInOutLinesForHU(I_M_HU topLevelHU);

	List<I_M_HU> retrieveHandlingUnitsByInOutLineId(InOutLineId inOutLineId);

	/**
	 * Retrieve the handling units assigned to the lines of a given inout if and only if they have status shipped.
	 */
	List<I_M_HU> retrieveShippedHandlingUnits(I_M_InOut inOut);

	Map<InOutLineId, List<I_M_HU>> retrieveShippedHUsByShipmentLineId(Set<InOutLineId> shipmentLineIds);

	List<I_M_HU> retrieveHUsForReceiptLineId(int receiptLineId);
}
