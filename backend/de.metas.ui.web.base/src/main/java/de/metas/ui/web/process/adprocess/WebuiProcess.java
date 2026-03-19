package de.metas.ui.web.process.adprocess;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.metas.ui.web.window.datatypes.PanelLayoutType;



/**
 * Annotation used to specify more webui related options to a process.
 *
 *
 *
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface WebuiProcess
{
	PanelLayoutType layoutType() default PanelLayoutType.Panel;
}
