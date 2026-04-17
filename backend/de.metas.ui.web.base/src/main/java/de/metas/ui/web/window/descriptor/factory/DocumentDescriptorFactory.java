 

package de.metas.ui.web.window.descriptor.factory;

import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.exceptions.DocumentLayoutBuildException;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;

import javax.annotation.Nullable;
import java.util.Optional;

public interface DocumentDescriptorFactory
{
	/**
	 * Tell the caller if they can expect this instance's methods to work with the given {@code windowId}.
	 *
	 * @param windowDocumentTypeId may be {@code null}. If {@code null}, then return {@code false}.
	 */
	boolean isWindowIdSupported(@Nullable WindowDocumentTypeId windowDocumentTypeId);

	DocumentDescriptor getDocumentDescriptor(WindowDocumentTypeId windowDocumentTypeId) throws DocumentLayoutBuildException;

	void invalidateForWindow(WindowDocumentTypeId windowDocumentTypeId);

	default DocumentEntityDescriptor getDocumentEntityDescriptor(final int AD_Window_ID)
	{
		final WindowDocumentTypeId windowDocumentTypeId = WindowDocumentTypeId.of(AD_Window_ID);
		return getDocumentDescriptor(windowDocumentTypeId).getEntityDescriptor();
	}

	default DocumentEntityDescriptor getDocumentEntityDescriptor(@NonNull final WindowDocumentTypeId windowDocumentTypeId)
	{
		return getDocumentDescriptor(windowDocumentTypeId).getEntityDescriptor();
	}

	default String getTableNameOrNull(final int AD_Window_ID)
	{
		return getDocumentEntityDescriptor(AD_Window_ID).getTableName();
	}

	default String getTableNameOrNull(final int AD_Window_ID, final DetailId detailId)
	{
		final DocumentEntityDescriptor descriptor = getDocumentEntityDescriptor(AD_Window_ID);
		if (detailId == null)
		{
			return descriptor.getTableName();
		}
		else
		{
			return descriptor.getIncludedEntityByDetailId(detailId).getTableName();
		}
	}

	default DocumentEntityDescriptor getDocumentEntityDescriptor(final DocumentPath documentPath)
	{
		final DocumentEntityDescriptor rootEntityDescriptor = getDocumentEntityDescriptor(documentPath.getWindowId());

		if (documentPath.isRootDocument())
		{
			return rootEntityDescriptor;
		}
		else
		{
			return rootEntityDescriptor.getIncludedEntityByDetailId(documentPath.getDetailId());
		}
	}

	default TableRecordReference getTableRecordReference(@NonNull final DocumentPath documentPath)
	{
		return getTableRecordReferenceIfPossible(documentPath)
				.orElseThrow(() -> new AdempiereException("Cannot determine table/record from " + documentPath));
	}

	default Optional<TableRecordReference> getTableRecordReferenceIfPossible(@NonNull final DocumentPath documentPath)
	{
		if (documentPath.getWindowIdOrNull() == null || !documentPath.getWindowId().isInt())
		{
			return Optional.empty();
		}

		final DocumentEntityDescriptor rootEntityDescriptor = getDocumentEntityDescriptor(documentPath.getWindowId());

		if (documentPath.isRootDocument())
		{
			final DocumentId rootDocumentId = documentPath.getDocumentId();
			if (!rootDocumentId.isInt())
			{
				return Optional.empty();
			}

			final String tableName = rootEntityDescriptor.getTableName();
			final int recordId = rootDocumentId.toInt();
			return Optional.of(TableRecordReference.of(tableName, recordId));
		}
		else
		{
			final DocumentId includedRowId = documentPath.getSingleRowId();
			if (!includedRowId.isInt())
			{
				return Optional.empty();
			}
			final DocumentEntityDescriptor includedEntityDescriptor = rootEntityDescriptor.getIncludedEntityByDetailId(documentPath.getDetailId());
			final String tableName = includedEntityDescriptor.getTableName();
			final int recordId = includedRowId.toInt();
			return Optional.of(TableRecordReference.of(tableName, recordId));
		}
	}
}
