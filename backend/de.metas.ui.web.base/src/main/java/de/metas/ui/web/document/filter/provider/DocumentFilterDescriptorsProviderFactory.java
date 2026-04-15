package de.metas.ui.web.document.filter.provider;

import de.metas.ui.web.window.descriptor.CreateFiltersProviderContext;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.Collection;


public interface DocumentFilterDescriptorsProviderFactory
{
	@Nullable
	DocumentFilterDescriptorsProvider createFiltersProvider(
			@NonNull CreateFiltersProviderContext context,
			@NonNull Collection<DocumentFieldDescriptor> fields);

	default boolean isActive()
	{
		return true;
	}
}
