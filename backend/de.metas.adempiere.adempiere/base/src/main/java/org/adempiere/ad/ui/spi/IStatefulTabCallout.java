package org.adempiere.ad.ui.spi;

import org.adempiere.ad.callout.api.ICalloutRecord;

import javax.annotation.Nullable;

/** */

public interface IStatefulTabCallout extends ITabCallout
{
	/**
	 * Called after {@link ICalloutRecord} was initialized.
	 */
	void onInit(@Nullable ICalloutRecord calloutRecord);
}
