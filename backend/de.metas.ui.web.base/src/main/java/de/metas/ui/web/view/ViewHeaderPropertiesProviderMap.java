package de.metas.ui.web.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;


@ToString
@EqualsAndHashCode
public class ViewHeaderPropertiesProviderMap
{
	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	public static ViewHeaderPropertiesProviderMap of(@NonNull final Optional<List<ViewHeaderPropertiesProvider>> optionalProviders)
	{
		return optionalProviders.map(ViewHeaderPropertiesProviderMap::of)
				.orElse(ViewHeaderPropertiesProviderMap.EMPTY);
	}

	public static ViewHeaderPropertiesProviderMap of(@NonNull final List<ViewHeaderPropertiesProvider> providers)
	{
		return !providers.isEmpty()
				? new ViewHeaderPropertiesProviderMap(providers)
				: EMPTY;
	}

	private static final ViewHeaderPropertiesProviderMap EMPTY = new ViewHeaderPropertiesProviderMap();

	private final ImmutableMap<String, ViewHeaderPropertiesProvider> mapTableNameVsProviders;
	private final ViewHeaderPropertiesProvider genericProviders;

	private ViewHeaderPropertiesProviderMap()
	{
		this.mapTableNameVsProviders = ImmutableMap.of();
		this.genericProviders = NullViewHeaderPropertiesProvider.instance;
	}

	private ViewHeaderPropertiesProviderMap(@NonNull final List<ViewHeaderPropertiesProvider> providers)
	{
		final ArrayList<ViewHeaderPropertiesProvider> genericProviders = new ArrayList<>();
		final ArrayListMultimap<String, ViewHeaderPropertiesProvider> providersByTableName = ArrayListMultimap.create();

		for (final ViewHeaderPropertiesProvider provider : providers)
		{
			final String appliesOnlyToTableName = provider.getAppliesOnlyToTableName();
			if (Check.isBlank(appliesOnlyToTableName))
			{
				genericProviders.add(provider);

				final Set<String> tableNames = ImmutableSet.copyOf(providersByTableName.keySet());
				for (final String tableName : tableNames)
				{
					providersByTableName.put(tableName, provider);
				}
			}
			else
			{
				if (!providersByTableName.containsKey(appliesOnlyToTableName))
				{
					providersByTableName.putAll(appliesOnlyToTableName, genericProviders);
				}

				providersByTableName.put(appliesOnlyToTableName, provider);
			}
		}

		this.genericProviders = CompositeViewHeaderPropertiesProvider.of(genericProviders);

		this.mapTableNameVsProviders = providersByTableName.keySet()
				.stream()
				.collect(ImmutableMap.toImmutableMap(
						tableName -> tableName,
						tableName -> CompositeViewHeaderPropertiesProvider.of(providersByTableName.get(tableName))));
	}

	public ViewHeaderPropertiesProvider getProvidersByTableName(@Nullable final String tableName)
	{
		return tableName != null
				? mapTableNameVsProviders.getOrDefault(tableName, genericProviders)
				: genericProviders;
	}
}
