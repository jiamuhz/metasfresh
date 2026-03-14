/**
 *
 */
package de.metas.adempiere.service;

/** */


import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;

import de.metas.process.ProcessInstanceInfo;
import de.metas.util.ISingletonService;

/**
 * @author tsa
 *
 */
public interface IPrinterRoutingBL extends ISingletonService
{
	// Printer Types - please keep in sync with X_AD_Printer.PRINTERTYPE_*
	String PRINTERTYPE_General = "G";
	String PRINTERTYPE_Fax = "F";
	String PRINTERTYPE_Label = "L";

	String findPrinterName(Properties ctx, int C_DocType_ID, int AD_Process_ID, int AD_Table_ID, String printerType);

	/**
	 * Uses the properties of the given <code>pi</code> to retrieve the printer to use via <code>AD_PrinterRouting</code>
	 */
	String findPrinterName(ProcessInstanceInfo pi);

	/**
	 * Try to find printing service for given parameters.
	 *
	 * @return printing service; never return null
	 * @throws AdempiereException if printing service was not found or printerType is not supported
	 */
	IPrintingService findPrintingService(Properties ctx, int C_DocType_ID, int AD_Process_ID, int AD_table_ID, String printerType);

	String getDefaultPrinterName();

	String getDefaultPrinterName(String printerType);
}
