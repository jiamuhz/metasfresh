package de.metas.ui.web.document.filter.provider;

import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.descriptor.CreateFiltersProviderContext;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


/**
 * {@link DocumentFilterDescriptorsProvider}s factory.
 *
 *
 *
 */
@Service
public final class DocumentFilterDescriptorsProvidersService
{
	private static final Logger logger = LogManager.getLogger(DocumentFilterDescriptorsProvidersService.class);

	private final ImmutableList<DocumentFilterDescriptorsProviderFactory> providerFactories;

	public DocumentFilterDescriptorsProvidersService(final List<DocumentFilterDescriptorsProviderFactory> providerFactories)
	{
		this.providerFactories = ImmutableList.copyOf(providerFactories);
		logger.info("Provider factories: {}", providerFactories);
	}

	public DocumentFilterDescriptorsProvider createFilterDescriptorsProvider(
			@NonNull final CreateFiltersProviderContext context,
			@NonNull final Collection<DocumentFieldDescriptor> fields)
	{
		final ImmutableList<DocumentFilterDescriptorsProvider> providers = providerFactories
				.stream()
				.filter(DocumentFilterDescriptorsProviderFactory::isActive)
				.map(providerFactory -> providerFactory.createFiltersProvider(context, fields))
				.filter(NullDocumentFilterDescriptorsProvider::isNotNull)
				.collect(ImmutableList.toImmutableList());

		return CompositeDocumentFilterDescriptorsProvider.compose(providers);
	}
}
