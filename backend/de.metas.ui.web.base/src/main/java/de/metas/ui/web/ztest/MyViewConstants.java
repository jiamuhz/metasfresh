package de.metas.ui.web.ztest;

import de.metas.common.util.WindowConstants;
import de.metas.i18n.AdMessageKey;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.experimental.UtilityClass;


@UtilityClass
public final class MyViewConstants
{
	public static final WindowId WINDOWID_PackageableView = WindowId.of(WindowConstants.PACKAGEABLE_VIEW_AD_WINDOW_ID); // FIXME: HARDCODED
	public static final String WINDOWID_MyView_String = "1000011"; // FIXME: HARDCODED
	public static final WindowId WINDOWID_MyView = WindowId.fromJson(WINDOWID_MyView_String);
	public static final String WINDOWID_MySubView_String = "mysubview";
	public static final WindowId WINDOWID_MySubView = WindowId.fromJson(WINDOWID_MySubView_String);

	public static final AdMessageKey MSG_WEBUI_PICKING_NO_PROCESSED_RECORDS = AdMessageKey.of("WEBUI_Picking_No_Processed_Records");
	public static final AdMessageKey MSG_WEBUI_PICKING_SELECT_PICKED_HU = AdMessageKey.of("WEBUI_Picking_SelectPickedHU");

	/**
	 * Tells the user that an action is only applicable if the selected row is a source HU.
	 */
	public static final AdMessageKey MSG_WEBUI_PICKING_SELECT_SOURCE_HU = AdMessageKey.of("WEBUI_Picking_SelectSourceHU");

	/**
	 * Tells the user that an action is only possible if there is a an available source HU to pick from (i.e. it also needs to have a quantity) or to return a quantity back into.
	 */
	public static final AdMessageKey MSG_WEBUI_PICKING_MISSING_SOURCE_HU = AdMessageKey.of("WEBUI_Picking_MissingSourceHU");

	/**
	 * Tells the user that an action is only possible if a picked CU (not a picking slot or a picked TU) is selected
	 */
	public static final AdMessageKey MSG_WEBUI_PICKING_SELECT_PICKED_CU = AdMessageKey.of("WEBUI_Picking_SelectPickedCU");

	public static final AdMessageKey MSG_WEBUI_PICKING_SELECT_PICKING_SLOT = AdMessageKey.of("WEBUI_Picking_SelectPickingSlot");

	public static final AdMessageKey MSG_WEBUI_PICKING_PICK_SOMETHING = AdMessageKey.of("WEBUI_Picking_PickSomething");
	public static final AdMessageKey MSG_WEBUI_PICKING_NO_UNPROCESSED_RECORDS = AdMessageKey.of("WEBUI_Picking_No_Unprocessed_Records");
	public static final AdMessageKey MSG_WEBUI_PICKING_AGGREGATING_CUS_TO_DIFF_ORDER_IS_FORBIDDEN = AdMessageKey.of("WEBUI_Picking_AggregatingCUsToDiffOrdersIsForbidden");
	public static final AdMessageKey MSG_WEBUI_PICKING_NOT_TOP_LEVEL_HU = AdMessageKey.of("WEBUI_Picking_Not_TopLevelHU");
	public static final AdMessageKey MSG_WEBUI_PICKING_DIVERGING_LOCATIONS = AdMessageKey.of("WEBUI_Picking_Diverging_Locations");
	public static final AdMessageKey MSG_WEBUI_PICKING_TOO_MANY_PACKAGEABLES_1P = AdMessageKey.of("WEBUI_Picking_Too_Many_Packageables");
	public static final AdMessageKey MSG_WEBUI_PICKING_CANNOT_PICK_INCLUDED_ROWS = AdMessageKey.of("WEBUI_Picking_CannotPickIncludedRows");

	public static final AdMessageKey MSG_WEBUI_PICKING_NO_PICKED_HU_FOUND = AdMessageKey.of("WEBUI_Picking_NoPickedHuFound");
	public static final AdMessageKey MSG_WEBUI_PICKING_TO_EXISTING_CUS_NOT_ALLOWED = AdMessageKey.of("WEBUI_Picking_PickingToExistingCUsNotAllowed");

	public static final String SYS_CONFIG_SHOW_ALL_PICKING_CANDIDATES_ON_PICKING_SLOTS = "de.metas.pickingSlots.showAllPickingCandidates";
}
