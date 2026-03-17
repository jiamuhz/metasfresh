package de.metas.notification;

import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

import java.util.Set;

/** */

@Value
public class UserNotificationsGroup
{
	public static UserNotificationsGroup.UserNotificationsGroupBuilder prepareDefault()
	{
		return UserNotificationsGroup.builder().groupInternalName(DEFAULT_GroupInternalName);
	}

	private static NotificationGroupName DEFAULT_GroupInternalName = NotificationGroupName.of("default");

	NotificationGroupName groupInternalName;
	Set<NotificationType> notificationTypes;

	@Builder
	private UserNotificationsGroup(
			@NonNull final NotificationGroupName groupInternalName,
			@Singular final Set<NotificationType> notificationTypes)
	{
		this.groupInternalName = groupInternalName;
		this.notificationTypes = ImmutableSet.copyOf(notificationTypes);
	}

	public boolean isNotifyUserInCharge()
	{
		return notificationTypes.contains(NotificationType.NotifyUserInCharge);
	}

	public boolean isNotifyByEMail()
	{
		return notificationTypes.contains(NotificationType.EMail);
	}

	public boolean isNotifyByInternalMessage()
	{
		return notificationTypes.contains(NotificationType.Notice);
	}

	public boolean hasAnyNotificationTypesExceptUserInCharge()
	{
		return hasAnyNotificationTypesExcept(NotificationType.NotifyUserInCharge);
	}

	public boolean hasAnyNotificationTypesExcept(final NotificationType typeToExclude)
	{
		final int notificationTypesCount = notificationTypes.size();
		if (notificationTypesCount <= 0)
		{
			return false;
		}
		else if (notificationTypesCount == 1)
		{
			return !notificationTypes.contains(typeToExclude);
		}
		else // notificationTypesCount > 1
		{
			return true;
		}
	}
}
