package de.metas.ui.web.window.descriptor.factory.standard;

import java.util.List;

import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.ad.window.api.IADWindowDAO;
import org.adempiere.ad.window.process.IWindowUIElementsGeneratorConsumer;
import org.adempiere.ad.window.process.WindowUIElementsGenerator;
import org.compiere.model.I_AD_Tab;
import org.compiere.model.I_AD_UI_Column;
import org.compiere.model.I_AD_UI_Element;
import org.compiere.model.I_AD_UI_ElementField;
import org.compiere.model.I_AD_UI_ElementGroup;
import org.compiere.model.I_AD_UI_Section;
import org.slf4j.Logger;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;

import de.metas.logging.LogManager;
import de.metas.util.Services;

  
final class InMemoryUIElementsProvider implements IWindowUIElementsProvider, IWindowUIElementsGeneratorConsumer
{
	private static final Logger logger = LogManager.getLogger(InMemoryUIElementsProvider.class);

	private final ListMultimap<AdTabId, I_AD_UI_Section> adTabId2sections = LinkedListMultimap.create();
	private final ListMultimap<I_AD_UI_Section, I_AD_UI_Column> section2columns = Multimaps.newListMultimap(Maps.newIdentityHashMap(), () -> Lists.newLinkedList());
	private final ListMultimap<I_AD_UI_Column, I_AD_UI_ElementGroup> column2elementGroups = Multimaps.newListMultimap(Maps.newIdentityHashMap(), () -> Lists.newLinkedList());
	private final ListMultimap<I_AD_UI_ElementGroup, I_AD_UI_Element> elementGroup2elements = Multimaps.newListMultimap(Maps.newIdentityHashMap(), () -> Lists.newLinkedList());
	private final ListMultimap<I_AD_UI_Element, I_AD_UI_ElementField> element2elementFields = Multimaps.newListMultimap(Maps.newIdentityHashMap(), () -> Lists.newLinkedList());

	@Override
	public void consume(final I_AD_UI_Section uiSection, final I_AD_Tab parent)
	{
		logger.debug("Generated in memory {} for {}", uiSection, parent);
		final AdTabId adTabId = AdTabId.ofRepoId(parent.getAD_Tab_ID());
		adTabId2sections.put(adTabId, uiSection);
	}

	@Override
	public List<I_AD_UI_Section> getUISections(final AdTabId adTabId)
	{
		// Generate the UI elements if needed
		if (!adTabId2sections.containsKey(adTabId))
		{
			final WindowUIElementsGenerator generator = WindowUIElementsGenerator.forConsumer(this);

			final I_AD_Tab adTab = Services.get(IADWindowDAO.class).getTabByIdInTrx(adTabId);
			final boolean primaryTab = adTab.getTabLevel() == 0;
			if (primaryTab)
			{
				generator.migratePrimaryTab(adTab);
			}
			else
			{
				generator.migrateDetailTab(adTab);
			}
		}

		return adTabId2sections.get(adTabId);
	}

	@Override
	public void consume(final I_AD_UI_Column uiColumn, final I_AD_UI_Section parent)
	{
		logger.debug("Generated in memory {} for {}", uiColumn, parent);
		section2columns.put(parent, uiColumn);
	}

	@Override
	public List<I_AD_UI_Column> getUIColumns(final I_AD_UI_Section uiSection)
	{
		return section2columns.get(uiSection);
	}

	@Override
	public void consume(final I_AD_UI_ElementGroup uiElementGroup, final I_AD_UI_Column parent)
	{
		logger.debug("Generated in memory {} for {}", uiElementGroup, parent);
		column2elementGroups.put(parent, uiElementGroup);
	}

	@Override
	public List<I_AD_UI_ElementGroup> getUIElementGroups(final I_AD_UI_Column uiColumn)
	{
		return column2elementGroups.get(uiColumn);
	}

	@Override
	public void consume(final I_AD_UI_Element uiElement, final I_AD_UI_ElementGroup parent)
	{
		logger.debug("Generated in memory {} for {}", uiElement, parent);
		elementGroup2elements.put(parent, uiElement);
	}

	@Override
	public List<I_AD_UI_Element> getUIElements(final I_AD_UI_ElementGroup uiElementGroup)
	{
		return elementGroup2elements.get(uiElementGroup);
	}

	@Override
	public List<I_AD_UI_Element> getUIElementsOfTypeLabels(final AdTabId adTabId)
	{
		return ImmutableList.of();
	}

	@Override
	public void consume(final I_AD_UI_ElementField uiElementField, final I_AD_UI_Element parent)
	{
		logger.debug("Generated in memory {} for {}", uiElementField, parent);
		element2elementFields.put(parent, uiElementField);
	}

	@Override
	public List<I_AD_UI_ElementField> getUIElementFields(final I_AD_UI_Element uiElement)
	{
		return element2elementFields.get(uiElement);
	}
}
