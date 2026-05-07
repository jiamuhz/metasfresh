package de.metas.adempiere.gui.search.impl;

import java.math.BigDecimal;

import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.ObjectUtils;
import org.compiere.apps.search.IGridTabRowBuilder;
import org.slf4j.Logger;

import de.metas.adempiere.gui.search.IHUPackingAware;
import de.metas.adempiere.gui.search.IHUPackingAwareBL;
import de.metas.handlingunits.model.I_C_Order;
import de.metas.handlingunits.model.I_C_OrderLine;
import de.metas.logging.LogManager;
import de.metas.util.Services;

/**
 * {@link IGridTabRowBuilder} which applies on {@link IHUPackingAware} object to wrapped {@link I_C_OrderLine}.
 *
 * @author tsa
 *
 */
public class OrderLineHUPackingGridRowBuilder implements IGridTabRowBuilder
{
	private static final transient Logger logger = LogManager.getLogger(OrderLineHUPackingGridRowBuilder.class);

	private IHUPackingAware record;

	public OrderLineHUPackingGridRowBuilder()
	{
		super();
	}

	@Override
	public boolean isValid()
	{
		if (record == null)
		{
			return false;
		}

		final int productID = record.getM_Product_ID();
		final BigDecimal qty = record.getQty();
		// 06730: Removed TU restrictions for now. We can choose no handling unit for a product, even if it has associated TUs.
		// final int partnerID = (null == record.getC_BPartner()) ? 0 : record.getC_BPartner().getC_BPartner_ID();
		// final Timestamp dateOrdered = record.getDateOrdered();


		if (productID <= 0)
		{
			return false;
		}

		if (qty == null || qty.signum() == 0)
		{
			return false;
		}
		// TODO: check record values

		// 06730: Removed TU restrictions for now. We can choose no handling unit for a product, even if it has associated TUs.
		// final boolean hasTUs = Services.get(IHUOrderBL.class).hasTUs(Env.getCtx(), partnerID, productID, dateOrdered);
		//
		// if (hasTUs)
		// {
		//
		// if (record.getM_HU_PI_Item_Product_ID() <= 0)
		// {
		// return false;
		// }
		//
		// if (record.getQtyPacks() == null || record.getQtyPacks().signum() <= 0)
		// {
		// return false;
		// }
		// }

		return true;
	}

	@Override
	public void setSource(final Object model)
	{
		if (model instanceof IHUPackingAware)
		{
			record = (IHUPackingAware)model;
		}
		else if (InterfaceWrapperHelper.isInstanceOf(model, I_C_Order.class))
		{
			final I_C_Order order = InterfaceWrapperHelper.create(model, I_C_Order.class);
			record = new OrderHUPackingAware(order);
		}
		else
		{
			record = null;
		}
	}

	@Override
	public void apply(final Object model)
	{
		if (!InterfaceWrapperHelper.isInstanceOf(model, I_C_OrderLine.class))
		{
			logger.debug("Skip applying because it's not an order line: {}", model);
			return;
		}

		final I_C_OrderLine orderLine = InterfaceWrapperHelper.create(model, I_C_OrderLine.class);
		applyOnOrderLine(orderLine);
	}

	public void applyOnOrderLine(final I_C_OrderLine orderLine)
	{
		final IHUPackingAware to = new OrderLineHUPackingAware(orderLine);

		Services.get(IHUPackingAwareBL.class).prepareCopyFrom(record)
				.overridePartner(false)
				.copyTo(to);
	}

	@Override
	public boolean isCreateNewRecord()
	{
		if (isValid())
		{
			return true;
		}
		return false;
	}

	@Override
	public String toString()
	{
		return ObjectUtils.toString(this);
	}
}
