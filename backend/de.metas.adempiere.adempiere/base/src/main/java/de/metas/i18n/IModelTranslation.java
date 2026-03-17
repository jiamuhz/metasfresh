package de.metas.i18n;

/** */

/**
 * Contains all translated column values for a particular model (record) and a particular AD_Language.
 * 
 *
 *
 */
public interface IModelTranslation
{
	String getAD_Language();

	/** @return true if given column is translated */
	boolean isTranslated(String columnName);

	/** @return translated value of given column */
	String getTranslation(final String columnName);
}
