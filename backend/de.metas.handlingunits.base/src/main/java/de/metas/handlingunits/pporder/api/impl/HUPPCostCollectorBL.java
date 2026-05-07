package de.metas.handlingunits.pporder.api.impl;

import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.IHUAssignmentBL;
import de.metas.handlingunits.IHUAssignmentDAO;
import de.metas.handlingunits.IHUStatusBL;
import de.metas.handlingunits.exceptions.HUException;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_PP_Cost_Collector;
import de.metas.handlingunits.pporder.api.IHUPPCostCollectorBL;
import de.metas.handlingunits.snapshot.IHUSnapshotDAO;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.IContextAware;
import org.eevolution.api.IPPCostCollectorBL;
import org.eevolution.api.ReceiptCostCollectorCandidate;

import java.util.Collection;
import java.util.List;

public class HUPPCostCollectorBL implements IHUPPCostCollectorBL
{
	@Override
	public I_PP_Cost_Collector createReceipt(
			@NonNull final ReceiptCostCollectorCandidate candidate,
			@NonNull final I_M_HU hu)
	{
		// services
		final IPPCostCollectorBL ppCostCollectorBL = Services.get(IPPCostCollectorBL.class);
		final IHUStatusBL huStatusBL = Services.get(IHUStatusBL.class);

		//
		// Create & process the receipt cost collector
		final I_PP_Cost_Collector cc = InterfaceWrapperHelper.create(ppCostCollectorBL.createReceipt(candidate), I_PP_Cost_Collector.class);
		
		// Assign the HU to cost collector
		assignHUs(cc, ImmutableList.of(hu));

		//
		// Activate the HU (assuming it was Planning)
		huStatusBL.setHUStatusActive(ImmutableList.of(hu));

		return cc;
	}

	@Override
	public void assignHUs(final org.eevolution.model.I_PP_Cost_Collector cc, final Collection<I_M_HU> husToAssign)
	{
		final IHUAssignmentBL huAssignmentBL = Services.get(IHUAssignmentBL.class);
		huAssignmentBL.assignHUs(cc, husToAssign);
	}

	@Override
	public void assertNoHUAssignments(final org.eevolution.model.I_PP_Cost_Collector cc)
	{
		Services.get(IHUAssignmentDAO.class).assertNoHUAssignmentsForModel(cc);
	}

	/**
	 * @return assigned top level HUs (i.e. the HUs which were assigned to original cost collector).
	 */
	@Override
	public List<I_M_HU> getTopLevelHUs(final org.eevolution.model.I_PP_Cost_Collector cc)
	{
		final IHUAssignmentDAO huAssignmentDAO = Services.get(IHUAssignmentDAO.class);
		return huAssignmentDAO.retrieveTopLevelHUsForModel(cc);
	}

	@Override
	public void restoreTopLevelHUs(final I_PP_Cost_Collector costCollector)
	{
		Check.assumeNotNull(costCollector, "costCollector not null");

		//
		// Retrieve the HUs which were assigned to original cost collector
		final List<I_M_HU> hus = getTopLevelHUs(costCollector);
		if (hus.isEmpty())
		{
			return;
		}

		//
		// Get the snapshot ID.
		// Make sure it exists, else we would not be able to restore the HUs.
		final String snapshotId = costCollector.getSnapshot_UUID();
		if (Check.isEmpty(snapshotId, true))
		{
			throw new HUException("@NotFound@ @Snapshot_UUID@ (" + costCollector + ")");
		}

		final IContextAware context = InterfaceWrapperHelper.getContextAware(costCollector);
		Services.get(IHUSnapshotDAO.class).restoreHUs()
				.setContext(context)
				.setSnapshotId(snapshotId)
				.setDateTrx(costCollector.getMovementDate())
				.setReferencedModel(costCollector)
				.addModels(hus)
				.restoreFromSnapshot();

	}
}
