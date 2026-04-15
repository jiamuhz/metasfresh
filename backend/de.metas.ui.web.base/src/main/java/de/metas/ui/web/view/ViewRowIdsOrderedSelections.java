package de.metas.ui.web.view;

import org.adempiere.exceptions.AdempiereException;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;


@EqualsAndHashCode
@ToString
final class ViewRowIdsOrderedSelections
{
	public static ViewRowIdsOrderedSelections ofDefaultSelection(
			@NonNull final ViewRowIdsOrderedSelection defaultSelectionBeforeFacetsFiltering,
			@NonNull final ViewRowIdsOrderedSelection defaultSelection)
	{
		final ImmutableMap<DocumentQueryOrderByList, ViewRowIdsOrderedSelection> selectionsByOrderBys = ImmutableMap.of();
		return new ViewRowIdsOrderedSelections(defaultSelectionBeforeFacetsFiltering, defaultSelection, selectionsByOrderBys);
	}

	@Getter
	private final ViewRowIdsOrderedSelection defaultSelectionBeforeFacetsFiltering;

	@Getter
	private final ViewRowIdsOrderedSelection defaultSelection;

	private final ImmutableMap<DocumentQueryOrderByList, ViewRowIdsOrderedSelection> selectionsByOrderBys;

	private ViewRowIdsOrderedSelections(
			@NonNull final ViewRowIdsOrderedSelection defaultSelectionBeforeFacetsFiltering,
			@NonNull final ViewRowIdsOrderedSelection defaultSelection,
			@NonNull final ImmutableMap<DocumentQueryOrderByList, ViewRowIdsOrderedSelection> selectionsByOrderBys)
	{
		this.defaultSelectionBeforeFacetsFiltering = defaultSelectionBeforeFacetsFiltering;
		this.defaultSelection = defaultSelection;
		this.selectionsByOrderBys = selectionsByOrderBys;
	}

	public ViewRowIdsOrderedSelections withDefaultSelection(
			@NonNull final ViewRowIdsOrderedSelection defaultSelectionBeforeFacetsFiltering,
			@NonNull final ViewRowIdsOrderedSelection defaultSelection)
	{
		if (ViewRowIdsOrderedSelection.equals(this.defaultSelectionBeforeFacetsFiltering, defaultSelectionBeforeFacetsFiltering)
				&& ViewRowIdsOrderedSelection.equals(this.defaultSelection, defaultSelection))
		{
			return this;
		}
		else
		{
			return ofDefaultSelection(defaultSelectionBeforeFacetsFiltering, defaultSelection);
		}
	}

	@FunctionalInterface
	public interface ViewRowIdsOrderedSelectionFactory
	{
		ViewRowIdsOrderedSelection create(ViewRowIdsOrderedSelection defaultSelection, DocumentQueryOrderByList orderBys);
	}

	public ViewRowIdsOrderedSelections withOrderBysSelectionIfAbsent(
			@NonNull final DocumentQueryOrderByList orderBys,
			@NonNull final ViewRowIdsOrderedSelectionFactory factory)
	{
		final ViewRowIdsOrderedSelection selection = getSelectionOrNull(orderBys);
		if (selection != null)
		{
			return this;
		}

		final ImmutableMap<DocumentQueryOrderByList, ViewRowIdsOrderedSelection> selectionsByOrderBysNew = ImmutableMap.<DocumentQueryOrderByList, ViewRowIdsOrderedSelection> builder()
				.putAll(selectionsByOrderBys)
				.put(orderBys, factory.create(defaultSelection, orderBys))
				.build();
		return new ViewRowIdsOrderedSelections(defaultSelectionBeforeFacetsFiltering, defaultSelection, selectionsByOrderBysNew);
	}

	public ViewRowIdsOrderedSelection getSelection(final DocumentQueryOrderByList orderBys)
	{
		final ViewRowIdsOrderedSelection selection = getSelectionOrNull(orderBys);
		if (selection == null)
		{
			throw new AdempiereException("No selection found for " + orderBys + " in " + this);
		}
		return selection;
	}

	private ViewRowIdsOrderedSelection getSelectionOrNull(final DocumentQueryOrderByList orderBys)
	{
		if (orderBys == null || orderBys.isEmpty())
		{
			return defaultSelection;
		}
		if (DocumentQueryOrderByList.equals(defaultSelection.getOrderBys(), orderBys))
		{
			return defaultSelection;
		}

		return selectionsByOrderBys.get(orderBys);
	}

	public ImmutableSet<String> getSelectionIds()
	{
		final ImmutableSet.Builder<String> selectionIds = ImmutableSet.builder();
		selectionIds.add(defaultSelection.getSelectionId());
		for (final ViewRowIdsOrderedSelection selection : selectionsByOrderBys.values())
		{
			selectionIds.add(selection.getSelectionId());
		}

		return selectionIds.build();
	}
}
