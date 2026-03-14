package de.metas.javaclasses;

/** */


import java.util.List;
import java.util.Properties;

import de.metas.javaclasses.model.I_AD_JavaClass;
import de.metas.javaclasses.model.I_AD_JavaClass_Type;
import de.metas.util.ISingletonService;

public interface IJavaClassDAO extends ISingletonService
{
	List<I_AD_JavaClass> retrieveAllJavaClasses(I_AD_JavaClass_Type type);

	List<I_AD_JavaClass> retrieveJavaClasses(Properties ctx, String javaClassTypeInternalName);

	/**
	 * Gets {@link I_AD_JavaClass} for given ID.
	 *
	 * If no record is found for ID, null is returned.
	 *
	 * @param ctx
	 * @param adJavaClassId
	 * @return java class or null
	 */
	I_AD_JavaClass retriveJavaClassOrNull(final Properties ctx, final int adJavaClassId);

	I_AD_JavaClass_Type retrieveJavaClassTypeOrNull(Properties ctx, String internalName);

}
