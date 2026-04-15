package de.metas.websocket;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.NonNull;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@EqualsAndHashCode
public final class WebsocketTopicName
{
	@JsonCreator
	public static WebsocketTopicName ofString(final String topicName)
	{
		return new WebsocketTopicName(topicName);
	}

	private final String topicName;

	private WebsocketTopicName(@NonNull final String topicName)
	{
		Check.assumeNotEmpty(topicName, "topicName is not empty");
		this.topicName = topicName;
	}

	/**
	 * @deprecated please use {@link #getAsString()}
	 */
	@Override
	@Deprecated
	public String toString()
	{
		return getAsString();
	}

	@JsonValue
	public String getAsString()
	{
		return topicName;
	}

	public boolean startsWith(@NonNull final String prefix)
	{
		return this.topicName.startsWith(prefix);
	}
}
