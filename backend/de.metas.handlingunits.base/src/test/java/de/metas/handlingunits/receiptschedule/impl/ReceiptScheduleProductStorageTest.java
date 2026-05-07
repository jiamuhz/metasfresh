package de.metas.handlingunits.receiptschedule.impl;

import de.metas.bpartner.BPartnerId;
import de.metas.business.BusinessTestHelper;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.handlingunits.storage.impl.AbstractProductStorageTest;
import de.metas.inoutcandidate.api.IReceiptScheduleBL;
import de.metas.util.Services;
import org.adempiere.ad.wrapper.POJOWrapper;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.warehouse.WarehouseId;

import java.math.BigDecimal;

import static org.assertj.core.api.Assumptions.assumeThat;

public class ReceiptScheduleProductStorageTest extends AbstractProductStorageTest
{
	private BPartnerId bpartnerId;
	private WarehouseId warehouseId;

	@Override
	protected void initialize()
	{
		super.initialize();

		bpartnerId = BPartnerId.ofRepoId(BusinessTestHelper.createBPartner("test").getC_BPartner_ID());
		warehouseId = WarehouseId.ofRepoId(BusinessTestHelper.createWarehouse("test").getM_Warehouse_ID());
	}

	@Override
	protected ReceiptScheduleProductStorage createStorage(final String qtyStr, final boolean reversal, final boolean outboundTrx)
	{
		assumeThat(!outboundTrx).as("We are not supporting outboundTrx for ReceiptSchedules").isTrue();
		assumeThat(!reversal).as("We are not supporting not reversal transactions only").isTrue();

		final BigDecimal qty = new BigDecimal(qtyStr);

		final I_M_ReceiptSchedule schedule = InterfaceWrapperHelper.newInstance(I_M_ReceiptSchedule.class, helper.contextProvider);
		schedule.setM_Warehouse_ID(warehouseId.getRepoId());
		schedule.setC_BPartner_ID(bpartnerId.getRepoId());
		schedule.setM_Product_ID(product.getM_Product_ID());
		schedule.setC_UOM_ID(uomEach.getC_UOM_ID());
		schedule.setQtyOrdered(qty);
		schedule.setQtyMoved(BigDecimal.ZERO);
		InterfaceWrapperHelper.save(schedule);

		// enabling string values because we want to make sure that only defined fields are used
		POJOWrapper.getWrapper(schedule).setStrictValues(true);

		return ReceiptScheduleProductStorage.builder()
				.receiptScheduleBL(Services.get(IReceiptScheduleBL.class))
				.receiptSchedule(schedule)
				.enforceCapacity(true)
				.build();
	}

}
