package de.metas.ui.web.window.descriptor.factory.standard;

import de.metas.ui.web.window.descriptor.LayoutElementType;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.ad.window.api.IADWindowDAO;
import org.compiere.model.I_AD_UI_Column;
import org.compiere.model.I_AD_UI_Element;
import org.compiere.model.I_AD_UI_ElementField;
import org.compiere.model.I_AD_UI_ElementGroup;
import org.compiere.model.I_AD_UI_Section;

import java.util.List;

  
final class DAOWindowUIElementsProvider implements IWindowUIElementsProvider
{
	private final transient IADWindowDAO windowDAO = Services.get(IADWindowDAO.class);

	@Override
	public List<I_AD_UI_Section> getUISections(final AdTabId adTabId)
	{
		return windowDAO.retrieveUISections(adTabId);
	}

	@Override
	public List<I_AD_UI_Column> getUIColumns(final I_AD_UI_Section uiSection)
	{
		return windowDAO.retrieveUIColumns(uiSection);
	}

	@Override
	public List<I_AD_UI_ElementGroup> getUIElementGroups(final I_AD_UI_Column uiColumn)
	{
		return windowDAO.retrieveUIElementGroups(uiColumn);
	}

	@Override
	public List<I_AD_UI_Element> getUIElements(final I_AD_UI_ElementGroup uiElementGroup)
	{
		return windowDAO.retrieveUIElements(uiElementGroup);
	}

	@Override
	public List<I_AD_UI_Element> getUIElementsOfTypeLabels(@NonNull final AdTabId adTabId)
	{
		return windowDAO.retrieveUIElementsQueryByTabId(adTabId)
				.addEqualsFilter(I_AD_UI_Element.COLUMN_AD_UI_ElementType, LayoutElementType.Labels.getCode())
				.create()
				.list(I_AD_UI_Element.class);
	}

	@Override
	public List<I_AD_UI_ElementField> getUIElementFields(final I_AD_UI_Element uiElement)
	{
		return windowDAO.retrieveUIElementFields(uiElement);
	}
}
