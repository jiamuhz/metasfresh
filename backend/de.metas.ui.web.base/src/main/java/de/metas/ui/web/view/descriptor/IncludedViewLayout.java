package de.metas.ui.web.view.descriptor;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

 

@Builder
@Value
public class IncludedViewLayout
{
	/**
	 * Default: has included view support, no auto-open, blur main view when the included view is opened
	 */
	public static IncludedViewLayout DEFAULT = builder().build();

	/**
	 * Automatically open the included view when a row from main view is selected
	 */
	boolean openOnSelect;

	/**
	 * Blur main view when included view is open
	 */
	@Default boolean blurWhenOpen = true;

	/**
	 * Automatically close the included view when the row from main view is deselected
	 */
	@Default boolean closeOnDeselect = true;
}
