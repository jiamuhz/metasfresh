package de.metas.ui.web.attachments;

import java.net.URI;

import de.metas.attachments.AttachmentEntryType;
import org.adempiere.archive.api.IArchiveBL;
import org.compiere.model.I_AD_Archive;
import org.compiere.util.MimeType;

import de.metas.attachments.AttachmentEntry;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.FileUtil;
import de.metas.util.Services;



class DocumentArchiveEntry implements IDocumentAttachmentEntry
{
	/* package */static DocumentArchiveEntry of(final DocumentId id, final I_AD_Archive archive)
	{
		return new DocumentArchiveEntry(id, archive);
	}

	private DocumentId id;
	private final I_AD_Archive archive;

	private DocumentArchiveEntry(final DocumentId id, final I_AD_Archive archive)
	{
		this.id = id;
		this.archive = archive;
	}

	@Override
	public DocumentId getId()
	{
		return id;
	}

	@Override
	public AttachmentEntryType getType()
	{
		return AttachmentEntryType.Data;
	}

	@Override
	public String getFilename()
	{
		final String contentType = getContentType();
		final String fileExtension = MimeType.getExtensionByType(contentType);
		final String name = archive.getName();
		return FileUtil.changeFileExtension(name, fileExtension);
	}

	@Override
	public byte[] getData()
	{
		final IArchiveBL archiveBL = Services.get(IArchiveBL.class);
		return archiveBL.getBinaryData(archive);
	}

	@Override
	public String getContentType()
	{
		final IArchiveBL archiveBL = Services.get(IArchiveBL.class);
		return archiveBL.getContentType(archive);
	}

	@Override
	public URI getUrl()
	{
		return null;
	}

}
