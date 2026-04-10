package de.metas.ui.web.ztest.myview;

import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.i18n.ITranslatableString;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.picking.pickingslot.PickingSlotView;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.NonNull;

import javax.annotation.Nullable;


/**
 *
 */
public class MyView extends AbstractCustomView<MyViewRow>
{
	public static MyView cast(final IView view)
	{
		return (MyView)view;
	}


	//private final ConcurrentHashMap<DocumentId, PickingSlotView> pickingSlotsViewByRowId = new ConcurrentHashMap<>();

	@Builder
	private MyView(
			@NonNull final ViewId viewId,
			@Nullable final ITranslatableString description,
			@NonNull final MyViewRowsData rowsData)
	{
		super(viewId, description, rowsData, NullDocumentFilterDescriptorsProvider.instance);
	}

	@Override
	protected MyViewRowsData getRowsData()
	{
		return MyViewRowsData.cast(super.getRowsData());
	}

	/**
	 * @return {@link I_M_Packageable_V#Table_Name}.
	 */
	@Override
	public String getTableNameOrNull(@Nullable final DocumentId ignored)
	{
		return I_M_Packageable_V.Table_Name;
	}

	@Override
	public void close(final ViewCloseAction action)
	{
		if (action.isDone())
		{
			//closePickingCandidatesFromRackSystemPickingSlots();
		}
	}

	/*
	public void setPickingSlotView(@NonNull final DocumentId rowId, @NonNull final PickingSlotView pickingSlotView)
	{
		pickingSlotsViewByRowId.put(rowId, pickingSlotView);
	}

	public void removePickingSlotView(@NonNull final DocumentId rowId, @NonNull final ViewCloseAction viewCloseAction)
	{
		final PickingSlotView view = pickingSlotsViewByRowId.remove(rowId);
		if (view != null)
		{
			view.close(viewCloseAction);
		}
	}

	public PickingSlotView getPickingSlotViewOrNull(@NonNull final DocumentId rowId)
	{
		return pickingSlotsViewByRowId.get(rowId);
	}

	public PickingSlotView computePickingSlotViewIfAbsent(@NonNull final DocumentId rowId, @NonNull final Supplier<PickingSlotView> pickingSlotViewFactory)
	{
		return pickingSlotsViewByRowId.computeIfAbsent(rowId, id -> pickingSlotViewFactory.get());
	}

	public void invalidatePickingSlotViews()
	{
		pickingSlotsViewByRowId.values().forEach(PickingSlotView::invalidateAll);
	}
	*/
}
