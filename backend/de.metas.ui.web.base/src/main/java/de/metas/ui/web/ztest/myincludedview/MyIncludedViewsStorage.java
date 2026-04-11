 

package de.metas.ui.web.ztest.myincludedview;

import de.metas.ui.web.view.*;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.ztest.MyViewConstants;
import de.metas.ui.web.ztest.myview.MyView;
import de.metas.ui.web.ztest.myview.MyViewRow;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.stream.Stream;

/**
 * {@link MyIncludedView}s index storage.
 * 
 * It's not actually a storage. It just forwards all calls to {@link MyView} where the {@link MyIncludedView}s are stored, one per each row.
 * 
 */
@Component
public class MyIncludedViewsStorage implements IViewsStorage4GivenWindow
{
	//@formatter:off
	@Override
	public WindowId getWindowId() { return MyViewConstants.WINDOWID_MyIncludedView; }
	//@formatter:off
	
	@Autowired
	private MyIncludedViewFactory myIncludedViewFactory;

	// NOTE: avoid using @Autowired because might introduce cyclic dependency.
	// We have a setter which will be called when this instance will be registered.
	private IViewsRepository viewsRepository;

	@Override
	public void setViewsRepository(@NonNull final IViewsRepository viewsRepository)
	{
		this.viewsRepository = viewsRepository;
	}

	@NonNull
	private IViewsRepository getViewsRepository()
	{
		return viewsRepository;
	}

	@Override
	public void put(final IView myIncludedView)
	{
		final ViewId myIncludedViewId = myIncludedView.getViewId();
		final MyView myView = getMyViewByMyIncludedViewId(myIncludedViewId);

		final DocumentId rowId = extractRowId(myIncludedViewId);

		myView.setMyIncludedView(rowId, MyIncludedView.cast(myIncludedView));
	}

	public static ViewId createViewId(@NonNull final ViewId myViewId, @NonNull final DocumentId myViewRowId)
	{
		if (!MyViewConstants.WINDOWID_MyView.equals(myViewId.getWindowId()))
		{
			throw new AdempiereException("Invalid pickingViewId '" + myViewId + "'. WindowId not matching.")
					.setParameter("expectedWindowId", MyViewConstants.WINDOWID_MyView);
		}

		return ViewId.ofParts(MyViewConstants.WINDOWID_MyIncludedView, myViewId.getViewIdPart(), myViewRowId.toJson());
	}

	private static ViewId extractMyViewId(final ViewId myIncludedViewId)
	{
		final String viewIdPart = myIncludedViewId.getViewIdPart();
		return ViewId.ofParts(MyViewConstants.WINDOWID_MyView, viewIdPart);
	}

	private static DocumentId extractRowId(@NonNull final ViewId myIncludedViewId)
	{
		final String rowIdStr = myIncludedViewId.getPart(2);
		return DocumentId.of(rowIdStr);
	}

	private MyView getMyViewByMyIncludedViewId(final ViewId myIncludedViewId)
	{
		final ViewId myViewId = extractMyViewId(myIncludedViewId);
		final MyView view = MyView.cast(getViewsRepository().getView(myViewId));
		return view;
	}

	@Nullable@Override
	public MyIncludedView getByIdOrNull(final ViewId myIncludedViewId)
	{
		final boolean create = true;
		return getOrCreateMyIncludedView(myIncludedViewId, create);
	}

	private MyIncludedView getOrCreateMyIncludedView(@NonNull final ViewId myIncludedViewId, final boolean create)
	{
		final MyView myView = getMyViewByMyIncludedViewId(myIncludedViewId);
		final DocumentId myViweRowId = extractRowId(myIncludedViewId);

		if (create)
		{
			return myView.computeMyIncludedViewIfAbsent(
					myViweRowId,
					() -> {
						final MyViewRow myViewRow = myView.getById(myViweRowId);
						final CreateViewRequest createViewRequest = CreateViewRequest
								.builder(MyViewConstants.WINDOWID_MyIncludedView, JSONViewDataType.includedView)
								.setParentViewId(myView.getViewId())
								.setParentRowId(myViewRow.getId())
								.build();

						return (MyIncludedView)myIncludedViewFactory.createView(createViewRequest);
					});
		}
		else
		{
			return myView.getMyIncludedViewOrNull(myViweRowId);
		}
	}

	@Override
	public void closeById(@NonNull final ViewId myIncludedViewId, @NonNull final ViewCloseAction closeAction)
	{
		final DocumentId rowId = extractRowId(myIncludedViewId);
		final MyView myView = getMyViewByMyIncludedViewId(myIncludedViewId);
		myView.removeMyIncludedView(rowId, closeAction);
	}

	@Override
	public void invalidateView(final ViewId myIncludedViewId)
	{
		final MyIncludedView myIncludedView = getOrCreateMyIncludedView(myIncludedViewId, false/* create */);
		if (myIncludedView == null)
		{
			return;
		}

		final MyView myView = getMyViewByMyIncludedViewId(myIncludedViewId);

		if (myView != null)
		{
			//we have to invalidate all the related pickingSlotViews in order to make sure the
			//changes available in UI when selecting different `packageableRows`
			myView.invalidateMyIncludedViews();
		}

		myIncludedView.invalidateAll();

		ViewChangesCollector.getCurrentOrAutoflush()
				.collectFullyChanged(myIncludedView);
	}

	@Override
	public Stream<IView> streamAllViews()
	{
		// Do we really have to implement this?
		return Stream.empty();
	}

}
