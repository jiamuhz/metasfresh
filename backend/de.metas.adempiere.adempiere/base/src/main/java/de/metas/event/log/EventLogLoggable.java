package de.metas.event.log;

import javax.annotation.Nullable;

import org.adempiere.util.lang.IAutoCloseable;

import de.metas.event.log.EventLogUserService.EventLogEntryRequest;
import de.metas.util.ILoggable;
import de.metas.util.Loggables;
import lombok.NonNull;

/** */

public class EventLogLoggable implements ILoggable
{
	private final Class<?> handlerClass;

	public static IAutoCloseable createAndRegisterThreadLocal(@NonNull final Class<?> handlerClass)
	{
		final EventLogLoggable eventLogLoggable = new EventLogLoggable(handlerClass);
		return Loggables.temporarySetLoggable(eventLogLoggable);
	}

	private EventLogLoggable(@NonNull final Class<?> handlerClass)
	{
		this.handlerClass = handlerClass;
	}

	@Override
	public ILoggable addLog(
			@NonNull final String msg,
			@Nullable final Object... msgParameters)
	{
		EventLogEntryRequest.builder()
				.formattedMessage(msg, msgParameters)
				.eventHandlerClass(handlerClass)
				.createAndStore();

		return this;
	}

}
