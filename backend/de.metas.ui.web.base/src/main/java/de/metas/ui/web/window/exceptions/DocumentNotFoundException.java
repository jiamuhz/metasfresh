package de.metas.ui.web.window.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Objects;

import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;


@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DocumentNotFoundException extends EntityNotFoundException
{
	private final DocumentPath documentPath;

	public DocumentNotFoundException(final DocumentPath documentPath)
	{
		super("No document found for " + documentPath);
		this.documentPath = documentPath;
	}

	public DocumentNotFoundException(final DocumentType documentType, final DocumentId documentTypeId, final DocumentId documentId)
	{
		this(DocumentPath.rootDocumentPath(documentType, documentTypeId, documentId));
	}

	public DocumentPath getDocumentPath()
	{
		return documentPath;
	}

	public void rethrowIfNotMatching(final DocumentPath documentPath)
	{
		if (!Objects.equal(this.documentPath, documentPath))
		{
			throw this;
		}
	}

}
