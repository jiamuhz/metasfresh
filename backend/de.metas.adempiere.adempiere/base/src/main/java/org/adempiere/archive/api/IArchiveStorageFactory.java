package org.adempiere.archive.api;

/** */

import de.metas.util.ISingletonService;
import lombok.Getter;
import lombok.NonNull;
import org.adempiere.archive.spi.IArchiveStorage;
import org.compiere.model.I_AD_Archive;

import java.util.Properties;

/**
 * Factory helper which creates instances of {@link IArchiveStorage} based on given context or given archive.
 *
 * @author tsa
 */
public interface IArchiveStorageFactory extends ISingletonService
{
	enum StorageType
	{
		Database("DB"),
		Filesystem("FS");

		@Getter
		private final String code;

		StorageType(@NonNull final String code)
		{
			this.code = code;
		}
	}

	/**
	 * AccessMode - from where the archive is accessed (client side, server side)
	 */
	enum AccessMode
	{
		CLIENT,
		SERVER,
		ALL,
	}

	/**
	 * Register a storage handler class
	 */
	void registerArchiveStorage(StorageType storageType, AccessMode accessMode, Class<? extends IArchiveStorage> storageClass);

	/**
	 * Default Archive Storage for context's tenant(AD_Client_ID).
	 */
	IArchiveStorage getArchiveStorage(final Properties ctx);

	/**
	 * Get storage for current AD_Client_ID and detected {@link AccessMode}.
	 */
	IArchiveStorage getArchiveStorage(Properties ctx, StorageType storageType);

	/**
	 * Get storage for current AD_Client_ID.
	 */
	IArchiveStorage getArchiveStorage(Properties ctx, StorageType storageType, AccessMode accessMode);

	/**
	 * Archive Storage used for given <code>archive</code>.
	 */
	IArchiveStorage getArchiveStorage(final I_AD_Archive archive);
}
