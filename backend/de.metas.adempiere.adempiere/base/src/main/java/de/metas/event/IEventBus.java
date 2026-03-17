package de.metas.event;

import java.util.function.Consumer;

/** */

/**
 * Bus of {@link Event}s.
 * <p>
 * To get an instance of this object, please use {@link IEventBusFactory}.
 * <p>
 * Note: if an event bus is only local or also remote (i.e. events are send to other hosts as well) just depends on wheter the event bus factory registered a an apprpriate subscriber to forward events
 * to other hosts.
 *
 * @author tsa
 */
public interface IEventBus
{
	/**
	 * @return the topic of this event bus. If the event bus is "distributed" then subscriber on other hosts will be notified about events on this host.
	 */
	Topic getTopic();

	/**
	 * Subscribe to this bus.
	 */
	void subscribe(IEventListener listener);

	void unsubscribe(IEventListener listener);

	/**
	 * Subscribe to this bus.
	 */
	void subscribe(Consumer<Event> eventConsumer);

	/**
	 * Subscribe and expect events with to have bodys of a particular type.
	 * Also see {@link #enqueueObject(Object)}.
	 *
	 * @param type the class of the object the consumer is subscribed to.
	 *             The event-bus will attempt to deserialize the event's body to an instance of this class.
	 *             
	 * @return the newly created event-listener, in case the caller wants to unsubscribe later           
	 */
	<T> IEventListener subscribeOn(Class<T> type, Consumer<T> eventConsumer);

	/**
	 * Post given event in the underlying bus.
	 */
	void processEvent(Event event);

	/**
	 * Enqueue given event in RabbitMQ.
	 */
	void enqueueEvent(Event event);

	/**
	 * Create an event and serialize the given {@code obj} to be the event's body (payload).
	 */
	void enqueueObject(Object obj);

	/**
	 * @return {@code true} if the bus was destroyed and it no longer accepts events.
	 */
	boolean isDestroyed();

	/**
	 * This can be configured via AD_SysConfig.
	 * <p>
	 * Async = true:
	 * <li>PRO: the thread which posted the event can continue right away; the event is enqueued and processing will be done by another thread</li>
	 * <li>CONs: no distributed tracing (didn't manage to get it to work); it's hard to keep track of the actual queue size => can lead to weird behavior</li>
	 * <p>
	 * Async = false:
	 * <li>analog to the PROD and CONs of async</li>
	 * <li>note that only one thread per eventbus is allowed to process its event (analog to the one worker thread that we have at async=true)</li>
	 *
	 * @return {@code true} if events are submitted to a dedicated worker thread such that the invoker of {@link #processEvent(Event)} doesn't have to wait for the listeners to be invoked.
	 */
	boolean isAsync();

	EventBusStats getStats();
}
