package org.adempiere.archive.api.impl;

/** */

import de.metas.email.EMailAddress;
import de.metas.email.mailboxes.UserEMailConfig;
import de.metas.logging.LogManager;
import de.metas.user.UserId;
import lombok.NonNull;
import org.adempiere.archive.api.ArchiveEmailSentStatus;
import org.adempiere.archive.api.ArchivePrintOutStatus;
import org.adempiere.archive.api.IArchiveEventManager;
import org.adempiere.archive.spi.IArchiveEventListener;
import org.compiere.model.I_AD_Archive;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArchiveEventManager implements IArchiveEventManager
{
	private static final Logger logger = LogManager.getLogger(ArchiveEventManager.class);
	private final CopyOnWriteArrayList<IArchiveEventListener> listeners = new CopyOnWriteArrayList<>();

	@Override
	public void registerArchiveEventListener(@NonNull final IArchiveEventListener listener)
	{
		final boolean registered = listeners.addIfAbsent(listener);

		if (registered)
		{
			logger.info("Registered {}", listener);
		}
		else
		{
			logger.warn("Skip registering {} because it was already registered", listener);
		}
	}

	@Override
	public void firePdfUpdate(
			@NonNull final I_AD_Archive archive,
			@Nullable final UserId userId)
	{
		for (final IArchiveEventListener listener : listeners)
		{
			listener.onPdfUpdate(archive, userId);
		}
	}

	@Override
	public void firePdfUpdate(
			@NonNull final I_AD_Archive archive,
			@Nullable final UserId userId, 
			String action)
	{
		for (final IArchiveEventListener listener : listeners)
		{
			listener.onPdfUpdate(archive, userId, action);
		}
	}

	@Override
	public void fireEmailSent(
			final I_AD_Archive archive,
			final UserEMailConfig user,
			final EMailAddress emailFrom,
			final EMailAddress emailTo,
			final EMailAddress emailCc,
			final EMailAddress emailBcc,
			final ArchiveEmailSentStatus status)
	{
		for (final IArchiveEventListener listener : listeners)
		{
			listener.onEmailSent(archive, user, emailFrom, emailTo, emailCc, emailBcc, status);
		}
	}

	@Override
	public void firePrintOut(
			final I_AD_Archive archive,
			@Nullable final UserId userId,
			final String printerName,
			final int copies,
			@NonNull final ArchivePrintOutStatus status)
	{
		for (final IArchiveEventListener listener : listeners)
		{
			listener.onPrintOut(archive, userId, printerName, copies, status);
		}
	}

	@Override
	public void fireVoidDocument(final I_AD_Archive archive)
	{
		for (final IArchiveEventListener listener : listeners)
		{
			listener.onVoidDocument(archive);
		}
	}
}
