package de.metas.ui.web.pickingslotsClearing.process;

import de.metas.util.Check;
import lombok.Builder;
import lombok.Value;



@Value
public class HUExtractedFromPickingSlotEvent
{
	private final int huId;
	private final int bpartnerId;
	private final int bpartnerLocationId;

	@Builder
	private HUExtractedFromPickingSlotEvent(final int huId, final int bpartnerId, final int bpartnerLocationId)
	{
		Check.assume(huId > 0, "huId > 0");
		this.huId = huId;
		this.bpartnerId = bpartnerId > 0 ? bpartnerId : 0;
		this.bpartnerLocationId = bpartnerLocationId > 0 ? bpartnerLocationId : 0;
	}

}
