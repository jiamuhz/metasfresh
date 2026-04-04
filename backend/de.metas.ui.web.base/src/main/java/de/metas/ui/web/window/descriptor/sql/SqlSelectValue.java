package de.metas.ui.web.window.descriptor.sql;

import de.metas.util.Check;
import de.metas.util.StringUtils;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.ad.column.ColumnSql;

import javax.annotation.Nullable;
import java.util.Objects;

 

/**
 * SQL to be used in expressions like <code>SELECT ... 'this field's sql' ... FROM ...</code>
 */
@EqualsAndHashCode
@ToString
public class SqlSelectValue
{
	@Nullable private final String tableNameOrAlias;
	@Nullable private final String columnName;

	@Getter
	@Nullable private final ColumnSql virtualColumnSql;

	@Getter
	private final String columnNameAlias;

	@Builder(toBuilder = true)
	private SqlSelectValue(
			@Nullable final String tableNameOrAlias,
			@Nullable final String columnName,
			@Nullable final ColumnSql virtualColumnSql,
			@NonNull final String columnNameAlias)
	{
		this.columnNameAlias = columnNameAlias;
		this.tableNameOrAlias = StringUtils.trimBlankToNull(tableNameOrAlias);

		if (virtualColumnSql != null)
		{
			this.columnName = null;
			this.virtualColumnSql = tableNameOrAlias != null ? virtualColumnSql.withJoinOnTableNameOrAlias(tableNameOrAlias) : virtualColumnSql;
		}
		else
		{
			Check.assumeNotEmpty(columnName, "columnName is not empty");

			this.columnName = columnName;
			this.virtualColumnSql = null;
		}
	}

	public String toSqlStringWithColumnNameAlias()
	{
		return toSqlString() + " AS " + columnNameAlias;
	}

	public String toSqlString()
	{
		if (virtualColumnSql != null)
		{
			return virtualColumnSql.toSqlStringWrappedInBracketsIfNeeded();
		}
		else if (tableNameOrAlias != null)
		{
			return tableNameOrAlias + "." + columnName;
		}
		else
		{
			return columnName;
		}
	}

	public boolean isVirtualColumn()
	{
		return virtualColumnSql != null;
	}

	public SqlSelectValue withJoinOnTableNameOrAlias(final String tableNameOrAlias)
	{
		return !Objects.equals(this.tableNameOrAlias, tableNameOrAlias)
				? toBuilder().tableNameOrAlias(tableNameOrAlias).build()
				: this;
	}

	public SqlSelectValue withColumnNameAlias(@NonNull final String columnNameAlias)
	{
		return !Objects.equals(this.columnNameAlias, columnNameAlias)
				? toBuilder().columnNameAlias(columnNameAlias).build()
				: this;
	}
}
