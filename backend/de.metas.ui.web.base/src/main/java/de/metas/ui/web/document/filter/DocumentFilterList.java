package de.metas.ui.web.document.filter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.util.GuavaCollectors;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Stream;

 

@EqualsAndHashCode
@ToString
public class DocumentFilterList
{
	public static DocumentFilterList ofList(@Nullable final Collection<DocumentFilter> list)
	{
		return list != null && !list.isEmpty()
				? new DocumentFilterList(Maps.uniqueIndex(list, DocumentFilter::getFilterId))
				: EMPTY;
	}

	private static DocumentFilterList ofMap(@NonNull final Map<String, DocumentFilter> filtersById)
	{
		return !filtersById.isEmpty()
				? new DocumentFilterList(ImmutableMap.copyOf(filtersById))
				: EMPTY;
	}

	public static DocumentFilterList of(@NonNull final DocumentFilter filter)
	{
		return ofList(ImmutableList.of(filter));
	}

	public static DocumentFilterList ofNullable(@Nullable final DocumentFilter filter)
	{
		return filter != null ? of(filter) : EMPTY;
	}

	public static DocumentFilterList of(@NonNull final DocumentFilter... filters)
	{
		return ofList(Arrays.asList(filters));
	}

	public static Collector<DocumentFilter, ?, DocumentFilterList> toDocumentFilterList()
	{
		return GuavaCollectors.collectUsingListAccumulator(DocumentFilterList::ofList);
	}

	public static final DocumentFilterList EMPTY = new DocumentFilterList(ImmutableMap.of());

	private final ImmutableMap<String, DocumentFilter> filtersById;

	private DocumentFilterList(@NonNull final ImmutableMap<String, DocumentFilter> filtersById)
	{
		this.filtersById = filtersById;
	}

	public static boolean equals(final DocumentFilterList list1, final DocumentFilterList list2)
	{
		return Objects.equals(list1, list2);
	}

	public boolean isEmpty()
	{
		return filtersById.isEmpty();
	}

	public ImmutableList<DocumentFilter> toList()
	{
		return ImmutableList.copyOf(filtersById.values());
	}

	public Stream<DocumentFilter> stream()
	{
		return filtersById.values().stream();
	}

	public DocumentFilterList mergeWith(@NonNull final DocumentFilterList other)
	{
		if (isEmpty())
		{
			return other;
		}
		else if (other.isEmpty())
		{
			return this;
		}
		else
		{
			final LinkedHashMap<String, DocumentFilter> filtersByIdNew = new LinkedHashMap<>(this.filtersById);
			filtersByIdNew.putAll(other.filtersById);

			return ofMap(filtersByIdNew);
		}
	}

	public DocumentFilterList mergeWith(@NonNull final DocumentFilter filter)
	{
		if (isEmpty())
		{
			return of(filter);
		}
		else
		{
			final LinkedHashMap<String, DocumentFilter> filtersByIdNew = new LinkedHashMap<>(this.filtersById);
			filtersByIdNew.put(filter.getFilterId(), filter);

			return ofMap(filtersByIdNew);
		}
	}

	public DocumentFilterList mergeWithNullable(@Nullable final DocumentFilter filter)
	{
		return filter != null ? mergeWith(filter) : this;
	}

	public Optional<DocumentFilter> getFilterById(@NonNull final String filterId)
	{
		final DocumentFilter filter = getFilterByIdOrNull(filterId);
		return Optional.ofNullable(filter);
	}

	private DocumentFilter getFilterByIdOrNull(@NonNull final String filterId)
	{
		return filtersById.get(filterId);
	}

	public void forEach(@NonNull final Consumer<DocumentFilter> consumer)
	{
		filtersById.values().forEach(consumer);
	}

	@Nullable
	public String getParamValueAsString(final String filterId, final String parameterName)
	{
		final DocumentFilter filter = getFilterByIdOrNull(filterId);
		if (filter == null)
		{
			return null;
		}

		return filter.getParameterValueAsString(parameterName);
	}

	public int getParamValueAsInt(final String filterId, final String parameterName, final int defaultValue)
	{
		final DocumentFilter filter = getFilterByIdOrNull(filterId);
		if (filter == null)
		{
			return defaultValue;
		}

		return filter.getParameterValueAsInt(parameterName, defaultValue);
	}

	public boolean getParamValueAsBoolean(final String filterId, final String parameterName, final boolean defaultValue)
	{
		final DocumentFilter filter = getFilterByIdOrNull(filterId);
		if (filter == null)
		{
			return defaultValue;
		}

		return filter.getParameterValueAsBoolean(parameterName, defaultValue);
	}
}
