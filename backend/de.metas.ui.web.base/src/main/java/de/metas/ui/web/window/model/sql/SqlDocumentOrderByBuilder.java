package de.metas.ui.web.window.model.sql;

import de.metas.ui.web.view.descriptor.SqlAndParams;
import de.metas.ui.web.view.descriptor.SqlAndParamsExpression;
import de.metas.ui.web.window.descriptor.sql.SqlEntityBinding;
import de.metas.ui.web.window.descriptor.sql.SqlOrderByValue;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.NonNull;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.CompositeStringExpression;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Optional;



public class SqlDocumentOrderByBuilder
{
	public static SqlDocumentOrderByBuilder newInstance(final SqlEntityBinding entityBinding)
	{
		return new SqlDocumentOrderByBuilder(entityBinding::getFieldOrderBy);
	}

	public static SqlDocumentOrderByBuilder newInstance(final SqlOrderByBindings bindings)
	{
		return new SqlDocumentOrderByBuilder(bindings);
	}

	@FunctionalInterface
	public interface SqlOrderByBindings
	{
		SqlOrderByValue getFieldOrderBy(String fieldName);
	}

	private final SqlOrderByBindings bindings;
	private String joinOnTableNameOrAlias;
	private boolean useColumnNameAlias;
	private ArrayList<SqlAndParams> beforeOrderBys;

	private SqlDocumentOrderByBuilder(@NonNull final SqlOrderByBindings bindings)
	{
		this.bindings = bindings;
	}

	public SqlDocumentOrderByBuilder joinOnTableNameOrAlias(final String joinOnTableNameOrAlias)
	{
		this.joinOnTableNameOrAlias = joinOnTableNameOrAlias;
		return this;
	}

	public SqlDocumentOrderByBuilder useColumnNameAlias(final boolean useColumnNameAlias)
	{
		this.useColumnNameAlias = useColumnNameAlias;
		return this;
	}

	public SqlDocumentOrderByBuilder beforeOrderBy(@Nullable final SqlAndParams beforeOrderBy)
	{
		if (beforeOrderBy != null && !beforeOrderBy.isEmpty())
		{
			if (this.beforeOrderBys == null)
			{
				this.beforeOrderBys = new ArrayList<>();
			}
			this.beforeOrderBys.add(beforeOrderBy);
		}
		return this;
	}

	public SqlDocumentOrderByBuilder beforeOrderBy(@Nullable final String beforeOrderBy)
	{
		if (beforeOrderBy != null)
		{
			beforeOrderBy(SqlAndParams.of(beforeOrderBy));
		}
		return this;
	}

	/**
	 * @return SQL order by (e.g. Column1 ASC, Column2 DESC)
	 */
	public Optional<SqlAndParamsExpression> buildSqlOrderBy(final DocumentQueryOrderByList orderBys)
	{
		if (orderBys.isEmpty())
		{
			return Optional.empty();
		}

		final SqlAndParamsExpression.Builder result = SqlAndParamsExpression.builder();

		//
		// First ORDER BYs
		if (beforeOrderBys != null && !beforeOrderBys.isEmpty())
		{
			for (final SqlAndParams beforeOrderBy : beforeOrderBys)
			{
				if (!result.isEmpty())
				{
					result.append(", ");
				}
				result.append(beforeOrderBy);
			}
		}

		//
		// Actual ORDER BY columns
		{
			final IStringExpression orderBysExpression = orderBys
					.stream()
					.map(this::buildSqlOrderBy)
					.filter(sql -> sql != null && !sql.isNullExpression())
					.collect(IStringExpression.collectJoining(", "));
			if (orderBysExpression != null && !orderBysExpression.isNullExpression())
			{
				if (!result.isEmpty())
				{
					result.append(", ");
				}
				result.append(orderBysExpression);
			}
		}

		return !result.isEmpty()
				? Optional.of(result.build())
				: Optional.empty();
	}

	private IStringExpression buildSqlOrderBy(final DocumentQueryOrderBy orderBy)
	{
		final String fieldName = orderBy.getFieldName();
		final SqlOrderByValue sqlExpression = bindings.getFieldOrderBy(fieldName);
		return buildSqlOrderBy(sqlExpression, orderBy.isAscending(), orderBy.isNullsLast());
	}

	private IStringExpression buildSqlOrderBy(
			final SqlOrderByValue orderBy,
			final boolean ascending,
			final boolean nullsLast)
	{
		if (orderBy.isNull())
		{
			return IStringExpression.NULL;
		}

		final CompositeStringExpression.Builder sql = IStringExpression.composer();
		if (useColumnNameAlias)
		{
			sql.append(orderBy.withJoinOnTableNameOrAlias(joinOnTableNameOrAlias).toSqlUsingColumnNameAlias());
		}
		else
		{
			sql.append("(").append(orderBy.withJoinOnTableNameOrAlias(joinOnTableNameOrAlias).toSourceSqlExpression()).append(")");
		}

		return sql.append(ascending ? " ASC" : " DESC")
				.append(nullsLast ? " NULLS LAST" : " NULLS FIRST")
				.build();
	}

}
