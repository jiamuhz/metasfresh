 

package de.metas.ui.web.view;

import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import lombok.NonNull;

import java.util.List;
import java.util.function.Supplier;

public interface IViewFactory
{
	/**
	 * Don't call it directly. Will be called by API.
	 */
	default void setViewsRepository(final IViewsRepository viewsRepository)
	{
	}

	/**
	 * Sets the windowId on which this factory was bind.
	 * Don't call it directly. Will be called by API.
	 */
	default void setWindowId(final WindowDocumentTypeId windowId)
	{
	}

	IView createView(@NonNull CreateViewRequest request);

	ViewLayout getViewLayout(WindowDocumentTypeId windowId, JSONViewDataType viewDataType, ViewProfileId profileId);

	default List<ViewProfile> getAvailableProfiles(final WindowDocumentTypeId windowId)
	{
		return ImmutableList.of();
	}

	default IView filterView(
			@NonNull final IView view,
			@NonNull final JSONFilterViewRequest filterViewRequest,
			@NonNull final Supplier<IViewsRepository> viewsRepo)
	{
		final CreateViewRequest createViewRequest = CreateViewRequest.filterViewBuilder(view, filterViewRequest).build();
		return createView(createViewRequest);
	}

	default IView deleteStickyFilter(final IView view, final String filterId)
	{
		final CreateViewRequest createViewRequest = CreateViewRequest.deleteStickyFilterBuilder(view, filterId).build();
		return createView(createViewRequest);
	}
}
