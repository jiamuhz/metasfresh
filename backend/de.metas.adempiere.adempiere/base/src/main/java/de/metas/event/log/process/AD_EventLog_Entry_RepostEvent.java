package de.metas.event.log.process;

import de.metas.util.Check;
import org.compiere.SpringContextHolder;

import com.google.common.collect.ImmutableList;

import de.metas.event.Event;
import de.metas.event.IEventBus;
import de.metas.event.IEventBusFactory;
import de.metas.event.Topic;
import de.metas.event.Type;
import de.metas.event.log.EventLogId;
import de.metas.event.log.EventLogService;
import de.metas.event.model.I_AD_EventLog;
import de.metas.event.model.I_AD_EventLog_Entry;
import de.metas.process.JavaProcess;
import de.metas.util.Services;

/** */

/**
 * Similar to {@link AD_EventLog_RepostEvent}, but the handler selected event log entry will be processed, event if it was already processed.
 */
public class AD_EventLog_Entry_RepostEvent extends JavaProcess
{
	private final IEventBusFactory eventBusFactory = Services.get(IEventBusFactory.class);
	private final EventLogService eventLogService = SpringContextHolder.instance.getBean(EventLogService.class);

	@Override
	protected String doIt() throws Exception
	{
		final I_AD_EventLog_Entry eventLogEntryRecord = getRecord(I_AD_EventLog_Entry.class);
		final I_AD_EventLog eventLogRecord = eventLogEntryRecord.getAD_EventLog();

		Check.assumeNotNull(eventLogRecord.getEventTopicName(), "EventTopicName is null");
		Check.assumeNotNull(eventLogRecord.getEventTypeName(), "EventTypeName is null");

		final Topic topic = Topic.builder()
				.name(eventLogRecord.getEventTopicName())
				.type(Type.valueOf(eventLogRecord.getEventTypeName()))
				.build();

		final boolean typeMismatchBetweenTopicAndBus = !Type.valueOf(eventLogRecord.getEventTypeName()).equals(topic.getType());

		if (typeMismatchBetweenTopicAndBus)
		{
			addLog("The given event log record has a different topic than the event bus!");
		}

		final IEventBus eventBus = eventBusFactory.getEventBus(topic);

		final ImmutableList<String> handlerToIgnore = ImmutableList.of(eventLogEntryRecord.getClassname());
		final Event event = eventLogService.loadEventForReposting(
				EventLogId.ofRepoId(eventLogRecord.getAD_EventLog_ID()),
				handlerToIgnore);

		eventBus.enqueueEvent(event);

		return MSG_OK;
	}

}
