package de.metas.ui.web.window.descriptor.factory.standard;

import java.util.List;

import org.adempiere.ad.element.api.AdTabId;
import org.compiere.model.I_AD_UI_Column;
import org.compiere.model.I_AD_UI_Element;
import org.compiere.model.I_AD_UI_ElementField;
import org.compiere.model.I_AD_UI_ElementGroup;
import org.compiere.model.I_AD_UI_Section;

 

interface IWindowUIElementsProvider
{
	List<I_AD_UI_Section> getUISections(AdTabId adTabId);

	List<I_AD_UI_Column> getUIColumns(I_AD_UI_Section uiSection);

	List<I_AD_UI_ElementGroup> getUIElementGroups(I_AD_UI_Column uiColumn);

	List<I_AD_UI_Element> getUIElements(I_AD_UI_ElementGroup uiElementGroup);

	List<I_AD_UI_Element> getUIElementsOfTypeLabels(AdTabId adTabId);

	List<I_AD_UI_ElementField> getUIElementFields(I_AD_UI_Element uiElement);

}
