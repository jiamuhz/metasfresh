package org.adempiere.ad.modelvalidator.annotations;

/** */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated method shall be triggered on a particular document action model validator event.
 *
 *
 * Your annotated method can have following formats:
 * <ul>
 * <li>public void myMethod(final MyModelClass model)
 * <li>public void myMethod(final MyModelClass model, final int timing)
 * </ul>
 *
 * @author tsa
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface DocValidate
{
	/**
	 * On which timings shall we call the annotated methods.
	 *
	 * For more information about timings, please check {@link org.compiere.model.ModelValidator} values.
	 *
	 * At least one event shall be specified.
	 */
	int[] timings();

	/**
	 * <code>true</code> if this method shall be executed after transaction commit.
	 * <p>
	 * WARNINGs:<br>
	 * * if you want do store things to DB, you need to do so in your own local transaction<br>
	 * * any failure will be just logged and will not prevent execution<br>
	 */
	boolean afterCommit() default false;
}
