package de.metas.ui.web.quickinput.inout;

import de.metas.handlingunits.model.I_M_HU_PackingMaterial;



public interface IEmptiesQuickInput
{
	//@formatter:off
	String COLUMNNAME_M_HU_PackingMaterial_ID = I_M_HU_PackingMaterial.COLUMNNAME_M_HU_PackingMaterial_ID;
	//int getM_HU_PackingMaterial_ID();
	I_M_HU_PackingMaterial getM_HU_PackingMaterial();
	//void setM_HU_PackingMaterial_ID(final int M_HU_PackingMaterial_ID);
	//void setM_HU_PackingMaterial(final I_M_HU_PackingMaterial huPIItemProduct);
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_Qty = "Qty";
	int getQty();
	//@formatter:on

}
