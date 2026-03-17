/**
 *
 */
package de.metas.bpartner_product;

/** */

import de.metas.bpartner.BPartnerId;
import de.metas.organization.OrgId;
import de.metas.product.ProductId;
import de.metas.util.ISingletonService;
import lombok.NonNull;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_BPartner_Product;
import org.compiere.model.I_M_Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

/**
 * @author cg
 *
 */
public interface IBPartnerProductDAO extends ISingletonService
{
	/**
	 * Retrieves all C_BPartner_Products for selected vendor
	 *
	 * @param Vendor_ID C_BPartner_ID
	 * @return C_BPartner_Products for Vendor_ID
	 */
	List<I_C_BPartner_Product> retrieveBPartnerForProduct(Properties ctx, BPartnerId Vendor_ID, ProductId productId, OrgId orgId);

	/**
	 * Retrieves single {@link I_C_BPartner_Product} association for the given product and partner. THe association must have the given ad_Org_ID or ad_org_id = 0
	 *
	 * @return the BPartner-Product association or null
	 */
	I_C_BPartner_Product retrieveBPartnerProductAssociation(I_C_BPartner partner, I_M_Product product, final OrgId orgId);

	/**
	 * Retrieves single {@link I_C_BPartner_Product} association. If there isn't an association for the given org, check if there isn't one for the org *
	 *
	 * @return the BPartner-Product association per org or null
	 */
	I_C_BPartner_Product retrieveBPartnerProductAssociation(Properties ctx, BPartnerId bpartnerId, ProductId productId, OrgId orgId);

	/**
	 * Retrieves the BP Product entry either if it is used for customer and has the BP = customerPartner (and has a bp vendor set)
	 * of is the currentVendor. The BP Product must be of the given org or of the org 0
	 *
	 * @return first entry, order by BP vendor and org_ID, nulls last
	 */
	I_C_BPartner_Product retrieveBPProductForCustomer(@NonNull I_C_BPartner customerPartner, @NonNull I_M_Product product, @NonNull OrgId orgId);

	List<I_C_BPartner_Product> retrieveForProductIds(Set<ProductId> productIds);

	List<ProductExclude> retrieveAllProductSalesExcludes();

	Optional<ProductExclude> getExcludedFromSaleToCustomer(ProductId productId, BPartnerId partnerId);

	Optional<ProductExclude> getExcludedFromPurchaseFromVendor(ProductId productId, BPartnerId partnerId);

	Map<BPartnerId, I_C_BPartner_Product> retrieveByVendorIds(Set<BPartnerId> vendorIds, ProductId productId, OrgId orgId);

	I_C_BPartner_Product retrieveByVendorId(BPartnerId vendorId, ProductId productId, OrgId orgId);

	List<I_C_BPartner_Product> retrieveAllBPartnerProductAssociations(Properties ctx, BPartnerId bpartnerId, ProductId productId, OrgId orgId, String trxName);

	Optional<ProductId> getProductIdByCustomerProductNo(BPartnerId customerId, String customerProductNo);

	Optional<ProductId> getProductIdByCustomerProductName(BPartnerId customerId, String customerProductName);

	List<I_C_BPartner_Product> retrieveByBPartnerId(BPartnerId bPartnerId);
}
