package de.metas.handlingunits.shipmentschedule.integrationtest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.adempiere.util.lang.ITableRecordReference;
import org.junit.Assert;

import de.metas.event.Event;
import de.metas.event.IEventBus;
import de.metas.event.IEventBusFactory;
import de.metas.event.IEventListener;
import de.metas.inout.event.InOutUserNotificationsProducer;
import de.metas.inout.model.I_M_InOut;
import de.metas.notification.UserNotification;
import de.metas.notification.UserNotificationUtils;
import de.metas.util.Services;

/**
 * Listens to InOutGenerate topic, collects the inouts which were notified and later can compare with a given list.
 *
 * @author tsa
 *
 */
public class InOutGeneratedNotificationChecker implements IEventListener
{
	public static InOutGeneratedNotificationChecker createAndSubscribe()
	{
		final InOutGeneratedNotificationChecker notificationsChecker = new InOutGeneratedNotificationChecker();

		Services.get(IEventBusFactory.class)
				.getEventBus(InOutUserNotificationsProducer.EVENTBUS_TOPIC)
				.subscribe(notificationsChecker);

		return notificationsChecker;
	}

	private final Set<Integer> notifiedInOutIds = new HashSet<>();

	private InOutGeneratedNotificationChecker()
	{
	}

	@Override
	public void onEvent(final IEventBus eventBus, final Event event)
	{
		final UserNotification notification = UserNotificationUtils.toUserNotification(event);
		final ITableRecordReference inoutRecord = notification.getTargetRecord();
		final int inoutId = inoutRecord.getRecord_ID();

		notifiedInOutIds.add(inoutId);
	}

	/**
	 * Asserts that all the inouts from given list were notified on the bus.
	 */
	public void assertAllNotified(final Iterable<? extends I_M_InOut> inouts)
	{
		final List<I_M_InOut> inoutsNotNotified = new ArrayList<>();

		for (final I_M_InOut inout : inouts)
		{
			final int inoutId = inout.getM_InOut_ID();
			if (!notifiedInOutIds.contains(inoutId))
			{
				inoutsNotNotified.add(inout);
			}
		}

		if (inoutsNotNotified.isEmpty())
		{
			return;
		}

		Assert.fail("Following inouts were expected to be notified but they were not: " + inoutsNotNotified);
	}
}
