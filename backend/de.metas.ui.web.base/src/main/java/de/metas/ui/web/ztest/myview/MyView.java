package de.metas.ui.web.ztest.myview;

import de.metas.i18n.ITranslatableString;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.ztest.myincludedview.MyIncludedView;
import lombok.Builder;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;


/**
 *
 */
public class MyView extends AbstractCustomView<MyViewRow>
{
	public static MyView cast(final IView view)
	{
		return (MyView)view;
	}


	private final ConcurrentHashMap<DocumentId, MyIncludedView> myIncludedViewByRowId = new ConcurrentHashMap<>();

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
	
	public void setMyIncludedView(@NonNull final DocumentId rowId, @NonNull final MyIncludedView myIncludedView)
	{
		myIncludedViewByRowId.put(rowId, myIncludedView);
	}

	public void removeMyIncludedView(@NonNull final DocumentId rowId, @NonNull final ViewCloseAction viewCloseAction)
	{
		final MyIncludedView view = myIncludedViewByRowId.remove(rowId);
		if (view != null)
		{
			view.close(viewCloseAction);
		}
	}

	public MyIncludedView getMyIncludedViewOrNull(@NonNull final DocumentId rowId)
	{
		return myIncludedViewByRowId.get(rowId);
	}

	public MyIncludedView computeMyIncludedViewIfAbsent(@NonNull final DocumentId rowId, @NonNull final Supplier<MyIncludedView> myIncludedViewSupplier)
	{
		return myIncludedViewByRowId.computeIfAbsent(rowId, id -> myIncludedViewSupplier.get());
	}

	public void invalidateMyIncludedViews()
	{
		myIncludedViewByRowId.values().forEach(MyIncludedView::invalidateAll);
	}
}
