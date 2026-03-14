package org.compiere.db;

/** */

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.slf4j.Logger;

import de.metas.logging.LogManager;
import de.metas.util.Check;

import javax.annotation.Nullable;

/**
 * {@link CConnection}'s attributes.
 *
 * @author tsa
 *
 */
@Builder
@Data
public final class CConnectionAttributes
{
	public static CConnectionAttributes empty()
	{
		return CConnectionAttributes.builder().build();
	}

	/**
	 * Creates connection attributes object based on given attributes string.
	 *
	 * NOTE: it is assumed that the connection attributes string was produced by {@link #toString()} method.
	 */
	public static CConnectionAttributes of(@NonNull final String attributesStr)
	{
		// NOTE: keep in sync with toString()
		final String dbPortString = attributesStr.substring(attributesStr.indexOf("DBport=") + 7, attributesStr.indexOf(",DBname="));

		return CConnectionAttributes.builder()
				.dbHost(attributesStr.substring(attributesStr.indexOf("DBhost=") + 7, attributesStr.indexOf(",DBport=")))
				.dbPort(parseDbPort(dbPortString))
				.dbName(getSubString(attributesStr, "DBname=", ","))
				.dbUid(attributesStr.substring(attributesStr.indexOf("UID=") + 4, attributesStr.indexOf(",PWD=")))
				.dbPwd(attributesStr.substring(attributesStr.indexOf("PWD=") + 4, attributesStr.indexOf("]")))
				.build();
	}

	@Nullable
	private static String getSubString(@NonNull final String attributesStr, @NonNull final String before, @NonNull final String after)
	{
		final int indexOfAppsPasswordStart = attributesStr.indexOf(before);
		final int indexOfAppsPasswordEnd = attributesStr.indexOf(after, indexOfAppsPasswordStart);
		if (indexOfAppsPasswordStart >= 0 && indexOfAppsPasswordEnd >= 0)
		{
			return attributesStr.substring(indexOfAppsPasswordStart + before.length(), indexOfAppsPasswordEnd);
		}
		return null;
	}

	private static final transient Logger logger = LogManager.getLogger(CConnectionAttributes.class);

	@Builder.Default
	private String dbHost = "localhost";

	@Builder.Default
	private int dbPort = 5432;

	@Builder.Default
	private String dbName = "metasfresh";
	@Builder.Default
	private String dbUid = "metasfresh";
	@Builder.Default
	private String dbPwd = "metasfresh";

	/**
	 * Builds connection attributes string representation.
	 *
	 * This string can be parsed back by using {@link #of(String)}.
	 *
	 * @return connection attributes string representation
	 */
	@Override
	public String toString()
	{
		// NOTE: keep in sync with the parser!!!

		final StringBuilder sb = new StringBuilder("CConnection[");
		sb
				.append("DBhost=").append(dbHost)
				.append(",DBport=").append(dbPort)
				.append(",DBname=").append(dbName)
				.append(",UID=").append(dbUid)
				.append(",PWD=").append(dbPwd);
		sb.append("]");
		return sb.toString();
	}	// toStringLong


	private static int parseDbPort(final String dbPortString)
	{
		try
		{
			if (Check.isBlank(dbPortString))
			{
				return -1;
			}
			else
			{
				return Integer.parseInt(dbPortString);
			}
		}
		catch (final Exception e)
		{
			logger.error("Error parsing db port: " + dbPortString, e);
			return -1;
		}
	} 	// setDbPort
}
