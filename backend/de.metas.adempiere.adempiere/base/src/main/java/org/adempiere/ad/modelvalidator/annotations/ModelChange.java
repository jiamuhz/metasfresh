package org.adempiere.ad.modelvalidator.annotations;

/** */


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.adempiere.ad.modelvalidator.ModelChangeType;

/**
 * Indicates that the annotated method shall be triggered on a particular model change validator event.
 *
 * Your annotated method can have following formats:
 * <ul>
 * <li>public void myMethod(final MyModelClass model)
 * <li>public void myMethod(final MyModelClass model, final ModelChangeType changeType)
 * </ul>
 *
 * @author tsa
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface ModelChange
{

	/**
	 * On which model change events shall we call the annotated methods.
	 *
	 * For more information about events, please check {@link ModelChangeType#getChangeType()} values.
	 *
	 * At least one event shall be specified.
	 */
	int[] timings() default {};

	/**
	 * Indicate that the method shall be called only if one of the given fields were changed.
	 * <p>
	 * This is optional and can be overridden by {@link #ignoreColumnsChanged()}.
	 */
	String[] ifColumnsChanged() default {};

	/**
	 * Specify the columns that shall be excluded from "column value changed" checking.
	 * <p>
	 * This is optional and overrides possible {@link #ifColumnsChanged()} settings. Example:
	 *
	 * <pre>
	 * ifColumnsChanged={IsPaid, C_BBartner_ID}, ignoreColumnsChanged={IsPaid}
	 * </pre>
	 *
	 * => in this case, the system will only check for <code>C_BBartner_ID</code>.
	 * <p>
	 * If the annotation is specifying only ignore columns but no {@link #ifColumnsChanged()}-columns then all columns excluding the ignore columns will be checked for changes.
	 */
	String[] ignoreColumnsChanged() default {};

	/**
	 * If true, this event shall be triggered only if there was an UI/user action (i.e. user changed the record manually from a window)
	 *
	 * @return
	 */
	boolean ifUIAction() default false;

	/**
	 * <code>true</code> if this method will be scheduled to be executed after transaction commit.
	 * <p>
	 * WARNINGs:<br>
	 * * if you want do store things to DB, you need to do so in your own local transaction<br>
	 * * any failure will be just logged and will not prevent execution<br>
	 */
	boolean afterCommit() default false;
	

	/**
	 * Skip calling this interceptor if we are copying (with details)
	 */
	boolean skipIfCopying() default false;
}
