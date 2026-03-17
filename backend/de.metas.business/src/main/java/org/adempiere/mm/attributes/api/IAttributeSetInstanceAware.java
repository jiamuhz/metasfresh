package org.adempiere.mm.attributes.api;

/** */

import org.compiere.model.I_M_AttributeSetInstance;
import org.compiere.model.I_M_Product;

/**
 * Important:
 * <ul>
 * <li>use {@link IAttributeSetInstanceAwareFactoryService#createOrNull(Object)} to get your instance.
 * <li>there is a default implementation that might well do what you want
 * <li>if you need a custom implementation, (e.g. in order to call a custom business logic within a getter), then pls implement and register your own {@link IAttributeSetInstanceAwareFactory}.
 * </ul>
 *
 */
public interface IAttributeSetInstanceAware
{
	// @formatter:off
	String COLUMNNAME_M_Product_ID = I_M_Product.COLUMNNAME_M_Product_ID;
	I_M_Product getM_Product();
	int getM_Product_ID();
	// @formatter:on

	// @formatter:off
	String COLUMNNAME_M_AttributeSetInstance_ID = I_M_AttributeSetInstance.COLUMNNAME_M_AttributeSetInstance_ID;
	I_M_AttributeSetInstance getM_AttributeSetInstance();
	int getM_AttributeSetInstance_ID();
	void setM_AttributeSetInstance(final I_M_AttributeSetInstance asi);

	void setM_AttributeSetInstance_ID( int M_AttributeSetInstance_ID);
	// @formatter:on

}
