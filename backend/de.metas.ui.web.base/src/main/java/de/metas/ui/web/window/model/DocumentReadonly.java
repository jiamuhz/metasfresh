package de.metas.ui.web.window.model;

import de.metas.i18n.BooleanWithReason;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.descriptor.decorator.ReadOnlyInfo;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;

import java.util.Optional;



/** Decides on whether a field is readonly, based one the field's document. Example: i an M_Product is not active, then its fields are in general read-only. */
@Value
@Builder
public class DocumentReadonly
{
	public static final DocumentReadonly NOT_READONLY = builder()
			.parentActive(true).active(true)
			.processed(false)
			.processing(false)
			.build();

	public static DocumentReadonly ofParent(final DocumentReadonly parentDocumentReadonly)
	{
		return builder()
				.parentActive(parentDocumentReadonly.active)
				.active(parentDocumentReadonly.active)
				.processed(parentDocumentReadonly.processed)
				.processing(parentDocumentReadonly.processing)
				.parentEnforcingReadOnly(parentDocumentReadonly.computeForceReadOnlyChildDocuments())
				.fieldsReadonly(null) // unknown (will fallback to not-readonly)
				.build();
	}

	boolean parentActive;
	boolean active;
	boolean processed;
	boolean processing;
	boolean parentEnforcingReadOnly;
	ExtendedMemorizingSupplier<ReadOnlyInfo> fieldsReadonly;

	@NonFinal
	public BooleanWithReason computeFieldReadonly(final String fieldName, final boolean alwaysUpdateable)
	{
		// Case: parent document is not active => fields of this document shall be completely readonly (including the IsActive flag)
		if (!parentActive)
		{
			return BooleanWithReason.TRUE; // readonly
		}

		if (parentEnforcingReadOnly)
		{
			return BooleanWithReason.TRUE; // readonly
		}

		// Case: this or parent document is processed => fields of this document shall be completely readonly if they were not flagged with AlwaysUpdateable
		if (processed || processing)
		{
			return alwaysUpdateable ? BooleanWithReason.FALSE : BooleanWithReason.TRUE; // readonly if not always updateable
		}

		// Case: this document is not active => fields of this document shall be completely readonly, BUT NOT the IsActive flag.
		// We shall alow user to edit it
		if (!active)
		{
			if (WindowConstants.FIELDNAME_IsActive.equals(fieldName))
			{
				return BooleanWithReason.FALSE; // not readonly
			}
			else
			{
				return BooleanWithReason.TRUE; // readonly
			}
		}

		// If we reached this point, it means the document and parent document are active and not processed
		// => readonly if fields are readonly.
		final ReadOnlyInfo isReadOnly = fieldsReadonly != null ? fieldsReadonly.get() : null;
		return Optional.ofNullable(isReadOnly)
				.map(ReadOnlyInfo::getIsReadOnlyWithReason)
				.filter(BooleanWithReason::isTrue)
				.orElse(BooleanWithReason.FALSE);
	}

	public boolean computeForceReadOnlyChildDocuments()
	{
		if (parentEnforcingReadOnly)
		{
			return true;
		}

		return Optional.ofNullable(fieldsReadonly)
				.map(ExtendedMemorizingSupplier::get)
				.map(ReadOnlyInfo::isForceReadOnlySubDocuments)
				.orElse(false);
	}
}
