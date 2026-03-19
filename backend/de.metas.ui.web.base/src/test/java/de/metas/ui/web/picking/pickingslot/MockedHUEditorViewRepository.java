package de.metas.ui.web.picking.pickingslot;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.google.common.collect.ImmutableList;

import de.metas.handlingunits.HuId;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorRowFilter;
import de.metas.ui.web.handlingunits.HUEditorRowFilters;
import de.metas.ui.web.handlingunits.HUEditorViewRepository;
import de.metas.ui.web.handlingunits.filter.HUIdsFilterData;
import de.metas.ui.web.view.ViewEvaluationCtx;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowIdsOrderedSelection;
import de.metas.ui.web.view.descriptor.SqlViewRowIdsConverter;
import de.metas.ui.web.view.descriptor.SqlViewRowsWhereClause;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.util.collections.PagedIterator.Page;
import lombok.NonNull;



public class MockedHUEditorViewRepository implements HUEditorViewRepository
{
	private final LinkedHashMap<HuId, HUEditorRow> rowsByHUId = new LinkedHashMap<>();

	public void addRow(final HUEditorRow row)
	{
		rowsByHUId.put(row.getHuId(), row);
	}

	@Override
	public void invalidateCache()
	{
		// nothing
	}

	@Override
	public List<HUEditorRow> retrieveHUEditorRows(final Set<HuId> huIds, final HUEditorRowFilter filter)
	{
		return huIds.stream()
				.map(rowsByHUId::get)
				.filter(Objects::nonNull)
				.filter(HUEditorRowFilters.toPredicate(filter))
				.collect(ImmutableList.toImmutableList());
	}

	@Override
	public HUEditorRow retrieveForHUId(final HuId huId)
	{
		final HUEditorRow row = rowsByHUId.get(huId);
		if (row == null)
		{
			throw new EntityNotFoundException("no row found for huId=" + huId);
		}
		return row;
	}

	@Override
	public Set<HuId> retrieveHUIdsEffective(
			@NonNull final HUIdsFilterData huIdsFilterData,
			@NonNull final DocumentFilterList allOtherFilters,
			@NonNull final SqlDocumentFilterConverterContext context)
	{
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public Page<HuId> retrieveHUIdsPage(final ViewEvaluationCtx viewEvalCtx, final ViewRowIdsOrderedSelection selection, final int firstRow, final int maxRows)
	{
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public ViewRowIdsOrderedSelection createSelection(final ViewEvaluationCtx viewEvalCtx, final ViewId viewId, final DocumentFilterList filters, final DocumentQueryOrderByList orderBys, final SqlDocumentFilterConverterContext context)
	{
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public ViewRowIdsOrderedSelection createSelectionFromSelection(final ViewEvaluationCtx viewEvalCtx, final ViewRowIdsOrderedSelection fromSelection, final DocumentQueryOrderByList orderBys)
	{
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public ViewRowIdsOrderedSelection removeRowIdsFromSelection(final ViewRowIdsOrderedSelection selection, final DocumentIdsSelection rowIdsToRemove)
	{
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public ViewRowIdsOrderedSelection addRowIdsToSelection(final ViewRowIdsOrderedSelection selection, final DocumentIdsSelection rowIdsToAdd)
	{
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public boolean containsAnyOfRowIds(final ViewRowIdsOrderedSelection selection, final DocumentIdsSelection rowIds)
	{
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void deleteSelection(final ViewRowIdsOrderedSelection selection)
	{
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public SqlViewRowIdsConverter getRowIdsConverter()
	{
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public SqlViewRowsWhereClause buildSqlWhereClause(final ViewRowIdsOrderedSelection selection, final DocumentIdsSelection rowIds)
	{
		throw new UnsupportedOperationException("not implemented");
	}

	/** Does nothing. */
	@Override
	public void warmUp(final Set<HuId> huIds)
	{
		// nothing to do
	}

}
