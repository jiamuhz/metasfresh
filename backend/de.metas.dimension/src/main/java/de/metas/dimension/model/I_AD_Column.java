package de.metas.dimension.model;
 

public interface I_AD_Column extends org.compiere.model.I_AD_Column
{
	//@formatter:off
	public static final String COLUMNNAME_IsDimension = "IsDimension";
	public boolean isDimension();
	public void setIsDimension(boolean isDimension);
	//@formatter:on
}
