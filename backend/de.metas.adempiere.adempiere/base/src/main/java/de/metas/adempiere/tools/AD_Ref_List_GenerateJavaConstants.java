package de.metas.adempiere.tools;

import ch.qos.logback.classic.Level;
import de.metas.logging.LogManager;
import de.metas.util.Check;
import org.adempiere.ad.migration.logger.MigrationScriptFileLoggerHolder;
import org.adempiere.ad.persistence.modelgen.ADRefListGenerator;
import org.adempiere.ad.persistence.modelgen.ListInfo;
import org.adempiere.ad.persistence.modelgen.TableAndColumnInfoRepository;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.tools.AdempiereToolsHelper;

/** */

/**
 * Helper CLI tool to generate java constants code for a given AD_Reference_ID.
 * 
 *
 *
 */
public class AD_Ref_List_GenerateJavaConstants
{
	public static void main(final String[] args)
	{
		//
		// Start ADempiere
		AdempiereToolsHelper.getInstance().startupMinimal();
		LogManager.setLevel(Level.DEBUG);
		MigrationScriptFileLoggerHolder.setEnabled(false); // metas: don't log migration scripts

		final TableAndColumnInfoRepository repository = new TableAndColumnInfoRepository();

		//
		// Get AD_Reference_ID parameter
		if (args.length < 1)
		{
			throw new AdempiereException("Provide AD_Reference_ID parameter");
		}
		final String adReferenceIdStr = args[0];
		Check.assumeNotEmpty(adReferenceIdStr, "Valid AD_Reference_ID parameter: {}", adReferenceIdStr);
		final int adReferenceId = Integer.parseInt(adReferenceIdStr.trim());

		//
		// Get the AD_Reference list info
		final ListInfo listInfo = repository.getListInfo(adReferenceId).orElse(null);
		if (listInfo == null)
		{
			throw new AdempiereException("No list info found for AD_Reference_ID=" + adReferenceId);
		}

		//
		// Generate the Java code
		final String javacode = ADRefListGenerator.newInstance()
				.setColumnName("MyColumnName")
				.setListInfo(listInfo)
				.generateConstants();

		//
		// Output the result
		System.out.println("Generated Java code:");
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println(javacode);
		System.out.println("--------------------------------------------------------------------------------------------");
	}
}
