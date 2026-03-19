package de.metas.ui.web.window.descriptor;

import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;

import java.util.Optional;

  
/**
 * Provides {@link LookupDescriptor} for a given {@link LookupScope}.
 *
 *
 */
@FunctionalInterface
public interface LookupDescriptorProvider
{
	enum LookupScope
	{
		DocumentField, DocumentFilter
	}

	/**
	 * @return lookup descriptor or null
	 */
	Optional<LookupDescriptor> provideForScope(LookupScope scope);

	default Optional<LookupDescriptor> provide() {return provideForScope(LookupScope.DocumentField);}

	default Optional<LookupDescriptor> provideForFilter() {return provideForScope(LookupScope.DocumentFilter);}

	default boolean isNumericKey()
	{
		return provide()
				.map(LookupDescriptor::isNumericKey)
				.orElse(false);
	}

	default Optional<String> getTableName()
	{
		return provide().flatMap(LookupDescriptor::getTableName);
	}

	default Optional<LookupSource> getLookupSourceType()
	{
		return provide().map(LookupDescriptor::getLookupSourceType);
	}

}
