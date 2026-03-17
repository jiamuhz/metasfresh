package de.metas.notification;

import org.adempiere.exceptions.AdempiereException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.metas.JsonObjectMapperHolder;
import de.metas.event.Event;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/** */

@UtilityClass
public class UserNotificationUtils
{
	private static final String EVENT_PARAM_Notification = "userNotification";

	private static final ObjectMapper jsonMapper = JsonObjectMapperHolder.sharedJsonObjectMapper();

	public static Event toEvent(@NonNull final UserNotification notification)
	{
		final String notificationAsJson;
		try
		{
			notificationAsJson = jsonMapper.writeValueAsString(notification);
		}
		catch (final JsonProcessingException ex)
		{
			throw new AdempiereException("Failed converting notification to JSON", ex)
					.setParameter("notification", notification);
		}

		return Event.builder()
				.addRecipient_User_ID(notification.getRecipientUserId())
				.putProperty(EVENT_PARAM_Notification, notificationAsJson)
				.build();
	}

	public static UserNotification toUserNotification(@NonNull final Event event)
	{
		final String notificationAsJson = event.getPropertyAsString(EVENT_PARAM_Notification);
		try
		{
			return jsonMapper.readValue(notificationAsJson, UserNotification.class);
		}
		catch (final Exception ex)
		{
			throw new AdempiereException("Failed converting event to " + UserNotification.class + ": " + event, ex);
		}
	}
}
