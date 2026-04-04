 

package de.metas.ui.web.accounting.filters;

import com.google.common.collect.ImmutableSet;
import com.jgoodies.common.base.Objects;
import de.metas.acct.api.impl.ElementValueId;
import de.metas.elementvalue.ElementValueService;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.sql.FilterSql;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Check;
import lombok.NonNull;
import org.compiere.SpringContextHolder;
import org.compiere.model.I_Fact_Acct;
import org.compiere.util.DB;

public class FactAcctFilterConverter implements SqlDocumentFilterConverter
{
	public static final transient FactAcctFilterConverter instance = new FactAcctFilterConverter();

	static final String FILTER_ID = "accountId";

	public static final String PARAM_ACCOUNT_VALUE_FROM = "AccountValueFrom";
	public static final String PARAM_ACCOUNT_VALUE_TO = "AccountValueTo";

	private FactAcctFilterConverter()
	{
	}

	@Override
	public boolean canConvert(final String filterId)
	{
		return Objects.equals(filterId, FILTER_ID);
	}

	@Override
	public FilterSql getSql(
			@NonNull final DocumentFilter filter,
			@NonNull final SqlOptions sqlOpts,
			final SqlDocumentFilterConverterContext ignored)
	{
		final String accountValueFrom = filter.getParameterValueAsString(PARAM_ACCOUNT_VALUE_FROM, null);
		final String accountValueTo = filter.getParameterValueAsString(PARAM_ACCOUNT_VALUE_TO, null);

		Check.assumeNotEmpty(accountValueFrom, "AccountValueFrom not empty");
		Check.assumeNotEmpty(accountValueTo, "AccountValueTo not empty");

		ElementValueService elementValueService = SpringContextHolder.instance.getBean(ElementValueService.class);
		final ImmutableSet<ElementValueId> ids = elementValueService.getElementValueIdsBetween(accountValueFrom, accountValueTo);

		if (ids.isEmpty())
		{
			return FilterSql.ALLOW_NONE;
		}

		final String columnName = sqlOpts.getTableNameOrAlias() + "." + I_Fact_Acct.COLUMNNAME_Account_ID;
		return FilterSql.ofWhereClause("(" + DB.buildSqlList(columnName, ids) + ")");
	}
}
