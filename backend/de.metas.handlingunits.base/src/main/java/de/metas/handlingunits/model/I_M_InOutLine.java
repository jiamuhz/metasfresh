package de.metas.handlingunits.model;

import java.math.BigDecimal;

public interface I_M_InOutLine extends de.metas.materialtracking.model.I_M_InOutLine
{
	// code formatter will be off to maintain aspect

	// @formatter:off
	public static final String COLUMNNAME_QtyTU_Calculated = "QtyTU_Calculated";
	public BigDecimal getQtyTU_Calculated();
	public void setQtyTU_Calculated(BigDecimal QtyTU_Calculated);
	// @formatter:on

	// @formatter:off
	public static final String COLUMNNAME_QtyTU_Override = "QtyTU_Override";
	public BigDecimal getQtyTU_Override();
	public void setQtyTU_Override(BigDecimal QtyTU_Override);
	// @formatter:on

	// @formatter:off
	public static final String COLUMNNAME_QtyCU_Calculated = "QtyCU_Calculated";
	public BigDecimal getQtyCU_Calculated();
	public void setQtyCU_Calculated(BigDecimal QtyCU_Calculated);
	// @formatter:on

	// @formatter:off
	// task: http://dewiki908/mediawiki/index.php/08228_Packvorschrift_%C3%A4nderbar_in_Lieferschen_plus_Recalc_plus_Abweichende_Menge_plus_Prozess_plus_Rolle_Spedition
	public static final String COLUMNNAME_IsManualPackingMaterial = "IsManualPackingMaterial";
	public void setIsManualPackingMaterial(boolean IsManualPackingMaterial);
	public boolean isManualPackingMaterial();
	// @formatter:on

	// @formatter:off
	public static final String COLUMNNAME_M_HU_PI_Item_Product_ID = "M_HU_PI_Item_Product_ID";
	public void setM_HU_PI_Item_Product_ID(int M_HU_PI_Item_Product_ID);
	public int getM_HU_PI_Item_Product_ID();
	public void setM_HU_PI_Item_Product(I_M_HU_PI_Item_Product M_HU_PI_Item_Product) throws RuntimeException;
	public I_M_HU_PI_Item_Product getM_HU_PI_Item_Product() throws RuntimeException;
	// @formatter:on

	// @formatter:off
	public static final String COLUMNNAME_M_HU_PI_Item_Product_Calculated_ID = "M_HU_PI_Item_Product_Calculated_ID";
	// public void setM_HU_PI_Item_Product_Calculated_ID(int M_HU_PI_Item_Product_Calculated_ID);
	public int getM_HU_PI_Item_Product_Calculated_ID();
	public void setM_HU_PI_Item_Product_Calculated(I_M_HU_PI_Item_Product M_HU_PI_Item_Product_Calculated) throws RuntimeException;
	public I_M_HU_PI_Item_Product getM_HU_PI_Item_Product_Calculated() throws RuntimeException;
	// @formatter:on

	// @formatter:off
	public static final String COLUMNNAME_M_HU_PI_Item_Product_Override_ID = "M_HU_PI_Item_Product_Override_ID";
	// public void setM_HU_PI_Item_Product_Override_ID(int M_HU_PI_Item_Product_Override_ID);
	public int getM_HU_PI_Item_Product_Override_ID();
	public void setM_HU_PI_Item_Product_Override(I_M_HU_PI_Item_Product M_HU_PI_Item_Product_Override) throws RuntimeException;
	public I_M_HU_PI_Item_Product getM_HU_PI_Item_Product_Override() throws RuntimeException;
	// @formatter:on

	// @formatter:off
		public static final String COLUMNNAME_M_HU_LUTU_Configuration_ID = "M_HU_LUTU_Configuration_ID";
		public void setM_HU_LUTU_Configuration_ID(int M_HU_LUTU_Configuration_ID);
		public void setM_HU_LUTU_Configuration(I_M_HU_LUTU_Configuration M_HU_LUTU_Configuration);
		public int getM_HU_LUTU_Configuration_ID();
		public I_M_HU_LUTU_Configuration getM_HU_LUTU_Configuration();
		// @formatter:on
}
