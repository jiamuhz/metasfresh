package de.metas.ui.web.window.model;

import java.util.Optional;

import org.adempiere.ad.expression.api.LogicExpressionResult;

import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.Document.OnValidStatusChanged;

 

public interface IIncludedDocumentsCollection
{
	DetailId getDetailId();

	IIncludedDocumentsCollection copy(Document parentDocumentCopy, CopyMode copyMode);

	OrderedDocumentsList getDocuments(DocumentQueryOrderByList orderBys);

	OrderedDocumentsList getDocumentsByIds(DocumentIdsSelection documentIds);

	Optional<Document> getDocumentById(DocumentId documentId);

	void updateStatusFromParent();

	void assertNewDocumentAllowed();

	LogicExpressionResult getAllowCreateNewDocument();

	LogicExpressionResult getAllowDeleteDocument();

	Document createNewDocument();

	void deleteDocuments(DocumentIdsSelection documentIds);

	DocumentValidStatus checkAndGetValidStatus(OnValidStatusChanged onValidStatusChanged);

	/**
	 * Check if there are any changes in any of the included documents
	 * 
	 * @return true if there are some changes
	 */
	boolean hasChangesRecursivelly();

	void saveIfHasChanges();

	void markStaleAll();

	void markStale(final DocumentIdsSelection rowIds);

	/** @return true if contains at least one stale document */
	boolean isStale();

	int getNextLineNo();

	default void onChildSaved(final Document document)
	{
		// nothing
	}

	default void onChildChanged(Document document)
	{
		// nothing
	}
}
