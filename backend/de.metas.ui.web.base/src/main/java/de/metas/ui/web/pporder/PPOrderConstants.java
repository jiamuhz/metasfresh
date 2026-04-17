package de.metas.ui.web.pporder;

import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.descriptor.DetailId;
import org.adempiere.ad.element.api.AdTabId;




public final class PPOrderConstants
{
	public static final String AD_WINDOW_ID_IssueReceipt_String = "540328"; // Manufacturing Issue/Receipt
	public static final WindowDocumentTypeId AD_WINDOW_ID_IssueReceipt = WindowDocumentTypeId.fromJson("540328"); // Manufacturing Issue/Receipt
	public static final WindowDocumentTypeId AD_WINDOW_ID_PP_Order = WindowDocumentTypeId.fromJson("53009"); // Manufacturing order standard window
	public static final DetailId TABID_ID_PP_Order_BOMLine = DetailId.fromAD_Tab_ID(AdTabId.ofRepoId(53039)); // Manufacturing order standard window - BOM line tab
}
