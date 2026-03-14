package de.metas.javaclasses.model.interceptor;

/** */

import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.ad.modelvalidator.annotations.Validator;
import org.compiere.model.ModelValidator;

import de.metas.javaclasses.IJavaClassBL;
import de.metas.javaclasses.model.I_AD_JavaClass;
import de.metas.util.Services;

@Validator(I_AD_JavaClass.class)
public class AD_JavaClass
{
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_CHANGE, ModelValidator.TYPE_BEFORE_NEW })
	public void onChangeClassname(final I_AD_JavaClass javaClassDef)
	{
		final IJavaClassBL javaClassBL = Services.get(IJavaClassBL.class);
		final Class<?> clazz = javaClassBL.verifyClassName(javaClassDef);
		javaClassDef.setIsInterface(clazz.isInterface());

		if (!clazz.isInterface())
		{
			// additionally try to instantiate the class
			javaClassBL.newInstance(javaClassDef);
		}
	}
}
