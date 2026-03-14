/**
 *
 */
package org.adempiere.model.tree.impl;

/** */

import de.metas.logging.LogManager;
import lombok.NonNull;
import org.adempiere.ad.table.api.TableName;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.tree.IPOTreeSupportFactory;
import org.adempiere.model.tree.spi.IPOTreeSupport;
import org.adempiere.model.tree.spi.impl.DefaultPOTreeSupport;
import org.slf4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tsa
 *
 */
public class POTreeSupportFactory implements IPOTreeSupportFactory
{
	private static final Logger logger = LogManager.getLogger(POTreeSupportFactory.class);

	private final ConcurrentHashMap<TableName, Class<? extends IPOTreeSupport>> map = new ConcurrentHashMap<>();

	@Override
	public IPOTreeSupport get(@NonNull final String tableName)
	{
		return get(TableName.ofString(tableName));
	}

	public IPOTreeSupport get(@NonNull final TableName tableName)
	{
		// NOTE: we need to create a new instance each time because IPOTreeSupport implementations are stateful

		final Class<? extends IPOTreeSupport> cl = map.get(tableName);

		final IPOTreeSupport result;
		if (cl == null)
		{
			result = new DefaultPOTreeSupport();
		}
		else
		{
			try
			{
				result = cl.getConstructor().newInstance();
			}
			catch (final Exception e)
			{
				throw new AdempiereException(e);
			}
		}
		result.setTableName(tableName.getAsString());
		return result;
	}

	@Override
	public void register(@NonNull final String tableName, @NonNull final Class<? extends IPOTreeSupport> clazz)
	{
		// do checks
		try
		{
			clazz.getConstructor();
		}
		catch (NoSuchMethodException e)
		{
			throw new AdempiereException("Class " + clazz + " does not have a public constructor without parameters", e);
		}

		// register
		map.put(TableName.ofString(tableName), clazz);

		logger.info("Registered {} for {}", clazz, tableName);
	}
}
