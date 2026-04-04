package de.metas.ui.web.window.descriptor.sql;

import de.metas.printing.esb.base.util.Check;
import de.metas.util.StringUtils;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.ConstantStringExpression;
import org.compiere.util.Evaluatee;

import javax.annotation.Nullable;
import java.util.Objects;



/**
 * 简单理解：当你的订单行存储了 C_BPartner_ID = 123，SqlSelectDisplayValue 负责生成类似这样的 SQL：
 *
 * (SELECT Name FROM C_BPartner WHERE C_BPartner_ID = 123) AS BPartnerName
 */

@EqualsAndHashCode
@ToString
public class SqlSelectDisplayValue
{
	@Nullable private final String joinOnTableNameOrAlias;
	@NonNull private final String joinOnColumnName;
	@Nullable private final SqlForFetchingLookupById sqlExpression;
	@Getter
	@NonNull private final String columnNameAlias;

	@Builder(toBuilder = true)
	private SqlSelectDisplayValue(
			@Nullable final String joinOnTableNameOrAlias,
			@NonNull final String joinOnColumnName,
			@Nullable final SqlForFetchingLookupById sqlExpression,
			@NonNull final String columnNameAlias)
	{
		this.joinOnTableNameOrAlias = StringUtils.trimBlankToNull(joinOnTableNameOrAlias);
		this.joinOnColumnName = joinOnColumnName;
		this.sqlExpression = sqlExpression;
		this.columnNameAlias = columnNameAlias;
	}

	/**
	 * @return (sql expression) AS columnNameAlias
	 */
	public String toSqlStringWithColumnNameAlias(@NonNull final Evaluatee ctx)
	{
		return toStringExpressionWithColumnNameAlias().evaluate(ctx, OnVariableNotFound.Fail);
	}

	/**
	 * @return (sql expression) AS columnNameAlias
	 */
	public IStringExpression toStringExpressionWithColumnNameAlias()
	{
		return IStringExpression.composer()
				.append("(").append(toStringExpression()).append(") AS ").append(columnNameAlias)
				.build();
	}

	public IStringExpression toStringExpression()
	{
		final String joinOnColumnNameFQ = !Check.isEmpty(joinOnTableNameOrAlias)
				? joinOnTableNameOrAlias + "." + joinOnColumnName
				: joinOnColumnName;

		if (sqlExpression == null)
		{
			return ConstantStringExpression.of(joinOnColumnNameFQ);
		}
		else
		{
			return sqlExpression.toStringExpression(joinOnColumnNameFQ);
		}
	}

	public IStringExpression toOrderByStringExpression()
	{
		final String joinOnColumnNameFQ = !Check.isEmpty(joinOnTableNameOrAlias)
				? joinOnTableNameOrAlias + "." + joinOnColumnName
				: joinOnColumnName;

		if (sqlExpression == null)
		{
			return ConstantStringExpression.of(joinOnColumnNameFQ);
		}
		else
		{
			return sqlExpression.toOrderByStringExpression(joinOnColumnNameFQ);
		}
	}

	public SqlSelectDisplayValue withJoinOnTableNameOrAlias(@Nullable final String joinOnTableNameOrAlias)
	{
		return !Objects.equals(this.joinOnTableNameOrAlias, joinOnTableNameOrAlias)
				? toBuilder().joinOnTableNameOrAlias(joinOnTableNameOrAlias).build()
				: this;
	}

	public String toSqlOrderByUsingColumnNameAlias()
	{
		final String columnNameAliasFQ = joinOnTableNameOrAlias != null ? joinOnTableNameOrAlias + "." + columnNameAlias : columnNameAlias;

		if (sqlExpression != null)
		{
			return columnNameAliasFQ + "[" + sqlExpression.getNameSqlArrayIndex() + "]";
		}
		else
		{
			return columnNameAliasFQ;
		}
	}
}
