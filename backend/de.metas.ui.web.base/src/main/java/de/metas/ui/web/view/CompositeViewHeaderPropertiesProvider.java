package de.metas.ui.web.view;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Nullable;

import org.adempiere.exceptions.AdempiereException;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.Check;
import lombok.NonNull;


public class CompositeViewHeaderPropertiesProvider implements ViewHeaderPropertiesProvider
{
	public static ViewHeaderPropertiesProvider of(final Collection<ViewHeaderPropertiesProvider> providers)
	{
		if (providers.isEmpty())
		{
			return NullViewHeaderPropertiesProvider.instance;
		}
		else if (providers.size() == 1)
		{
			return providers.iterator().next();
		}
		else
		{
			return new CompositeViewHeaderPropertiesProvider(ImmutableList.copyOf(providers));
		}
	}

	private final String appliesOnTableName;
	private final ImmutableList<ViewHeaderPropertiesProvider> providers;

	private CompositeViewHeaderPropertiesProvider(@NonNull final ImmutableList<ViewHeaderPropertiesProvider> providers)
	{
		Check.assumeNotEmpty(providers, "providers is not empty");

		this.appliesOnTableName = extractAppliesOnTableName(providers);
		this.providers = providers;
	}

	@Nullable
	private static String extractAppliesOnTableName(final ImmutableList<ViewHeaderPropertiesProvider> providers)
	{
		final ImmutableSet<String> tableNames = providers.stream()
				.map(ViewHeaderPropertiesProvider::getAppliesOnlyToTableName)
				.filter(Objects::nonNull)
				.distinct()
				.collect(ImmutableSet.toImmutableSet());
		if (tableNames.isEmpty())
		{
			return null;
		}
		else if (tableNames.size() == 1)
		{
			return tableNames.iterator().next();
		}
		else
		{
			throw new AdempiereException("Generic providers and providers for same table can be groupped: " + providers);
		}
	}

	@Override
	public String getAppliesOnlyToTableName()
	{
		return appliesOnTableName;
	}

	@Override
	public @NonNull ViewHeaderProperties computeHeaderProperties(@NonNull final IView view)
	{
		ViewHeaderProperties result = ViewHeaderProperties.EMPTY;

		for (final ViewHeaderPropertiesProvider provider : providers)
		{
			final ViewHeaderProperties properties = provider.computeHeaderProperties(view);
			result = result.combine(properties);
		}

		return result;
	}

	@Override
	public ViewHeaderPropertiesIncrementalResult computeIncrementallyOnRowsChanged(
			@NonNull final ViewHeaderProperties currentHeaderProperties,
			@NonNull final IView view,
			@NonNull final Set<DocumentId> changedRowIds,
			final boolean watchedByFrontend)
	{
		ViewHeaderProperties computedHeaderProperties = currentHeaderProperties;
		for (final ViewHeaderPropertiesProvider provider : providers)
		{
			final ViewHeaderPropertiesIncrementalResult partialResult = provider.computeIncrementallyOnRowsChanged(
					computedHeaderProperties,
					view,
					changedRowIds,
					watchedByFrontend);

			if (partialResult.isComputed())
			{
				computedHeaderProperties = partialResult.getComputeHeaderProperties();
			}
			else if (partialResult.isFullRecomputeRequired())
			{
				return ViewHeaderPropertiesIncrementalResult.fullRecomputeRequired();
			}
			else
			{
				throw new AdempiereException("Unknow partial result type: " + partialResult);
			}
		}

		return ViewHeaderPropertiesIncrementalResult.computed(computedHeaderProperties);
	}
}
