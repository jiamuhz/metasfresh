package de.metas.handlingunits.model;

import com.google.common.annotations.VisibleForTesting;

import java.math.BigDecimal;

public interface I_M_ShipmentSchedule extends
		de.metas.inoutcandidate.model.I_M_ShipmentSchedule // ,
// IHUDeliveryQuantities
{
	// code formatter will be off to maintain aspect

	// @formatter:off
	void setQtyOrdered_TU (java.math.BigDecimal QtyOrdered_TU);
	java.math.BigDecimal getQtyOrdered_TU();
    String COLUMNNAME_QtyOrdered_TU = "QtyOrdered_TU";
    // @formatter:on

	// @formatter:off
	void setQtyOrdered_LU (java.math.BigDecimal QtyOrdered_LU);
	java.math.BigDecimal getQtyOrdered_LU();
    String COLUMNNAME_QtyOrdered_LU = "QtyOrdered_LU";
    // @formatter:on

	// begin task 05130
	// @formatter:off
	String COLUMNNAME_M_HU_PI_Version_ID = "M_HU_PI_Version_ID";
	void setM_HU_PI_Version_ID(int M_HU_PI_Version_ID);
	int getM_HU_PI_Version_ID();
	void setM_HU_PI_Version(I_M_HU_PI_Version M_HU_PI_Version) throws RuntimeException;
	I_M_HU_PI_Version getM_HU_PI_Version() throws RuntimeException;
	// @formatter:on

	// @formatter:off
	String COLUMNNAME_QtyProvided = "QtyProvided";
	void setQtyProvided(BigDecimal QtyProvided);
	BigDecimal getQtyProvided();
	// @formatter:on
	// end task 05130

	// begin task 05097
	// @formatter:off
	String COLUMNNAME_PackDescription = "PackDescription";
	String getPackDescription();
	void setPackDescription(String packDescription);
	// @formatter:on

	// @formatter:off
	/** virtual column QtyItemCapacity **/
	String COLUMNNAME_QtyItemCapacity = "QtyItemCapacity";
	BigDecimal getQtyItemCapacity();
	@Deprecated @VisibleForTesting void setQtyItemCapacity(final BigDecimal qtyItemCapacity);
	// @formatter:on

	// @formatter:off
	/**
	 * This is the "effective" value, coming from either {@link #getM_HU_PI_Item_Product_Calculated_ID()}<br>
	 * or {@link #getM_HU_PI_Item_Product_Override_ID()}.
	 */
	String COLUMNNAME_M_HU_PI_Item_Product_ID = "M_HU_PI_Item_Product_ID";
	void setM_HU_PI_Item_Product_ID(int M_HU_PI_Item_Product_ID);
	void setM_HU_PI_Item_Product(final I_M_HU_PI_Item_Product M_HU_PI_Item_Product);
	int getM_HU_PI_Item_Product_ID();
	// public I_M_HU_PI_Item_Product getM_HU_PI_Item_Product();
	// @formatter:on
	// end task 05097

	// @formatter:off
	String COLUMNNAME_M_HU_PI_Item_Product_Override_ID = "M_HU_PI_Item_Product_Override_ID";
	void setM_HU_PI_Item_Product_Override_ID(int M_HU_PI_Item_Product_Override_ID);
	//void setM_HU_PI_Item_Product_Override(final I_M_HU_PI_Item_Product M_HU_PI_Item_Product_Override);
	int getM_HU_PI_Item_Product_Override_ID();
	I_M_HU_PI_Item_Product getM_HU_PI_Item_Product_Override() throws RuntimeException;
	// @formatter:on

	// 08255

	// @formatter:off
	String COLUMNNAME_QtyTU_Calculated = "QtyTU_Calculated";
	BigDecimal getQtyTU_Calculated();
	void setQtyTU_Calculated(BigDecimal QtyTU_Calculated);
	// @formatter:on

	// @formatter:off
	String COLUMNNAME_QtyTU_Override = "QtyTU_Override";
	BigDecimal getQtyTU_Override();
	void setQtyTU_Override(BigDecimal QtyTU_Override);
	// @formatter:on

	// @formatter:off
	String COLUMNNAME_M_HU_PI_Item_Product_Calculated_ID = "M_HU_PI_Item_Product_Calculated_ID";
	// public void setM_HU_PI_Item_Product_Calculated_ID(int M_HU_PI_Item_Product_Calculated_ID);
	int getM_HU_PI_Item_Product_Calculated_ID();
	void setM_HU_PI_Item_Product_Calculated(I_M_HU_PI_Item_Product M_HU_PI_Item_Product_Calculated) throws RuntimeException;
	I_M_HU_PI_Item_Product getM_HU_PI_Item_Product_Calculated() throws RuntimeException;
	// @formatter:on
}
