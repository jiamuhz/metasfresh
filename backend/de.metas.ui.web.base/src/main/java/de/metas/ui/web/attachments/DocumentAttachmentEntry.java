package de.metas.ui.web.attachments;

import de.metas.attachments.AttachmentEntryType;
import lombok.NonNull;
import lombok.ToString;

import java.net.URI;

import org.compiere.Adempiere;

import de.metas.attachments.AttachmentEntry;
import de.metas.attachments.AttachmentEntryService;
import de.metas.ui.web.window.datatypes.DocumentId;



/**
 * Attachment entry
 *
 *
 *
 */
@ToString
class DocumentAttachmentEntry implements IDocumentAttachmentEntry
{
	/* package */static DocumentAttachmentEntry of(@NonNull final DocumentId id, @NonNull final AttachmentEntry entry)
	{
		return new DocumentAttachmentEntry(id, entry);
	}

	private final DocumentId id;
	private final AttachmentEntry entry;

	private DocumentAttachmentEntry(
			@NonNull final DocumentId id,
			@NonNull final AttachmentEntry entry)
	{
		this.id = id;
		this.entry = entry;
	}

	@Override
	public DocumentId getId()
	{
		return id;
	}

	@Override
	public AttachmentEntryType getType()
	{
		return entry.getType();
	}

	@Override
	public String getFilename()
	{
		return entry.getFilename();
	}

	@Override
	public byte[] getData()
	{
		final AttachmentEntryService attachmentEntryService = Adempiere.getBean(AttachmentEntryService.class);
		return attachmentEntryService.retrieveData(entry.getId());
	}

	@Override
	public String getContentType()
	{
		return entry.getMimeType();
	}

	@Override
	public URI getUrl()
	{
		return entry.getUrl();
	}
}
