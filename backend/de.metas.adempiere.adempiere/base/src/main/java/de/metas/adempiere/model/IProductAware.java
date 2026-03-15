package de.metas.adempiere.model;

/** */


import org.compiere.model.I_M_Product;

/**
 * {@link org.adempiere.model.InterfaceWrapperHelper#asColumnReferenceAwareOrNull(Object, Class)} to obtain an instance.
 * 
 *
 *
 */
public interface IProductAware
{
	int getM_Product_ID();
	I_M_Product getM_Product();
}
