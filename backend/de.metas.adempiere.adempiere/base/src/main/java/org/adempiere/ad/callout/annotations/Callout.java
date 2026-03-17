package org.adempiere.ad.callout.annotations;

/** */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.adempiere.ad.callout.spi.IProgramaticCalloutProvider;

/**
 * Classes annotated as callout may be registered with an instance of {@link IProgramaticCalloutProvider}.
 * The system will then invoke their methods that are annotated with {@link CalloutMethod}.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Callout
{
	/** Level on which a call is considered "recursive" */
	enum RecursionAvoidanceLevel
	{
		/**
		 * Consider as recursive call a call which is about a method of the same class
		 */
		CalloutClass,
		/**
		 * Consider as recursive call a call which is about a method which is already in call trace
		 */
		CalloutMethod,
		/**
		 * Consider as recursive call a call which is about a field(column name) which is already in call trace
		 */
		CalloutField,
	}

	/**
	 * Interface model class on which this Callout will be bound
	 *
	 * NOTE: class name shall be the same as the callout name (metas naming conventions)
	 */
	public Class<?> value();

	/**
	 * Configures on which level the callout execution recursion shall be avoided.
	 */
	public RecursionAvoidanceLevel recursionAvoidanceLevel() default RecursionAvoidanceLevel.CalloutClass;

}
