package de.metas.ui.web.view.descriptor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class SqlViewRowsWhereClauseTest
{
	@Nested
	public class isNoRecords
	{
		@Test
		public void noRecords()
		{
			final SqlViewRowsWhereClause whereClause = SqlViewRowsWhereClause.noRecords();
			assertThat(whereClause.isNoRecords()).isTrue();
		}

		@Test
		public void noSqls()
		{
			final SqlViewRowsWhereClause whereClause = SqlViewRowsWhereClause.builder().build();

			assertThat(whereClause.isNoRecords()).isTrue();
		}

		@Test
		public void someEmptySqls()
		{
			final SqlViewRowsWhereClause whereClause = SqlViewRowsWhereClause.builder()
					.rowsPresentInTable(SqlAndParams.of("    "))
					.build();

			assertThat(whereClause.isNoRecords()).isTrue();
		}

		@Test
		public void rowsPresentInViewSelection_set()
		{
			final SqlViewRowsWhereClause whereClause = SqlViewRowsWhereClause.builder()
					.rowsPresentInViewSelection(SqlAndParams.of("test"))
					.build();

			assertThat(whereClause.isNoRecords()).isFalse();
		}

		@Test
		public void rowsPresentInTable_set()
		{
			final SqlViewRowsWhereClause whereClause = SqlViewRowsWhereClause.builder()
					.rowsPresentInTable(SqlAndParams.of("test"))
					.build();

			assertThat(whereClause.isNoRecords()).isFalse();
		}

		@Test
		public void rowsMatchingFilter_set()
		{
			final SqlViewRowsWhereClause whereClause = SqlViewRowsWhereClause.builder()
					.rowsMatchingFilter(SqlAndParams.of("test"))
					.build();

			assertThat(whereClause.isNoRecords()).isFalse();
		}
	}
}
