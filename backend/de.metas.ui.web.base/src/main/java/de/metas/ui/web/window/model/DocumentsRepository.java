package de.metas.ui.web.window.model;

import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import lombok.NonNull;

import javax.annotation.Nullable;


public interface DocumentsRepository
{
	enum SaveResult
	{
		SAVED,
		DELETED,
	}

	OrderedDocumentsList retrieveDocuments(DocumentQuery query, IDocumentChangesCollector changesCollector);

	/** @return document or null */
	Document retrieveDocument(DocumentQuery query, IDocumentChangesCollector changesCollector);

	/** @return document or null */
	default Document retrieveDocumentById(final DocumentEntityDescriptor entityDescriptor, final DocumentId recordId, final IDocumentChangesCollector changesCollector)
	{
		return retrieveDocument(DocumentQuery.ofRecordId(entityDescriptor, recordId).setChangesCollector(changesCollector).build(), changesCollector);
	}

	/**
	 * Retrieves parent's {@link DocumentId} for a child document identified by given query.
	 *
	 * @return parent's {@link DocumentId}; never returns null
	 */
	DocumentId retrieveParentDocumentId(DocumentEntityDescriptor parentEntityDescriptor, DocumentQuery childDocumentQuery);

	/**
	 * @return newly created document (not saved); never returns null
	 */
	Document createNewDocument(DocumentEntityDescriptor entityDescriptor, @Nullable final Document parentDocument, final IDocumentChangesCollector changesCollector);

	void refresh(Document document);

	SaveResult save(Document document);

	void delete(Document document);

	String retrieveVersion(DocumentEntityDescriptor entityDescriptor, int documentIdAsInt);

	int retrieveLastLineNo(DocumentQuery query);

	/** Can be called to verify that this repository belongs with the given {@code entityDescriptor} */
	default void assertThisRepository(@NonNull final DocumentEntityDescriptor entityDescriptor)
	{
		final DocumentsRepository documentsRepository = entityDescriptor.getDataBinding().getDocumentsRepository();
		if (documentsRepository != this)
		{
			// shall not happen
			throw new IllegalArgumentException("Entity descriptor's repository is invalid: " + entityDescriptor
					+ "\n Expected: " + this
					+ "\n But it was: " + documentsRepository);
		}
	}
}
