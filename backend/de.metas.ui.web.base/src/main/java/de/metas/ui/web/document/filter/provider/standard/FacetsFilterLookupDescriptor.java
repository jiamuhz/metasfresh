package de.metas.ui.web.document.filter.provider.standard;

import com.google.common.collect.ImmutableList;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.view.*;
import de.metas.ui.web.view.ISqlViewDataRepository;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.LookupValuesPage;
import de.metas.ui.web.window.datatypes.Values;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.SimpleLookupDescriptorTemplate;
import de.metas.ui.web.window.model.lookup.IdsToFilter;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.StringUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DisplayType;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;



final class FacetsFilterLookupDescriptor extends SimpleLookupDescriptorTemplate
{
	private final IMsgBL msgBL = Services.get(IMsgBL.class);
	private final IViewsRepository viewsRepository;

	@Getter
	private final String filterId;
	@Getter
	private final String fieldName;
	private final DocumentFieldWidgetType fieldWidgetType;
	@Getter
	private final boolean numericKey;
	private final int maxFacetsToFetch;
	private final LookupDescriptor fieldLookupDescriptor;

	@Builder
	private FacetsFilterLookupDescriptor(
			@NonNull final IViewsRepository viewsRepository,
			//
			@NonNull final String filterId,
			@NonNull final String fieldName,
			@NonNull final DocumentFieldWidgetType fieldWidgetType,
			final boolean numericKey,
			final int maxFacetsToFetch,
			@Nullable final LookupDescriptor fieldLookupDescriptor)
	{
		Check.assumeGreaterThanZero(maxFacetsToFetch, "maxFacetsToFetch");

		this.viewsRepository = viewsRepository;

		this.filterId = filterId;
		this.fieldName = fieldName;
		this.fieldWidgetType = fieldWidgetType;
		this.numericKey = numericKey;
		this.maxFacetsToFetch = maxFacetsToFetch;
		this.fieldLookupDescriptor = fieldLookupDescriptor;
	}

	@Override
	public Optional<String> getLookupTableName()
	{
		return fieldLookupDescriptor.getLookupDataSourceFetcher().getLookupTableName();
	}

	@Override
	public Set<String> getDependsOnFieldNames()
	{
		return fieldLookupDescriptor.getDependsOnFieldNames();
	}

	@Override
	public LookupDataSourceContext.Builder newContextForFetchingById(final Object id)
	{
		final LookupDataSourceContext.Builder builder = fieldLookupDescriptor != null
				? fieldLookupDescriptor.getLookupDataSourceFetcher().newContextForFetchingById(id)
				: LookupDataSourceContext.builderWithoutTableName().putFilterById(IdsToFilter.ofSingleValue(id));

		return builder
				.requiresParameter(LookupDataSourceContext.PARAM_ViewId)
				.requiresParameter(LookupDataSourceContext.PARAM_ViewSize);
	}

	@Override
	public LookupValue retrieveLookupValueById(final @NonNull LookupDataSourceContext evalCtx)
	{
		return fieldLookupDescriptor.getLookupDataSourceFetcher().retrieveLookupValueById(evalCtx);
	}

	@Override
	public LookupDataSourceContext.Builder newContextForFetchingList()
	{
		final LookupDataSourceContext.Builder builder = fieldLookupDescriptor != null
				? fieldLookupDescriptor.getLookupDataSourceFetcher().newContextForFetchingList()
				: LookupDataSourceContext.builderWithoutTableName();

		return builder
				.requiresParameter(LookupDataSourceContext.PARAM_ViewId)
				.requiresParameter(LookupDataSourceContext.PARAM_ViewSize);
	}

	@Override
	public LookupValuesPage retrieveEntities(final LookupDataSourceContext evalCtx)
	{
		final DefaultView view = getView(evalCtx);

		return view.getFacetFiltersCacheMap()
				.computeIfAbsent(filterId, () -> createFacetFilterViewCache(view))
				.getAvailableValues()
				.pageByOffsetAndLimit(
						evalCtx.getOffset(0),
						evalCtx.getLimit(Integer.MAX_VALUE));
	}

	private FacetFilterViewCache createFacetFilterViewCache(final DefaultView view)
	{
		final ISqlViewDataRepository viewDataRepository = view.getViewDataRepository();
		final ViewEvaluationCtx viewEvalCtx = view.getViewEvaluationCtx();
		final String selectionId = view.getDefaultSelectionBeforeFacetsFiltering().getSelectionId();

		List<Object> rawValues = viewDataRepository.retrieveFieldValues(
				viewEvalCtx,
				selectionId,
				fieldName,
				maxFacetsToFetch);

		boolean valuesAreOrdered = false;
		if (fieldWidgetType.isDateOrTime()
				|| fieldWidgetType.isNumeric()
				|| fieldWidgetType.isText())
		{
			// in case of date/time/numeric fields we shall order them by their value
			// and not alphabetically by their string representation
			rawValues = rawValues.stream()
					.sorted()
					.collect(ImmutableList.toImmutableList());
			valuesAreOrdered = true;
		}

		final LookupValuesList lookupValues = rawValues.stream()
				.map(this::convertRawFieldValueToLookupValue)
				.filter(Objects::nonNull)
				.distinct()
				.collect(LookupValuesList.collect())
				.ordered(valuesAreOrdered);

		return FacetFilterViewCache.builder()
				.filterId(filterId)
				.availableValues(lookupValues)
				.build();
	}

	private DefaultView getView(@NonNull final LookupDataSourceContext evalCtx)
	{
		final ViewId viewId = evalCtx.getViewId();
		return DefaultView.cast(viewsRepository.getView(viewId));
	}

	@Nullable
	private LookupValue convertRawFieldValueToLookupValue(final Object fieldValue)
	{
		if (fieldValue == null)
		{
			return null;
		}
		else if (fieldValue instanceof LookupValue)
		{
			return (LookupValue)fieldValue;
		}
		else if (fieldValue instanceof LocalDate)
		{
			final LocalDate date = (LocalDate)fieldValue;
			return StringLookupValue.of(
					Values.localDateToJson(date),
					TranslatableStrings.date(date));
		}
		else if (fieldValue instanceof Boolean)
		{
			final boolean booleanValue = StringUtils.toBoolean(fieldValue);
			return StringLookupValue.of(
					DisplayType.toBooleanString(booleanValue),
					msgBL.getTranslatableMsgText(booleanValue));
		}
		else if (fieldValue instanceof String)
		{
			final String stringValue = (String)fieldValue;
			return StringLookupValue.of(stringValue, stringValue);
		}
		else
		{
			throw new AdempiereException("Value not supported: " + fieldValue + " (" + fieldValue.getClass() + ")")
					.appendParametersToMessage()
					.setParameter("fieldName", fieldName);
		}
	}
}
