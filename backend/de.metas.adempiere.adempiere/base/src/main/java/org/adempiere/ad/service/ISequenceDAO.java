package org.adempiere.ad.service;

/** */

import java.util.Properties;

import org.compiere.model.I_AD_Sequence;

import de.metas.document.sequence.IDocumentNoBuilderFactory;
import de.metas.util.ISingletonService;

/**
 * Service to access the actual {@link I_AD_Sequence} table. To get actual sequence numbers, use {@link IDocumentNoBuilderFactory}.
 *
 */
public interface ISequenceDAO extends ISingletonService
{
	I_AD_Sequence retrieveTableSequenceOrNull(final Properties ctx, final String tableName, final String trxName);

	I_AD_Sequence retrieveTableSequenceOrNull(final Properties ctx, final String tableName);

	ITableSequenceChecker createTableSequenceChecker(Properties ctx);

	/**
	 * Rename the sequence name when a given table name was changed
	 */
	void renameTableSequence(Properties ctx, String tableNameOld, String tableNameNew);
}
