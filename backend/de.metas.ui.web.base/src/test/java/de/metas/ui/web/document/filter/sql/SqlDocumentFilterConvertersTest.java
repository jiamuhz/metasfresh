package de.metas.ui.web.document.filter.sql;

import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.descriptor.sql.SqlEntityBinding;
import de.metas.ui.web.window.model.sql.SqlOptions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



public class SqlDocumentFilterConvertersTest
{
	private final static SqlDocumentFilterConverter customConverter = new SqlDocumentFilterConverter()
	{
		@Override
		public boolean canConvert(final String filterId)
		{
			return true;
		}

		/**
		 * This method won't be called throughout our test
		 */
		@Override
		public FilterSql getSql(final DocumentFilter filter, final SqlOptions sqlOpts, final SqlDocumentFilterConverterContext context)
		{
			throw new UnsupportedOperationException();
		}
	};

	@Test
	public void createEntityBindingEffectiveConverter_uses_decorator_of_entityBinding()
	{
		final SqlEntityBinding sqlEntityBinding = Mockito.mock(SqlEntityBinding.class);
		Mockito.doReturn(Optional.of(new CustomDocumentFilterConverterDecorator()))
				.when(sqlEntityBinding)
				.getFilterConverterDecorator();
		Mockito.doReturn(SqlDocumentFilterConverters.emptyList())
				.when(sqlEntityBinding)
				.getFilterConverters();

		final SqlDocumentFilterConverter result = SqlDocumentFilterConverters.createEntityBindingEffectiveConverter(sqlEntityBinding);
		assertThat(result).isNotNull();
		assertThat(result)
				.as("Our sqlEntityBinding shall return a filterConverterDecoratorProvider that in turn provides exactly the customConverter from this test")
				.isSameAs(customConverter);
	}

	private static class CustomDocumentFilterConverterDecorator implements SqlDocumentFilterConverterDecorator
	{
		@Override
		public SqlDocumentFilterConverter decorate(final SqlDocumentFilterConverter converter)
		{
			return customConverter;
		}

		@Override
		public WindowDocumentTypeId getWindowId()
		{
			throw new UnsupportedOperationException("getWindowId is not supposed to be called within this test");
		}
	}
}
