/** */

package de.metas.cache.model;

import com.google.common.collect.ImmutableList;
import org.adempiere.model.InterfaceWrapperHelper;

import java.util.List;

public final class DirectModelCacheInvalidateRequestFactory implements ModelCacheInvalidateRequestFactory
{
	public static final transient DirectModelCacheInvalidateRequestFactory instance = new DirectModelCacheInvalidateRequestFactory();

	private DirectModelCacheInvalidateRequestFactory()
	{
	}

	@Override
	public List<CacheInvalidateRequest> createRequestsFromModel(
			final ICacheSourceModel model,
			final ModelCacheInvalidationTiming timing_NOTUSED)
	{
		final int recordId = model.getRecordId();
		final String keyColumnName = InterfaceWrapperHelper.getKeyColumnName(model.getTableName());
		if (recordId < InterfaceWrapperHelper.getFirstValidIdByColumnName(keyColumnName))
		{
			return ImmutableList.of();
		}

		final String tableName = model.getTableName();
		return ImmutableList.of(CacheInvalidateRequest.rootRecord(tableName, recordId));
	}
}
