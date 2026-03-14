package org.adempiere.server.rpl.api;

/** */


import java.util.Properties;

import org.w3c.dom.Document;

/**
 * Helper used to actual import an XML document
 * 
 * @author tsa
 * 
 */
public interface IImportHelper
{

	/**
	 * After initialization API calls this method to configure initial context
	 * 
	 * @param initialCtx
	 */
	public void setInitialCtx(Properties initialCtx);

	/**
	 * Import XML document
	 * 
	 * @param ctx
	 * @param result
	 * @param documentToBeImported
	 * @param trxName
	 * @return response document or null if there is no response
	 */
	Document importXMLDocument(StringBuilder result, Document documentToBeImported, String trxName);
}
