package de.metas.handlingunits.ordercandidate.spi.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import de.metas.bpartner.BPartnerLocationAndCaptureId;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.TimeUtil;
import org.springframework.stereotype.Component;

import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.inout.IHUPackingMaterialDAO;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.handlingunits.model.I_M_HU_PackingMaterial;
import de.metas.order.SOTrx;
import de.metas.money.CurrencyId;
import de.metas.ordercandidate.api.IOLCandEffectiveValuesBL;
import de.metas.ordercandidate.model.I_C_OLCand;
import de.metas.ordercandidate.spi.IOLCandValidator;
import de.metas.ordercandidate.spi.impl.DefaultOLCandValidator;
import de.metas.pricing.IEditablePricingContext;
import de.metas.pricing.PriceListId;
import de.metas.pricing.PricingSystemId;
import de.metas.pricing.service.IPriceListDAO;
import de.metas.pricing.service.IPricingBL;
import de.metas.product.ProductId;
import de.metas.util.Services;

/**
 * @task 08147: validate if the C_OLCand's PIIP is OK
 */
@Component
public class OLCandPIIPPriceValidator implements IOLCandValidator
{

	/** @return {@code 30}; needs to run after {@link DefaultOLCandValidator} because it needs that validator's results. (concrete example: PricingsystemId) */
	@Override
	public int getSeqNo()
	{
		return 30;
	}

	/**
	 * Validates
	 * <ul>
	 * <li>the UOM conversion; we will need convertToProductUOM in order to get the QtyOrdered in the order line.
	 * <li>the pricing into to the PIIP's packing material product
	 * </ul>
	 */
	@Override
	public void validate(@NonNull final I_C_OLCand olCand)
	{
		// If there is a PIIP, then verify that there is pricing info for the packing material. Otherwise, completing the order will fail later on.
		final I_M_HU_PI_Item_Product huPIItemProduct = OLCandPIIPUtil.extractHUPIItemProductOrNull(olCand);
		if (huPIItemProduct != null)
		{
			final IHUPackingMaterialDAO packingMaterialDAO = Services.get(IHUPackingMaterialDAO.class);
			final List<I_M_HU_PackingMaterial> packingMaterials = packingMaterialDAO.retrievePackingMaterials(huPIItemProduct);
			for (final I_M_HU_PackingMaterial pm : packingMaterials)
			{
				final ProductId packingMaterialProductId = ProductId.ofRepoIdOrNull(pm.getM_Product_ID());
				checkForPrice(olCand, packingMaterialProductId);
			}
		}
	}



	private void checkForPrice(final I_C_OLCand olCand, final ProductId packingMaterialProductId)
	{
		final PricingSystemId pricingSystemId = PricingSystemId.ofRepoIdOrNull(olCand.getM_PricingSystem_ID());

		final IOLCandEffectiveValuesBL olCandEffectiveValuesBL = Services.get(IOLCandEffectiveValuesBL.class);
		final LocalDate datePromisedEffective = TimeUtil.asLocalDate(olCandEffectiveValuesBL.getDatePromised_Effective(olCand));
		final BPartnerLocationAndCaptureId billBPLocationId = olCandEffectiveValuesBL.getBillLocationAndCaptureEffectiveId(olCand);

		final PriceListId plId = Services.get(IPriceListDAO.class).retrievePriceListIdByPricingSyst(pricingSystemId, billBPLocationId, SOTrx.SALES);
		if (plId == null)
		{
			throw new AdempiereException("@PriceList@ @NotFound@: @M_PricingSystem@ " + pricingSystemId + ", @Bill_Location@ " + billBPLocationId);
		}

		final IPricingBL pricingBL = Services.get(IPricingBL.class);
		final IEditablePricingContext pricingCtx = pricingBL.createPricingContext();
		pricingCtx.setBPartnerId(BPartnerId.ofRepoIdOrNull(olCand.getBill_BPartner_ID()));
		pricingCtx.setSOTrx(SOTrx.SALES);
		pricingCtx.setQty(BigDecimal.ONE); // we don't care for the actual quantity we just want to verify that there is a price

		pricingCtx.setPricingSystemId(pricingSystemId);
		pricingCtx.setPriceListId(plId);
		pricingCtx.setProductId(packingMaterialProductId);
		pricingCtx.setPriceDate(datePromisedEffective);
		pricingCtx.setCurrencyId(CurrencyId.ofRepoId(olCand.getC_Currency_ID()));
		pricingCtx.setFailIfNotCalculated();

		Services.get(IPricingBL.class).calculatePrice(pricingCtx);
	}
}
