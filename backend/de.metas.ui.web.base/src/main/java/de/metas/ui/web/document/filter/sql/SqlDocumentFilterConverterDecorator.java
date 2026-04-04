package de.metas.ui.web.document.filter.sql;

import org.springframework.stereotype.Component;

import de.metas.ui.web.view.SqlViewFactory;
import de.metas.ui.web.window.datatypes.WindowId;

 

/**
 * Implementors shall be annotated with {@link Component}, discovered by spring and be autowired into {@link SqlViewFactory}.
 * <p>
 * When a view is created for the implementor's {@link WindowId}, the "normal" {@link SqlDocumentFilterConverter} can be wrapped.<br>
 * It can later be called and its results be augmented.
 * 
 *
 * 
 * @task Extend framework to allow modification of standard filter results https://github.com/metasfresh/metasfresh-webui-api/issues/628
 */
public interface SqlDocumentFilterConverterDecorator
{
	WindowId getWindowId();

	SqlDocumentFilterConverter decorate(SqlDocumentFilterConverter converter);
}
