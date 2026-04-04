

package de.metas.ui.web.ddorder;

import com.google.common.collect.ImmutableList;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorViewFactoryTemplate;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper.ClassViewColumnOverrides;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.datatypes.WindowId;

@ViewFactory(windowId = HUsToMoveViewFactory.WINDOW_ID_STRING, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class HUsToMoveViewFactory extends HUEditorViewFactoryTemplate
{
	public static final String WINDOW_ID_STRING = "husToMove";
	public static final WindowId WINDOW_ID = WindowId.fromJson(WINDOW_ID_STRING);

	public HUsToMoveViewFactory()
	{
		super(ImmutableList.of(HUsToMoveHUEditorViewCustomizer.instance));
	}

	@Override
	protected void customizeViewLayout(final ViewLayout.Builder viewLayoutBuilder, final JSONViewDataType viewDataType)
	{
		viewLayoutBuilder
				.clearElements()
				.addElementsFromViewRowClassAndFieldNames(HUEditorRow.class,
														  viewDataType,
														  ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HUCode).restrictToMediaType(MediaType.SCREEN).build(),
														  ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_Product),
														  ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_Locator),
														  ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_QtyCU),
														  ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_UOM),
														  ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HU_UnitType).restrictToMediaType(MediaType.SCREEN).build(),
														  ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_PackingInfo).restrictToMediaType(MediaType.SCREEN).build(),
														  ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HUStatus).restrictToMediaType(MediaType.SCREEN).build());
	}
}
