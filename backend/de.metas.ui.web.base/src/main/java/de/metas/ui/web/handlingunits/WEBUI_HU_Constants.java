package de.metas.ui.web.handlingunits;

import de.metas.i18n.AdMessageKey;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import lombok.experimental.UtilityClass;

import static de.metas.ui.web.handlingunits.HUEditorRow.SYSCFG_PREFIX;

 

@UtilityClass
public final class WEBUI_HU_Constants
{
	public static final String WEBUI_HU_Window_ID_String = "540189"; // FIXME: hardcoded
	public static final WindowDocumentTypeId WEBUI_HU_Window_ID = WindowDocumentTypeId.fromJson(WEBUI_HU_Window_ID_String);

	public static final WindowDocumentTypeId WEBUI_HU_Trace_Window_ID = WindowDocumentTypeId.fromJson("540353");

	/**
	 * This message has one parameter: QtyTU>
	 */
	public static final AdMessageKey MSG_NotEnoughTUsFound = AdMessageKey.of("WEBUI_M_HU_MoveTUsToDirectWarehouse.NotEnoughTUsFound");

	public static final AdMessageKey MSG_WEBUI_ONLY_TOP_LEVEL_HU = AdMessageKey.of("WEBUI_Only_TopLevelHU");

	public static final AdMessageKey MSG_WEBUI_SELECT_ACTIVE_UNSELECTED_HU = AdMessageKey.of("WEBUI_Picking_Select_Active_UnSelected_HUs");

	public static final AdMessageKey MSG_WEBUI_ONLY_CU = AdMessageKey.of("WEBUI_Only_CU");

	public static final String SYS_CONFIG_CLEARANCE = SYSCFG_PREFIX + ".ClearanceStatus.IsDisplayed";
}
