package de.metas.ui.web.process.descriptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.metas.process.JavaProcess;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;



/**
 * Annotate {@link JavaProcess} methods which are responsible for providing lookup values for a given parameter.
 * In this case, the default parameter's lookup (as defined in application dictionary) won't be used.
 *
 * The annotated method shall have following characteristics:
 * <ul>
 * <li>return type shall ALWAYS be {@link LookupValuesList}
 * <li>parameters (if any) can be: {@link LookupDataSourceContext}
 * </ul>
 *
 * Note: please take care that the respective parameter's default value is among the list returned by your method implementation.
 *
 *
 *
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface ProcessParamLookupValuesProvider
{
	/** parameter name for whom we will provide the lookup values */
	String parameterName();

	/** list of parameter names on which the lookup values fetching depends on */
	String[] dependsOn() default {};

	/** true if we will provide {@link IntegerLookupValue}s, else {@link StringLookupValue}s are assumed */
	boolean numericKey();

	LookupSource lookupSource() default LookupSource.list;

	/** optional lookup table name; needed for zoom into */
	String lookupTableName() default "";
}
