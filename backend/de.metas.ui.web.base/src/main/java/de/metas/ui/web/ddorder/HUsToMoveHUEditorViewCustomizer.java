 

package de.metas.ui.web.ddorder;

import de.metas.distribution.ddorder.lowlevel.model.I_DD_OrderLine;
import de.metas.ui.web.handlingunits.HUEditorViewBuilder;
import de.metas.ui.web.handlingunits.HUEditorViewCustomizer;
import de.metas.ui.web.handlingunits.process.WEBUI_M_HU_Transform;

final class HUsToMoveHUEditorViewCustomizer implements HUEditorViewCustomizer
{
	public static final transient HUsToMoveHUEditorViewCustomizer instance = new HUsToMoveHUEditorViewCustomizer();

	private HUsToMoveHUEditorViewCustomizer()
	{
	}

	@Override
	public String getReferencingTableNameToMatch()
	{
		return I_DD_OrderLine.Table_Name;
	}

	@Override
	public Boolean isAttributesAlwaysReadonly()
	{
		return Boolean.FALSE;
	}

	@Override
	public void beforeCreate(final HUEditorViewBuilder viewBuilder)
	{
		viewBuilder.setParameter(WEBUI_M_HU_Transform.PARAM_CheckExistingHUsInsideView, true);
	}
}
