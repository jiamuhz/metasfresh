package de.metas.ui.web.process.adprocess;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;

 

/**
 * Process used to test parent and child views informations provided by webui frontend.
 * 
 *
 * @task https://github.com/metasfresh/metasfresh-webui-api/issues/755
 */
public class WEBUI_TestParentChildViewParams extends ViewBasedProcessTemplate implements IProcessPrecondition
{
	@Override
	public final ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		return ProcessPreconditionsResolution.accept()
				.deriveWithCaptionOverride(buildProcessCaption());
	}

	private String buildProcessCaption()
	{
		final List<String> flags = new ArrayList<>();
		if (getParentViewRowIdsSelection() != null)
		{
			flags.add("parentView");
		}
		if (getChildViewRowIdsSelection() != null)
		{
			flags.add("childView");
		}

		final StringBuilder caption = new StringBuilder(getClass().getSimpleName());
		if (!flags.isEmpty())
		{
			caption.append(" (").append(Joiner.on(", ").join(flags)).append(")");
		}

		return caption.toString();
	}

	@Override
	protected String doIt() throws Exception
	{
		return "Parent: " + getParentViewRowIdsSelection()
				+ "\n Child: " + getChildViewRowIdsSelection();
	}
}
