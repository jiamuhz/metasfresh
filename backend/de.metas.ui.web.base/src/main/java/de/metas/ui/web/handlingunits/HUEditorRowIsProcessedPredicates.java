package de.metas.ui.web.handlingunits;

import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import lombok.experimental.UtilityClass;

 

@UtilityClass
public class HUEditorRowIsProcessedPredicates
{
	public static final HUEditorRowIsProcessedPredicate NEVER = new Never();
	public static final HUEditorRowIsProcessedPredicate IF_NOT_PLANNING_HUSTATUS = new IfNotPlanningHUStatus();

	private static final class Never implements HUEditorRowIsProcessedPredicate
	{
		@Override
		public boolean isProcessed(I_M_HU hu)
		{
			return false;
		}
	}

	private static final class IfNotPlanningHUStatus implements HUEditorRowIsProcessedPredicate
	{
		@Override
		public boolean isProcessed(I_M_HU hu)
		{
			return !X_M_HU.HUSTATUS_Planning.equals(hu.getHUStatus());
		}

	}

}
