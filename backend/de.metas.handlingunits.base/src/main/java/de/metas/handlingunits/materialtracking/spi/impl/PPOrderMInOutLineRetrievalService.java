package de.metas.handlingunits.materialtracking.spi.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.adempiere.ad.model.util.ModelByIdComparator;
import org.eevolution.api.IPPCostCollectorBL;
import org.eevolution.api.IPPCostCollectorDAO;
import org.eevolution.model.I_PP_Cost_Collector;

import com.google.common.collect.ImmutableList;

import de.metas.handlingunits.IHUAssignmentDAO;
import de.metas.handlingunits.IHUAssignmentDAO.HuAssignment;
import de.metas.handlingunits.inout.IHUInOutDAO;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_Assignment;
import org.eevolution.api.PPOrderId;
import de.metas.materialtracking.model.I_M_InOutLine;
import de.metas.materialtracking.spi.IPPOrderMInOutLineRetrievalService;
import de.metas.util.Loggables;
import de.metas.util.Services;

public class PPOrderMInOutLineRetrievalService implements IPPOrderMInOutLineRetrievalService
{

	@Override
	public List<I_M_InOutLine> provideIssuedInOutLines(final I_PP_Cost_Collector issueCostCollector)
	{
		final Set<I_M_InOutLine> result = new TreeSet<>(ModelByIdComparator.getInstance());

		final IHUAssignmentDAO huAssignmentDAO = Services.get(IHUAssignmentDAO.class);

		List<HuAssignment> huAssignmentsForModel = huAssignmentDAO.retrieveTUHUAssignmentsForModelQuery(issueCostCollector)
				.create()
				.stream()
				.map(HuAssignment::ofDataRecordAllowMissingHU)
				.collect(ImmutableList.toImmutableList());

		if (huAssignmentsForModel.isEmpty())
		{
			// fallback
			huAssignmentsForModel = huAssignmentDAO.retrieveLowLevelHUAssignmentsForModel(issueCostCollector);
		}

		for (final HuAssignment huAssignment : huAssignmentsForModel)
		{
			final I_M_HU hu = huAssignment.getLowestLevelHU();
			if (hu == null)
			{
				continue;
			}

			final I_M_InOutLine inoutLine = Services.get(IHUInOutDAO.class).retrieveCompletedReceiptLineOrNull(hu);
			if (inoutLine == null || !inoutLine.getM_InOut().isProcessed())
			{
				// there is no iol
				// or it's not processed (which should not happen)
				continue;
			}
			result.add(inoutLine);
		}
		return new ArrayList<>(result);
	}

	@Override
	public Map<Integer, BigDecimal> retrieveIolAndQty(final PPOrderId ppOrderId)
	{
		final IPPCostCollectorBL ppCostCollectorBL = Services.get(IPPCostCollectorBL.class);
		final IPPCostCollectorDAO ppCostCollectorDAO = Services.get(IPPCostCollectorDAO.class);
		final IHUAssignmentDAO huAssignmentDAO = Services.get(IHUAssignmentDAO.class);
		final IHUInOutDAO huInOutDAO = Services.get(IHUInOutDAO.class);

		final Map<Integer, BigDecimal> iolMap = new HashMap<>();

		final List<I_PP_Cost_Collector> costCollectors = ppCostCollectorDAO.getByOrderId(ppOrderId);
		for (final I_PP_Cost_Collector costCollector : costCollectors)
		{
			if (!ppCostCollectorBL.isAnyComponentIssueOrCoProduct(costCollector))
			{
				continue;
			}

			final List<I_M_HU_Assignment> huAssignmentsForModel = huAssignmentDAO.retrieveTopLevelHUAssignmentsForModel(costCollector);

			final Map<Integer, I_M_InOutLine> id2iol = new HashMap<>();

			for (final I_M_HU_Assignment assignment : huAssignmentsForModel)
			{
				final I_M_HU hu = assignment.getM_HU();
				final I_M_InOutLine inoutLine = huInOutDAO.retrieveCompletedReceiptLineOrNull(hu);
				if (inoutLine == null || !inoutLine.getM_InOut().isProcessed())
				{
					// there is no iol
					// or it's not processed (which should not happen)
					continue;
				}
				id2iol.put(inoutLine.getM_InOutLine_ID(), inoutLine);
			}

			BigDecimal qtyToAllocate = ppCostCollectorBL.getMovementQtyInStockingUOM(costCollector).toBigDecimal();
			for (final I_M_InOutLine inoutLine : id2iol.values())
			{
				final BigDecimal qty = qtyToAllocate.min(inoutLine.getMovementQty());
				iolMap.put(inoutLine.getM_InOutLine_ID(), qty);
				qtyToAllocate = qtyToAllocate.subtract(inoutLine.getMovementQty()).max(BigDecimal.ZERO);
			}

			if (qtyToAllocate.signum() > 0)
			{
				Loggables.addLog("PROBLEM: PP_Cost_Collector {0} of PP_Order {1} has a remaining unallocated qty of {2}!", costCollector, ppOrderId, qtyToAllocate);
			}
		}

		return iolMap;
	}
}
