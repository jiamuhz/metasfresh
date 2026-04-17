package de.metas.ui.web.address;

import org.compiere.model.I_C_Country;
import org.compiere.model.I_C_Postal;
import org.compiere.model.I_C_Region;

public interface IAddressModel
{
	//@formatter:off
	String COLUMNNAME_Address1 = "Address1";
	String getAddress1();
	void setAddress1(String address);
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_Address2 = "Address2";
	String getAddress2();
	void setAddress2(String address);
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_Address3 = "Address3";
	String getAddress3();
	void setAddress3(String address);
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_Address4 = "Address4";
	String getAddress4();
	void setAddress4(String address);
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_Postal = "Postal";
	String getPostal();
	void setPostal(String postal);
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_City = "City";
	void setCity(String City);
	String getCity();
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_C_Region_ID = "C_Region_ID";
	void setC_Region_ID(int C_Region_ID);
	int getC_Region_ID();
	I_C_Region getC_Region();
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_C_Country_ID = "C_Country_ID";
	void setC_Country_ID(int C_Country_ID);
	int getC_Country_ID();
	I_C_Country getC_Country();
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_HasRegion = "HasRegion";
	void setHasRegion(boolean HasRegion);
	boolean isHasRegion();
	//@formatter:on
	

	//@formatter:off
	String COLUMNNAME_C_Postal_ID = "C_Postal_ID";
	void setC_Postal_ID(int C_Postal_ID);
	int getC_Postal_ID();
	I_C_Postal getC_Postal();
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_POBox = "POBox";
	void setPOBox(String POBox);
	String getPOBox();
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_IsPOBoxNum = "IsPOBoxNum";
	void setIsPOBoxNum(boolean isPOBoxNum);
	boolean isPOBoxNum();
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_Street = "Street";
	void setStreet (String Street);
    String getStreet();
	//@formatter:on


	//@formatter:off
	String COLUMNNAME_DHL_PostId = "DHL_PostId";
	void setDHL_PostId (String DHL_PostId);
	String getDHL_PostId();
	//@formatter:on

	//@formatter:off
	String COLUMNNAME_HouseNumber = "HouseNumber";
	void setHouseNumber (String HouseNumber);
	String getHouseNumber();
	//@formatter:on
}
