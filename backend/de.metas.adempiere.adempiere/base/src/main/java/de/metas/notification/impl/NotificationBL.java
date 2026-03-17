package de.metas.notification.impl;

import de.metas.logging.LogManager;
import de.metas.notification.INotificationBL;
import de.metas.notification.IRoleNotificationsConfigRepository;
import de.metas.notification.IUserNotificationsConfigRepository;
import de.metas.notification.NotificationSenderTemplate;
import de.metas.notification.RoleNotificationsConfig;
import de.metas.notification.UserNotificationRequest;
import de.metas.notification.UserNotificationsConfig;
import de.metas.notification.spi.IRecordTextProvider;
import de.metas.notification.spi.impl.CompositeRecordTextProvider;
import de.metas.security.RoleId;
import de.metas.user.UserId;
import de.metas.util.Services;
import lombok.NonNull;
import org.slf4j.Logger;

import java.util.List;

/** */

public class NotificationBL implements INotificationBL
{
	private static final Logger logger = LogManager.getLogger(NotificationBL.class);
	private final CompositeRecordTextProvider ctxProviders = new CompositeRecordTextProvider();

	@Override
	public NotificationSenderTemplate newNotificationSender()
	{
		final NotificationSenderTemplate sender = new NotificationSenderTemplate();
		sender.setRecordTextProvider(ctxProviders);
		return sender;
	}

	@Override
	public void sendAfterCommit(@NonNull final UserNotificationRequest request)
	{
		try
		{
			newNotificationSender().sendAfterCommit(request);
		}
		catch (Exception ex)
		{
			logger.warn("Failed sending notification: {}", request, ex);
		}
	}

	@Override
	public void sendAfterCommit(@NonNull final List<UserNotificationRequest> requests)
	{
		try
		{
			if (requests.isEmpty())
			{
				return;
			}

			newNotificationSender().sendAfterCommit(requests);
		}
		catch (Exception ex)
		{
			logger.warn("Failed sending notifications: {}", requests, ex);
		}
	}

	@Override
	public void send(@NonNull final UserNotificationRequest request)
	{
		try
		{
			newNotificationSender().send(request);
		}
		catch (Exception ex)
		{
			logger.warn("Failed sending notification: {}", request, ex);
		}
	}

	@Override
	public void addCtxProvider(final IRecordTextProvider ctxProvider)
	{
		ctxProviders.addCtxProvider(ctxProvider);
	}

	@Override
	public void setDefaultCtxProvider(final IRecordTextProvider defaultCtxProvider)
	{
		ctxProviders.setDefaultCtxProvider(defaultCtxProvider);
	}

	@Override
	public UserNotificationsConfig getUserNotificationsConfig(final UserId adUserId)
	{
		return Services.get(IUserNotificationsConfigRepository.class).getByUserId(adUserId);
	}

	@Override
	public RoleNotificationsConfig getRoleNotificationsConfig(final RoleId adRoleId)
	{
		return Services.get(IRoleNotificationsConfigRepository.class).getByRoleId(adRoleId);
	}

}
