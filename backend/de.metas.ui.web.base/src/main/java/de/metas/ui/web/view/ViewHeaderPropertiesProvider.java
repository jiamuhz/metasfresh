package de.metas.ui.web.view;

import java.util.Set;

import javax.annotation.Nullable;

import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.NonNull;


public interface ViewHeaderPropertiesProvider
{
	@Nullable
	String getAppliesOnlyToTableName();

	@NonNull
	ViewHeaderProperties computeHeaderProperties(@NonNull IView view);
	
	ViewHeaderPropertiesIncrementalResult computeIncrementallyOnRowsChanged(
			@NonNull ViewHeaderProperties currentHeaderProperties,
			@NonNull IView view,
			@NonNull Set<DocumentId> changedRowIds,
			final boolean watchedByFrontend);
}
