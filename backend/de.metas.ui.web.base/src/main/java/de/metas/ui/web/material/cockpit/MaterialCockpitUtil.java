package de.metas.ui.web.material.cockpit;

import org.adempiere.service.ISysConfigBL;
import org.compiere.util.Env;

import de.metas.dimension.DimensionSpec;
import de.metas.dimension.IDimensionspecDAO;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.common.util.CoalesceUtil;



public final class MaterialCockpitUtil
{
	public static final String WINDOWID_MaterialCockpitView_String = "540376";
	public static final WindowDocumentTypeId WINDOWID_MaterialCockpitView = WindowDocumentTypeId.fromJson(WINDOWID_MaterialCockpitView_String);

	public static final String WINDOWID_MaterialCockpit_Detail_String = "540395";
	public static final WindowDocumentTypeId WINDOWID_MaterialCockpit_DetailView = WindowDocumentTypeId.fromJson(WINDOWID_MaterialCockpitView_String);

	public static final String WINDOW_MaterialCockpit_StockDetail_String = "540457";
	public static final WindowDocumentTypeId WINDOW_MaterialCockpit_StockDetailView = WindowDocumentTypeId.of(Integer.parseInt(WINDOW_MaterialCockpit_StockDetail_String));

	public static final String SYSCONFIG_DIM_SPEC_INTERNAL_NAME = "de.metas.ui.web.material.cockpit.DIM_Dimension_Spec.InternalName";
	public static final String DEFAULT_DIM_SPEC_INTERNAL_NAME = "Material_Cockpit_Default_Spec";

	public static final String SYSCONFIG_INCLUDE_PER_PLANT_DETAIL_ROWS = "de.metas.ui.web.material.cockpit.DisplayPerPlantDetailRows";

	public static final String DONT_FILTER = "DONT_FILTER";
	public static final String NON_EMPTY = "NON_EMPTY";

	private MaterialCockpitUtil()
	{
	}

	public static DimensionSpec retrieveDimensionSpec()
	{
		final ISysConfigBL sysConfigBL = Services.get(ISysConfigBL.class);
		final IDimensionspecDAO dimensionspecDAO = Services.get(IDimensionspecDAO.class);

		final String dimSpecName = sysConfigBL.getValue(
				SYSCONFIG_DIM_SPEC_INTERNAL_NAME,
				DEFAULT_DIM_SPEC_INTERNAL_NAME,
				Env.getAD_Client_ID(),
				Env.getAD_Org_ID(Env.getCtx()));

		final DimensionSpec dimensionSpec = dimensionspecDAO.retrieveForInternalNameOrNull(CoalesceUtil.firstNotEmptyTrimmed(
				dimSpecName,
				DEFAULT_DIM_SPEC_INTERNAL_NAME));

		return Check.assumeNotNull(dimensionSpec, "Unable to load DIM_Dimension_Spec record with InternalName={}", dimSpecName);
	}
}
