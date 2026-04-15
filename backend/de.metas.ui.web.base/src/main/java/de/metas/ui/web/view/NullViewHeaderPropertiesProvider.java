package de.metas.ui.web.view;

import java.util.Set;

import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.NonNull;


public final class NullViewHeaderPropertiesProvider implements ViewHeaderPropertiesProvider
{
	public static final transient NullViewHeaderPropertiesProvider instance = new NullViewHeaderPropertiesProvider();

	private NullViewHeaderPropertiesProvider()
	{
	}

	@Override
	public String getAppliesOnlyToTableName()
	{
		return null;
	}

	@Override
	public @NonNull ViewHeaderProperties computeHeaderProperties(@NonNull final IView view)
	{
		return ViewHeaderProperties.EMPTY;
	}

	@Override
	public ViewHeaderPropertiesIncrementalResult computeIncrementallyOnRowsChanged(
			@NonNull final ViewHeaderProperties currentHeaderProperties,
			@NonNull final IView view,
			@NonNull final Set<DocumentId> changedRowIds,
			final boolean watchedByFrontend)
	{
		return ViewHeaderPropertiesIncrementalResult.computedAsEmpty();
	}
}
