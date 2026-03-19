package de.metas.ui.web.window.model.sql;

import de.metas.util.Check;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.exceptions.AdempiereException;



/**
 * Various instructions to SQL code generators
 */
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class SqlOptions
{
	/**
	 * advice the SQL code generators to use table alias (e.g. "master") instead of fully qualified table name
	 */
	public static SqlOptions usingTableAlias(@NonNull final String sqlTableAlias)
	{
		if (USE_TABLE_ALIAS_MASTER.tableAlias.equals(sqlTableAlias))
		{
			return USE_TABLE_ALIAS_MASTER;
		}

		return builder()
				.useTableAlias(true)
				.tableAlias(sqlTableAlias)
				.build();
	}

	/**
	 * advice the SQL code generators to use fully qualified table name instead of table alias
	 */
	public static SqlOptions usingTableName(final String tableName)
	{
		return SqlOptions.builder()
				.useTableAlias(false)
				.tableName(tableName)
				.build();
	}

	private static final SqlOptions USE_TABLE_ALIAS_MASTER = SqlOptions.builder().useTableAlias(true).tableAlias("master").build();

	private final boolean useTableAlias;
	private final String tableAlias;
	private final String tableName;

	@Builder
	private SqlOptions(
			final boolean useTableAlias,
			final String tableAlias,
			final String tableName)
	{
		this.useTableAlias = useTableAlias;
		if (useTableAlias)
		{
			Check.assumeNotEmpty(tableAlias, "tableAlias is not empty");
			this.tableAlias = tableAlias;
			this.tableName = null;
		}
		else
		{
			Check.assumeNotEmpty(tableName, "tableName is not empty");
			this.tableAlias = null;
			this.tableName = tableName;
		}
	}

	public boolean isUseTableAlias()
	{
		return useTableAlias;
	}

	public String getTableAlias()
	{
		if (!useTableAlias)
		{
			throw new AdempiereException("tableAlias is not available for " + this);
		}
		return tableAlias;
	}

	public String getTableNameOrAlias()
	{
		return useTableAlias ? tableAlias : tableName;
	}
}
