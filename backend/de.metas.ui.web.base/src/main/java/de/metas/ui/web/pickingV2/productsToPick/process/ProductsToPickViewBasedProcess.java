 

package de.metas.ui.web.pickingV2.productsToPick.process;

import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.picking.PickingCandidate;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pickingV2.PickingConstantsV2;
import de.metas.ui.web.pickingV2.productsToPick.ProductsToPickView;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRow;
import de.metas.ui.web.pickingV2.productsToPick.rows.WebuiPickHUResult;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Stream;

public abstract class ProductsToPickViewBasedProcess extends ViewBasedProcessTemplate implements IProcessPrecondition
{
	@Override
	protected abstract ProcessPreconditionsResolution checkPreconditionsApplicable();

	protected final boolean isPickerProfile()
	{
		return getViewProfileId() == null;
	}

	protected final boolean isReviewProfile()
	{
		return PickingConstantsV2.PROFILE_ID_ProductsToPickView_Review.equals(getViewProfileId());
	}

	@Override
	protected final ProductsToPickView getView()
	{
		return ProductsToPickView.cast(super.getView());
	}

	protected final List<ProductsToPickRow> getSelectedRows()
	{
		final DocumentIdsSelection rowIds = getSelectedRowIds();
		return getView()
				.streamByIds(rowIds)
				.collect(ImmutableList.toImmutableList());
	}

	protected final List<ProductsToPickRow> getAllRows()
	{
		return streamAllRows()
				.collect(ImmutableList.toImmutableList());
	}

	protected Stream<ProductsToPickRow> streamAllRows()
	{
		return getView()
				.streamByIds(DocumentIdsSelection.ALL);
	}

	protected void updateViewRowFromPickingCandidate(@NonNull final DocumentId rowId, @NonNull final PickingCandidate pickingCandidate)
	{
		getView().updateViewRowFromPickingCandidate(rowId, pickingCandidate);
	}

	protected void updateViewRowFromPickingCandidate(@NonNull final ImmutableList<WebuiPickHUResult> pickHUResults)
	{
		pickHUResults.forEach(r -> updateViewRowFromPickingCandidate(r.getDocumentId(), r.getPickingCandidate()));
	}
}
