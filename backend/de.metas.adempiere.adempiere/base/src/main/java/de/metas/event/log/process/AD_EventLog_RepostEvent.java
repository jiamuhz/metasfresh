package de.metas.event.log.process;

import de.metas.event.Event;
import de.metas.event.IEventBus;
import de.metas.event.IEventBusFactory;
import de.metas.event.Topic;
import de.metas.event.Type;
import de.metas.event.log.EventLogId;
import de.metas.event.log.EventLogService;
import de.metas.event.model.I_AD_EventLog;
import de.metas.process.JavaProcess;
import de.metas.util.Check;
import de.metas.util.Services;
import org.compiere.SpringContextHolder;

/** */

public class AD_EventLog_RepostEvent extends JavaProcess
{
	@Override
	protected String doIt() throws Exception
	{
		final I_AD_EventLog eventLogRecord = getRecord(I_AD_EventLog.class);

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

		final EventLogId eventLogId = EventLogId.ofRepoId(eventLogRecord.getAD_EventLog_ID());

		final EventLogService eventLogService = SpringContextHolder.instance.getBean(EventLogService.class);
		final Event event = eventLogService.loadEventForReposting(eventLogId);

		final IEventBus eventBus = Services.get(IEventBusFactory.class).getEventBus(topic);
		eventBus.enqueueEvent(event);

		return MSG_OK;
	}

}
