package de.metas.adempiere.gui.search.impl;

import de.metas.adempiere.gui.search.IHUPackingAware;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.product.ProductId;
import de.metas.uom.UomId;
import lombok.Data;
import org.adempiere.mm.attributes.AttributeSetInstanceId;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Plain POJO implementation of {@link IHUPackingAware}
 *
 * @author tsa
 */
@Data
public class PlainHUPackingAware implements IHUPackingAware
{
	private ProductId productId;
	private AttributeSetInstanceId asiId;
	private BigDecimal qty;
	private UomId uomId;
	private HUPIItemProductId piItemProductId;
	private BigDecimal qtyTU;
	private BigDecimal qtyCUsPerTU;
	private BPartnerId bpartnerId;
	private boolean inDispute = false;

	@Override
	@Deprecated
	public int getM_Product_ID()
	{
		return ProductId.toRepoId(getProductId());
	}

	@Override
	@Deprecated
	public void setM_Product_ID(final int productId)
	{
		setProductId(ProductId.ofRepoIdOrNull(productId));
	}

	@Override
	@Deprecated
	public int getC_UOM_ID()
	{
		return UomId.toRepoId(getUomId());
	}

	@Override
	@Deprecated
	public void setC_UOM_ID(final int uomId)
	{
		setUomId(UomId.ofRepoIdOrNull(uomId));
	}

	@Override
	@Deprecated
	public int getM_HU_PI_Item_Product_ID()
	{
		return HUPIItemProductId.toRepoId(getPiItemProductId());
	}

	@Override
	@Deprecated
	public void setM_HU_PI_Item_Product_ID(final int piItemProductId)
	{
		setPiItemProductId(HUPIItemProductId.ofRepoIdOrNull(piItemProductId));
	}

	@Override
	@Deprecated
	public int getM_AttributeSetInstance_ID()
	{
		return AttributeSetInstanceId.toRepoId(getAsiId());
	}

	@Override
	@Deprecated
	public void setM_AttributeSetInstance_ID(final int M_AttributeSetInstance_ID)
	{
		setAsiId(AttributeSetInstanceId.ofRepoIdOrNull(M_AttributeSetInstance_ID));
	}

	@Override
	@Deprecated
	public int getC_BPartner_ID()
	{
		return BPartnerId.toRepoId(getBpartnerId());
	}

	@Override
	@Deprecated
	public void setC_BPartner_ID(final int bpartnerId)
	{
		setBpartnerId(BPartnerId.ofRepoIdOrNull(bpartnerId));
	}

	@Override
	public Optional<BigDecimal> getQtyCUsPerTU()
	{
		return Optional.ofNullable(qtyCUsPerTU);
	}

	@Override
	public void setQtyCUsPerTU(final BigDecimal qtyCUsPerTU)
	{
		this.qtyCUsPerTU = qtyCUsPerTU;
	}
}