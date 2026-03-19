package de.metas.ui.web.handlingunits.util;

import java.math.BigDecimal;

import org.compiere.model.I_C_UOM;

import de.metas.handlingunits.model.I_M_HU_PI;



/**
 * Packing informations used by {@link HUPackingInfoFormatter}.
 * 
 * Check {@link HUPackingInfos} for creating various instances.
 * 
 *
 *
 */
public interface IHUPackingInfo
{
	//@formatter:off
	I_M_HU_PI getM_LU_HU_PI();
	//@formatter:on

	//@formatter:off
	I_M_HU_PI getM_TU_HU_PI();
	boolean isInfiniteQtyTUsPerLU();
	BigDecimal getQtyTUsPerLU();
	//@formatter:on

	//@formatter:off
	boolean isInfiniteQtyCUsPerTU();
	BigDecimal getQtyCUsPerTU();
	I_C_UOM getQtyCUsPerTU_UOM();
	//@formatter:on
}
