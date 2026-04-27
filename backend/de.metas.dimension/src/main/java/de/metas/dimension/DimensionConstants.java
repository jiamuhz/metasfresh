package de.metas.dimension;

import de.metas.i18n.AdMessageKey;

 
public final class DimensionConstants
{
	private DimensionConstants()
	{
	}

	/**
	 * Entity type, also database schema name.
	 */
	public static final String ENTITY_TYPE = "\"de.metas.dimension\"";

	/**
	 * NAme of a view that selects all values for a given attribute dimension specification.
	 */
	public static final String VIEW_DIM_Dimension_Spec_Attribute_AllValue = ENTITY_TYPE + ".DIM_Dimension_Spec_Attribute_AllValues";


	/**
	 * Placeholder for empty attribute value in dimension
	 */
	public static final String DIM_EMPTY = "DIM_EMPTY";

	/**
	 * Message for non or empty attribute value.
	 */
	public static final AdMessageKey MSG_NoneOrEmpty = AdMessageKey.of("NoneOrEmpty");

}
