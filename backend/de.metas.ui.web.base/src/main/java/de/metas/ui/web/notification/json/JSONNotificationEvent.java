package de.metas.ui.web.notification.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;


/**
 * Websocket notification event.
 *
 *
 * The JSON notification event will have following structure:
 *
 * <pre>
 * 	{
 * 		eventType: "New" | "Read" | "Delete"
 * 
 * 		notificationId: <the notification id>
 * 
 * 		// The actual notification.
 *		// NOTE: this field is optional and it will be present only when it makes sense (i.e. when eventType=New).
 * 		notification: {
 * 			// See Swagger for JSON notification structure.
 *		}
 *
 *		unreadCount: current number of notifications which are unread
 * }
 * </pre>
 *
 *
 *
 */
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@ToString
public final class JSONNotificationEvent implements Serializable
{
	public static final JSONNotificationEvent eventNew(final JSONNotification notification, final int unreadCount)
	{
		String notificationId = notification.getId();
		return new JSONNotificationEvent(EventType.New, notificationId, notification, unreadCount);
	}

	public static final JSONNotificationEvent eventRead(final String notificationId, final int unreadCount)
	{
		final JSONNotification notification = null;
		return new JSONNotificationEvent(EventType.Read, notificationId, notification, unreadCount);
	}

	public static final JSONNotificationEvent eventReadAll()
	{
		final String notificationId = null;
		final JSONNotification notification = null;
		final int unreadCount = 0;
		return new JSONNotificationEvent(EventType.ReadAll, notificationId, notification, unreadCount);
	}

	public static final JSONNotificationEvent eventDeleted(final String notificationId, final int unreadCount)
	{
		final JSONNotification notification = null;
		return new JSONNotificationEvent(EventType.Delete, notificationId, notification, unreadCount);
	}

	public static final JSONNotificationEvent eventDeletedAll()
	{
		final String notificationId = null;
		final JSONNotification notification = null;
		final int unreadCount = 0;
		return new JSONNotificationEvent(EventType.DeleteAll, notificationId, notification, unreadCount);
	}

	public static enum EventType
	{
		New, Read, ReadAll, Delete, DeleteAll
	};

	@JsonProperty("eventType")
	private final EventType eventType;

	@JsonProperty("notificationId")
	private final String notificationId;

	@JsonProperty("notification")
	@JsonInclude(JsonInclude.Include.NON_ABSENT)
	private final JSONNotification notification;

	@JsonProperty("unreadCount")
	@JsonInclude(JsonInclude.Include.NON_ABSENT)
	private final Integer unreadCount;

	private JSONNotificationEvent(final EventType eventType, final String notificationId, final JSONNotification notification, final Integer unreadCount)
	{
		this.eventType = eventType;
		this.notificationId = notificationId;
		this.notification = notification;
		this.unreadCount = unreadCount;
	}
}
