package de.metas.ui.web.view.descriptor;

import lombok.Builder;
import lombok.Value;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.impl.TypedSqlQueryFilter;

import javax.annotation.Nullable;
import java.util.Objects;

 

/**
 * Target table: rows original table
 */
@Value
public class SqlViewRowsWhereClause
{
	public static SqlViewRowsWhereClause noRecords()
	{
		return NO_RECORDS;
	}

	private static final SqlViewRowsWhereClause NO_RECORDS = builder().noRecords(true).build();
	private static final SqlAndParams SQL_ALWAYS_FALSE = SqlAndParams.of("1=2");

	boolean noRecords;
	@Nullable SqlAndParams rowsPresentInViewSelection;
	boolean isRowsNotPresentInViewSelection;
	@Nullable SqlAndParams rowsPresentInTable;
	@Nullable SqlAndParams rowsMatchingFilter;

	@Builder(toBuilder = true)
	private SqlViewRowsWhereClause(
			final boolean noRecords,
			@Nullable final SqlAndParams rowsPresentInViewSelection,
			final boolean isRowsNotPresentInViewSelection,
			@Nullable final SqlAndParams rowsPresentInTable,
			@Nullable final SqlAndParams rowsMatchingFilter)
	{
		this.noRecords = noRecords;
		this.rowsPresentInViewSelection = SqlAndParams.emptyToNull(rowsPresentInViewSelection);
		this.isRowsNotPresentInViewSelection = isRowsNotPresentInViewSelection;
		this.rowsPresentInTable = SqlAndParams.emptyToNull(rowsPresentInTable);
		this.rowsMatchingFilter = SqlAndParams.emptyToNull(rowsMatchingFilter);
	}

	public boolean isNoRecords()
	{
		return noRecords || toSqlAndParams() == SQL_ALWAYS_FALSE;
	}

	public SqlAndParams toSqlAndParams()
	{
		if (noRecords)
		{
			return SQL_ALWAYS_FALSE;
		}
		else
		{
			SqlAndParams viewSelectionWhereClause;
			if (rowsPresentInViewSelection == null || rowsPresentInViewSelection.isEmpty())
			{
				viewSelectionWhereClause = null;
			}
			else if (isRowsNotPresentInViewSelection)
			{
				viewSelectionWhereClause = rowsPresentInViewSelection.negate();
			}
			else
			{
				viewSelectionWhereClause = rowsPresentInViewSelection;
			}

			return SqlAndParams.andNullables(viewSelectionWhereClause, rowsPresentInTable, rowsMatchingFilter)
					.orElse(SQL_ALWAYS_FALSE);
		}
	}

	public String toSqlString()
	{
		return toSqlAndParams().toSqlStringInlineParams();
	}

	public <T> IQueryFilter<T> toQueryFilter()
	{
		final SqlAndParams sqlAndParams = toSqlAndParams();
		return TypedSqlQueryFilter.of(sqlAndParams.getSql(), sqlAndParams.getSqlParams());
	}

	public SqlViewRowsWhereClause withRowsNotPresentInViewSelection()
	{
		return !this.isRowsNotPresentInViewSelection
				? toBuilder().isRowsNotPresentInViewSelection(true).build()
				: this;
	}

	public SqlViewRowsWhereClause withRowsMatchingFilter(@Nullable final SqlAndParams rowsMatchingFilter)
	{
		return !Objects.equals(this.rowsMatchingFilter, rowsMatchingFilter)
				? toBuilder().rowsMatchingFilter(rowsMatchingFilter).build()
				: this;
	}
}
