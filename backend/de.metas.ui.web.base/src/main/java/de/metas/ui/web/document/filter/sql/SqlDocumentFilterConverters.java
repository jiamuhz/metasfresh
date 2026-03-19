package de.metas.ui.web.document.filter.sql;

import de.metas.ui.web.window.descriptor.sql.SqlEntityBinding;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

 

/**
 * Static methods to create and manipulate {@link SqlDocumentFilterConverter} instances.
 *
 *
 */
@UtilityClass
public final class SqlDocumentFilterConverters
{
	/**
	 * Convenient method to create the effective converter instance from the given <code>entityBinding</code>.
	 * It will use
	 * <ul>
	 * <li>entity binding's registered converters list: {@link SqlEntityBinding#getFilterConverters()}
	 * <li>{@link SqlDefaultDocumentFilterConverter} as a fallback/default converter.
	 * </ul>
	 */
	public static SqlDocumentFilterConverter createEntityBindingEffectiveConverter(@NonNull final SqlEntityBinding entityBinding)
	{
		final SqlDocumentFilterConverter converters = entityBinding.getFilterConverters()
				.withFallback(SqlDefaultDocumentFilterConverter.newInstance(entityBinding));

		final SqlDocumentFilterConverterDecorator decoratorOrNull = entityBinding.getFilterConverterDecorator().orElse(null);
		return decoratorOrNull != null
				? decoratorOrNull.decorate(converters)
				: converters;
	}

	public static SqlDocumentFilterConvertersList.Builder listBuilder()
	{
		return SqlDocumentFilterConvertersList.builder();
	}

	public static SqlDocumentFilterConvertersList emptyList()
	{
		return SqlDocumentFilterConvertersList.EMPTY;
	}
}
