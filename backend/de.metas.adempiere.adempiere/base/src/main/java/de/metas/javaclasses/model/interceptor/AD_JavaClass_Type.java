package de.metas.javaclasses.model.interceptor;

/** */

import java.util.List;

import org.adempiere.ad.callout.annotations.Callout;
import org.adempiere.ad.callout.annotations.CalloutMethod;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.compiere.model.ModelValidator;

import de.metas.javaclasses.IJavaClassBL;
import de.metas.javaclasses.IJavaClassDAO;
import de.metas.javaclasses.IJavaClassTypeBL;
import de.metas.javaclasses.model.I_AD_JavaClass;
import de.metas.javaclasses.model.I_AD_JavaClass_Type;
import de.metas.util.Services;

/**
 * Note: this class is both an interceptor and a callout.
 *
 * @author metas-dev <dev@metasfresh.com>
 *
 */
@Interceptor(I_AD_JavaClass_Type.class)
@Callout(I_AD_JavaClass_Type.class)
public class AD_JavaClass_Type
{
	/**
	 * Loads the given {@code type}'s {@link I_AD_JavaClass} records and attempts to initialize them. This method only needs to be called on java class type changes (because new types don't have
	 * classes). It needs to be called <b>after</b> the change, because the classes are loaded from DB and the in turn load the type from DB (so it's mandatory that the type has already been stored
	 * within the current transaction).
	 *
	 * @param type
	 */
	@ModelChange(
			timings = { ModelValidator.TYPE_AFTER_CHANGE },
			ifColumnsChanged = { I_AD_JavaClass_Type.COLUMNNAME_Classname })
	public void onChangeClassname(final I_AD_JavaClass_Type type)
	{
		final IJavaClassDAO javaClassDAO = Services.get(IJavaClassDAO.class);

		final List<I_AD_JavaClass> classes = javaClassDAO.retrieveAllJavaClasses(type);
		for (final I_AD_JavaClass clazz : classes)
		{
			if(!clazz.isActive())
			{
				continue;
			}
			if(clazz.isInterface())
			{
				continue;
			}
			Services.get(IJavaClassBL.class).newInstance(clazz);
		}
	}

	@ModelChange(
			timings = { ModelValidator.TYPE_BEFORE_CHANGE, ModelValidator.TYPE_BEFORE_NEW },
			ifColumnsChanged = { I_AD_JavaClass_Type.COLUMNNAME_Classname })
	public void checkClassAndUpdateInternalName(final I_AD_JavaClass_Type javaClassType)
	{
		final boolean throwEx = true; // we don't want an exception.
		checkClassAndUpdateInternalName(javaClassType, throwEx);
	}

	@CalloutMethod(columnNames = I_AD_JavaClass_Type.COLUMNNAME_Classname)
	public void checkClassAndUpdateInternalName(I_AD_JavaClass_Type javaClassType, ICalloutField field)
	{
		final boolean throwEx = false; // we don't want an exception.
		checkClassAndUpdateInternalName(javaClassType, throwEx);
	}

	private void checkClassAndUpdateInternalName(I_AD_JavaClass_Type javaClassType, final boolean throwEx)
	{
		final IJavaClassTypeBL javaClassTypeBL = Services.get(IJavaClassTypeBL.class);

		final Class<?> classNameClass = javaClassTypeBL.checkClassName(javaClassType, throwEx);
		if (classNameClass == null)
		{
			javaClassType.setInternalName(null);
			return;
		}
		javaClassType.setInternalName(classNameClass.getName());
	}
}
