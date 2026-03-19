package de.metas.ui.web.view;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.view.descriptor.SqlViewRowsWhereClause;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.NonNull;



public interface ViewRowIdsOrderedSelectionFactory
{
	ViewRowIdsOrderedSelection createOrderedSelection(ViewEvaluationCtx viewEvalCtx,
			ViewId viewId,
			DocumentFilterList filters,
			final DocumentQueryOrderByList orderBys,
			final boolean applySecurityRestrictions,
			SqlDocumentFilterConverterContext context);

	/**
	 * @return a new {@link ViewRowIdsOrderedSelection} from a given <code>fromSelection</code> ordered by <code>orderBys</code>
	 */
	ViewRowIdsOrderedSelection createOrderedSelectionFromSelection(
			ViewEvaluationCtx viewEvalCtx,
			ViewRowIdsOrderedSelection fromSelection,
			DocumentFilterList filters,
			DocumentQueryOrderByList orderBys,
			final SqlDocumentFilterConverterContext filterConverterCtx);

	SqlViewRowsWhereClause getSqlWhereClause(ViewId viewId, DocumentIdsSelection rowIds);

	ViewRowIdsOrderedSelection addRowIdsToSelection(ViewRowIdsOrderedSelection selection, DocumentIdsSelection rowIds);

	ViewRowIdsOrderedSelection removeRowIdsFromSelection(ViewRowIdsOrderedSelection selection, DocumentIdsSelection rowIds);

	ViewRowIdsOrderedSelection removeAndAddRowIdsFromSelection(
			@NonNull ViewRowIdsOrderedSelection selection,
			@NonNull DocumentIdsSelection rowIdsToRemove,
			@NonNull DocumentIdsSelection rowIdsToAdd,
			@NonNull AddRemoveChangedRowIdsCollector changesCollector);

	boolean containsAnyOfRowIds(ViewRowIdsOrderedSelection selection, DocumentIdsSelection rowIds);

	default void deleteSelection(@NonNull final String selectionId)
	{
		deleteSelections(ImmutableSet.of(selectionId));
	}

	void deleteSelections(Set<String> selectionIds);

	void scheduleDeleteSelections(Set<String> selectionIds);
}
