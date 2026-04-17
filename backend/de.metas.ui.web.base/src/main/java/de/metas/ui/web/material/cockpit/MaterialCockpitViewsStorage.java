 

package de.metas.ui.web.material.cockpit;

import de.metas.cache.CacheMgt;
import de.metas.cache.model.CacheInvalidateMultiRequest;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.ui.web.view.DefaultViewsStorage;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsStorage4GivenWindow;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import lombok.NonNull;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.model.I_M_Product;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.stream.Stream;

/**
 * This {@link IViewsStorage4GivenWindow} implementation is dedicated to storing {@link MaterialCockpitView}.
 * The actual work is done by an internal instance of {@link DefaultViewsStorage}.
 * We store {@link MaterialCockpitView}s inside this dedicated storage,
 * because we need to invalidate those views on changes in two different tables (the standard framework could handle only one).
 */
@Service
public class MaterialCockpitViewsStorage implements IViewsStorage4GivenWindow
{
	private final DefaultViewsStorage defaultViewsStorage = new DefaultViewsStorage(Duration.ofHours(1));

	public MaterialCockpitViewsStorage()
	{
		CacheMgt.get().addCacheResetListener(I_MD_Cockpit.Table_Name, cacheInvalidateRequest -> {
			notifyViewOfCacheReset(cacheInvalidateRequest);
			return 0;
		});
		CacheMgt.get().addCacheResetListener(I_MD_Stock.Table_Name, cacheInvalidateRequest -> {
			notifyViewOfCacheReset(cacheInvalidateRequest);
			return 0;
		});
		CacheMgt.get().addCacheResetListener(I_M_Product.Table_Name, cacheInvalidateRequest -> {
			notifyViewOfCacheReset(cacheInvalidateRequest);
			return 0;
		});
	}

	private void notifyViewOfCacheReset(@NonNull final CacheInvalidateMultiRequest cacheInvalidateRequest)
	{
		for (final IView view : getAllViews())
		{
			if (cacheInvalidateRequest.isResetAll())
			{
				view.invalidateAll();
			}
			else
			{
				final TableRecordReferenceSet recordsEffective = cacheInvalidateRequest.getRecordsEffective();
				view.notifyRecordsChanged(recordsEffective, true);
			}
		}
	}

	/**
	 * @return {@link MaterialCockpitUtil#WINDOWID_MaterialCockpitView} since that'S what we want to store inside this service.
	 */
	@Override
	public WindowDocumentTypeId getWindowId()
	{
		return MaterialCockpitUtil.WINDOWID_MaterialCockpitView;
	}

	@Override
	public void put(final IView view)
	{
		defaultViewsStorage.put(view);
	}

	@Nullable
	@Override
	public IView getByIdOrNull(final ViewId viewId)
	{
		return defaultViewsStorage.getByIdOrNull(viewId);
	}

	@Override
	public void closeById(@NonNull final ViewId viewId, @NonNull final ViewCloseAction closeAction)
	{
		defaultViewsStorage.closeById(viewId, closeAction);
	}

	@Override
	public Stream<IView> streamAllViews()
	{
		return defaultViewsStorage.streamAllViews();
	}

	@Override
	public void invalidateView(final ViewId viewId)
	{
		defaultViewsStorage.invalidateView(viewId);
	}
}
