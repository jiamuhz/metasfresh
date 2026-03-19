package de.metas.ui.web.document.filter.provider;

import com.google.common.collect.ImmutableList;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.json.JSONDocumentFilter;
import de.metas.util.GuavaCollectors;
import lombok.NonNull;
import lombok.ToString;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

  
@ToString
final class CompositeDocumentFilterDescriptorsProvider implements DocumentFilterDescriptorsProvider
{
	public static DocumentFilterDescriptorsProvider compose(@NonNull final List<DocumentFilterDescriptorsProvider> providers)
	{
		if (providers.isEmpty())
		{
			return NullDocumentFilterDescriptorsProvider.instance;
		}

		final ImmutableList<DocumentFilterDescriptorsProvider> nonNullProviders = providers.stream()
				.filter(NullDocumentFilterDescriptorsProvider::isNotNull)
				.collect(ImmutableList.toImmutableList());

		if (nonNullProviders.isEmpty())
		{
			return NullDocumentFilterDescriptorsProvider.instance;
		}
		else if (nonNullProviders.size() == 1)
		{
			return nonNullProviders.get(0);
		}

		return new CompositeDocumentFilterDescriptorsProvider(nonNullProviders);
	}

	private final ImmutableList<DocumentFilterDescriptorsProvider> providers;

	private CompositeDocumentFilterDescriptorsProvider(@NonNull final ImmutableList<DocumentFilterDescriptorsProvider> providers)
	{
		this.providers = providers;
	}

	@Override
	public Collection<DocumentFilterDescriptor> getAll()
	{
		return providers
				.stream()
				.map(DocumentFilterDescriptorsProvider::getAll)
				.flatMap(Collection::stream)
				.sorted(Comparator.comparing(DocumentFilterDescriptor::getSortNo))
				.collect(GuavaCollectors.toImmutableMapByKey(DocumentFilterDescriptor::getFilterId)) // make sure each filterId is unique!
				.values();
	}

	@Override
	public DocumentFilterDescriptor getByFilterIdOrNull(final String filterId)
	{
		return providers
				.stream()
				.map(provider -> provider.getByFilterIdOrNull(filterId))
				.filter(Objects::nonNull)
				.findFirst()
				.orElse(null);
	}

	@Override
	public DocumentFilter unwrap(@NonNull final JSONDocumentFilter jsonFilter)
	{
		return getProviderByFilterId(jsonFilter.getFilterId())
				.map(provider -> provider.unwrap(jsonFilter))
				.orElseGet(() -> JSONDocumentFilter.unwrapAsGenericFilter(jsonFilter));
	}

	private Optional<DocumentFilterDescriptorsProvider> getProviderByFilterId(@NonNull final String filterId)
	{
		return providers
				.stream()
				.filter(provider -> provider.getByFilterIdOrNull(filterId) != null)
				.findFirst();
	}
}
