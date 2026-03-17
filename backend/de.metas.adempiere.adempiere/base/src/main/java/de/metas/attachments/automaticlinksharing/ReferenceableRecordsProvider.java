package de.metas.attachments.automaticlinksharing;

import java.util.Collection;

import org.adempiere.util.lang.ITableRecordReference;

import de.metas.attachments.AttachmentEntry;
import de.metas.attachments.automaticlinksharing.RecordToReferenceProviderService.ExpandResult;

/** */

public interface ReferenceableRecordsProvider
{
	/**
	 * For the given records that shall be linked to an attachment entry, retrieve additional records that shall <b>also</b> be linked.
	 * Don't worry about the possibility that the records returned by this method might be already linked to begin with.
	 *
	 * @param newlyLinkedRecordRefs records that are to be just linked to an attachment entry.
	 */
	public ExpandResult expand(
			AttachmentEntry attachmentEntry,
			Collection<? extends ITableRecordReference> newlyLinkedRecordRefs);
}
