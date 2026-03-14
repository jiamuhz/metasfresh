package org.adempiere.ad.modelvalidator.annotations;

/** */


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Same as {@link Interceptor}
 *
 * WARNING: this annotation is about to be deprecated. Please consider using {@link Interceptor}.
 *
 * @author tsa
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
// @Deprecated // commented out because we will get too many deprecations
public @interface Validator
{
	/**
	 * Same as {@link Interceptor#value()}
	 *
	 * @return
	 */
	public Class<?> value();
}
