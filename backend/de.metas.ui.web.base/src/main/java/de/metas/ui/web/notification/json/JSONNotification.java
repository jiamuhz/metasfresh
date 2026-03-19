package de.metas.ui.web.notification.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import de.metas.notification.UserNotification;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONOptions;


@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public final class JSONNotification implements Serializable
{
	public static JSONNotification of(final UserNotification notification, final JSONOptions jsonOpts)
	{
		return new JSONNotification(notification, jsonOpts);
	}

	@JsonProperty("id")
	private final String id;
	@JsonProperty("message")
	private final String message;
	@JsonProperty("timestamp")
	private final String timestamp;
	@JsonProperty("important")
	private final boolean important;
	@JsonProperty("read")
	private final boolean read;

	@JsonProperty("target")
	private final JSONNotificationTarget target;

	private JSONNotification(
			final UserNotification notification,
			final JSONOptions jsonOpts)
	{
		id = String.valueOf(notification.getId());
		message = notification.getMessage(jsonOpts.getAdLanguage());
		timestamp = DateTimeConverters.toJson(notification.getTimestamp(), jsonOpts.getZoneId());
		important = notification.isImportant();
		read = notification.isRead();

		target = JSONNotificationTarget.of(notification);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("id", id)
				.add("message", message)
				.add("timestamp", timestamp)
				.add("important", important)
				.add("read", read)
				.add("target", target)
				.toString();
	}

	public String getId()
	{
		return id;
	}

	public String getMessage()
	{
		return message;
	}

	public String getTimestamp()
	{
		return timestamp;
	}

	public boolean isImportant()
	{
		return important;
	}

	public boolean isRead()
	{
		return read;
	}
}
