package de.metas.websocket;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;


@EqualsAndHashCode
public final class WebsocketSubscriptionId
{
	public static WebsocketSubscriptionId of(
			@NonNull final WebsocketSessionId sessionId,
			@NonNull final String subscriptionId)
	{
		return new WebsocketSubscriptionId(sessionId, subscriptionId);
	}

	@Getter
	private final WebsocketSessionId sessionId;
	private final String subscriptionId;

	private WebsocketSubscriptionId(
			@NonNull final WebsocketSessionId sessionId,
			@NonNull final String subscriptionId)
	{
		Check.assumeNotEmpty(subscriptionId, "subscriptionId is not empty");
		this.sessionId = sessionId;
		this.subscriptionId = subscriptionId;
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
		return sessionId.getAsString() + "/" + subscriptionId;
	}

	public boolean isMatchingSessionId(final WebsocketSessionId sessionId)
	{
		return Objects.equals(this.sessionId, sessionId);
	}
}
