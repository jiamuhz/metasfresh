package de.metas.ui.web.view;

 

/**
 * To be implemented by {@link IView}s which want to also override {@link IViewRow} properties.
 * 
 *
 * @see ViewRowOverridesHelper
 */
public interface IViewRowOverrides
{
	default ViewId getIncludedViewId(final IViewRow row)
	{
		return row.getIncludedViewId();
	}
}
