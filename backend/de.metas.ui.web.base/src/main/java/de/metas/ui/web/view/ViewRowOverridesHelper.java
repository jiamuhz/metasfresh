package de.metas.ui.web.view;

import lombok.experimental.UtilityClass;

 

@UtilityClass
public final class ViewRowOverridesHelper
{
	public static final IViewRowOverrides getViewRowOverrides(final IView view)
	{
		if (view instanceof IViewRowOverrides)
		{
			return (IViewRowOverrides)view;
		}
		else
		{
			return NULL;
		}
	}

	public static final boolean extractSupportIncludedViews(final IViewRow row, final IViewRowOverrides rowOverrides)
	{
		if (rowOverrides != null)
		{
			if(rowOverrides.getIncludedViewId(row) != null)
			{
				return true;
			}
		}
		
		return row.getIncludedViewId() != null;
	}

	public static final ViewId extractIncludedViewId(final IViewRow row, final IViewRowOverrides rowOverrides)
	{
		if (rowOverrides != null)
		{
			final ViewId includedViewId = rowOverrides.getIncludedViewId(row);
			if (includedViewId != null)
			{
				return includedViewId;
			}
		}
		
		return row.getIncludedViewId();
	}

	private static final class NullViewRowOverrides implements IViewRowOverrides
	{
	};

	public static final NullViewRowOverrides NULL = new NullViewRowOverrides();
}
