package de.metas.ui.web.process.view;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.process.ProcessInstanceResult.ResultAction;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.PanelLayoutType;

 

/**
 * View action annotation.
 * 
 * Accepted arguments:
 * <ul>
 * <li>the {@link IView}
 * <li>selected document ids: {@link DocumentIdsSelection}
 * <li>parameter fields annotated with {@link ViewActionParam}
 * </ul>
 * 
 * Accepted return types:
 * <ul>
 * <li><code>void</code>
 * <li>{@link ResultAction} like "open included view", "open report" etc
 * </ul>
 * 
 *
 *
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface ViewAction
{
	/**
	 * AD_Message.Value or the caption text
	 * 
	 * @return
	 */
	String caption();

	String description() default "";

	boolean defaultAction() default false;

	PanelLayoutType layoutType() default PanelLayoutType.Panel;

	Class<? extends Precondition> precondition() default AlwaysAllowPrecondition.class;

	public interface Precondition
	{
		ProcessPreconditionsResolution matches(IView view, DocumentIdsSelection selectedDocumentIds);
	}

	public static final class AlwaysAllowPrecondition implements Precondition
	{
		public static final transient ViewAction.AlwaysAllowPrecondition instance = new ViewAction.AlwaysAllowPrecondition();

		@Override
		public ProcessPreconditionsResolution matches(IView view, DocumentIdsSelection selectedDocumentIds)
		{
			return ProcessPreconditionsResolution.accept();
		}
	}
}
