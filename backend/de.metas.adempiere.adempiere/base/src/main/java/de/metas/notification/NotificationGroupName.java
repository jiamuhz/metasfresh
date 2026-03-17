package de.metas.notification;

import de.metas.event.Topic;
import de.metas.util.Check;
import lombok.NonNull;
import lombok.Value;

/** */

@Value
public class NotificationGroupName
{
	public static NotificationGroupName of(final String valueAsString)
	{
		return new NotificationGroupName(valueAsString);
	}

	public static NotificationGroupName of(@NonNull final Topic topic)
	{
		return NotificationGroupName.of(topic.getName());
	}

	private final String valueAsString;

	private NotificationGroupName(@NonNull final String valueAsString)
	{
		Check.assumeNotEmpty(valueAsString, "valueAsString shall not be empty");
		this.valueAsString = valueAsString;
	}

	public Topic toTopic()
	{
		return Topic.distributed(valueAsString);
	}
}
