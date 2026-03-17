package de.metas.acct.api;

import com.google.common.collect.ImmutableSet;
import de.metas.costing.CostElementId;
import de.metas.costing.CostTypeId;
import de.metas.costing.CostingLevel;
import de.metas.costing.CostingMethod;
import de.metas.currency.CurrencyPrecision;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Value;

/** */

@Value
@Builder
public class AcctSchemaCosting
{
	@NonNull
	CurrencyPrecision costingPrecision;
	@NonNull
	CostTypeId costTypeId;
	@NonNull
	CostingLevel costingLevel;
	@NonNull
	CostingMethod costingMethod;

	@NonNull
	@Default
	ImmutableSet<CostElementId> postOnlyCostElementIds = ImmutableSet.of();
}
