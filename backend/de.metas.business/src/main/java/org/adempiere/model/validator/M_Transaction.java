package org.adempiere.model.validator;

/** */


import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_M_Locator;
import org.compiere.model.I_M_Transaction;
import org.compiere.model.ModelValidator;

import de.metas.product.IStorageBL;
import de.metas.util.Services;

@Interceptor(I_M_Transaction.class)
public class M_Transaction
{
	@ModelChange(timings = ModelValidator.TYPE_BEFORE_DELETE)
	public void updateStorageOnDelete(final I_M_Transaction mtrx)
	{
		final Properties ctx = InterfaceWrapperHelper.getCtx(mtrx);
		final String trxName = InterfaceWrapperHelper.getTrxName(mtrx);
		final I_M_Locator locator = mtrx.getM_Locator();
		final BigDecimal diffQtyOnHand = mtrx.getMovementQty().negate();
		final BigDecimal diffQtyReserved = BigDecimal.ZERO;
		final BigDecimal diffQtyOrdered = BigDecimal.ZERO;

		// FIXME: consider to do it async, after commit to make sure we are consistent!
		Services.get(IStorageBL.class).add(ctx,
				locator.getM_Warehouse_ID(),
				locator.getM_Locator_ID(),
				mtrx.getM_Product_ID(),
				mtrx.getM_AttributeSetInstance_ID(), // M_AttributeSetInstance_ID
				0, // reservationAttributeSetInstance_ID
				diffQtyOnHand,
				diffQtyReserved,
				diffQtyOrdered,
				trxName);
	}
}
