 

package de.metas.ui.web.pporder.process;

import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.pporder.PPOrderLineRowId;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.NonNull;
import org.eevolution.api.PPOrderBOMLineId;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class WEBUI_PP_Order_HUEditor_ProcessBase extends HUEditorProcessTemplate
{
	protected final Stream<HUEditorRow> retrieveSelectedAndEligibleHUEditorRows()
	{
		final HUEditorView huEditorView = HUEditorView.cast(super.getView());
		final Stream<HUEditorRow> huEditorRows = huEditorView.streamByIds(getSelectedRowIds());

		return retrieveEligibleHUEditorRows(huEditorRows);
	}

	protected static Stream<HUEditorRow> retrieveEligibleHUEditorRows(@NonNull final Stream<HUEditorRow> inputStream)
	{
		final SourceHUsService sourceHuService = SourceHUsService.get();

		return inputStream
				.filter(HUEditorRow::isHUStatusActive)
				.filter(huRow -> !sourceHuService.isHuOrAnyParentSourceHu(huRow.getHuId()));
	}

	protected Optional<PPOrderLinesView> getPPOrderView()
	{
		final ViewId parentViewId = getView().getParentViewId();
		if (parentViewId == null)
		{
			return Optional.empty();
		}

		final PPOrderLinesView ppOrderView = getViewsRepo().getView(parentViewId, PPOrderLinesView.class);
		return Optional.of(ppOrderView);
	}

	@Nullable
	protected PPOrderBOMLineId getSelectedOrderBOMLineId()
	{
		final DocumentId documentId = getView().getParentRowId();
		if (documentId == null)
		{
			return null;
		}

		return PPOrderLineRowId.fromDocumentId(documentId)
				.getPPOrderBOMLineIdIfApplies()
				.orElse(null);
	}
}
