package org.adempiere.archive.spi;

/** */

import de.metas.email.EMailAddress;
import de.metas.email.mailboxes.UserEMailConfig;
import de.metas.user.UserId;
import org.adempiere.archive.api.ArchiveEmailSentStatus;
import org.adempiere.archive.api.ArchivePrintOutStatus;
import org.adempiere.archive.api.IArchiveEventManager;
import org.compiere.model.I_AD_Archive;

/**
 * Implementors can be registered to {@link IArchiveEventManager#registerArchiveEventListener(IArchiveEventListener)} and can then be fired using that manager.
 *
 *
 */
public interface IArchiveEventListener
{
	default void onPdfUpdate(final I_AD_Archive archive, final UserId userId)
	{
		// nothing
	}

	default void onPdfUpdate(
			final I_AD_Archive archive,
			final UserId userId,
			final String action)
	{
		// nothing
	}

	default void onEmailSent(
			final I_AD_Archive archive,
			final UserEMailConfig user,
			final EMailAddress from,
			final EMailAddress to,
			final EMailAddress cc,
			final EMailAddress bcc,
			final ArchiveEmailSentStatus status)
	{
		// nothing
	}

	default void onPrintOut(
			final I_AD_Archive archive,
			final UserId userId,
			final String printerName,
			final int copies,
			final ArchivePrintOutStatus status)
	{
		// nothing
	}

	default void onVoidDocument(final I_AD_Archive archive)
	{
		// nothing
	}
}
