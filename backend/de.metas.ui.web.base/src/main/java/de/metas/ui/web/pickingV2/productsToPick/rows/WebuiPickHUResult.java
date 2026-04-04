 

package de.metas.ui.web.pickingV2.productsToPick.rows;

import de.metas.handlingunits.picking.PickingCandidate;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class WebuiPickHUResult
{
	@NonNull DocumentId documentId;
	@NonNull PickingCandidate pickingCandidate;
}
