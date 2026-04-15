package de.metas.ui.web.document.filter.provider.standard;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import lombok.NonNull;
import lombok.ToString;


@ToString
public final class FacetFilterViewCacheMap
{
	public static FacetFilterViewCacheMap newInstance()
	{
		return new FacetFilterViewCacheMap();
	}

	private final ConcurrentHashMap<String, FacetFilterViewCache> cachesById = new ConcurrentHashMap<>();

	private FacetFilterViewCacheMap()
	{
	}

	public FacetFilterViewCache computeIfAbsent(
			@NonNull final String id,
			@NonNull final Supplier<FacetFilterViewCache> supplier)
	{
		return cachesById.computeIfAbsent(id, k -> supplier.get());
	}
}
