/**
 *
 */
package de.metas.handlingunits.model;

import java.math.BigDecimal;

/**
 *
 *
 *
 */
public interface I_C_OrderLine extends de.metas.interfaces.I_C_OrderLine
{
	// @formatter:off
	String COLUMNNAME_PackDescription = "PackDescription";
	String getPackDescription();
	void setPackDescription(String packDescription);
	// @formatter:on

	// @formatter:off
	String COLUMNNAME_M_HU_PI_Item_Product_ID = "M_HU_PI_Item_Product_ID";
	void setM_HU_PI_Item_Product_ID(int M_HU_PI_Item_Product_ID);
	void setM_HU_PI_Item_Product(final I_M_HU_PI_Item_Product M_HU_PI_Item_Product);
	int getM_HU_PI_Item_Product_ID();
	I_M_HU_PI_Item_Product getM_HU_PI_Item_Product() throws RuntimeException;
	// @formatter:on

	// begin task 05097
	// @formatter:off
	//public static final String COLUMNNAME_IsPackagingMaterial = "IsPackagingMaterial";
	@Override boolean isPackagingMaterial();
	@Override void setIsPackagingMaterial(boolean isPackagingMaterial);
	// @formatter:on

	// @formatter:off
	String COLUMNNAME_C_PackingMaterial_OrderLine_ID = "C_PackingMaterial_OrderLine_ID";
	void setC_PackingMaterial_OrderLine_ID(int C_OrderLineSource_ID);
	void setC_PackingMaterial_OrderLine(final I_C_OrderLine ol);
	int getC_PackingMaterial_OrderLine_ID();
	I_C_OrderLine getC_PackingMaterial_OrderLine() throws RuntimeException;
	// @formatter:on
	// end task 05097

	// @formatter:off
	String COLUMNNAME_IsManualQtyItemCapacity = "IsManualQtyItemCapacity";
	boolean isManualQtyItemCapacity();
	void setIsManualQtyItemCapacity(boolean isManualQtyItemCapacity);
	// @formatter:on

	// @formatter:off
	String COLUMNNAME_QtyEnteredTU = "QtyEnteredTU";
	BigDecimal getQtyEnteredTU();
	void setQtyEnteredTU(BigDecimal QtyEnteredTU);
	// @formatter:on
}
