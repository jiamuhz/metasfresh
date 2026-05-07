package de.metas.handlingunits.empties;

import java.math.BigDecimal;

import de.metas.handlingunits.impl.AbstractPackingMaterialDocumentLine;
import de.metas.handlingunits.model.I_M_InOutLine;
import de.metas.product.ProductId;
import de.metas.uom.IUOMConversionBL;
import de.metas.uom.UomId;
import de.metas.util.Check;
import de.metas.util.Services;

/* package */class EmptiesInOutLinePackingMaterialDocumentLine extends AbstractPackingMaterialDocumentLine
{
	private final I_M_InOutLine inoutLine;

	EmptiesInOutLinePackingMaterialDocumentLine(final I_M_InOutLine inoutLine)
	{
		super();

		Check.assumeNotNull(inoutLine, "inoutLine not null");
		this.inoutLine = inoutLine;
	}

	public I_M_InOutLine getM_InOutLine()
	{
		return inoutLine;
	}

	@Override
	public ProductId getProductId()
	{
		return ProductId.ofRepoId(inoutLine.getM_Product_ID());
	}

	/**
	 * @returns MovementQty of the wrapped inout line
	 */
	@Override
	public BigDecimal getQty()
	{
		return inoutLine.getMovementQty();
	}

	/**
	 * Sets both MovementQty and QtyEntered of the wrapped order line.
	 *
	 * @param qty MovementQty which will also be converted to QtyEntered.
	 */
	@Override
	protected void setQty(final BigDecimal qty)
	{
		inoutLine.setMovementQty(qty);

		final IUOMConversionBL uomConversionBL = Services.get(IUOMConversionBL.class);

		final BigDecimal qtyEntered = uomConversionBL.convertFromProductUOM(getProductId(), UomId.ofRepoId(inoutLine.getC_UOM_ID()), qty);
		inoutLine.setQtyEntered(qtyEntered);
	}
}
