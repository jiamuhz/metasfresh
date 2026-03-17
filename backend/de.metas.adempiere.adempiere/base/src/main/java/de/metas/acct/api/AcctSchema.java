package de.metas.acct.api;

import com.google.common.collect.ImmutableSet;
import de.metas.acct.api.impl.AcctSchemaPeriodControl;
import de.metas.currency.CurrencyPrecision;
import de.metas.money.CurrencyId;
import de.metas.organization.OrgId;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
import org.adempiere.service.ClientId;

/** */

@Value
@Builder
@ToString(of = { "name", "currencyId", "costing" })
public class AcctSchema
{
	@NonNull
	AcctSchemaId id;
	@NonNull
	ClientId clientId;
	@NonNull
	OrgId orgId;

	@NonNull
	String name;

	@NonNull
	CurrencyId currencyId;
	@NonNull
	CurrencyPrecision standardPrecision;

	@NonNull
	AcctSchemaCosting costing;
	@NonNull
	AcctSchemaValidCombinationOptions validCombinationOptions;
	@NonNull
	TaxCorrectionType taxCorrectionType;
	@NonNull
	@Default
	ImmutableSet<OrgId> postOnlyForOrgIds = ImmutableSet.of();
	boolean accrual;
	boolean allowNegativePosting;
	boolean postTradeDiscount;
	boolean postServices;
	boolean postIfSameClearingAccounts;
	boolean isAllowMultiDebitAndCredit;

	boolean isAutoSetDebtoridAndCreditorid;
	int debtorIdPrefix;
	int creditorIdPrefix;

	@NonNull
	AcctSchemaGeneralLedger generalLedger;

	@NonNull
	AcctSchemaDefaultAccounts defaultAccounts;

	@NonNull
	AcctSchemaPeriodControl periodControl;

	@NonNull
	AcctSchemaElementsMap schemaElements;

	public boolean isPostOnlyForSomeOrgs()
	{
		return !postOnlyForOrgIds.isEmpty();
	}

	public boolean isAllowPostingForOrg(@NonNull final OrgId orgId)
	{
		if (postOnlyForOrgIds.isEmpty())
		{
			return true;
		}
		else
		{
			return postOnlyForOrgIds.contains(orgId);
		}
	}

	public boolean isDisallowPostingForOrg(@NonNull final OrgId orgId)
	{
		return !isAllowPostingForOrg(orgId);
	}

	public boolean isElementEnabled(@NonNull final AcctSchemaElementType elementType)
	{
		return getSchemaElements().isElementEnabled(elementType);
	}

	public AcctSchemaElement getSchemaElementByType(@NonNull final AcctSchemaElementType elementType)
	{
		return getSchemaElements().getByElementType(elementType);
	}

	public ImmutableSet<AcctSchemaElementType> getSchemaElementTypes()
	{
		return getSchemaElements().getElementTypes();
	}

	public ChartOfAccountsId getChartOfAccountsId()
	{
		return getSchemaElements().getChartOfAccountsId();
	}
}
