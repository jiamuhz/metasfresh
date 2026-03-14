package de.metas.javaclasses;

/** */

import org.adempiere.exceptions.AdempiereException;

import de.metas.javaclasses.model.I_AD_JavaClass;
import de.metas.javaclasses.model.I_AD_JavaClass_Type;
import de.metas.util.ISingletonService;

public interface IJavaClassTypeBL extends ISingletonService
{
	/**
	 * Check the given {@code javaClassType}'s {@code Classname}.<br>
	 * If the name is empty then the method jsut returns <code>null</code>.<br>
	 * If the class for the given <code>Classname</code> can be loadedby the current thread's context class loader, then it is returned by the method.<br>
	 * * Otherwise the method throws an {@link AdempiereException}.
	 *
	 * @param javaClassType
	 * @param throwEx if <code>false</code>, no exception is thrown, but instead <code>null</code> is returned. USeful for callouts, when the class name is jsut currently entered into the field.
	 * @throws AdempiereException if the given {@code Classname} is set, but the class can't be loaded.
	 */
	Class<?> checkClassName(I_AD_JavaClass_Type javaClassType, boolean throwEx);

	/**
	 * Search the classpath for classes that
	 * <ul>
	 * <li>either, if the the given <code>javaClassType</code>'s {@link I_AD_JavaClass_Type#COLUMN_Classname} is not an annotation, implement or extend it
	 * <li>or, if the <code>Classname</code> is an annotation, are annotated with it (including subclasses of the annotated class!).
	 * </ul>
	 *
	 * The results from the classpath search is used to update the {@link I_AD_JavaClass}es that belong to the given <code>javaClassType</code>:
	 * <ul>
	 * <li>stale <code>AD_JavaClass</code> records are set to <code>IsActive='N'</code>
	 * <li>missing <code>AD_JavaClass</code> records are created
	 * <li>existing <code>AD_JavaClass</code> records that were set to <code>IsActive='N'</code> are reactivated if the respective class "reappeared".
	 * </ul>
	 *
	 * <p>
	 * Note: use {@link AD_JavaClass} to exclude a given class or interface from the search.
	 *
	 * @param javaClassType
	 */
	void updateClassRecordsList(I_AD_JavaClass_Type javaClassType);
}
