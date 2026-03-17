package de.metas.connection.impl;

/** */

import com.mchange.v2.c3p0.AbstractConnectionCustomizer;
import de.metas.connection.IConnectionCustomizerService;
import de.metas.logging.LogManager;
import de.metas.util.Services;
import org.slf4j.Logger;

import java.sql.Connection;

/**
 * Use this class to add specific behavior to c3p0 connection handling.
 *
 * Added for task 04006.
 *
 * see <a href="http://www.mchange.com/projects/c3p0/#connection_customizers">...</a>
 */
public class DB_PostgreSQL_ConnectionCustomizer extends AbstractConnectionCustomizer
{

	private static final String CLIENTINFO_ApplicationName = "ApplicationName";
	private static final Logger log = LogManager.getLogger(DB_PostgreSQL_ConnectionCustomizer.class);

	/**
	 * When a new connection is acquired from the underlying postgres JDBC driver, this method sets the connections log limit to "WARNING".
	 * That way, only warning messages (and above) will be send to
	 * the client to prevent an OutOfMemoryError due to too many messages being send from verbose and long-running DB functions.
	 *
	 * Added for task 04006.
	 */
	@Override
	public void onAcquire(final Connection c, final String parentDataSourceIdentityToken) throws Exception
	{
		log.debug("Attempting to set client_min_messages=WARNING for pooled connection: {} ", c);
		c.prepareStatement("SET client_min_messages=WARNING").execute();

		c.setClientInfo(CLIENTINFO_ApplicationName, "metasfresh");
	}

	@Override
	public void onCheckIn(final Connection c, final String parentDataSourceIdentityToken) throws Exception
	{
		// NOTE: it's much more efficient to reset the ApplicationName here because this method is called in another thread
		c.setClientInfo(CLIENTINFO_ApplicationName, "metasfresh/returned-to-pool"); // task 08353
	}

	@Override
	public void onCheckOut(final Connection c, final String parentDataSourceIdentityToken) throws Exception
	{
		// NOTE: it's much more efficient to reset the ApplicationName here because this method is called in another thread
		c.setClientInfo(CLIENTINFO_ApplicationName, "metasfresh/checked-out-from-pool"); // task 08353

		final IConnectionCustomizerService connectionCustomizerService = Services.get(IConnectionCustomizerService.class);
		connectionCustomizerService.fireRegisteredCustomizers(c);
	}

}
