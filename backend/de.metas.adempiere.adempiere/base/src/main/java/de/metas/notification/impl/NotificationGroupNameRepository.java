package de.metas.notification.impl;

import java.util.Set;

import org.adempiere.ad.dao.IQueryBL;
import org.compiere.model.I_AD_NotificationGroup;

import com.google.common.collect.ImmutableBiMap;

import de.metas.cache.CCache;
import de.metas.notification.INotificationGroupNameRepository;
import de.metas.notification.NotificationGroupName;
import de.metas.util.Services;

/** */

public class NotificationGroupNameRepository implements INotificationGroupNameRepository
{
	private final CCache<Integer, ImmutableBiMap<Integer, NotificationGroupName>> notificationGroupNames = CCache.newCache(I_AD_NotificationGroup.Table_Name, 1, CCache.EXPIREMINUTES_Never);

	@Override
	public NotificationGroupName getById(final int notificationGroupId)
	{
		return getNotificationGroupInternalNamesById().get(notificationGroupId);
	}

	@Override
	public int getNotificationGroupId(final NotificationGroupName notificationGroupName)
	{
		return getNotificationGroupInternalNamesById().inverse().getOrDefault(notificationGroupName, -1);
	}

	@Override
	public Set<NotificationGroupName> getAll()
	{
		return getNotificationGroupInternalNamesById().values();
	}

	private ImmutableBiMap<Integer, NotificationGroupName> getNotificationGroupInternalNamesById()
	{
		return notificationGroupNames.getOrLoad(0, this::retrieveNotificationGroupInternalNamesById);
	}

	private ImmutableBiMap<Integer, NotificationGroupName> retrieveNotificationGroupInternalNamesById()
	{
		return Services.get(IQueryBL.class)
				.createQueryBuilderOutOfTrx(I_AD_NotificationGroup.class)
				.addOnlyActiveRecordsFilter()
				.create()
				.stream()
				.collect(ImmutableBiMap.toImmutableBiMap(
						I_AD_NotificationGroup::getAD_NotificationGroup_ID,
						notificationGroupRecord -> NotificationGroupName.of(notificationGroupRecord.getInternalName())));
	}

}
