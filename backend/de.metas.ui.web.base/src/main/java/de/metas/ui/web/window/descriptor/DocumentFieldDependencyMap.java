package de.metas.ui.web.window.descriptor;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Multimap;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * Immutable document field's dependencies map.
 *
 *
 */
public final class DocumentFieldDependencyMap
{
	public static Builder builder()
	{
		return new Builder();
	}

	private static final DocumentFieldDependencyMap EMPTY = new DocumentFieldDependencyMap();

	public enum DependencyType
	{
		/**
		 * Entity readonly
		 */
		DocumentReadonlyLogic,

		/**
		 * Field's Readonly logic
		 */
		ReadonlyLogic,
		/**
		 * Field's Display logic
		 */
		DisplayLogic,
		/**
		 * Field's Mandatory logic
		 */
		MandatoryLogic,
		/**
		 * Field's lookup values
		 */
		LookupValues,
		/**
		 * Field's value
		 */
		FieldValue,
	}

	public static final EnumSet<DependencyType> DEPENDENCYTYPES_DocumentLevel = EnumSet.of(DependencyType.DocumentReadonlyLogic);
	public static final EnumSet<DependencyType> DEPENDENCYTYPES_FieldLevel = EnumSet.complementOf(DEPENDENCYTYPES_DocumentLevel);

	public static final String DOCUMENT_Readonly = "$DocumentReadonly";
	public static final Set<String> DOCUMENT_ALL_FIELDS = ImmutableSet.of(DOCUMENT_Readonly);

	@FunctionalInterface
	public interface IDependencyConsumer
	{
		void consume(String dependentFieldName, DependencyType dependencyType);
	}

	/**
	 * Map: "dependency type" to "depends on field name" to list of "dependent field name"
	 */
	private final ImmutableMap<DependencyType, Multimap<String, String>> type2name2dependencies;

	private DocumentFieldDependencyMap(final Builder builder)
	{
		type2name2dependencies = builder.getType2Name2DependenciesMap();
	}

	/**
	 * Empty constructor
	 */
	private DocumentFieldDependencyMap()
	{
		type2name2dependencies = ImmutableMap.of();
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.addValue(type2name2dependencies)
				.toString();
	}

	public String toStringX()
	{
		final StringBuilder sb = new StringBuilder();

		for (final Map.Entry<DependencyType, Multimap<String, String>> l1 : type2name2dependencies.entrySet())
		{
			final DependencyType dependencyType = l1.getKey();
			final Multimap<String, String> name2dependencies = l1.getValue();
			for (final Map.Entry<String, String> l2 : name2dependencies.entries())
			{
				final String dependsOnFieldName = l2.getKey();
				final String fieldName = l2.getValue();

				if (sb.length() > 0)
				{
					sb.append("\n");
				}
				sb.append(dependencyType).append(": ").append(dependsOnFieldName).append(" -> ").append(fieldName);
			}
		}

		return sb.toString();
	}

	public void consumeForChangedFieldName(final String changedFieldName, final IDependencyConsumer consumer)
	{
		for (final DependencyType dependencyType : DependencyType.values())
		{
			final Multimap<String, String> name2dependencies = type2name2dependencies.get(dependencyType);
			if (name2dependencies == null || name2dependencies.isEmpty())
			{
				continue;
			}

			for (final String dependentFieldName : name2dependencies.get(changedFieldName))
			{
				consumer.consume(dependentFieldName, dependencyType);
			}
		}
	}

	//
	//
	//
	//
	//
	public static final class Builder
	{
		private final Map<DependencyType, ImmutableSetMultimap.Builder<String, String>> type2name2dependencies = new HashMap<>();

		private Builder()
		{
			super();
		}

		public DocumentFieldDependencyMap build()
		{
			if (type2name2dependencies.isEmpty())
			{
				return EMPTY;
			}
			return new DocumentFieldDependencyMap(this);
		}

		private ImmutableMap<DependencyType, Multimap<String, String>> getType2Name2DependenciesMap()
		{
			final ImmutableMap.Builder<DependencyType, Multimap<String, String>> builder = ImmutableMap.builder();
			for (final Entry<DependencyType, ImmutableSetMultimap.Builder<String, String>> e : type2name2dependencies.entrySet())
			{
				final DependencyType dependencyType = e.getKey();
				final Multimap<String, String> name2dependencies = e.getValue().build();
				if (name2dependencies.isEmpty())
				{
					continue;
				}

				builder.put(dependencyType, name2dependencies);
			}

			return builder.build();
		}

		public Builder add(
				@NonNull final String fieldName,
				@Nullable final Collection<String> dependsOnFieldNames,
				@NonNull final DependencyType dependencyType)
		{
			if (dependsOnFieldNames == null || dependsOnFieldNames.isEmpty())
			{
				return this;
			}

			final ImmutableSetMultimap.Builder<String, String> fieldName2dependsOnFieldNames
					= type2name2dependencies.computeIfAbsent(dependencyType, k -> ImmutableSetMultimap.builder());

			for (final String dependsOnFieldName : dependsOnFieldNames)
			{
				fieldName2dependsOnFieldNames.put(dependsOnFieldName, fieldName);
			}

			return this;
		}

		public Builder add(@Nullable final DocumentFieldDependencyMap dependencies)
		{
			if (dependencies == null || dependencies == EMPTY)
			{
				return this;
			}

			for (final Map.Entry<DependencyType, Multimap<String, String>> l1 : dependencies.type2name2dependencies.entrySet())
			{
				final DependencyType dependencyType = l1.getKey();

				final ImmutableSetMultimap.Builder<String, String> name2dependencies
						= type2name2dependencies.computeIfAbsent(dependencyType, k -> ImmutableSetMultimap.builder());

				name2dependencies.putAll(l1.getValue());
			}

			return this;
		}

	}
}
