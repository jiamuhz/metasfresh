package de.metas.ui.web.picking.pickingslot;

import com.google.common.annotations.VisibleForTesting;
import de.metas.ui.web.handlingunits.HUEditorRowType;
import de.metas.ui.web.view.IViewRowType;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;

 
@Value
public class PickingSlotRowType implements IViewRowType
{
	/**
	 * Name of a dedicated picking slot row's type. Other possible name types are borrowed from {@link HUEditorRowType}.
	 */
	@VisibleForTesting
	static final String M_PICKING_SLOT = "M_Picking_Slot";

	public static PickingSlotRowType forPickingSlotRow()
	{
		final HUEditorRowType huEditorRowType = null;
		return new PickingSlotRowType(M_PICKING_SLOT, huEditorRowType);
	}

	public static PickingSlotRowType forPickingHuRow(@NonNull final HUEditorRowType huEditorRowType)
	{
		return new PickingSlotRowType(huEditorRowType.getName(), huEditorRowType);
	}

	@NonNull
	String name;
	@Nullable
	HUEditorRowType huEditorRowType;

	public boolean isLU() { return huEditorRowType != null && huEditorRowType == HUEditorRowType.LU; }

	public boolean isTU() { return huEditorRowType != null && huEditorRowType == HUEditorRowType.TU; }

	public boolean isCU() { return huEditorRowType != null && huEditorRowType == HUEditorRowType.VHU; }

	public boolean isHUStorage() { return huEditorRowType != null && huEditorRowType == HUEditorRowType.HUStorage; }

	public boolean isCUOrStorage() { return isCU() || isHUStorage(); }
}
