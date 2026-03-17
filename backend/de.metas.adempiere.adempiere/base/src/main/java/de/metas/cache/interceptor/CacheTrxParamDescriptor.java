package de.metas.cache.interceptor;

/** */

import java.lang.annotation.Annotation;

import org.adempiere.model.InterfaceWrapperHelper;
import org.slf4j.Logger;

import de.metas.cache.annotation.CacheTrx;
import de.metas.logging.LogManager;

/**
 * Handles {@link CacheTrx} annotation.
 *
 * @author tsa
 *
 */
class CacheTrxParamDescriptor implements ICachedMethodPartDescriptor
{
	private static final transient Logger logger = LogManager.getLogger(CacheTrxParamDescriptor.class);

	private final int parameterIndex;
	private final boolean isModel;

	CacheTrxParamDescriptor(final Class<?> parameterType, final int parameterIndex, final Annotation annotation)
	{
		super();

		this.parameterIndex = parameterIndex;
		if (String.class.isAssignableFrom(parameterType))
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
		final Object trxNameObj = params[parameterIndex];

		String trxName = null;
		boolean error = false;
		Exception errorException = null;
		if (trxNameObj == null)
		{
			trxName = null;
		}
		else if (isModel)
		{
			try
			{
				trxName = InterfaceWrapperHelper.getTrxName(trxNameObj);
			}
			catch (final Exception ex)
			{
				error = true;
				errorException = ex;
			}
		}
		else if (trxNameObj instanceof String)
		{
			trxName = (String)trxNameObj;
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
					.setInvalidParameter(parameterIndex, trxNameObj)
					.setAnnotation(CacheTrx.class);
			logger.warn("Invalid parameter. Skip caching", ex);
			return;
		}

		// NOTE: we assume the caller will separate caches per transaction, so there is no point to have it as part of the cache key
		// More,
		// * consider the case of ThreadInherited transactions which we would have to resolve them here
		// * it shall be the responsibility of the builder to include it in key, in case it's really needed
		// keyBuilder.add(trxName);

		keyBuilder.setTrxName(trxName);
	}
}
