package de.metas.ui.web.window.descriptor;

import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesPage;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Set;



/**
 * Simple template implementation of {@link LookupDescriptor} and {@link LookupDataSourceFetcher}.
 *
 * 
 */
public abstract class SimpleLookupDescriptorTemplate implements LookupDescriptor, LookupDataSourceFetcher
{
	@Override
	public final LookupDataSourceFetcher getLookupDataSourceFetcher()
	{
		return this;
	}

	@Override
	public final boolean isHighVolume()
	{
		// NOTE: method will never be called because isCached() == true
		return false;
	}

	@Override
	public LookupSource getLookupSourceType()
	{
		return LookupSource.list;
	}

	@Override
	public boolean hasParameters()
	{
		return !getDependsOnFieldNames().isEmpty();
	}

	@Override
	public abstract boolean isNumericKey();

	@Override
	public abstract Set<String> getDependsOnFieldNames();

	//
	//
	//
	// -----------------------
	//
	//

	@Override
	public LookupDataSourceContext.Builder newContextForFetchingById(final Object id)
	{
		return LookupDataSourceContext.builderWithoutTableName();
	}

	@Override
	@Nullable
	public abstract LookupValue retrieveLookupValueById(@NonNull LookupDataSourceContext evalCtx);

	@Override
	public LookupDataSourceContext.Builder newContextForFetchingList()
	{
		return LookupDataSourceContext.builderWithoutTableName();
	}

	@Override
	public abstract LookupValuesPage retrieveEntities(LookupDataSourceContext evalCtx);

	@Override
	@Nullable
	public final String getCachePrefix()
	{
		// NOTE: method will never be called because isCached() == true
		return null;
	}

	@Override
	public final boolean isCached()
	{
		return true;
	}

	@Override
	public void cacheInvalidate()
	{
	}

	@Override
	public Optional<WindowId> getZoomIntoWindowId()
	{
		return Optional.empty();
	}

}
