package de.metas.distribution.ddorder.producer;

import com.google.common.base.MoreObjects;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.mm.attributes.api.IAttributeSetInstanceAware;
import org.compiere.model.I_M_AttributeSetInstance;
import org.compiere.model.I_M_Product;
import org.eevolution.model.I_DD_OrderLine;

/**
 * Wraps {@link I_DD_OrderLine} and makes it behave like {@link IAttributeSetInstanceAware}.
 *
 *
 */
class DDOrderLineAttributeSetInstanceAware implements IAttributeSetInstanceAware
{
	public static DDOrderLineAttributeSetInstanceAware ofASIFrom(final I_DD_OrderLine ddOrderLine)
	{
		final boolean isASITo = false;
		return new DDOrderLineAttributeSetInstanceAware(ddOrderLine, isASITo);
	}

	public static DDOrderLineAttributeSetInstanceAware ofASITo(final I_DD_OrderLine ddOrderLine)
	{
		final boolean isASITo = true;
		return new DDOrderLineAttributeSetInstanceAware(ddOrderLine, isASITo);
	}

	private final I_DD_OrderLine ddOrderLine;
	private final boolean isASITo;

	private DDOrderLineAttributeSetInstanceAware(@NonNull final I_DD_OrderLine ddOrderLine, final boolean isASITo)
	{
		this.ddOrderLine = ddOrderLine;
		this.isASITo = isASITo;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.add("ddOrderLine", ddOrderLine)
				.add("isASITo", isASITo)
				.toString();
	}

	@Override
	public I_M_Product getM_Product()
	{
		final IProductBL productBL = Services.get(IProductBL.class);
		return productBL.getById(ProductId.ofRepoId(ddOrderLine.getM_Product_ID()));
	}

	@Override
	public int getM_Product_ID()
	{
		return ddOrderLine.getM_Product_ID();
	}

	@Override
	public I_M_AttributeSetInstance getM_AttributeSetInstance()
	{
		return isASITo ? ddOrderLine.getM_AttributeSetInstanceTo() : ddOrderLine.getM_AttributeSetInstance();
	}

	@Override
	public int getM_AttributeSetInstance_ID()
	{
		return isASITo ? ddOrderLine.getM_AttributeSetInstanceTo_ID() : ddOrderLine.getM_AttributeSetInstance_ID();
	}

	@Override
	public void setM_AttributeSetInstance(final I_M_AttributeSetInstance asi)
	{
		if (isASITo)
		{
			ddOrderLine.setM_AttributeSetInstanceTo(asi);
		}
		else
		{
			ddOrderLine.setM_AttributeSetInstance(asi);
		}
	}

	@Override
	public void setM_AttributeSetInstance_ID(final int M_AttributeSetInstance_ID)
	{
		if (isASITo)
		{
			ddOrderLine.setM_AttributeSetInstanceTo_ID(M_AttributeSetInstance_ID);
		}
		else
		{
			ddOrderLine.setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		}
	}
}
