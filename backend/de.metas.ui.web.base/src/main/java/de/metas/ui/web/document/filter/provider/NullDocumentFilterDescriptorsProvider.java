package de.metas.ui.web.document.filter.provider;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import de.metas.ui.web.document.filter.DocumentFilterDescriptor;

 

@Immutable
public final class NullDocumentFilterDescriptorsProvider implements DocumentFilterDescriptorsProvider
{
	public static final transient NullDocumentFilterDescriptorsProvider instance = new NullDocumentFilterDescriptorsProvider();

	public static boolean isNull(@Nullable final DocumentFilterDescriptorsProvider provider)
	{
		return provider == null || provider == instance;
	}

	public static boolean isNotNull(@Nullable final DocumentFilterDescriptorsProvider provider)
	{
		return !isNull(provider);
	}

	private NullDocumentFilterDescriptorsProvider()
	{
	}

	@Override
	public Collection<DocumentFilterDescriptor> getAll()
	{
		return ImmutableList.of();
	}

	@Override
	public DocumentFilterDescriptor getByFilterIdOrNull(String filterId)
	{
		return null;
	}

	/**
	 * NOTE: required for snapshot testing
	 */
	@JsonValue
	private Map<String, String> toJson()
	{
		return ImmutableMap.of();
	}
}
