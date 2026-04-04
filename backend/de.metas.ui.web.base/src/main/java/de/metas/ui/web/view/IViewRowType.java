package de.metas.ui.web.view;

 

/**
 * {@link IViewRow} record type
 * 
 *
 *
 */
public interface IViewRowType
{
	String getName();

	/**
	 * The name of the icon associated with this row type. It's the frontend's job to come up with the actual icon.
	 * 
	 * Currently available icons on frontend are defined in {@link ViewRowTypeIconNames}.
	 */
	default String getIconName()
	{
		return getName();
	}

}
