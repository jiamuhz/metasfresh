package de.metas.websocket.producers;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.SetMultimap;
import de.metas.websocket.WebsocketSessionId;
import de.metas.websocket.WebsocketSubscriptionId;
import de.metas.websocket.WebsocketTopicName;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Set;


public class WebsocketActiveSubscriptionsIndex
{
	private final SetMultimap<WebsocketTopicName, WebsocketSubscriptionId> subscriptionIdsByTopicName = HashMultimap.create();
	private final SetMultimap<WebsocketSessionId, WebsocketSubscriptionId> subscriptionIdsBySessionId = HashMultimap.create();
	private final HashMap<WebsocketSubscriptionId, WebsocketTopicName> topicNamesBySubscriptionId = new HashMap<>();

	public synchronized void addSubscription(
			@NonNull final WebsocketSubscriptionId subscriptionId,
			@NonNull final WebsocketTopicName topicName)
	{
		subscriptionIdsByTopicName.put(topicName, subscriptionId);
		subscriptionIdsBySessionId.put(subscriptionId.getSessionId(), subscriptionId);
		topicNamesBySubscriptionId.put(subscriptionId, topicName);
	}

	@Nullable
	public synchronized WebsocketTopicName removeSubscriptionAndGetTopicName(@NonNull final WebsocketSubscriptionId subscriptionId)
	{
		final WebsocketTopicName topicName = topicNamesBySubscriptionId.remove(subscriptionId);
		if (topicName != null)
		{
			subscriptionIdsByTopicName.removeAll(topicName);
		}

		return topicName;
	}

	@Nullable
	public synchronized Set<WebsocketTopicName> removeSessionAndGetTopicNames(@NonNull final WebsocketSessionId sessionId)
	{
		final Set<WebsocketSubscriptionId> subscriptionIds = subscriptionIdsBySessionId.removeAll(sessionId);
		if (subscriptionIds == null || subscriptionIds.isEmpty())
		{
			return ImmutableSet.of();
		}

		final ImmutableSet.Builder<WebsocketTopicName> topicNames = ImmutableSet.builder();
		for (final WebsocketSubscriptionId subscriptionId : subscriptionIds)
		{
			final WebsocketTopicName topicName = removeSubscriptionAndGetTopicName(subscriptionId);
			if (topicName != null)
			{
				topicNames.add(topicName);
			}
		}

		return topicNames.build();
	}

	public synchronized boolean hasSubscriptionsForTopicName(@NonNull final WebsocketTopicName topicName)
	{
		return subscriptionIdsByTopicName.containsKey(topicName);
	}

	public synchronized ImmutableSetMultimap<WebsocketTopicName, WebsocketSubscriptionId> getSubscriptionIdsByTopicName()
	{
		return ImmutableSetMultimap.copyOf(subscriptionIdsByTopicName);
	}
}
