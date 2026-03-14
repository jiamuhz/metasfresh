package org.adempiere.ad.ui.spi.impl;

/** */

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.adempiere.ad.callout.api.ICalloutRecord;
import org.adempiere.ad.ui.spi.ITabCallout;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import de.metas.util.Check;

@Immutable
public final class CompositeTabCallout implements ITabCallout
{
	public static final Builder builder()
	{
		return new Builder();
	}

	private final List<ITabCallout> tabCallouts;

	private CompositeTabCallout(final List<ITabCallout> tabCallouts)
	{
		super();
		this.tabCallouts = ImmutableList.copyOf(tabCallouts);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.addValue(tabCallouts)
				.toString();
	}

	@Override
	public void onIgnore(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onIgnore(calloutRecord);
		}
	}

	@Override
	public void onNew(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onNew(calloutRecord);
		}
	}

	@Override
	public void onSave(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onSave(calloutRecord);
		}
	}

	@Override
	public void onDelete(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onDelete(calloutRecord);
		}
	}

	@Override
	public void onRefresh(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onRefresh(calloutRecord);
		}
	}

	@Override
	public void onRefreshAll(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onRefreshAll(calloutRecord);
		}
	}

	@Override
	public void onAfterQuery(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onAfterQuery(calloutRecord);
		}
	}

	public static final class Builder
	{
		private final List<ITabCallout> tabCalloutsAll = new ArrayList<>();

		private Builder()
		{
			super();
		}

		public ITabCallout build()
		{
			if (tabCalloutsAll.isEmpty())
			{
				return ITabCallout.NULL;
			}
			else if (tabCalloutsAll.size() == 1)
			{
				return tabCalloutsAll.get(0);
			}
			else
			{
				return new CompositeTabCallout(tabCalloutsAll);
			}
		}

		public Builder addTabCallout(final ITabCallout tabCallout)
		{
			Check.assumeNotNull(tabCallout, "tabCallout not null");

			if (tabCalloutsAll.contains(tabCallout))
			{
				return this;
			}
			tabCalloutsAll.add(tabCallout);

			return this;
		}
	}
}
