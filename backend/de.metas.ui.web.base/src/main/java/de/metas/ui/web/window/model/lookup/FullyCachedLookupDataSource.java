package de.metas.ui.web.window.model.lookup;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.cache.CCache;
import de.metas.cache.CCacheStats;
import de.metas.cache.CCache.CacheMapType;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.LookupValuesPage;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.util.Check;
import lombok.NonNull;
import org.compiere.model.I_AD_SysConfig;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatees;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


class FullyCachedLookupDataSource implements LookupDataSource
{
	public static FullyCachedLookupDataSource of(final LookupDataSourceFetcher fetcher)
	{
		return new FullyCachedLookupDataSource(fetcher);
	}

	private static final String NAME = "OnePartition";

	private final LookupDataSourceFetcher fetcher;

	private final transient CCache<LookupDataSourceContext, LookupValuesList> cacheByPartition;

	private FullyCachedLookupDataSource(@NonNull final LookupDataSourceFetcher fetcher)
	{
		this.fetcher = fetcher;

		final String cachePrefix = fetcher.getCachePrefix();
		Check.assumeNotEmpty(cachePrefix, "cachePrefix is not empty");
		final int maxSize = 100;
		final int expireAfterMinutes = 60 * 2;
		cacheByPartition = CCache.<LookupDataSourceContext, LookupValuesList>builder()
				.cacheName(cachePrefix + "#" + NAME + "#LookupByPartition")
				.cacheMapType(CacheMapType.LRU)
				.initialCapacity(maxSize)
				.expireMinutes(expireAfterMinutes)
				.additionalTableNameToResetFor(I_AD_SysConfig.Table_Name) // when the AvailableToPromiseRepository's SysConfig changes, we need to reset the cache. The same might apply to other cases.
				.build();
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.add("fetcher", fetcher)
				.toString();
	}

	private LookupValuesList getLookupValuesList(final Evaluatee parentEvaluatee)
	{
		return cacheByPartition.getOrLoad(
				createLookupDataSourceContext(parentEvaluatee),
				evalCtx -> fetcher.retrieveEntities(evalCtx).getValues());
	}

	@NonNull
	private LookupDataSourceContext createLookupDataSourceContext(final Evaluatee parentEvaluatee)
	{
		return fetcher.newContextForFetchingList()
				.setParentEvaluatee(parentEvaluatee)
				.putFilter(LookupDataSourceContext.FILTER_Any, FIRST_ROW, Integer.MAX_VALUE)
				.build();
	}

	@Override
	public LookupValuesPage findEntities(final Evaluatee ctx, final String filter, final int firstRow, final int pageLength)
	{
		final LookupValuesList partition = getLookupValuesList(ctx);
		if (partition.isEmpty())
		{
			return LookupValuesPage.EMPTY;
		}

		final Predicate<LookupValue> filterPredicate = LookupValueFilterPredicates.of(filter);
		final LookupValuesList allMatchingValues;
		if (filterPredicate == LookupValueFilterPredicates.MATCH_ALL)
		{
			allMatchingValues = partition;
		}
		else
		{
			allMatchingValues = partition.filter(filterPredicate);
		}

		return allMatchingValues.pageByOffsetAndLimit(firstRow, pageLength);
	}

	@Override
	public LookupValuesPage findEntities(final Evaluatee ctx, final int pageLength)
	{
		return findEntities(ctx, null, 0, pageLength);
	}

	@Override
	public LookupValue findById(final Object idObj)
	{
		final Object idNormalized = LookupValue.normalizeId(idObj, fetcher.isNumericKey());
		if (idNormalized == null)
		{
			return null;
		}

		final LookupValuesList partition = getLookupValuesList(Evaluatees.empty());
		return partition.getById(idNormalized);
	}

	@Override
	public @NonNull LookupValuesList findByIdsOrdered(@NonNull final Collection<?> ids)
	{
		final ImmutableList<Object> idsNormalized = LookupValue.normalizeIds(ids, fetcher.isNumericKey());
		if (idsNormalized.isEmpty())
		{
			return LookupValuesList.EMPTY;
		}

		final LookupValuesList partition = getLookupValuesList(Evaluatees.empty());
		return partition.getByIdsInOrder(idsNormalized);
	}


	@Override
	public DocumentZoomIntoInfo getDocumentZoomInto(final int id)
	{
		final String tableName = fetcher.getLookupTableName()
				.orElseThrow(() -> new IllegalStateException("Failed converting id=" + id + " to " + DocumentZoomIntoInfo.class + " because the fetcher returned null TableName: " + fetcher));

		return DocumentZoomIntoInfo.of(tableName, id);
	}

	@Override
	public Optional<WindowDocumentTypeId> getZoomIntoWindowId()
	{
		return fetcher.getZoomIntoWindowId();
	}

	@Override
	public List<CCacheStats> getCacheStats()
	{
		return ImmutableList.of(cacheByPartition.stats());
	}

	@Override
	public void cacheInvalidate()
	{
		cacheByPartition.reset();
	}
}
