package org.adempiere.archive.api;

/** */

import de.metas.report.PrintCopies;
import de.metas.util.ISingletonService;
import lombok.NonNull;
import org.adempiere.ad.persistence.ModelDynAttributeAccessor;
import org.adempiere.archive.AdArchive;
import org.adempiere.archive.ArchiveId;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.I_AD_Archive;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.Optional;

/**
 * Archive related business logic
 *
 * @author tsa
 *
 */
public interface IArchiveBL extends ISingletonService
{
	/**
	 * Allow to store the required number of copies per archive. Storing it inside the AD_Archive record (i.e. DB) makes no sense, because one AD_Archive can be printed multiple times.
	 * The value that is set here will be used in the respective printing queue item
	 *
	 * @implSpec Task <a href="https://github.com/metasfresh/metasfresh/issues/1240">1240</a>
	 */
	ModelDynAttributeAccessor<I_AD_Archive, PrintCopies> COPIES_PER_ARCHIVE = new ModelDynAttributeAccessor<>(PrintCopies.class);

	AdArchive getById(@NonNull ArchiveId id);

	@NonNull
	ArchiveResult archive(@NonNull ArchiveRequest request);

	String getContentType(I_AD_Archive archive);

	byte[] getBinaryData(I_AD_Archive archive);

	InputStream getBinaryDataAsStream(I_AD_Archive archive);

	void setBinaryData(I_AD_Archive archive, byte[] data);

	Optional<AdArchive> getLastArchive(@NonNull TableRecordReference reference);

	Optional<I_AD_Archive> getLastArchiveRecord(@NonNull TableRecordReference reference);

	Optional<Resource> getLastArchiveBinaryData(@NonNull TableRecordReference reference);
}
