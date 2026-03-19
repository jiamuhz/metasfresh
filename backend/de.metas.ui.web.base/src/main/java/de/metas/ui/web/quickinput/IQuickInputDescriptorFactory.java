package de.metas.ui.web.quickinput;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import de.metas.order.SOTrx;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.descriptor.DetailId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

 

/**
 * Quick input descriptor factory.
 *
 * Implementations shall be annotated with {@link Component} and they will be automatically discovered and registered.
 */
public interface IQuickInputDescriptorFactory
{
	/**
	 * Gets matching keys on which this factory shall be registered.
	 *
	 * NOTE to implementors: This method will be called once, when the factory is discovered and registered,
	 * so it's safe to compute the result just in time (instead of precomputing and storing it).
	 */
	Set<MatchingKey> getMatchingKeys();

	QuickInputDescriptor createQuickInputDescriptor(
			final DocumentType documentType,
			final DocumentId documentTypeId,
			final DetailId detailId,
			final Optional<SOTrx> soTrx);

	//
	//
	// -------------------------------------------------------------------
	//
	//

	/** Key used to identify the {@link IQuickInputDescriptorFactory} to be used */
	@Value
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class MatchingKey
	{
		public static MatchingKey includedDocument(final DocumentType documentType, final int documentTypeIdInt, final String tableName)
		{
			final DocumentId documentTypeId = DocumentId.of(documentTypeIdInt);
			return new MatchingKey(documentType, documentTypeId, tableName);
		}

		public static MatchingKey includedDocument(
				final DocumentType rootDocumentType,
				final DocumentId rootDocumentTypeId,
				final String includedTableName)
		{
			return new MatchingKey(rootDocumentType, rootDocumentTypeId, includedTableName);
		}

		public static MatchingKey ofTableName(final String tableName)
		{
			final DocumentType documentType = null;
			final DocumentId documentTypeId = null;
			return new MatchingKey(documentType, documentTypeId, tableName);
		}

		private final DocumentType documentType;
		private final DocumentId documentTypeId;
		private final String tableName;
	}
}
