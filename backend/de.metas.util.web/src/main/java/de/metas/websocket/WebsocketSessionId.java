package de.metas.websocket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.NonNull;


@EqualsAndHashCode
public final class WebsocketSessionId
{
	@JsonCreator
	public static WebsocketSessionId ofString(final String sessionId)
	{
		return new WebsocketSessionId(sessionId);
	}

	private final String sessionId;

	private WebsocketSessionId(@NonNull final String sessionId)
	{
		Check.assumeNotEmpty(sessionId, "sessionId is not empty");
		this.sessionId = sessionId;
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
		return sessionId;
	}
}
