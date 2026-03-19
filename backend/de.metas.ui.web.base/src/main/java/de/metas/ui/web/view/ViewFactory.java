package de.metas.ui.web.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;

import de.metas.ui.web.view.json.JSONViewDataType;



/**
 * Used to annotate {@link IViewFactory} implementations which shall be automatically discovered and registered.
 *
 *
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface ViewFactory
{
	String windowId();

	/** supported view types (empty means all) */
	JSONViewDataType[] viewTypes() default {};
}
