package de.metas.notification;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import de.metas.email.EMailAddress;
import de.metas.email.EMailCustomType;
import de.metas.organization.OrgId;
import de.metas.user.UserId;
import de.metas.util.Check;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
import org.adempiere.service.ClientId;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/** */

@Value
public class UserNotificationsConfig
{
	UserId userId;
	String userADLanguage; // might be null
	ClientId clientId;
	OrgId orgId;

	@Getter(AccessLevel.NONE)
	ImmutableList<UserNotificationsGroup> userNotificationGroups; // needed for toBuilder()
	Map<NotificationGroupName, UserNotificationsGroup> userNotificationGroupsByInternalName;
	UserNotificationsGroup defaults;

	EMailAddress email;
	EMailCustomType eMailCustomType;
	UserId userInChargeId;

	@Builder(toBuilder = true)
	private UserNotificationsConfig(
			@NonNull final UserId userId,
			final String userADLanguage,
			final ClientId clientId,
			final OrgId orgId,
			@NonNull @Singular final Collection<UserNotificationsGroup> userNotificationGroups,
			@NonNull final UserNotificationsGroup defaults,
			final EMailAddress email,
			@Nullable final EMailCustomType eMailCustomType,
			final UserId userInChargeId)
	{
		this.userId = userId;
		this.userADLanguage = Check.isEmpty(userADLanguage) ? null : userADLanguage;

		this.clientId = clientId != null ? clientId : ClientId.SYSTEM;
		this.orgId = orgId != null ? orgId : OrgId.ANY;

		this.userNotificationGroups = ImmutableList.copyOf(userNotificationGroups);
		this.userNotificationGroupsByInternalName = Maps.uniqueIndex(userNotificationGroups, UserNotificationsGroup::getGroupInternalName);
		this.defaults = defaults;

		this.email = email;
		this.eMailCustomType = eMailCustomType;
		this.userInChargeId = userInChargeId;
	}

	public UserNotificationsGroup getGroupByName(@NonNull final NotificationGroupName groupName)
	{
		return userNotificationGroupsByInternalName.getOrDefault(groupName, defaults);
	}

	public String getUserADLanguageOrGet(@NonNull final Supplier<String> defaultLanguageSupplier)
	{
		final String adLanguage = getUserADLanguage();
		return adLanguage != null ? adLanguage : defaultLanguageSupplier.get();
	}

	public boolean isUserInChargeSet()
	{
		return userInChargeId != null;
	}

	public UserNotificationsConfig deriveWithNotificationTypes(final Set<NotificationType> notificationTypes)
	{
		return toBuilder()
				.clearUserNotificationGroups()
				.defaults(UserNotificationsGroup.prepareDefault().notificationTypes(notificationTypes).build())
				.build();
	}

	public UserNotificationsConfig deriveWithEMailCustomType(final EMailCustomType eMailCustomType)
	{
		return toBuilder()
				.eMailCustomType(eMailCustomType)
				.build();
	}

	public UserNotificationsConfig deriveWithNotificationGroups(final List<UserNotificationsGroup> notificationGroups)
	{
		if (notificationGroups.isEmpty())
		{
			return this;
		}

		final Map<NotificationGroupName, UserNotificationsGroup> newUserNotificationGroupsByInternalName = new HashMap<>(userNotificationGroupsByInternalName);
		notificationGroups.forEach(notificationGroup -> newUserNotificationGroupsByInternalName.put(notificationGroup.getGroupInternalName(), notificationGroup));

		return toBuilder()
				.clearUserNotificationGroups()
				.userNotificationGroups(newUserNotificationGroupsByInternalName.values())
				.build();
	}

}
