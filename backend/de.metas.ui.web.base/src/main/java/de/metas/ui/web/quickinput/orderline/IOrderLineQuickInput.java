package de.metas.ui.web.quickinput.orderline;

import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;

import java.math.BigDecimal;

 

public interface IOrderLineQuickInput
{
	//@formatter:off
	String COLUMNNAME_M_Product_ID = "M_Product_ID";
	IntegerLookupValue getM_Product_ID();
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_M_HU_PI_Item_Product_ID = "M_HU_PI_Item_Product_ID";
	int getM_HU_PI_Item_Product_ID();
	// I_M_HU_PI_Item_Product getM_HU_PI_Item_Product();
	// void setM_HU_PI_Item_Product_ID(final int M_HU_PI_Item_Product_ID);
	void setM_HU_PI_Item_Product(final I_M_HU_PI_Item_Product huPIItemProduct);
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_Qty = "Qty";
	BigDecimal getQty();
	BigDecimal setQty(BigDecimal qty);
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_ShipmentAllocation_BestBefore_Policy = "ShipmentAllocation_BestBefore_Policy";
	String getShipmentAllocation_BestBefore_Policy();
	void setShipmentAllocation_BestBefore_Policy(String bestBeforePolicy);
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_C_CompensationGroup_Schema_ID = "C_CompensationGroup_Schema_ID";
	int getC_CompensationGroup_Schema_ID();
	void setC_CompensationGroup_Schema_ID(int value);
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_C_Flatrate_Conditions_ID = "C_Flatrate_Conditions_ID";
	void setC_Flatrate_Conditions_ID(int C_Flatrate_Conditions_ID);
	int getC_Flatrate_Conditions_ID();
	//@formatter:on


	//@formatter:off
	String COLUMNNAME_C_VAT_Code_ID = "C_VAT_Code_ID";
	void setC_VAT_Code_ID(int C_VAT_Code_ID);
	int getC_VAT_Code_ID();
	//@formatter:on
}
