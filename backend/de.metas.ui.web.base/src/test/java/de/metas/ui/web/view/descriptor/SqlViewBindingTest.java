package de.metas.ui.web.view.descriptor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterDecorator;
import de.metas.ui.web.view.descriptor.SqlViewBinding.Builder;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;

 

public class SqlViewBindingTest
{
	@Test
	public void createSqlViewBinding_Has_Null_Decorator_By_Default()
	{
		final SqlViewBinding sqlViewBinding = createMinimalBuilder().build();

		assertThat(sqlViewBinding).isNotNull();
		assertThat(sqlViewBinding.getFilterConverterDecorator()).isNotPresent();
	}

	@Test
	public void createSqlViewBinding_With_Custom_FilterConverterDecoratorProvider()
	{
		final CustomSqlDocumentFilterConverterDecoratorProvider customDecoratorProvider = new CustomSqlDocumentFilterConverterDecoratorProvider();

		final SqlViewBinding sqlViewBinding = createMinimalBuilder()
				.filterConverterDecorator(customDecoratorProvider)
				.build();

		assertThat(sqlViewBinding).isNotNull();
		assertThat(sqlViewBinding.getFilterConverterDecorator().get()).isSameAs(customDecoratorProvider);
	}

	private Builder createMinimalBuilder()
	{
		final SqlViewRowFieldBinding field = SqlViewRowFieldBinding.builder()
				.fieldName("fieldName")
				.widgetType(DocumentFieldWidgetType.Amount)
				.sqlValueClass(String.class)
				.fieldLoader((rs, adLanguage) -> "dummyFieldValue")
				.keyColumn(true)
				.sqlSelectValue(SqlSelectValue.builder()
						.columnName("fieldName")
						.columnNameAlias("fieldName")
						.build())
				.build();

		return SqlViewBinding.builder()
				.tableName("dummyTable")
				.field(field)
				.displayFieldNames("displayFieldName");
	}

	public static class CustomSqlDocumentFilterConverterDecoratorProvider implements SqlDocumentFilterConverterDecorator
	{
		@Override
		public WindowId getWindowId()
		{
			return WindowId.of(23);
		}

		@Override
		public SqlDocumentFilterConverter decorate(SqlDocumentFilterConverter converter)
		{
			throw new UnsupportedOperationException("The decorate method is not supposed to be called in this test");
		}

	}

}
