package org.compiere.model;

import org.adempiere.ad.ui.spi.ITabCallout;
import org.compiere.model.StateChangeEvent.StateChangeEventType;

import com.google.common.base.MoreObjects;

import de.metas.util.Check;

/** */

/**
 * Listen on {@link GridTab}'s {@link StateChangeEvent}s and call the proper {@link ITabCallout} methods.
 */
/*package */final class GridTabCalloutStateChangeListener implements StateChangeListener
{
	public static final void bind(final GridTab gridTab, final ITabCallout callouts)
	{
		if(callouts == null || callouts == ITabCallout.NULL)
		{
			return; // nothing to bind
		}
		
		final GridTabCalloutStateChangeListener listener = new GridTabCalloutStateChangeListener(callouts);
		gridTab.addStateChangeListener(listener);
	}
	
	private final ITabCallout callouts;

	private GridTabCalloutStateChangeListener(final ITabCallout callouts)
	{
		Check.assumeNotNull(callouts, "callouts not null");
		this.callouts = callouts;
	}
	
	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.addValue(callouts)
				.toString();
	}

	@Override
	public void stateChange(final StateChangeEvent event)
	{
		final GridTab gridTab = event.getSource();
		final StateChangeEventType eventType = event.getEventType();
		switch (eventType)
		{
			case DATA_REFRESH_ALL:
				callouts.onRefreshAll(gridTab);
				break;
			case DATA_REFRESH:
				callouts.onRefresh(gridTab);
				break;
			case DATA_NEW:
				callouts.onNew(gridTab);
				break;
			case DATA_DELETE:
				callouts.onDelete(gridTab);
				break;
			case DATA_SAVE:
				callouts.onSave(gridTab);
				break;
			case DATA_IGNORE:
				callouts.onIgnore(gridTab);
				break;
			default:
				// tolerate all other events, event if they are meaningless for us
				// throw new AdempiereException("EventType " + eventType + " is not supported");
				break;
		}
	}

}
