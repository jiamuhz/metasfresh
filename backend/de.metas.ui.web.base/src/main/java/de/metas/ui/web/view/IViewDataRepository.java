package de.metas.ui.web.view;

import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.view.descriptor.SqlViewRowsWhereClause;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlOptions;
import org.adempiere.exceptions.DBException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

 

/**
 * View data repository.
 * This repository is responsible for fetching {@link IViewRow} or even their models.
 * 
 *
 *
 */
public interface IViewDataRepository
{
	String getTableName();

	SqlViewRowsWhereClause getSqlWhereClause(ViewId viewId, DocumentFilterList filters, DocumentIdsSelection rowIds, SqlOptions sqlOpts);

	Map<String, DocumentFieldWidgetType> getWidgetTypesByFieldName();

	DocumentFilterDescriptorsProvider getViewFilterDescriptors();

	IViewRow retrieveById(ViewEvaluationCtx viewEvalCtx, ViewId viewId, DocumentId rowId);

	List<IViewRow> retrievePage(ViewEvaluationCtx viewEvalCtx, ViewRowIdsOrderedSelection orderedSelection, int firstRow, int pageLength) throws DBException;

	List<DocumentId> retrieveRowIdsByPage(ViewEvaluationCtx viewEvalCtx, ViewRowIdsOrderedSelection orderedSelection, int firstRow, int pageLength);

	<T> List<T> retrieveModelsByIds(ViewId viewId, DocumentIdsSelection rowIds, Class<T> modelClass);

	<T> Stream<T> retrieveModelsByIdsAsStream(ViewId viewId, DocumentIdsSelection rowIds, Class<T> modelClass);
	
	ViewRowIdsOrderedSelection createOrderedSelectionFromSelection(
			final ViewEvaluationCtx viewEvalCtx,
			ViewRowIdsOrderedSelection fromSelection,
			DocumentFilterList filters,
			DocumentQueryOrderByList orderBys,
			SqlDocumentFilterConverterContext filterConverterCtx);

	void deleteSelection(String selectionId);

	void scheduleDeleteSelections(Set<String> selectionIds);

	ViewRowIdsOrderedSelection createOrderedSelection(ViewEvaluationCtx viewEvalCtx, ViewId viewId, DocumentFilterList filters, boolean applySecurityRestrictions, SqlDocumentFilterConverterContext context);

	ViewRowIdsOrderedSelection addRemoveChangedRows(
			ViewRowIdsOrderedSelection selection,
			DocumentFilterList filters, Set<DocumentId> rowIds,
			AddRemoveChangedRowIdsCollector changesCollector);

	List<Object> retrieveFieldValues(ViewEvaluationCtx viewEvalCtx, String selectionId, String fieldName, int limit);
}
