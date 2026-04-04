

package de.metas.ui.web.quickinput.ddorderline;

import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.distribution.ddorder.DDOrderLineCreateRequest;
import de.metas.distribution.ddorder.DDOrderService;
import de.metas.product.ProductId;
import de.metas.ui.web.quickinput.IQuickInputProcessor;
import de.metas.ui.web.quickinput.QuickInput;
import de.metas.ui.web.window.datatypes.DocumentId;
import org.compiere.SpringContextHolder;
import de.metas.distribution.ddorder.DDOrderLineId;
import org.eevolution.model.I_DD_Order;

import java.math.BigDecimal;
import java.util.Set;

public class DDOrderLineQuickInputProcessor implements IQuickInputProcessor
{
	private final DDOrderService ddOrderService = SpringContextHolder.instance.getBean(DDOrderService.class);

	@Override
	public Set<DocumentId> process(final QuickInput quickInput)
	{
		final IDDOrderLineQuickInput ddOrderQuickInput = quickInput.getQuickInputDocumentAs(IDDOrderLineQuickInput.class);

		final ProductId productId = ProductId.ofRepoId(ddOrderQuickInput.getM_Product_ID());
		final HUPIItemProductId mHUPIProductID = HUPIItemProductId.ofRepoIdOrNull(ddOrderQuickInput.getM_HU_PI_Item_Product_ID());
		final BigDecimal qty = ddOrderQuickInput.getQty();

		final I_DD_Order ddOrder = quickInput.getRootDocumentAs(I_DD_Order.class);

		final DDOrderLineCreateRequest ddOrderLineCreateRequest = DDOrderLineCreateRequest.builder()
				.ddOrder(ddOrder)
				.productId(productId)
				.mHUPIProductID(mHUPIProductID)
				.qtyEntered(qty)
				.build();

		final DDOrderLineId ddOrderLineId = ddOrderService.addDDOrderLine(ddOrderLineCreateRequest);

		final DocumentId documentId = DocumentId.of(ddOrderLineId.getRepoId());
		return ImmutableSet.of(documentId);
	}
}
