 

package de.metas.ui.web.view;

import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;

/**
 * Implementations of this interface are responsible of storing {@link IView} references for a particular window ID identified by {@link #getWindowId()}.
 * 该接口实现者负责存储特定窗口相关的视图
 *
 *
 */
public interface IViewsStorage4GivenWindow
{
	/**
	 * @return the window ID for whom this storage is storing the {@link IView} references. This method will be called by API on registration time.
	 */
	WindowDocumentTypeId getWindowId();

	/**
	 * Don't call it directly. Will be called by API.
	 */
	default void setViewsRepository(final IViewsRepository viewsRepository)
	{
	}

	/**
	 * Adds given view to the index. If the view already exists, it will be overridden.
	 */
	void put(IView view);

	/**
	 * @return the {@link IView} identified by <code>viewId</code> or <code>null</code> if not found.
	 */
	@Nullable
	IView getByIdOrNull(ViewId viewId);

	/**
	 * Closes and removes the view identified by given <code>viewId</code>. If the view does not exist, the method will do nothing, i.e. not failing.
	 */
	void closeById(ViewId viewId, ViewCloseAction closeAction);

	Stream<IView> streamAllViews();

	default ImmutableList<IView> getAllViews()
	{
		return streamAllViews().collect(ImmutableList.toImmutableList());
	}

	void invalidateView(ViewId viewId);

}
