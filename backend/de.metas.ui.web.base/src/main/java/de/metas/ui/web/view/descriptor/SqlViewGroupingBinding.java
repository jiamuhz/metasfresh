package de.metas.ui.web.view.descriptor;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;

 

@Builder
@ToString
public final class SqlViewGroupingBinding
{
	@Singular("groupBy")
	private final ImmutableSet<String> groupByFieldNames;
	@Singular("columnSql")
	private final ImmutableMap<String, SqlSelectValue> columnSqlByFieldName;

	@NonNull
	@Default
	@Getter
	private final SqlViewRowIdsConverter rowIdsConverter = SqlViewRowIdsConverters.TO_INT_STRICT;

	public ImmutableSet<String> getGroupByFieldNames()
	{
		return groupByFieldNames;
	}

	public boolean isGroupBy(final String fieldName)
	{
		return groupByFieldNames.contains(fieldName);
	}

	public SqlSelectValue getColumnSqlByFieldName(final String fieldName)
	{
		return columnSqlByFieldName.get(fieldName);
	}

	public boolean isAggregated(final String fieldName)
	{
		return columnSqlByFieldName.containsKey(fieldName);
	}

}
