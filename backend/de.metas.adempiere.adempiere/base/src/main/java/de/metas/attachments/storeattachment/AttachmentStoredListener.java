package de.metas.attachments.storeattachment;

import java.net.URI;

import de.metas.attachments.AttachmentEntry;

/** */

/** Listener to be fired when an attachment was exported outside of metasfresh. */
public interface AttachmentStoredListener
{
	void attachmentWasStored(AttachmentEntry attachmentEntry, URI storageIdentifier);
}
