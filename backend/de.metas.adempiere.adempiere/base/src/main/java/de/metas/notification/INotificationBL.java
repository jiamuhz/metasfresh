package de.metas.notification;

import java.util.List;

import de.metas.notification.spi.IRecordTextProvider;
import de.metas.security.RoleId;
import de.metas.user.UserId;
import de.metas.util.ISingletonService;

/** */

public interface INotificationBL extends ISingletonService
{
	NotificationSenderTemplate newNotificationSender();

	void send(UserNotificationRequest request);

	void sendAfterCommit(UserNotificationRequest request);

	void sendAfterCommit(List<UserNotificationRequest> requests);

	void addCtxProvider(IRecordTextProvider ctxProvider);

	void setDefaultCtxProvider(IRecordTextProvider defaultCtxProvider);

	UserNotificationsConfig getUserNotificationsConfig(UserId adUserId);

	RoleNotificationsConfig getRoleNotificationsConfig(RoleId adRoleId);
}
