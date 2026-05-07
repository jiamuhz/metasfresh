package de.metas.handlingunits.receiptschedule.impl;

import static de.metas.common.util.CoalesceUtil.coalesce;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.IContextAware;

import de.metas.handlingunits.document.impl.AbstractHUAllocations;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.handlingunits.receiptschedule.IHUReceiptScheduleDAO;
import de.metas.handlingunits.storage.IProductStorage;
import de.metas.quantity.StockQtyAndUOMQty;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;

/**
 * Manage HU allocations to a particular {@link I_M_ReceiptSchedule}. See {@link ReceiptScheduleHUGenerator} for the code that creates those HUs.
 *
 * @author tsa
 *
 */
public class ReceiptScheduleHUAllocations extends AbstractHUAllocations
{
	//
	// Services
	private final IHUReceiptScheduleDAO huReceiptScheduleDAO = Services.get(IHUReceiptScheduleDAO.class);

	public ReceiptScheduleHUAllocations(final I_M_ReceiptSchedule receiptSchedule, final IProductStorage productStorage)
	{
		super(
				InterfaceWrapperHelper.create(receiptSchedule, I_M_ReceiptSchedule.class),
				productStorage);
	}

	public ReceiptScheduleHUAllocations(final I_M_ReceiptSchedule receiptSchedule)
	{
		this(receiptSchedule, (IProductStorage)null);
	}

	@Override
	protected final I_M_ReceiptSchedule getDocumentLineModel()
	{
		return (I_M_ReceiptSchedule)super.getDocumentLineModel();
	}

	@Override
	protected final void deleteAllocations()
	{
		final I_M_ReceiptSchedule receiptSchedule = getDocumentLineModel();
		final String trxName = getTrxName();
		huReceiptScheduleDAO.deleteHandlingUnitAllocations(receiptSchedule, trxName);
	}

	@Override
	protected final void createAllocation(final I_M_HU luHU,
			final I_M_HU tuHU,
			final I_M_HU vhu,
			@NonNull final StockQtyAndUOMQty qtyToAllocate,
			final boolean deleteOldTUAllocations)
	{
		// In case TU is null, consider using VHU as HU (i.e. the case of an VHU on LU, or free VHU)
		// NOTE: we do this shit because in some BLs TU is assumed to be there not null
		// and also, before VHU level allocation the TU field was filled with VHU.

		final I_M_HU tuHUActual = coalesce(tuHU, vhu);
		Check.assumeNotNull(tuHUActual, "At least one of tuHU or vhu needs to be not null; qtyToAllocate={}", qtyToAllocate);

		final IContextAware contextProvider = getContextProvider();
		final I_M_ReceiptSchedule receiptSchedule = getDocumentLineModel();

		if (deleteOldTUAllocations)
		{
			deleteAllocationsOfTU(receiptSchedule, tuHUActual);
		}

		final HUReceiptScheduleAllocBuilder builder = new HUReceiptScheduleAllocBuilder();
		builder.setContext(contextProvider)
				.setM_ReceiptSchedule(receiptSchedule)
				.setM_InOutLine(null)
				.setQtyToAllocate(qtyToAllocate.toZero())
				.setQtyWithIssues(qtyToAllocate.toZero()) // to be sure...
		;
		builder.setHU_QtyAllocated(qtyToAllocate)
				.setM_LU_HU(luHU)
				.setM_TU_HU(tuHUActual)
				.setVHU(vhu);

		// Create RSA and save it
		builder.buildAndSave();
	}

	/**
	 * Remove existing receipt schedule allocations for the given TU.
	 */
	private final void deleteAllocationsOfTU(final I_M_ReceiptSchedule receiptSchedule, final I_M_HU tuHU)
	{
		final String trxName = getTrxName();

		final List<I_M_HU> tradingUnitsToUnassign = Collections.singletonList(tuHU);
		huReceiptScheduleDAO.deleteTradingUnitAllocations(receiptSchedule, tradingUnitsToUnassign, trxName);
	}

	@Override
	protected void deleteAllocations(final Collection<I_M_HU> husToUnassign)
	{
		final I_M_ReceiptSchedule receiptSchedule = getDocumentLineModel();
		final String trxName = getTrxName();
		huReceiptScheduleDAO.deleteHandlingUnitAllocations(receiptSchedule, husToUnassign, trxName);
	}
}
