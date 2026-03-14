package de.metas.adempiere.addon.impl;

/** */

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;

import de.metas.adempiere.addon.IAddOn;

public class AddonStarterTest
{

	@Test
	public void startAddon()
	{

		final Properties props = new Properties();
		props.put("SomeAddon", TestAddon.class.getName());

		final AddonStarter starter = new AddonStarter(props);

		starter.startAddons();

		assertEquals(TestAddon.invokationCount, 1);
	}

	public static class TestAddon implements IAddOn
	{
		private static int invokationCount = 0;

		@Override
		public void beforeConnection()
		{
			invokationCount++;
		}
	}

}
