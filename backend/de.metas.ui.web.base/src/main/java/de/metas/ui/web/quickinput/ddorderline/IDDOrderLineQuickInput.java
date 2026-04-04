 

package de.metas.ui.web.quickinput.ddorderline;

import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import org.compiere.model.I_M_Product;

import java.math.BigDecimal;

public interface IDDOrderLineQuickInput
{
	//@formatter:off
	String COLUMNNAME_M_Product_ID = "M_Product_ID";
	int getM_Product_ID();
	I_M_Product getM_Product();
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_M_HU_PI_Item_Product_ID = "M_HU_PI_Item_Product_ID";
	int getM_HU_PI_Item_Product_ID();
	// I_M_HU_PI_Item_Product getM_HU_PI_Item_Product();
	void setM_HU_PI_Item_Product_ID(final int M_HU_PI_Item_Product_ID);
	void setM_HU_PI_Item_Product(final I_M_HU_PI_Item_Product huPIItemProduct);
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_Qty = "Qty";
	BigDecimal getQty();
	//@formatter:on
}
