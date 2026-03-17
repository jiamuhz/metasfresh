package de.metas.attachments.storeattachment;

import java.net.URI;

import de.metas.common.util.time.SystemTime;
import org.springframework.stereotype.Component;

import de.metas.attachments.AttachmentEntry;
import de.metas.attachments.AttachmentEntryService;
import de.metas.attachments.AttachmentTags;
import lombok.NonNull;

/** */

@Component
public class DefaultAttachmentStoredListener implements AttachmentStoredListener
{
	private final AttachmentEntryService attachmentEntryService;

	public DefaultAttachmentStoredListener(@NonNull final AttachmentEntryService attachmentEntryService)
	{
		this.attachmentEntryService = attachmentEntryService;
	}

	@Override
	public void attachmentWasStored(@NonNull final AttachmentEntry attachmentEntry, @NonNull final URI storageIdentifier)
	{
		final AttachmentTags attachmentTags = attachmentEntry.getTags()
				.withTag(AttachmentTags.TAGNAME_STORED_PREFIX + SystemTime.millis(), storageIdentifier.toString());
		final AttachmentEntry attachmentEntryWithStoreInfo = attachmentEntry
				.toBuilder()
				.tags(attachmentTags)
				.build();

		attachmentEntryService.save(attachmentEntryWithStoreInfo);
	}

}
