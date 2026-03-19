package de.metas.ui.web.document.filter.sql;

import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.window.model.sql.SqlOptions;
import lombok.NonNull;
import lombok.ToString;

 

/**
 * Composite {@link SqlDocumentFilterConverter} implementation which internally uses a converters list and a default converter.
 *
 * 
 *
 */
@ToString
/* package */final class SqlDocumentFilterConvertersListWithFallback implements SqlDocumentFilterConverter
{
	public static SqlDocumentFilterConvertersListWithFallback newInstance(final SqlDocumentFilterConvertersList converters, final SqlDocumentFilterConverter defaultConverter)
	{
		return new SqlDocumentFilterConvertersListWithFallback(converters, defaultConverter);
	}

	private final SqlDocumentFilterConvertersList converters;
	private final SqlDocumentFilterConverter defaultConverter;

	private SqlDocumentFilterConvertersListWithFallback(@NonNull final SqlDocumentFilterConvertersList converters, @NonNull final SqlDocumentFilterConverter defaultConverter)
	{
		this.converters = converters;
		this.defaultConverter = defaultConverter;
	}

	@Override
	public boolean canConvert(final String filterId)
	{
		return true;
	}

	@Override
	public FilterSql getSql(
			@NonNull final DocumentFilter filter,
			@NonNull final SqlOptions sqlOpts,
			@NonNull final SqlDocumentFilterConverterContext context)
	{
		// Find the effective converter to be used for given filter
		final String filterId = filter.getFilterId();
		final SqlDocumentFilterConverter effectiveConverter = converters.getConverterOrDefault(filterId, defaultConverter);

		// Convert the filter to SQL using the effective converter
		return effectiveConverter.getSql(filter, sqlOpts, context);
	}
}
