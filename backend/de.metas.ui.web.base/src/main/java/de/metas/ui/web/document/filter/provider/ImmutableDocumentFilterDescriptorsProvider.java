package de.metas.ui.web.document.filter.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import lombok.NonNull;


@Immutable
public final class ImmutableDocumentFilterDescriptorsProvider implements DocumentFilterDescriptorsProvider
{
	private final ImmutableMap<String, DocumentFilterDescriptor> descriptorsByFilterId;

	public static ImmutableDocumentFilterDescriptorsProvider of(final List<DocumentFilterDescriptor> descriptors)
	{
		if (descriptors == null || descriptors.isEmpty())
		{
			return EMPTY;
		}
		return new ImmutableDocumentFilterDescriptorsProvider(descriptors);
	}

	public static ImmutableDocumentFilterDescriptorsProvider of(final DocumentFilterDescriptor... descriptors)
	{
		if (descriptors == null || descriptors.length == 0)
		{
			return EMPTY;
		}
		return new ImmutableDocumentFilterDescriptorsProvider(Arrays.asList(descriptors));
	}

	public static Builder builder()
	{
		return new Builder();
	}

	private static final ImmutableDocumentFilterDescriptorsProvider EMPTY = new ImmutableDocumentFilterDescriptorsProvider();

	private ImmutableDocumentFilterDescriptorsProvider(final List<DocumentFilterDescriptor> descriptors)
	{
		descriptorsByFilterId = Maps.uniqueIndex(descriptors, DocumentFilterDescriptor::getFilterId);
	}

	private ImmutableDocumentFilterDescriptorsProvider()
	{
		descriptorsByFilterId = ImmutableMap.of();
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.addValue(descriptorsByFilterId.keySet())
				.toString();
	}

	@Override
	public Collection<DocumentFilterDescriptor> getAll()
	{
		return descriptorsByFilterId.values();
	}

	@Override
	public DocumentFilterDescriptor getByFilterIdOrNull(final String filterId)
	{
		return descriptorsByFilterId.get(filterId);
	}

	//

	public static class Builder
	{
		private final List<DocumentFilterDescriptor> descriptors = new ArrayList<>();

		private Builder()
		{
		}

		public ImmutableDocumentFilterDescriptorsProvider build()
		{
			if (descriptors.isEmpty())
			{
				return EMPTY;
			}
			return new ImmutableDocumentFilterDescriptorsProvider(descriptors);
		}

		public Builder addDescriptor(@NonNull final DocumentFilterDescriptor descriptor)
		{
			descriptors.add(descriptor);
			return this;
		}

		public Builder addDescriptors(@NonNull final Collection<DocumentFilterDescriptor> descriptors)
		{
			if (descriptors.isEmpty())
			{
				return this;
			}

			this.descriptors.addAll(descriptors);
			return this;
		}

		public Builder addDescriptors(@NonNull final DocumentFilterDescriptorsProvider provider)
		{
			addDescriptors(provider.getAll());
			return this;
		}
	}
}
