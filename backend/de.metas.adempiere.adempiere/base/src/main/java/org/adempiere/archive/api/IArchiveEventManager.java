package org.adempiere.archive.api;

/** */

import de.metas.email.EMailAddress;
import de.metas.email.mailboxes.UserEMailConfig;
import de.metas.user.UserId;
import de.metas.util.ISingletonService;
import org.adempiere.archive.spi.IArchiveEventListener;
import org.compiere.model.I_AD_Archive;

/**
 * Can be called from different places to "inform" registered {@link IArchiveEventListener}s about events.
 *
 *
 */
public interface IArchiveEventManager extends ISingletonService
{
	/**
	 * To be used where you need to pass the "1 copy" parameter.
	 * <p>
	 * NOTE: please use this constant instead of hardcoding the number "1" because in most of the cases, you are passing this because the copies is not available or is not implemented.
	 */
	int COPIES_ONE = 1;

	void registerArchiveEventListener(IArchiveEventListener listener);

	void firePdfUpdate(
			I_AD_Archive archive,
			UserId userId,
			String action);

	void firePdfUpdate(I_AD_Archive archive, UserId userId);

	void fireEmailSent(
			I_AD_Archive archive,
			UserEMailConfig user,
			EMailAddress from,
			EMailAddress to,
			EMailAddress cc,
			EMailAddress bcc,
			ArchiveEmailSentStatus status);

	void firePrintOut(
			I_AD_Archive archive,
			UserId userId,
			String printerName,
			int copies,
			ArchivePrintOutStatus status);

	/**
	 * To be invoked if the archive document is voided, reversed etc.
	 */
	void fireVoidDocument(I_AD_Archive archive);
}
