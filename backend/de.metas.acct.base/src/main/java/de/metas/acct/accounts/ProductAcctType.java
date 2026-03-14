package de.metas.acct.accounts;

import de.metas.acct.AccountConceptualName;
import de.metas.util.Check;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_Product_Acct;

import javax.annotation.Nullable;

/** */

@RequiredArgsConstructor
@Getter
public enum ProductAcctType
{
	P_Revenue_Acct(I_M_Product_Acct.COLUMNNAME_P_Revenue_Acct),
	P_Expense_Acct(I_M_Product_Acct.COLUMNNAME_P_Expense_Acct),
	P_Asset_Acct(I_M_Product_Acct.COLUMNNAME_P_Asset_Acct),
	P_COGS_Acct(I_M_Product_Acct.COLUMNNAME_P_COGS_Acct),
	P_PurchasePriceVariance_Acct(I_M_Product_Acct.COLUMNNAME_P_PurchasePriceVariance_Acct),
	P_InvoicePriceVariance_Acct(I_M_Product_Acct.COLUMNNAME_P_InvoicePriceVariance_Acct),
	P_TradeDiscountRec_Acct(I_M_Product_Acct.COLUMNNAME_P_TradeDiscountRec_Acct),
	P_TradeDiscountGrant_Acct(I_M_Product_Acct.COLUMNNAME_P_TradeDiscountGrant_Acct),
	P_CostAdjustment_Acct(I_M_Product_Acct.COLUMNNAME_P_CostAdjustment_Acct),
	P_InventoryClearing_Acct(I_M_Product_Acct.COLUMNNAME_P_InventoryClearing_Acct),
	P_WIP_Acct(I_M_Product_Acct.COLUMNNAME_P_WIP_Acct),
	P_MethodChangeVariance_Acct(I_M_Product_Acct.COLUMNNAME_P_MethodChangeVariance_Acct),
	P_UsageVariance_Acct(I_M_Product_Acct.COLUMNNAME_P_UsageVariance_Acct),
	P_RateVariance_Acct(I_M_Product_Acct.COLUMNNAME_P_RateVariance_Acct),
	P_MixVariance_Acct(I_M_Product_Acct.COLUMNNAME_P_MixVariance_Acct),
	P_FloorStock_Acct(I_M_Product_Acct.COLUMNNAME_P_FloorStock_Acct),
	P_CostOfProduction_Acct(I_M_Product_Acct.COLUMNNAME_P_CostOfProduction_Acct),
	P_Labor_Acct(I_M_Product_Acct.COLUMNNAME_P_Labor_Acct),
	P_Burden_Acct(I_M_Product_Acct.COLUMNNAME_P_Burden_Acct),
	P_OutsideProcessing_Acct(I_M_Product_Acct.COLUMNNAME_P_OutsideProcessing_Acct),
	P_Overhead_Acct(I_M_Product_Acct.COLUMNNAME_P_Overhead_Acct),
	P_Scrap_Acct(I_M_Product_Acct.COLUMNNAME_P_Scrap_Acct),
	P_ExternallyOwnedStock_Acct(I_M_Product_Acct.COLUMNNAME_P_ExternallyOwnedStock_Acct),
	//
	;

	@NonNull private final String columnName;
	@Getter @NonNull private final AccountConceptualName accountConceptualName;

	ProductAcctType(@NonNull final String columnName)
	{
		this.columnName = columnName;
		this.accountConceptualName = AccountConceptualName.ofString(columnName);
	}

	@Nullable
	public static ProductAcctType ofName(@Nullable final String name)
	{
		if (Check.isBlank(name))
		{
			return null;
		}

		try
		{
			return valueOf(name);
		}
		catch (final Exception exception)
		{
			throw new AdempiereException("No " + ProductAcctType.class + " found for name: " + name);
		}
	}

	public boolean isCOGS() {return P_COGS_Acct.equals(this);}
}
