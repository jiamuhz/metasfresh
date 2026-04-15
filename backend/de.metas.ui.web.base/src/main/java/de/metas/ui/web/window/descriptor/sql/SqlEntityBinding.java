package de.metas.ui.web.window.descriptor.sql;

import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterDecorator;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverters;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConvertersList;
import de.metas.ui.web.view.descriptor.SqlAndParams;
import lombok.NonNull;
import org.adempiere.ad.expression.api.IStringExpression;

import java.util.Optional;
import java.util.regex.Pattern;



public interface SqlEntityBinding
{
	String getTableName();

	String getTableAlias();

	IStringExpression getSqlWhereClause();

	/**
	 * @return field binding or throws exception in case it was not found
	 */
	SqlEntityFieldBinding getFieldByFieldName(String fieldName);

	/**
	 * @return SQL expression to be used when ordering by given field; if the field was not found and exception will be thrown
	 */
	default SqlOrderByValue getFieldOrderBy(String fieldName)
	{
		return getFieldByFieldName(fieldName).getSqlOrderBy();
	}

	default DocumentFilterDescriptorsProvider getFilterDescriptorsProvider()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * @return registered document filter to SQL converters
	 */
	default SqlDocumentFilterConvertersList getFilterConverters()
	{
		return SqlDocumentFilterConverters.emptyList();
	}

	default Optional<SqlDocumentFilterConverterDecorator> getFilterConverterDecorator()
	{
		return Optional.empty();
	}

	default String replaceTableNameWithTableAlias(final String sql)
	{
		final String tableName = getTableName();
		final String tableAlias = getTableAlias();
		return replaceTableNameWithTableAlias(sql, tableName, tableAlias);
	}

	default String replaceTableNameWithTableAlias(final String sql, @NonNull final String tableAlias)
	{
		final String tableName = getTableName();
		return replaceTableNameWithTableAlias(sql, tableName, tableAlias);
	}

	default SqlAndParams replaceTableNameWithTableAlias(final SqlAndParams sql, @NonNull final String tableAlias)
	{
		return SqlAndParams.of(
				replaceTableNameWithTableAlias(sql.getSql(), tableAlias),
				sql.getSqlParams());
	}

	static String replaceTableNameWithTableAlias(final String sql, @NonNull final String tableName, @NonNull final String tableAlias)
	{
		if (sql == null || sql.isEmpty())
		{
			return sql;
		}

		final String matchTableNameIgnoringCase = "(?i)" + Pattern.quote(tableName + ".");
		final String sqlFixed = sql.replaceAll(matchTableNameIgnoringCase, tableAlias + ".");
		return sqlFixed;
	}
}
