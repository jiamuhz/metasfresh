package org.adempiere.ad.dao;

/** */

/**
 * Query Statistics Logger: build up a list of top used SQL queries
 * NOTE: it is disabled by default
 *
 * @author tsa
 *
 */
public interface IQueryStatisticsLogger
{
	String SYSTEM_PROPERTY_LOG_TO_SYSTEM_ERROR = "org.adempiere.ad.dao.impl.QueryStatisticsLogger.LogToSystemError";


	/**
	 * Enable statistics logging and also enable SQL tracing.
	 * The executed SQLs will be printed to {@link System#err}.
	 */
	void enableSqlTracing();

	/**
	 * Disable statistics logging
	 */
	void disableSqlTracing();

	/**
	 * IF this property is set then only such SQLs which contain the given String as substring are logged.
	 *
	 * @param filterBy
	 */
	void setFilterBy(String filterBy);

	/**
	 * Clears the filtering. See {@link #setFilterBy(String)}.
	 */
	void clearFilterBy();

	/**
	 *
	 * @return string array of top used SQL queries with statistics informations (ordered from most used to less used)
	 */
	String[] getTopTotalDurationQueriesAsString();

	/**
	 * Gets top SQL queries ordered by their total summed executon time (descending).
	 *
	 * @return
	 */
	String[] getTopCountQueriesAsString();

	/**
	 * Gets top SQL queries ordered by their average execution time (descending)
	 *
	 * @return
	 */
	String[] getTopAverageDurationQueriesAsString();

	/**
	 * Enable record of SQL execution time with micrometer
	 */
	void enableRecordWithMicrometer();

	/**
	 * Disable record of SQL execution time with micrometer
	 */
	void disableRecordWithMicrometer();
}
