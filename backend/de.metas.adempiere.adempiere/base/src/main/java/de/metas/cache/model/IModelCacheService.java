package de.metas.cache.model;

/** */

import java.util.Properties;

import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.PO;

import de.metas.cache.model.ITableCacheConfig.TrxLevel;
import de.metas.util.ISingletonService;

/**
 * Model level caching service. Use it to enable e.g.
 * {@link InterfaceWrapperHelper#create(Properties, int, Class, String)} to take advantage of caching.
 *
 * @author tsa
 *
 */
public interface IModelCacheService extends ISingletonService
{
	ITableCacheConfigBuilder createTableCacheConfigBuilder(String tableName);

	ITableCacheConfigBuilder createTableCacheConfigBuilder(Class<?> modelClass);

	/**
	 * Creates and adds a simple caching profile (using {@link #createDefaultTableCacheConfig(Class)}) which enables caching for <code>modelClass</code>'s TableName.
	 *
	 * If a caching configuration already exists, it will be overwritten.
	 *
	 * @param modelClass
	 * @see #createDefaultTableCacheConfig(Class)
	 */
	void addTableCacheConfig(Class<?> modelClass);

	/**
	 * Same as {@link #addTableCacheConfig(Class)} but the caching configuration will be added only if there is no one already.
	 *
	 * @param modelClass
	 * @see #createDefaultTableCacheConfig(Class)
	 */
	void addTableCacheConfigIfAbsent(Class<?> modelClass);

	/**
	 * Creates a default caching configuration for given <code>modelClass</code>.
	 *
	 * The default caching configuration will
	 * <ul>
	 * <li>enabled
	 * <li>caching transaction level will be {@link TrxLevel#All}
	 * </ul>
	 *
	 * @param modelClass
	 * @return caching configuration.
	 */
	ITableCacheConfig createDefaultTableCacheConfig(Class<?> modelClass);

	/**
	 * Retrieves {@link PO} object from cache.
	 *
	 * @param ctx
	 * @param tableName
	 * @param Record_ID
	 * @param trxName
	 * @return cached {@link PO} object or null
	 */
	PO retrieveObject(Properties ctx, String tableName, int Record_ID, String trxName);

	/**
	 * Add given {@link PO} object to cache.
	 *
	 * NOTE: call this method ONLY after you <b>"freshly"</b>retrieved your object from DB, and did not yet do any changes to it.
	 *
	 * @param po persistent object. In case it's null, it will be silently ignored.
	 */
	void addToCache(PO po);

	void invalidate(CacheInvalidateMultiRequest request);
}
