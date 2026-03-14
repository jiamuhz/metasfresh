package org.adempiere.server.rpl.api;

/** */


import java.util.Iterator;
import java.util.Properties;

import org.adempiere.server.rpl.IImportProcessor;
import org.adempiere.util.lang.IAutoCloseable;
import org.compiere.model.AdempiereProcessor;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_EXP_FormatLine;
import org.compiere.model.I_IMP_Processor;
import org.compiere.model.I_IMP_ProcessorLog;
import org.compiere.model.I_IMP_ProcessorParameter;

import de.metas.util.ILoggable;
import de.metas.util.ISingletonService;
import de.metas.util.Loggables;

public interface IIMPProcessorBL extends ISingletonService
{
	I_IMP_ProcessorLog createLog(I_IMP_Processor impProcessor, String summary, String text, String reference, Throwable error);

	/**
	 * Set up a thread-local loggable that can be used within import and export business logic.
	 * <p>
	 * Hints:
	 * <li>Use {@link Loggables#get()} to retrieve this loggable.
	 * <li>All {@link ILoggable#addLog(String, Object...)} invocations
	 * will call {@link #createLog(I_IMP_Processor, String, String, String, Throwable)} with the given {@code impProcessor} and {@code reference}.
	 */
	IAutoCloseable setupTemporaryLoggable(I_IMP_Processor impProcessor, String reference);

	/**
	 * Gets XML message as String
	 *
	 * @param pLog
	 * @return xml message or null
	 */
	String getXmlMessage(I_IMP_ProcessorLog pLog);

	org.w3c.dom.Document getXmlDocument(I_IMP_ProcessorLog plog);

	/**
	 * Mark error log as resolved (e.g. to be called after it was resubmitted successfully).
	 *
	 * @param plog
	 */
	void markResolved(I_IMP_ProcessorLog plog);

	/**
	 * Invoke the import processor and attempt to (re-)import the given logs.
	 */
	void resubmit(Iterator<I_IMP_ProcessorLog> logs, boolean failfast, ILoggable loggable);

	IImportProcessor getIImportProcessor(I_IMP_Processor impProcessor);

	/**
	 * Create/Update Parameter
	 *
	 * @param impProcessor
	 * @param key parameter key
	 * @param name parameter name (human readable)
	 * @param desc parameter description
	 * @param help parameter help
	 * @param value parameter value
	 * @return created/updated parameter
	 */
	I_IMP_ProcessorParameter createParameter(I_IMP_Processor impProcessor, String key, String name, String desc, String help, String value);

	I_IMP_ProcessorParameter createParameter(I_IMP_Processor impProcessor, String key, String value);

	AdempiereProcessor asAdempiereProcessor(I_IMP_Processor impProcessor);

	I_IMP_Processor getIMP_Processor(AdempiereProcessor adempiereProcessor);

	IImportHelper createImportHelper(Properties initialCtx);

	void setImportHelperClass(Class<? extends IImportHelper> importHelperClass);

	/**
	 * Returns the reference of the given column or (if an overriding reference is set there) from the given line.
	 *
	 * @param column
	 * @param formatLine
	 * @return AD_Reference of column and formatLine
	 */
	int getAD_Reference_ID(I_AD_Column column, I_EXP_FormatLine formatLine);

	/**
	 * Returns the table and column that the given embedded or referencing format line points to. Throws an exception if the given line's type is neither <code>ReferencedEXPFormat</code> nor
	 * <code>EmbeddedEXPFormat</code>.
	 *
	 * @param formatLine
	 * @return
	 */
	ITableAndColumn getTargetTableAndColumn(I_EXP_FormatLine formatLine);

	/**
	 * Simple interface to return the result of {@link IIMPProcessorBL#getTargetTableAndColumn(I_EXP_FormatLine)}
	 *
	 *
	 */
	interface ITableAndColumn
	{
		String getTableName();

		String getColumnName();
	}
}
