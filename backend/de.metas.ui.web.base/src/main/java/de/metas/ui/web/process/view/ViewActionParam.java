package de.metas.ui.web.process.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;

 

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface ViewActionParam
{
	String caption();

	boolean mandatory() default true;

	DocumentFieldWidgetType widgetType();

	String sqlLookupTableName() default "";
}
