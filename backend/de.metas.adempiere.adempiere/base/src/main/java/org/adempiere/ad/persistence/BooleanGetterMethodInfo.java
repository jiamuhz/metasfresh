package org.adempiere.ad.persistence;

/** */

import java.lang.reflect.Method;

/**
 * Boolean getter handler
 * <p>
 * e.g. org.compiere.model.I_C_Invoice.isProcessed()
 *
 * @author tsa
 */
/* package */class BooleanGetterMethodInfo extends AbstractModelMethodInfo
{

	private final String propertyName;
	private final Class<?> returnType;

	public BooleanGetterMethodInfo(final Method interfaceMethod, final String propertyName)
	{
		super(interfaceMethod);
		this.propertyName = propertyName;
		this.returnType = interfaceMethod.getReturnType();
	}

	@Override
	public Object invoke(final IModelInternalAccessor model, final Object[] methodArgs) throws Exception
	{
		// TODO: optimization: cache matched PropertyName and ColumnIndex

		String propertyNameToUse = propertyName;
		int ii = model.getColumnIndex(propertyNameToUse);
		if (ii >= 0)
		{
			return model.getValue(propertyNameToUse, ii, returnType);
		}

		propertyNameToUse = "Is" + propertyName;
		ii = model.getColumnIndex(propertyNameToUse);
		if (ii >= 0)
		{
			return model.getValue(propertyNameToUse, ii, returnType);
		}

		propertyNameToUse = "is" + propertyName;
		ii = model.getColumnIndex(propertyNameToUse);
		if (ii >= 0)
		{
			return model.getValue(propertyNameToUse, ii, returnType);
		}

		final Method interfaceMethod = getInterfaceMethod();
		if (interfaceMethod.isDefault())
		{
			return model.invokeParent(interfaceMethod, methodArgs);
		}

		//
		throw new IllegalArgumentException("Method " + interfaceMethod + " is not supported on model " + model);
	}

}
