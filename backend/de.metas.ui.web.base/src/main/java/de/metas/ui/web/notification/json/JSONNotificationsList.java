package de.metas.ui.web.notification.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import de.metas.notification.UserNotificationsList;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.GuavaCollectors;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONNotificationsList
{
	public static final JSONNotificationsList EMPTY = new JSONNotificationsList();

	public static final JSONNotificationsList of(final UserNotificationsList notifications, final JSONOptions jsonOpts)
	{
		if (notifications.isEmpty())
		{
			return EMPTY;
		}

		return new JSONNotificationsList(notifications, jsonOpts);
	}

	@JsonProperty("totalCount")
	private final int totalCount;
	@JsonProperty("unreadCount")
	private final int unreadCount;
	@JsonProperty("notifications")
	private final List<JSONNotification> notifications;

	private JSONNotificationsList(final UserNotificationsList notifications, final JSONOptions jsonOpts)
	{
		super();
		totalCount = notifications.getTotalCount();
		unreadCount = notifications.getTotalUnreadCount();
		this.notifications = notifications.getNotifications()
				.stream()
				.map(notification -> JSONNotification.of(notification, jsonOpts))
				.collect(GuavaCollectors.toImmutableList());
	}

	private JSONNotificationsList()
	{
		super();
		totalCount = 0;
		unreadCount = 0;
		notifications = ImmutableList.of();
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.add("totalCount", totalCount)
				.add("unreadCount", unreadCount)
				.add("notifications", notifications)
				.toString();
	}
	
	public int getTotalCount()
	{
		return totalCount;
	}
	
	public int getUnreadCount()
	{
		return unreadCount;
	}

	public List<JSONNotification> getNotifications()
	{
		return notifications;
	}
}
