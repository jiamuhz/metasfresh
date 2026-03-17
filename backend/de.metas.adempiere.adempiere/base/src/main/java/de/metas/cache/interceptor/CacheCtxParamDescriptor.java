package de.metas.cache.interceptor;

/** */

import java.lang.annotation.Annotation;
import java.util.Properties;

import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.Env;
import org.compiere.util.Util.ArrayKey;
import org.slf4j.Logger;

import de.metas.cache.annotation.CacheCtx;
import de.metas.logging.LogManager;

/**
 * Handles {@link CacheCtx} annotation.
 *
 * @author tsa
 *
 */
public class CacheCtxParamDescriptor implements ICachedMethodPartDescriptor
{
	private static final transient Logger logger = LogManager.getLogger(CacheCtxParamDescriptor.class);

	private final int parameterIndex;
	private final boolean isModel;

	CacheCtxParamDescriptor(final Class<?> parameterType, final int parameterIndex, final Annotation annotation)
	{
		super();

		this.parameterIndex = parameterIndex;
		if (Properties.class.isAssignableFrom(parameterType))
		{
			isModel = false;
		}
		else if (InterfaceWrapperHelper.isModelInterface(parameterType))
		{
			isModel = true;
		}
		else
		{
			throw new CacheIntrospectionException("Parameter has unsupported type")
					.setParameter(parameterIndex, parameterType);
		}
	}

	@Override
	public void extractKeyParts(final CacheKeyBuilder keyBuilder, final Object targetObject, final Object[] params)
	{
		final Object ctxObj = params[parameterIndex];
		if (ctxObj == null)
		{
			keyBuilder.setSkipCaching();

			final CacheGetException ex = new CacheGetException("Got null context parameter.")
					.setTargetObject(targetObject)
					.setMethodArguments(params)
					.setInvalidParameter(parameterIndex, ctxObj)
					.setAnnotation(CacheCtx.class);
			logger.warn("Got null context object. Skip caching", ex);
			return;
		}

		Properties ctx = null;
		boolean error = false;
		Exception errorException = null;
		if (isModel)
		{
			try
			{
				ctx = InterfaceWrapperHelper.getCtx(ctxObj);
			}
			catch (final Exception ex)
			{
				error = true;
				errorException = ex;
			}
		}
		else if (ctxObj instanceof Properties)
		{
			ctx = (Properties)ctxObj;
		}
		else
		{
			error = true;
		}

		if (error)
		{
			keyBuilder.setSkipCaching();

			final CacheGetException ex = new CacheGetException("Invalid parameter type.")
					.addSuppressIfNotNull(errorException)
					.setTargetObject(targetObject)
					.setMethodArguments(params)
					.setInvalidParameter(parameterIndex, ctxObj)
					.setAnnotation(CacheCtx.class);
			logger.warn("Invalid parameter type for @CacheCtx annotation. Skip caching.", ex);
			return;
		}

		final ArrayKey key = buildCacheKey(ctx);
		keyBuilder.add(key);
	}

	private static final ArrayKey buildCacheKey(final Properties ctx)
	{
		return new ArrayKey(
				Env.getAD_Client_ID(ctx),
				Env.getAD_Role_ID(ctx),
				Env.getAD_User_ID(ctx),
				Env.getAD_Language(ctx));
	}

	/**
	 * Method used to compare if to contexts are considered to be equal from caching perspective.
	 * Equality from caching perspective means that the following is equal:
	 * <ul>
	 * <li>AD_Client_ID
	 * <li>AD_Role_ID
	 * <li>AD_User_ID
	 * <li>AD_Language
	 * </ul>
	 *
	 * @param ctx1
	 * @param ctx2
	 * @return true if given contexts shall be considered equal from caching perspective
	 */
	public static final boolean isSameCtx(final Properties ctx1, final Properties ctx2)
	{
		if (ctx1 == ctx2)
		{
			return true;
		}
		if (ctx1 == null || ctx2 == null)
		{
			return false;
		}

		return buildCacheKey(ctx1).equals(buildCacheKey(ctx2));
	}
}
