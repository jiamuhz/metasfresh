package de.metas.material.cockpit.stock;

import de.metas.material.event.commons.AttributesKey;
import de.metas.organization.OrgId;
import de.metas.product.ProductId;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.service.ClientId;
import org.adempiere.warehouse.WarehouseId;

 

@Value
public class StockDataRecordIdentifier
{
	ClientId clientId;
	OrgId orgId;
	WarehouseId warehouseId;
	ProductId productId;
	AttributesKey storageAttributesKey;

	@Builder
	private StockDataRecordIdentifier(
			@NonNull final ClientId clientId,
			@NonNull final OrgId orgId,
			@NonNull final WarehouseId warehouseId,
			@NonNull final ProductId productId,
			@NonNull final AttributesKey storageAttributesKey)
	{
		Check.errorUnless(orgId.isRegular(), "The given orgId may not be 'any' (*)."); // we are talking stock here. those always belong to an org

		storageAttributesKey.assertNotAllOrOther();
		this.clientId = clientId;
		this.orgId = orgId;
		this.warehouseId = warehouseId;
		this.productId = productId;
		this.storageAttributesKey = storageAttributesKey;
	}
}
