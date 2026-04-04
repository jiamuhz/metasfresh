package de.metas.ui.web.view;

import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.Builder;
import lombok.NonNull;
import org.adempiere.util.lang.SynchronizedMutable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;



final class ViewRowIdsOrderedSelectionsHolder
{
	private final SqlViewDataRepository viewDataRepository;

	private final ViewId viewId;
	private final boolean applySecurityRestrictions;
	private final Supplier<ViewEvaluationCtx> viewEvaluationCtxSupplier;
	private final DocumentFilterList filtersExcludingFacets;
	private final DocumentFilterList facetFilters;

	private final AtomicBoolean selectionDeleteBeforeCreate = new AtomicBoolean(false);
	private final SynchronizedMutable<ViewRowIdsOrderedSelections> currentSelectionsRef = SynchronizedMutable.of(null);

	@Builder
	private ViewRowIdsOrderedSelectionsHolder(
			@NonNull final SqlViewDataRepository viewDataRepository,
			@NonNull final ViewId viewId,
			final boolean applySecurityRestrictions,
			@NonNull final DocumentFilterList stickyFilters,
			@NonNull final DocumentFilterList filters,
			@NonNull final Supplier<ViewEvaluationCtx> viewEvaluationCtxSupplier)
	{
		this.viewDataRepository = viewDataRepository;
		this.viewId = viewId;
		this.applySecurityRestrictions = applySecurityRestrictions;
		this.viewEvaluationCtxSupplier = viewEvaluationCtxSupplier;

		final ArrayList<DocumentFilter> filtersExcludingFacetsList = new ArrayList<>();
		final ArrayList<DocumentFilter> facetFiltersList = new ArrayList<>();

		filtersExcludingFacetsList.addAll(stickyFilters.toList()); // consider all sticky filters as non facet filters

		for (final DocumentFilter filter : filters.toList())
		{
			if (filter.isFacetFilter())
			{
				facetFiltersList.add(filter);
			}
			else
			{
				filtersExcludingFacetsList.add(filter);
			}
		}

		filtersExcludingFacets = DocumentFilterList.ofList(filtersExcludingFacetsList);
		facetFilters = DocumentFilterList.ofList(facetFiltersList);
	}

	public long getSize()
	{
		return getDefaultSelection().getSize();
	}

	public DocumentQueryOrderByList getDefaultOrderBys()
	{
		return getDefaultSelection().getOrderBys();
	}

	public int getQueryLimit()
	{
		return getDefaultSelection().getQueryLimit().toIntOrZero();
	}

	public boolean isQueryLimitHit()
	{
		return getDefaultSelection().isQueryLimitHit();
	}

	@Nullable
	public EmptyReason getEmptyReason()
	{
		return getDefaultSelection().getEmptyReason();
	}

	public ViewRowIdsOrderedSelection getDefaultSelectionBeforeFacetsFiltering()
	{
		return getCurrentSelections().getDefaultSelectionBeforeFacetsFiltering();
	}

	public ViewRowIdsOrderedSelection getDefaultSelection()
	{
		return getCurrentSelections().getDefaultSelection();
	}

	private ViewRowIdsOrderedSelections getCurrentSelections()
	{
		return currentSelectionsRef.computeIfNull(this::createViewRowIdsOrderedSelections);
	}

	private ViewRowIdsOrderedSelections computeCurrentSelections(@NonNull final UnaryOperator<ViewRowIdsOrderedSelections> remappingFunction)
	{
		return currentSelectionsRef.compute(previousSelections -> {
			final ViewRowIdsOrderedSelections selections = previousSelections != null
					? previousSelections
					: createViewRowIdsOrderedSelections();

			return remappingFunction.apply(selections);
		});
	}

	public ViewRowIdsOrderedSelections computeCurrentSelectionsIfPresent(@NonNull final UnaryOperator<ViewRowIdsOrderedSelections> remappingFunction)
	{
		return currentSelectionsRef.computeIfNotNull(remappingFunction);
	}

