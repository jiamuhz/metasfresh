package de.metas.ui.web.handlingunits.process;

import com.google.common.collect.ImmutableList;
import de.metas.Profiles;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.handlingunits.receiptschedule.IHUReceiptScheduleBL;
import de.metas.inoutcandidate.api.IReceiptScheduleBL;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.quantity.Quantity;
import de.metas.quantity.StockQtyAndUOMQty;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.NonNull;
import org.springframework.context.annotation.Profile;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;



/**
 * Process used to receive HUs for more then one receipt schedule.
 * <p>
 * It creates one VHU for each receipt schedule, using it's remaining quantity to move.
 *
 *
 * https://github.com/metasfresh/metasfresh-webui/issues/182
 */
@Profile(Profiles.PROFILE_Webui)
public class WEBUI_M_ReceiptSchedule_ReceiveCUs extends ReceiptScheduleBasedProcess
{

	private final transient IHUReceiptScheduleBL huReceiptScheduleBL = Services.get(IHUReceiptScheduleBL.class);
	private final transient IReceiptScheduleBL receiptScheduleBL = Services.get(IReceiptScheduleBL.class);


	private boolean allowMultipleReceiptsSchedules = true; // by default we shall allow multiple lines
	private boolean allowNoQuantityAvailable = false; // by default we shall not allow lines which have no quantity available

	protected final void setDisallowMultipleReceiptsSchedules() {this.allowMultipleReceiptsSchedules = false;}

	protected final void setAllowNoQuantityAvailable() {this.allowNoQuantityAvailable = true;}

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable(final IProcessPreconditionsContext context)
	{
		if (context.isNoSelection())
		{
			return ProcessPreconditionsResolution.rejectBecauseNoSelection();
		}

		//
		// Check if we are allowed to select multiple lines
		if (!allowMultipleReceiptsSchedules && context.isMoreThanOneSelected())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("select only one line");
		}

		//
		// Fetch the receipt schedules which have some qty available for receiving
		final List<I_M_ReceiptSchedule> receiptSchedules = context.streamSelectedModels(I_M_ReceiptSchedule.class)
				.filter(receiptSchedule -> allowNoQuantityAvailable || getDefaultAvailableQtyToReceive(receiptSchedule).signum() > 0)
				.collect(ImmutableList.toImmutableList());
		if (receiptSchedules.isEmpty())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("nothing to receive");
		}

		return checkEligibleForReceivingHUs(receiptSchedules)
				.and(() -> checkSingleBPartner(receiptSchedules));
	}

	@Override
	@RunOutOfTrx
	protected final String doIt()
	{
		final List<I_M_HU> hus = streamReceiptSchedulesToReceive()
				.map(this::createPlanningVHU)
				.filter(Objects::nonNull)
				.collect(GuavaCollectors.toImmutableList());

		openHUsToReceive(hus);

		return MSG_OK;
	}

	protected Stream<I_M_ReceiptSchedule> streamReceiptSchedulesToReceive()
	{
		return retrieveActiveSelectedRecordsQueryBuilder(I_M_ReceiptSchedule.class)
				.create()
				.stream(I_M_ReceiptSchedule.class);
	}

	@Nullable
	private I_M_HU createPlanningVHU(final I_M_ReceiptSchedule receiptSchedule)
	{
		return huReceiptScheduleBL.createPlanningVHU(receiptSchedule, getEffectiveQtyToReceive(receiptSchedule));
	}

	protected Quantity getEffectiveQtyToReceive(I_M_ReceiptSchedule rs)
	{
		return getDefaultAvailableQtyToReceive(rs);
	}

	protected final Quantity getDefaultAvailableQtyToReceive(@NonNull final I_M_ReceiptSchedule rs)
	{
		final StockQtyAndUOMQty qty = receiptScheduleBL.getQtyToMove(rs);
		return qty.getStockQty().toZeroIfNegative();
	}
}
