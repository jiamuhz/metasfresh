package de.metas.copy_with_details;

/** */

import de.metas.copy_with_details.template.CopyTemplate;
import lombok.NonNull;
import org.adempiere.ad.element.api.AdWindowId;
import org.compiere.model.PO;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public interface CopyRecordSupport
{
	default Optional<PO> copyToNew(@NonNull PO fromPO) {return copyToNew(fromPO, null);}

	/**
	 * Recursively copy given PO and it's children
	 *
	 * @return copied PO or empty if given PO shall not be copied for some reason
	 */
	Optional<PO> copyToNew(@NonNull PO fromPO, @Nullable CopyTemplate template);

	/**
	 * Recursively copy all childrens of <code>fromPO</code> to given <code>toPO</code>
	 */
	void copyChildren(@NonNull PO toPO, @NonNull PO fromPO);

	CopyRecordSupport setParentLink(@NonNull PO parentPO, @NonNull String parentLinkColumnName);

	CopyRecordSupport setAdWindowId(@Nullable AdWindowId adWindowId);

	/**
	 * Allows other modules to install custom code to be executed each time a record was copied.
	 * <p>
	 * <b>Important:</b> usually it makes sense to register a listener not here, but by invoking {@link CopyRecordFactory#registerCopyRecordSupport(String, Class)}.
	 * A listener that is registered there will be added to each CopyRecordSupport instance created by that factory.
	 */
	@SuppressWarnings("UnusedReturnValue")
	CopyRecordSupport onRecordCopied(OnRecordCopiedListener listener);

	default CopyRecordSupport onRecordCopied(final List<OnRecordCopiedListener> listeners)
	{
		listeners.forEach(this::onRecordCopied);
		return this;
	}

	CopyRecordSupport onChildRecordCopied(OnRecordCopiedListener listener);

	default CopyRecordSupport oChildRecordCopied(final List<OnRecordCopiedListener> listeners)
	{
		listeners.forEach(this::onChildRecordCopied);
		return this;
	}
}
