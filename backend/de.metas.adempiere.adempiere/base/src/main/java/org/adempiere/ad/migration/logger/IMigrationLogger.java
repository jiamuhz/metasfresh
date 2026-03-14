/**
 *
 */
package org.adempiere.ad.migration.logger;

/** */

import com.google.common.collect.ImmutableSet;
import de.metas.util.ISingletonService;
import org.adempiere.ad.session.MFSession;
import org.adempiere.service.ClientId;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

import java.util.Collection;

/**
 * @author tsa
 */
public interface IMigrationLogger extends ISingletonService
{
	boolean isLogTableName(String tableName, ClientId clientId);

	/**
	 * Create migration step using the current {@link IMigrationLoggerContext} for the specified {@link PO}
	 */
	void logMigration(IMigrationLoggerContext migrationCtx, PO po, POInfo info, String event);

	/**
	 * Create migration step using the current session for the specified {@link PO}
	 */
	void logMigration(MFSession session, PO po, POInfo info, String event);

	/**
	 * Create a raw SQL migration step for the specified {@link PO}
	 */
	void logMigrationSQL(PO contextPO, String sql);

	/**
	 * Add table to ignore list (ignore specified table when logging migration steps).
	 */
	void addTablesToIgnoreList(String... tableName);

	void addTablesToIgnoreList(Collection<String> tableNames);

	/**
	 * Gets a list of table names that shall be ignored when creating migration scripts.
	 * NOTE:
	 * <ul>
	 * <li>all table names are uppercase
	 * <li>based on <code>clientId</code>, the list could be different
	 * </ul>
	 */
	ImmutableSet<String> getTablesToIgnoreUC(ClientId clientId);
}
