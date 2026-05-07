package de.metas.cache.model;

import java.util.Set;

import de.metas.cache.TableNamesGroup;
import lombok.NonNull;

public interface IModelCacheInvalidateRequestFactoryGroup
{
	TableNamesGroup getTableNamesToEnableRemoveCacheInvalidation();

	Set<ModelCacheInvalidateRequestFactory> getFactoriesByTableName(@NonNull String tableName, @NonNull ModelCacheInvalidationTiming timing);
}
