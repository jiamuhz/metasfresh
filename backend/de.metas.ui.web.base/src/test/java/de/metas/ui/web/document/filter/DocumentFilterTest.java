package de.metas.ui.web.document.filter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;


public class DocumentFilterTest
{
	@Test
	public void sqlFilter()
	{
		final DocumentFilter filter = DocumentFilter.builder()
				.setFilterId("filter1")
				.setCaption("caption1")
				.setFacetFilter(true)
				.addParameter(DocumentFilterParam.ofSqlWhereClause(true, "SQL WHERE CLAUSE"))
				.build();

		assertThat(filter.getParameters())
				.containsExactly(DocumentFilterParam.ofSqlWhereClause(true, "SQL WHERE CLAUSE"));
	}

	@Nested
	public class equalsTests
	{
		@Test
		public void testEquals()
		{
			final DocumentFilter filter1 = DocumentFilter.builder()
					.setFilterId("filter1")
					.setCaption("caption1")
					.setFacetFilter(true)
					.addParameter(DocumentFilterParam.builder()
							.setFieldName("param1")
							.setOperator(Operator.BETWEEN)
							.setValue("value1")
							.setValueTo("value2")
							.build())
					.build();

			final DocumentFilter filter2 = DocumentFilter.builder()
					.setFilterId("filter1")
					.setCaption("caption1")
					.setFacetFilter(true)
					.addParameter(DocumentFilterParam.builder()
							.setFieldName("param1")
							.setOperator(Operator.BETWEEN)
							.setValue("value1")
							.setValueTo("value2")
							.build())
					.build();

			assertThat(filter1)
					.isEqualTo(filter2);
		}

		@Test
		public void testNotEquals()
		{
			final DocumentFilter filter1 = DocumentFilter.builder()
					.setFilterId("filter1")
					.setCaption("caption1")
					.setFacetFilter(true)
					.addParameter(DocumentFilterParam.builder()
							.setFieldName("param1")
							.setOperator(Operator.BETWEEN)
							.setValue("value1")
							.setValueTo("value2")
							.build())
					.build();

			final DocumentFilter filter2 = DocumentFilter.builder()
					.setFilterId("filter2")
					.setCaption("caption1")
					.setFacetFilter(true)
					.addParameter(DocumentFilterParam.builder()
							.setFieldName("param1")
							.setOperator(Operator.BETWEEN)
							.setValue("value1")
							.setValueTo("value2")
							.build())
					.build();

			assertThat(filter1)
					.isNotEqualTo(filter2);
		}
	}
}
