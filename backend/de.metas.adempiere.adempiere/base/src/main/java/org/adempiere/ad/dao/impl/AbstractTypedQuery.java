package org.adempiere.ad.dao.impl;

/** */

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
import org.adempiere.ad.dao.ICompositeQueryUpdaterExecutor;
import org.adempiere.ad.dao.IQueryInsertExecutor;
import org.adempiere.ad.dao.IQueryInsertExecutor.QueryInsertExecutorResult;
import org.adempiere.exceptions.DBException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.IQuery;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * Contains common methods to be used in {@link IQuery} implementations.
 *
 * @param <T> model type
 * @author tsa
 */
public abstract class AbstractTypedQuery<T> implements IQuery<T>
{
	@Nullable
	@Override
	public T firstOnly() throws DBException
	{
		return firstOnly(getModelClass());
	}

	@Override
	public final <ET extends T> ET firstOnly(final Class<ET> clazz) throws DBException
	{
		final boolean throwExIfMoreThenOneFound = true;
		return firstOnly(clazz, throwExIfMoreThenOneFound);
	}

	@Override
	public final <ET extends T> ET firstOnlyOrNull(final Class<ET> clazz) throws DBException
	{
		final boolean throwExIfMoreThenOneFound = false;
		return firstOnly(clazz, throwExIfMoreThenOneFound);
	}

	@NonNull
	@Override
	public final T firstOnlyNotNull() {return firstOnlyNotNull(getModelClass());}

	@NonNull
	@Override
	public final <ET extends T> ET firstOnlyNotNull(final Class<ET> clazz) throws DBException
	{
		final boolean throwExIfMoreThenOneFound = true;
		final ET model = firstOnly(clazz, throwExIfMoreThenOneFound);
		if (model == null)
		{
			throw new DBException("@NotFound@ @" + getTableName() + "@"
					+ "\n\n@Query@: " + this);
		}
		return model;
	}

	@NonNull
	@Override
	public final <ET extends T> ET firstNotNull(final Class<ET> clazz) throws DBException
	{
		final ET model = first(clazz);
		if (model == null)
		{
			throw new DBException("@NotFound@ @" + getTableName() + "@"
					+ "\n\n@Query@: " + this);
		}

		return model;
	}

	/**
	 * @param throwExIfMoreThenOneFound if true and there more than one record found it will throw exception, <code>null</code> will be returned otherwise.
	 * @return model or null
	 */
	protected abstract <ET extends T> ET firstOnly(final Class<ET> clazz, final boolean throwExIfMoreThenOneFound) throws DBException;

	@Override
	public <ET extends T> Map<Integer, ET> mapById(final Class<ET> clazz)
	{
		final List<ET> list = list(clazz);
		final Map<Integer, ET> map = new HashMap<>(list.size());
		for (final ET item : list)
		{
			final int itemId = InterfaceWrapperHelper.getId(item);
			map.put(itemId, item);
		}

		return map;
	}

	@Override
	public <ID extends RepoIdAware, ET extends T> Map<ID, ET> mapByRepoIdAware(@NonNull final IntFunction<ID> idMapper, @NonNull final Class<ET> clazz) throws DBException
	{
		final Function<ET, ID> record2RepoIdAware = (record) -> {
			final int recordId = InterfaceWrapperHelper.getId(record);
			return idMapper.apply(recordId);
		};

		return stream(clazz)
				.collect(ImmutableMap.toImmutableMap(record2RepoIdAware, Function.identity()));
	}

	@Override
	public final List<Map<String, Object>> listColumns(final String... columnNames)
	{
		final boolean distinct = false;
		return listColumns(distinct, columnNames);
	}

	@Override
	public final List<Map<String, Object>> listDistinct(final String... columnNames)
	{
		final boolean distinct = true;
		return listColumns(distinct, columnNames);
	}

	/**
	 * Selects given columns and return the result as a list of ColumnName to Value map.
	 *
	 * @param distinct true if the value rows shall be district
	 * @return a list of rows, where each row is a {@link Map} having the required columns as keys.
	 */
	protected abstract List<Map<String, Object>> listColumns(final boolean distinct, final String... columnNames);

	@Override
	public <K, ET extends T> ImmutableMap<K, ET> map(final Class<ET> modelClass, final Function<ET, K> keyFunction)
	{
		final List<ET> list = list(modelClass);
		return Maps.uniqueIndex(list, keyFunction::apply);
	}
	@Override
	public <K> ImmutableMap<K, T> map(@NonNull final Function<T, K> keyFunction)
	{
		final List<T> list = list();
		return Maps.uniqueIndex(list, keyFunction::apply);
	}

	@Override
	public <K, ET extends T> ListMultimap<K, ET> listMultimap(final Class<ET> modelClass, final Function<ET, K> keyFunction)
	{
		final ListMultimap<K, ET> map = LinkedListMultimap.create();
		final List<ET> list = list(modelClass);
		for (final ET item : list)
		{
			final K key = keyFunction.apply(item);
			map.put(key, item);
		}
		return map;
	}

	@Override
	public <K, ET extends T> Collection<List<ET>> listAndSplit(final Class<ET> modelClass, final Function<ET, K> keyFunction)
	{
		final ListMultimap<K, ET> map = listMultimap(modelClass, keyFunction);
		return Multimaps.asMap(map).values();
	}

	@Override
	public ICompositeQueryUpdaterExecutor<T> updateDirectly()
	{
		return new CompositeQueryUpdaterExecutor<>(this);
	}

	@Override
	public <ToModelType> IQueryInsertExecutor<ToModelType, T> insertDirectlyInto(final Class<ToModelType> toModelClass)
	{
		return new QueryInsertExecutor<>(toModelClass, this);
	}

	/**
	 * Convenience method that evaluates {@link IQuery#OPTION_ReturnReadOnlyRecords}.
	 */
	protected boolean isReadOnlyRecords()
	{
		return Boolean.TRUE.equals(getOption(OPTION_ReturnReadOnlyRecords));
	}

	abstract <ToModelType> QueryInsertExecutorResult executeInsert(final QueryInsertExecutor<ToModelType, T> queryInserter);
}
