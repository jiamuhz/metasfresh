package org.adempiere.mm.attributes.api;

/** */

import java.math.MathContext;
import java.util.Date;
import java.util.Properties;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import org.adempiere.mm.attributes.AttributeId;
import org.adempiere.mm.attributes.AttributeListValue;
import org.adempiere.mm.attributes.spi.IAttributeValueGenerator;
import org.adempiere.mm.attributes.spi.IAttributeValuesProvider;
import org.compiere.model.I_M_Attribute;

import de.metas.bpartner.BPartnerId;
import de.metas.product.ProductId;
import de.metas.util.ISingletonService;

public interface IAttributesBL extends ISingletonService
{
	I_M_Attribute getAttributeById(AttributeId attributeId);

	AttributeAction getAttributeAction(Properties ctx);

	IAttributeValueGenerator getAttributeValueGenerator(org.compiere.model.I_M_Attribute attributeParam);

	/**
	 * Returns a new attribute value generator instance for the given <code>attributeParam</code>
	 * or <code>null</code> if the given parameter has no <code>AD_JavaClass_ID</code> set or that class is not an IAttributeValueGenerator.
	 */
	IAttributeValueGenerator getAttributeValueGeneratorOrNull(org.compiere.model.I_M_Attribute attributeParam);

	/**
	 * Retrieves {@link IAttributeValuesProvider} to be used for given attribute (if any)
	 *
	 * @return {@link IAttributeValuesProvider} or null
	 */
	IAttributeValuesProvider createAttributeValuesProvider(org.compiere.model.I_M_Attribute attribute);

	/**
	 * Gets product attribute by ID.
	 *
	 * If the attribute is applicable to given product (i.e. it's included in product's attribute set), the attribute will be returned.
	 * Else, null will be returned.
	 *
	 * @return {@link I_M_Attribute} or null
	 */
	I_M_Attribute getAttributeOrNull(ProductId productId, AttributeId attributeId);

	boolean hasAttributeAssigned(ProductId productId, AttributeId attributeId);

	boolean isMandatoryOn(@NonNull ProductId productId, @NonNull AttributeId attributeId, @NonNull AttributeSourceDocument attributeSourceDocument);

	ImmutableList<I_M_Attribute> getAttributesMandatoryOnPicking(ProductId productId);

	ImmutableList<I_M_Attribute> getAttributesMandatoryOnManufacturing(ProductId productId);

	ImmutableList<I_M_Attribute> getAttributesMandatoryOnShipment(ProductId productId);

	/**
	 * @return math context of this attribute or DEFAULT_MATHCONTEXT if the attribute's UOM is null
	 */
	MathContext getMathContext(org.compiere.model.I_M_Attribute attribute);

	/**
	 * Calculates Best-Before date for given product and bpartner.
	 *
	 * @return best-before date or <code>null</code> if it does not apply
	 */
	Date calculateBestBeforeDate(Properties ctx, ProductId productId, BPartnerId vendorBPartnerId, Date dateReceipt);

	int getNumberDisplayType(I_M_Attribute attribute);

	boolean isStorageRelevant(final AttributeId attributeId);

	AttributeListValue retrieveAttributeValueOrNull(AttributeId attributeId, String value);
}
