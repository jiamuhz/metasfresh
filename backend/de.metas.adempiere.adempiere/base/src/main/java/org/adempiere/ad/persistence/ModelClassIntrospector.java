package org.adempiere.ad.persistence;

/** */

import org.adempiere.model.InterfaceWrapperHelper;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Used to fetch {@link IModelClassInfo}s from model classes.
 *
 * @author tsa
 *
 */
public final class ModelClassIntrospector implements IModelClassIntrospector
{
	public static final IModelClassIntrospector instance = new ModelClassIntrospector();

	public static IModelClassIntrospector getInstance()
	{
		return instance;
	}

	private final Map<Class<?>, Optional<IModelClassInfo>> modelClassInfos = new ConcurrentHashMap<>(500);

	private ModelClassIntrospector()
	{
	}

	@Nullable
	@Override
	public IModelClassInfo getModelClassInfo(@Nullable final Class<?> clazz)
	{
		if (clazz == null)
		{
			return null;
		}

		return modelClassInfos
				.computeIfAbsent(clazz, this::createModelClassInfo)
				.orElse(null);
	}

	private Optional<IModelClassInfo> createModelClassInfo(final Class<?> clazz)
	{
		final String tableName = getTableNameOrNull(clazz);
		if (tableName != null || clazz.isInterface())
		{
			final IModelClassInfo modelClassInfo = new ModelClassInfo(this, clazz, tableName);
			return Optional.of(modelClassInfo);
		}
		else
		{
			return Optional.empty();
		}

	}

	public Map<Method, IModelMethodInfo> createModelMethodInfos(final Class<?> clazz)
	{
		final Map<Method, IModelMethodInfo> modelMethodsInfo = new HashMap<>();
		for (final Method method : clazz.getMethods())
		{
			final IModelMethodInfo modelMethodInfo = createModelMethodInfo(method);
			modelMethodsInfo.put(method, modelMethodInfo);
		}

		return modelMethodsInfo;
	}

	/**
	 * Creates {@link IModelMethodInfo} for given <code>method</code>
	 *
	 * @param method
	 * @return method info; never return null
	 */
	public IModelMethodInfo createModelMethodInfo(final Method method)
	{
		String methodName = method.getName();
		final Class<?>[] parameters = method.getParameterTypes();
		final int parametersCount = parameters == null ? 0 : parameters.length;

		if (methodName.startsWith("set") && parametersCount == 1)
		{
			final String propertyName = methodName.substring(3); // method name without "set" prefix
			final Class<?> paramType = parameters[0];
			if (InterfaceWrapperHelper.isModelInterface(paramType))
			{
				final ModelSetterMethodInfo methodInfo = new ModelSetterMethodInfo(method, paramType, propertyName + "_ID");
				return methodInfo;
			}
			else
			{
				final ValueSetterMethodInfo methodInfo = new ValueSetterMethodInfo(method, propertyName);
				return methodInfo;
			}
		}
		else if (methodName.startsWith("get") && (parametersCount == 0))
		{
			String propertyName = methodName.substring(3);

			if (InterfaceWrapperHelper.isModelInterface(method.getReturnType()))
			{
				final String columnName = propertyName + "_ID";
				final ModelGetterMethodInfo methodInfo = new ModelGetterMethodInfo(method, columnName);
				return methodInfo;
			}
			else
			{
				final ValueGetterMethodInfo methodInfo = new ValueGetterMethodInfo(method, propertyName);
				return methodInfo;
			}
		}
		else if (methodName.startsWith("is") && (parametersCount == 0))
		{
			final String propertyName = methodName.substring(2);
			final BooleanGetterMethodInfo methodInfo = new BooleanGetterMethodInfo(method, propertyName);
			return methodInfo;
		}
		else if (methodName.equals("equals") && parametersCount == 1)
		{
			final EqualsMethodInfo methodInfo = new EqualsMethodInfo(method);
			return methodInfo;
		}
		else
		{
			final InvokeParentMethodInfo methodInfo = new InvokeParentMethodInfo(method);
			return methodInfo;
		}
	}

	private static final String getTableNameOrNull(final Class<?> clazz)
	{
		try
		{
			final Field field = clazz.getField("Table_Name");
			if (!field.isAccessible())
			{
				field.setAccessible(true);
			}
			return (String)field.get(null);
		}
		catch (final Exception e)
		{
			return null;
		}
	}

}
