package de.metas.attachments.automaticlinksharing;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.adempiere.util.lang.ITableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import de.metas.attachments.AttachmentEntry;

/** */

@Service
public class RecordToReferenceProviderService
{
	private final List<ReferenceableRecordsProvider> handlers;

	public RecordToReferenceProviderService(@NonNull final Optional<List<ReferenceableRecordsProvider>> handlers)
	{
		this.handlers = handlers.orElse(ImmutableList.of());
	}

	/**
	 * For the given attachments and linked records, find further records that shall <i>also</i> be linked to those attachments.
	 *
	 * @param newlyLinkedRecords may be a collection of model objects or {@link ITableRecordReference}s
	 */
	public ExpandResult expand(
			@NonNull final ImmutableList<AttachmentEntry> attachmentEntries,
			@NonNull final Collection<? extends Object> newlyLinkedRecords)
	{
		final List<TableRecordReference> tableRecordReferences = TableRecordReference.ofCollection(newlyLinkedRecords);

		final ImmutableSet.Builder<ITableRecordReference> result = ImmutableSet.builder();

		for (final ReferenceableRecordsProvider handler : handlers)
		{
			for (final AttachmentEntry attachmentEntry : attachmentEntries)
			{
				final ExpandResult singleHandlerResult = handler.expand(attachmentEntry, tableRecordReferences);
				result.addAll(singleHandlerResult.getAdditionalReferences());
			}
		}
		return new ExpandResult(result.build());
	}

	@Value
	@Builder
	public static class ExpandResult
	{
		public static final ExpandResult EMPTY = new ExpandResult(ImmutableSet.of());

		ImmutableSet<? extends ITableRecordReference> additionalReferences;
	}
}
