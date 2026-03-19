package de.metas.ui.web.document.filter.sql;

import java.util.Collection;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableList;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;



/**
 * Immutable collection of {@link SqlDocumentFilterConverter}s indexed by filterId.
 *
 * To create new instances, please use {@link SqlDocumentFilterConverters}.
 *
 *
 *
 */
@Immutable
@ToString
@EqualsAndHashCode
public final class SqlDocumentFilterConvertersList
{
	/* package */static Builder builder()
	{
		return new Builder();
	}

	/* package */static final SqlDocumentFilterConvertersList EMPTY = new SqlDocumentFilterConvertersList(ImmutableList.of());

	private final ImmutableList<SqlDocumentFilterConverter> converters;

	private SqlDocumentFilterConvertersList(@NonNull final ImmutableList<SqlDocumentFilterConverter> converters)
	{
		this.converters = converters;
	}

	public SqlDocumentFilterConverter getConverterOrDefault(final String filterId, final SqlDocumentFilterConverter defaultConverter)
	{
		for (final SqlDocumentFilterConverter converter : converters)
		{
			if (converter.canConvert(filterId))
			{
				return converter;
			}
		}

		return defaultConverter;
	}

	public SqlDocumentFilterConverter withFallback(@NonNull final SqlDocumentFilterConverter fallback)
	{
		return SqlDocumentFilterConvertersListWithFallback.newInstance(this, fallback);
	}

	//
	//
	//
	//
	//
	public static class Builder
	{
		private ImmutableList.Builder<SqlDocumentFilterConverter> converters = null;

		private Builder()
		{
		}

		public SqlDocumentFilterConvertersList build()
		{
			if (converters == null)
			{
				return EMPTY;
			}

			final ImmutableList<SqlDocumentFilterConverter> converters = this.converters.build();
			if (converters.isEmpty())
			{
				return EMPTY;
			}

			return new SqlDocumentFilterConvertersList(converters);
		}

		public Builder converter(@NonNull final SqlDocumentFilterConverter converter)
		{
			if (converters == null)
			{
				converters = ImmutableList.builder();
			}
			converters.add(converter);
			return this;
		}

		public Builder converters(@NonNull final Collection<SqlDocumentFilterConverter> converters)
		{
			if (converters.isEmpty())
			{
				return this;
			}

			if (this.converters == null)
			{
				this.converters = ImmutableList.builder();
			}
			this.converters.addAll(converters);
			return this;
		}

	}
}
