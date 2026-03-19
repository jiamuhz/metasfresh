package de.metas.ui.web.window.descriptor;

import de.metas.util.lang.ReferenceListAwareEnum;
import de.metas.util.lang.ReferenceListAwareEnums;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.ad.validationRule.AdValRuleId;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.X_AD_Column;

import javax.annotation.Nullable;
import java.util.OptionalInt;



@Value
@SuppressWarnings({ "OptionalAssignedToNull", "OptionalUsedAsFieldOrParameterType" })
public class DocumentFieldDefaultFilterDescriptor
{
	boolean defaultFilter;
	int defaultFilterSeqNo;
	FilterOperator operator;
	boolean showFilterIncrementButtons;
	Object autoFilterInitialValue;
	boolean showFilterInline;
	@Nullable AdValRuleId adValRuleId;

	boolean facetFilter;
	int facetFilterSeqNo;
	OptionalInt maxFacetsToFetch;

	@Builder
	public DocumentFieldDefaultFilterDescriptor(
			final int seqNo,
			//
			final boolean defaultFilter,
			final int defaultFilterSeqNo,
			final FilterOperator operator,
			final boolean showFilterIncrementButtons,
			final boolean showFilterInline,
			@Nullable final Object autoFilterInitialValue,
			@Nullable AdValRuleId adValRuleId,
			//
			final boolean facetFilter,
			final int facetFilterSeqNo,
			@Nullable final OptionalInt maxFacetsToFetch)
	{
		if (!defaultFilter && !facetFilter)
		{
			throw new AdempiereException("defaultFilter or facetFilter shall be true");
		}

		if (defaultFilter)
		{
			this.defaultFilter = true;
			this.defaultFilterSeqNo = defaultFilterSeqNo > 0 ? defaultFilterSeqNo : Integer.MAX_VALUE;
			this.operator = operator != null ? operator : FilterOperator.EQUALS_OR_ILIKE;
			this.showFilterIncrementButtons = showFilterIncrementButtons;
			this.autoFilterInitialValue = autoFilterInitialValue;
			this.showFilterInline = showFilterInline;
			this.adValRuleId = adValRuleId;
		}
		else
		{
			this.defaultFilter = false;
			this.defaultFilterSeqNo = Integer.MAX_VALUE;
			this.operator = FilterOperator.EQUALS_OR_ILIKE;
			this.showFilterIncrementButtons = false;
			this.autoFilterInitialValue = null;
			this.showFilterInline = false;
			this.adValRuleId = null;
		}

		if (facetFilter)
		{
			this.facetFilter = true;
			this.facetFilterSeqNo = facetFilterSeqNo > 0 ? facetFilterSeqNo : Integer.MAX_VALUE;
			this.maxFacetsToFetch = maxFacetsToFetch != null ? maxFacetsToFetch : OptionalInt.empty();
		}
		else
		{
			this.facetFilter = false;
			this.facetFilterSeqNo = Integer.MAX_VALUE;
			this.maxFacetsToFetch = OptionalInt.empty();
		}
	}

	public enum FilterOperator implements ReferenceListAwareEnum
	{
		EQUALS_OR_ILIKE(X_AD_Column.FILTEROPERATOR_EqualsOrLike),
		BETWEEN(X_AD_Column.FILTEROPERATOR_Between),
		IS_NOT_NULL(X_AD_Column.FILTEROPERATOR_NotNull),
		;

		@Getter
		private final String code;

		private static final ReferenceListAwareEnums.ValuesIndex<FilterOperator> index = ReferenceListAwareEnums.index(FilterOperator.values());

		FilterOperator(@NonNull final String code)
		{
			this.code = code;
		}

		public static FilterOperator ofNullableStringOrEquals(@Nullable final String code)
		{
			final FilterOperator type = index.ofNullableCode(code);
			return type != null ? type : EQUALS_OR_ILIKE;
		}

		public static FilterOperator ofCode(@NonNull final String code)
		{
			return index.ofCode(code);
		}
	}
}