	private ViewRowIdsOrderedSelections createViewRowIdsOrderedSelections()
	{
		if (selectionDeleteBeforeCreate.get())
		{
			viewDataRepository.deleteSelection(viewId.getViewId());
		}

		final ViewEvaluationCtx viewEvalCtx = getViewEvaluationCtx();

		final SqlDocumentFilterConverterContext filterConverterContext = SqlDocumentFilterConverterContext.builder()
				.viewId(viewId)
				.userRolePermissionsKey(viewEvalCtx.getPermissionsKey())
				.queryIfNoFilters(viewDataRepository.isQueryIfNoFilters())
				.build();

		final ViewRowIdsOrderedSelection selectionBeforeFacetsFiltering = viewDataRepository.createOrderedSelection(
				viewEvalCtx,
				viewId,
				filtersExcludingFacets,
				applySecurityRestrictions,
				filterConverterContext);

		final ViewRowIdsOrderedSelection selection;
		if (!facetFilters.isEmpty())
		{
			selection = viewDataRepository.createOrderedSelectionFromSelection(
					viewEvalCtx,
					selectionBeforeFacetsFiltering,
					facetFilters,
					/* orderBys */DocumentQueryOrderByList.EMPTY,
					filterConverterContext);
		}
		else
		{
			selection = selectionBeforeFacetsFiltering;
		}

		return ViewRowIdsOrderedSelections.ofDefaultSelection(selectionBeforeFacetsFiltering, selection);
	}

	public void forgetCurrentSelections()
	{
		selectionDeleteBeforeCreate.set(true);
		final ViewRowIdsOrderedSelections selections = currentSelectionsRef.setValueAndReturnPrevious(null);
		if (selections != null)
		{
			final ImmutableSet<String> selectionIds = selections.getSelectionIds();
			viewDataRepository.scheduleDeleteSelections(selectionIds);
		}
	}

	private ViewEvaluationCtx getViewEvaluationCtx()
	{
		return viewEvaluationCtxSupplier.get();
	}

	public void updateChangedRows(
			@NonNull final Set<DocumentId> changedRowIds,
			@NonNull final AddRemoveChangedRowIdsCollector changesCollector)
	{
		if (changedRowIds.isEmpty())
		{
			return;
		}

		computeCurrentSelectionsIfPresent(selections -> addRemoveChangedRows(selections, changedRowIds, changesCollector));
	}

	private ViewRowIdsOrderedSelections addRemoveChangedRows(
			@NonNull final ViewRowIdsOrderedSelections selections,
			@NonNull final Set<DocumentId> rowIds,
			@NonNull final AddRemoveChangedRowIdsCollector changesCollector)
	{
		final ViewRowIdsOrderedSelection defaultSelectionBeforeFacetsFiltering = viewDataRepository.addRemoveChangedRows(
				selections.getDefaultSelectionBeforeFacetsFiltering(),
				filtersExcludingFacets,
				rowIds,
				changesCollector);

		final ViewRowIdsOrderedSelection defaultSelection;
		if (!facetFilters.isEmpty())
		{
			defaultSelection = viewDataRepository.addRemoveChangedRows(
					selections.getDefaultSelection(),
					facetFilters,
					rowIds,
					changesCollector);
		}
		else
		{
			defaultSelection = defaultSelectionBeforeFacetsFiltering;
		}

		return selections.withDefaultSelection(defaultSelectionBeforeFacetsFiltering, defaultSelection);
	}

	public ViewRowIdsOrderedSelection getOrderedSelection(final DocumentQueryOrderByList orderBys)
	{
		return computeCurrentSelections(selections -> computeOrderBySelectionIfAbsent(selections, orderBys))
				.getSelection(orderBys);
	}

	private ViewRowIdsOrderedSelections computeOrderBySelectionIfAbsent(
			@NonNull final ViewRowIdsOrderedSelections selections,
			@Nullable final DocumentQueryOrderByList orderBys)
	{
		return selections.withOrderBysSelectionIfAbsent(
				orderBys,
				this::createSelectionFromSelection);
	}

	private ViewRowIdsOrderedSelection createSelectionFromSelection(
			@NonNull final ViewRowIdsOrderedSelection fromSelection,
			@Nullable final DocumentQueryOrderByList orderBys)
	{
		final ViewEvaluationCtx viewEvaluationCtx = getViewEvaluationCtx();
		final SqlDocumentFilterConverterContext filterConverterContext = SqlDocumentFilterConverterContext.builder()
				.userRolePermissionsKey(viewEvaluationCtx.getPermissionsKey())
				.build();

		return viewDataRepository.createOrderedSelectionFromSelection(
				viewEvaluationCtx,
				fromSelection,
				DocumentFilterList.EMPTY,
				orderBys,
				filterConverterContext);
	}

	public Set<DocumentId> retainExistingRowIds(@NonNull final Set<DocumentId> rowIds)
	{
		if (rowIds.isEmpty())
		{
			return ImmutableSet.of();
		}

		return viewDataRepository.retrieveRowIdsMatchingFilters(
				viewId,
				DocumentFilterList.EMPTY,
				rowIds);
	}
}
