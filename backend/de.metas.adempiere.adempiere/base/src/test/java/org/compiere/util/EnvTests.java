package org.compiere.util;

import static org.assertj.core.api.Assertions.assertThat;

/** */

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import org.adempiere.util.lang.IAutoCloseable;
import org.junit.Assert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import de.metas.user.UserId;
import de.metas.util.Check;
import de.metas.util.collections.CollectionUtils;
import de.metas.util.collections.IdentityHashSet;

public class EnvTests
{
	/** Creates a new context for testing */
	private Properties newContext()
	{
		return new Properties();
	}

	/** Creates a new context for testing */
	private Properties newContext(final Properties parentCtx)
	{
		return new Properties(parentCtx);
	}

	@SuppressWarnings("unchecked")
	private <T> T defaultValue(String desc, Class<T> type)
	{
		if (type == Timestamp.class)
		{
			return (T)nextValue(Timestamp.class, desc);
		}
		else if (type == int.class)
		{
			return (T)nextValue(int.class, desc);
		}
		else if (type == String.class)
		{
			return (T)nextValue(String.class, desc);
		}
		else
		{
			throw new RuntimeException("Type " + type + " not supported (desc:" + desc + ")");
		}
	}

	private <T> String name(String context, T defaultValue, Class<T> type)
	{
		String defaultValueStr;
		if (type == Timestamp.class)
		{
			defaultValueStr = Env.toString((Timestamp)defaultValue);
		}
		else if (type == int.class)
		{
			defaultValueStr = Integer.toString((Integer)defaultValue);
		}
		else
		{
			defaultValueStr = defaultValue.toString();
		}
		final List<String> modifiers = null;
		return new CtxName(context, modifiers, defaultValueStr).toString();
	}

	private int currentInt = 1;
	private long currentTS = System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 365 * 10; // 10years before

	@SuppressWarnings("unchecked")
	private <T> T nextValue(Class<T> type, String desc)
	{
		if (type == String.class)
		{
			String value = desc;
			// currentInt++;
			return (T)value;
		}
		else if (type == int.class)
		{
			Integer value = currentInt;
			currentInt++;
			return (T)value;
		}
		else if (type == Timestamp.class)
		{
			long valueMillis = currentTS;
			// String millis because setContext is also stripping them
			valueMillis = (valueMillis / 1000) * 1000L;
			final Timestamp value = new Timestamp(valueMillis);

			currentTS += 1000 * 60 * 60 * 24; // next second
			return (T)value;
		}
		else
		{
			throw new RuntimeException("Type " + type + " not supported");
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T getNoValue(Class<T> type)
	{
		if (type == String.class)
		{
			return (T)Env.CTXVALUE_NullString;
		}
		else if (type == int.class)
		{
			return (T)(Integer)Env.CTXVALUE_NoValueInt;
		}
		else if (type == Timestamp.class)
		{
			return null; // TODO
		}
		else
		{
			throw new RuntimeException("Type " + type + " not supported");
		}
	}

	private <T> T setContextItemAndTest(Properties ctx, String context, Class<T> type)
	{
		final T value = nextValue(type, "Value_" + context);
		if (type == String.class)
		{
			Env.setContextItem(ctx, context, (String)value);
		}
		else if (type == int.class)
		{
			Env.setContextItem(ctx, context, (Integer)value);
		}
		else if (type == Timestamp.class)
		{
			Env.setContextItem(ctx, context, (Timestamp)value);
		}
		else
		{
			throw new RuntimeException("Type " + type + " not supported");
		}

		// final T valueActual = getContext(ctx, context, type);
		// assertEquals(value, valueActual);
		assertContextSet(ctx, context, type, value);

		return value;
	}

	@SuppressWarnings("unchecked")
	private <T> T getContextItem(Properties ctx, String context, Class<T> type)
	{
		if (type == String.class)
		{
			return (T)Env.getContextItem(ctx, context);
		}
		else if (type == int.class)
		{
			return (T)(Integer)Env.getContextItemAsInt(ctx, context);
		}
		else if (type == Timestamp.class)
		{
			return (T)Env.getContextItemAsDate(ctx, context);
		}
		else
		{
			throw new RuntimeException("Type " + type + " not supported");
		}
	}

	private <T> void assertContextSet(final Properties ctx, String context, Class<T> type, T valueExpected)
	{
		final T valueActual = getContextItem(ctx, context, type);
		final String message = "Invalid context value for " + context
				+ "\n Context: " + ctx;
		assertThat(valueActual).as(message).isEqualTo(valueExpected);
	}

	private void assertContextNotSet(final Properties ctx, final String context)
	{
		final String valueActual = Env.getContextItem(ctx, context);
		final String message = "Context value for " + context + " shall not be set"
				+ "\n Context: " + ctx;
		assertThat(valueActual).as(message).isSameAs(Env.CTXVALUE_NullString);
	}

	private void assertDefaults(final Properties ctx, final Properties defaultsExpected)
	{
		final Properties defaultsActual = getDefaults(ctx);
		Assert.assertSame("Context shall not have defaults: " + ctx, defaultsExpected, defaultsActual);
	}

	private Properties getDefaults(final Properties ctx)
	{
		Check.assumeNotNull(ctx, "ctx not null");
		try
		{
			final Field defaultsField = ctx.getClass().getDeclaredField("defaults");
			defaultsField.setAccessible(true);

			final Properties defaults = (Properties)defaultsField.get(ctx);
			return defaults;
		}
		catch (Exception e)
		{
			throw new AssertionError("Failed getting the defaults of " + ctx, e);
		}
	}

	private <T> T setContextItemAndTest(Properties ctx, int WindowNo, String context, Class<T> type)
	{
		final T value = nextValue(type, "Value_window" + WindowNo + "_" + context);
		if (type == String.class)
		{
			Env.setContextItem(ctx, WindowNo, context, (String)value);
		}
		else if (type == int.class)
		{
			Env.setContextItem(ctx, WindowNo, context, (Integer)value);
		}
		else if (type == Timestamp.class)
		{
			Env.setContextItem(ctx, WindowNo, context, (Timestamp)value);
		}
		else
		{
			throw new RuntimeException("Type " + type + " not supported");
		}
		final T valueActual = getContextItem(ctx, WindowNo, context, type);
		assertThat(valueActual).isEqualTo(value);

		return value;
	}

	@SuppressWarnings("unchecked")
	private <T> T getContextItem(Properties ctx, int WindowNo, String context, Class<T> type)
	{
		if (type == String.class)
		{
			return (T)Env.getContextItem(ctx, WindowNo, context);
		}
		else if (type == int.class)
		{
			return (T)(Integer)Env.getContextItemAsInt(ctx, WindowNo, context);
		}
		else if (type == Timestamp.class)
		{
			return (T)Env.getContextItemAsDate(ctx, WindowNo, context);
		}
		else
		{
			throw new RuntimeException("Type " + type + " not supported");
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T getContextItem(Properties ctx, int WindowNo, String context, boolean onlyWindow, Class<T> type)
	{
		if (type == String.class)
		{
			return (T)Env.getContextItem(ctx, WindowNo, context, onlyWindow);
		}
		else if (type == int.class)
		{
			return (T)(Integer)Env.getContextItemAsInt(ctx, WindowNo, context, onlyWindow);
		}
		else if (type == Timestamp.class)
		{
			return (T)Env.getContextItemAsDate(ctx, WindowNo, context, onlyWindow);
		}
		else
		{
			throw new RuntimeException("Type " + type + " not supported");
		}
	}

	private <T> T setContextItemAndTest(Properties ctx, int WindowNo, int TabNo, String context, Class<T> type)
	{
		final T value = nextValue(type, "Value_window" + WindowNo + "_Tab" + TabNo + "_" + context);
		if (type == String.class)
		{
			Env.setContextItem(ctx, WindowNo, TabNo, context, (String)value);
		}
		else if (type == int.class)
		{
			Env.setContextAsInt(ctx, WindowNo, TabNo, context, (Integer)value);
		}
		else if (type == Timestamp.class)
		{
			Env.setContextItemAsDate(ctx, WindowNo, TabNo, context, (Timestamp)value);
		}
		else
		{
			throw new RuntimeException("Type " + type + " not supported");
		}
		final T valueActual = getContextItem(ctx, WindowNo, TabNo, context, type);
		assertThat(valueActual).isEqualTo(value);

		return value;
	}

	@SuppressWarnings("unchecked")
	private <T> T getContextItem(Properties ctx, int WindowNo, int TabNo, String context, Class<T> type)
	{
		if (type == String.class)
		{
			return (T)Env.getContextItem(ctx, WindowNo, TabNo, context);
		}
		else if (type == int.class)
		{
			return (T)(Integer)Env.getContextItemAsInt(ctx, WindowNo, TabNo, context);
		}
		else if (type == Timestamp.class)
		{
			return (T)Env.getContextItemAsDate(ctx, WindowNo, TabNo, context);
		}
		else
		{
			throw new RuntimeException("Type " + type + " not supported");
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T getContextItem(Properties ctx, int WindowNo, int TabNo, String context, boolean onlyTab, Class<T> type)
	{
		if (type == String.class)
		{
			return (T)Env.getContextItem(ctx, WindowNo, TabNo, context, onlyTab);
		}
		else if (type == int.class)
		{
			return (T)(Integer)Env.getContextItemAsInt(ctx, WindowNo, TabNo, context, onlyTab);
		}
		else if (type == Timestamp.class)
		{
			return (T)Env.getContextItemAsDate(ctx, WindowNo, TabNo, context, onlyTab);
		}
		else
		{
			throw new RuntimeException("Type " + type + " not supported");
		}
	}

	// Test: Env.getContext(ctx, WindowNo, context)
	private <T> void testGetContextItem(T expectedValue, Properties ctx, int WindowNo, String context, Class<T> type)
	{
		assertThat(getContextItem(ctx, WindowNo, context, type)).isEqualTo(expectedValue);

		T defaultValue = defaultValue("Default_W" + WindowNo + "_" + context, type);
		T expectedValue2 = Objects.equals(expectedValue, getNoValue(type)) ? defaultValue : expectedValue;
		assertThat(getContextItem(ctx, WindowNo, name(context, defaultValue, type), type)).isEqualTo(expectedValue2);
	}

	// Test: Env.getContext(ctx, WindowNo, TabNo, context);
	private <T> void testGetContextItem(T expectedValue, Properties ctx, int WindowNo, int TabNo, String context, Class<T> type)
	{
		assertThat(getContextItem(ctx, WindowNo, TabNo, context, type)).isEqualTo(expectedValue);

		T defaultValue = defaultValue("Default_W" + WindowNo + "_T" + TabNo + "_" + context, type);
		T expectedValue2 = Objects.equals(expectedValue, getNoValue(type)) ? defaultValue : expectedValue;
		assertThat(getContextItem(ctx, WindowNo, TabNo, name(context, defaultValue, type), type)).isEqualTo(expectedValue2);
	}

	// Test: Env.getContext(ctx, WindowNo, context, onlyWindow);
	private <T> void testGetContextItem(T expectedValue, Properties ctx, int WindowNo, String context, boolean onlyWindow, Class<T> type)
	{
		assertThat(getContextItem(ctx, WindowNo, context, onlyWindow, type)).isEqualTo(expectedValue);

		T defaultValue = defaultValue("DefaultValue_W" + WindowNo + "_" + context, type);
		T expectedValue2 = Objects.equals(expectedValue, getNoValue(type)) ? defaultValue : expectedValue;
		assertThat(getContextItem(ctx, WindowNo, name(context, defaultValue, type), onlyWindow, type)).isEqualTo(expectedValue2);
	}

	// Test: Env.getContext(ctx, WindowNo, TabNo, context, onlyTab);
	private <T> void testGetContextItem(T expectedValue, Properties ctx, int WindowNo, int TabNo, String context, boolean onlyTab, Class<T> type)
	{
		assertThat(getContextItem(ctx, WindowNo, TabNo, context, onlyTab, type)).isEqualTo(expectedValue);

		T defaultValue = defaultValue("DefaultValue_W" + WindowNo + "_T" + TabNo + "_" + context, type);
		T expectedValue2 = Objects.equals(expectedValue, getNoValue(type)) ? defaultValue : expectedValue;
		assertThat(getContextItem(ctx, WindowNo, TabNo, name(context, defaultValue, type), onlyTab, type)).isEqualTo(expectedValue2);
	}

	private void testRemove(Properties ctx, String context)
	{
		Env.setContextItem(ctx, context, (String)null);
		assertContextNotSet(ctx, context);
	}

	private void testRemove(Properties ctx, int WindowNo, String context)
	{
		Env.setContextItem(ctx, WindowNo, context, (String)null);
		assertContextNotSet(ctx, WindowNo + "|" + context);
	}

	private void testRemove(Properties ctx, int WindowNo, int TabNo, String context)
	{
		Env.setContextItem(ctx, WindowNo, TabNo, context, (String)null);
		assertContextNotSet(ctx, WindowNo + "|" + TabNo + "|" + context);
	}

	@SuppressWarnings("unused")
	private <T> void testGetContextItem(Class<T> type)
	{
		final Properties ctx = newContext();
		final T vglobal1 = setContextItemAndTest(ctx, "#global1", type);
		final T value1_global = setContextItemAndTest(ctx, "#name1", type);
		final T value1_window1 = setContextItemAndTest(ctx, 1, "name1", type);
		final T value1_window1_global = setContextItemAndTest(ctx, 1, "#name1", type);
		final T value2_window1 = setContextItemAndTest(ctx, 1, "name2", type);
		final T value1_window1_tab1 = setContextItemAndTest(ctx, 1, 1, "name1", type);
		final T value1_window1_tab2 = setContextItemAndTest(ctx, 1, 2, "name1", type);
		final T value1_window1_tabInfo = setContextItemAndTest(ctx, 1, Env.TAB_INFO, "name1", type);

		//
		// Test: Env.getContext(ctx, WindowNo, context)
		testGetContextItem(value1_global, ctx, 2, "name1", type);
		testGetContextItem(vglobal1, ctx, 2, "global1", type);

		//
		// Test: Env.getContext(ctx, WindowNo, TabNo, context);
		testGetContextItem(value1_window1_tab1, ctx, 1, 1, "name1", type);
		testGetContextItem(value1_window1, ctx, 1, 3, "name1", type);
		testGetContextItem(value1_window1_tabInfo, ctx, 1, Env.TAB_INFO, "name1", type);
		testGetContextItem(vglobal1, ctx, 1, 3, "global1", type);

		//
		// Test: Env.getContext(ctx, WindowNo, context, onlyWindow);
		testGetContextItem(value1_window1, ctx, 1, "name1", false, type);
		testGetContextItem(value1_window1, ctx, 1, "name1", true, type);
		testGetContextItem(vglobal1, ctx, 1, "global1", false, type);
		if (type != Timestamp.class) // TODO: following method returns current timestamp:
		{
			testGetContextItem(getNoValue(type), ctx, 1, "global1", true, type);
		}

		//
		// Test: Env.getContext(ctx, WindowNo, TabNo, context, onlyTab);
		testGetContextItem(value1_window1_tab1, ctx, 1, 1, "name1", false, type);
		testGetContextItem(value1_window1_tab1, ctx, 1, 1, "name1", true, type);
		testGetContextItem(value1_window1_tab2, ctx, 1, 2, "name1", false, type);
		testGetContextItem(value1_window1_tab2, ctx, 1, 2, "name1", true, type);
		testGetContextItem(value1_window1, ctx, 1, 3, "name1", false, type);
		if (type != Timestamp.class) // TODO: following method returns current timestamp:
		{
			testGetContextItem(getNoValue(type), ctx, 1, 3, "name1", true, type);
			testGetContextItem(getNoValue(type), ctx, 1, Env.TAB_INFO, "name2", false, type);
			testGetContextItem(getNoValue(type), ctx, 1, Env.TAB_INFO, "name2", true, type);
			testGetContextItem(getNoValue(type), ctx, 1, 3, "global1", true, type);
		}
		testGetContextItem(value1_window1_tabInfo, ctx, 1, Env.TAB_INFO, "name1", false, type);
		testGetContextItem(value1_window1_tabInfo, ctx, 1, Env.TAB_INFO, "name1", true, type);
		testGetContextItem(vglobal1, ctx, 1, 3, "global1", false, type);

		//
		// Test: explicit global (window level)
		testGetContextItem(vglobal1, ctx, 1, "#global1", false, type);
		testGetContextItem(vglobal1, ctx, 1, "#global1", true, type);
		testGetContextItem(value1_window1_global, ctx, 1, "#name1", false, type);
		testGetContextItem(value1_window1_global, ctx, 1, "#name1", true, type);

		//
		// Test: explicit global (tab level)
		testGetContextItem(vglobal1, ctx, 1, 1, "#global1", false, type);

		// TODO: fails because on tab level, explicit global is not supported. Is this a bug?
		// testGetContext("vglobal1", Env.getContext(ctx, 1, 1, "#global1", true, type);
		if (type != Timestamp.class) // TODO: following method returns current timestamp:
		{
			testGetContextItem(getNoValue(type), ctx, 1, 1, "#global1", true, type);
		}

		//
		// Test remove:
		testRemove(ctx, "#global1");
		testRemove(ctx, "#name1");
		testRemove(ctx, 1, "name1");
		testRemove(ctx, 1, "#name1");
		testRemove(ctx, 1, "name2");
		testRemove(ctx, 1, 1, "name1");
		testRemove(ctx, 1, 2, "name1");
		testRemove(ctx, 1, Env.TAB_INFO, "name1");
		// assertEquals(0, ctx.size()); // does not apply because it could be that it's not removed but just flagged as removed

		//
		// Test org.compiere.util.Env.setContext(Properties, int, int, String, String)
		// which is not removing the key in case value is null (like other methods do)
		// but instead is setting the value to "" or 0 (if context ends with _ID)
		Env.setContextItem(ctx, 10, 10, "TestName", (String)null);
		assertThat(ctx.get("10|10|TestName")).isEqualTo("");
		Env.setContextItem(ctx, 10, 10, "TestName_ID", (String)null);
		assertThat(ctx.get("10|10|TestName_ID")).isEqualTo("0");
	}

	@Test
	public void testGetConfig()
	{
		for (Class<?> type : new Class<?>[] { String.class, int.class, Timestamp.class })
		{
			testGetContextItem(type);
		}
	}

	@Test
	public void test_Env_parseContext()
	{
		final Properties ctx = newContext();
		final int windowNo = 1234;
		Env.setContextItem(ctx, windowNo, "C_BPartner_ID", "100");

		final String sql = "@NotExistingField_ID/13@=13" +
				" AND C_BPartner_Location.C_BPartner_ID=@C_BPartner_ID/-1@" +
				" AND C_BPartner_Location.IsShipTo='Y'" +
				" AND C_BPartner_Location.IsActive='Y'";

		final String sqlParsed1 = "13=13" +
				" AND C_BPartner_Location.C_BPartner_ID=100" +
				" AND C_BPartner_Location.IsShipTo='Y'" +
				" AND C_BPartner_Location.IsActive='Y'";
		assertThat(Env.parseContext(ctx, windowNo, sql, false)) // onlyWindow=false
				.isEqualTo(sqlParsed1);

		final String sqlParsed2 = "13=13" +
				" AND C_BPartner_Location.C_BPartner_ID=-1" +
				" AND C_BPartner_Location.IsShipTo='Y'" +
				" AND C_BPartner_Location.IsActive='Y'";
		assertThat(Env.parseContext(ctx, 999999, sql, false)) // onlyWindow=false
				.isEqualTo(sqlParsed2);

		Env.setContextItem(ctx, "#NotExistingField_ID", "112233");
		final String sqlParsed3 = "112233=13" +
				" AND C_BPartner_Location.C_BPartner_ID=100" +
				" AND C_BPartner_Location.IsShipTo='Y'" +
				" AND C_BPartner_Location.IsActive='Y'";
		assertThat(Env.parseContext(ctx, windowNo, sql, false)) // onlyWindow=false
				.isEqualTo(sqlParsed3);
	}

	@Test
	public void test_Env_globalVarsWithoutHash()
	{
		final Properties ctx = newContext();
		Env.setContextItem(ctx, "Login.RememberMe", true);
		assertThat(Env.getContextItem(ctx, "Login.RememberMe"))
				.as("Login.RememberMe should be Yes")
				.isEqualTo("Y");
	}

	@Test
	public void test_getRemoteCallCtx_WithDefaults()
	{
		final Properties ctxParent = newContext();
		assertDefaults(ctxParent, null); // no defaults
		Env.setContextItem(ctxParent, Env.CTXNAME_AD_Client_ID, 1);
		Env.setContextItem(ctxParent, Env.CTXNAME_AD_Org_ID, 2);
		Env.setContextItem(ctxParent, Env.CTXNAME_AD_User_ID, 3);
		Env.setContextItem(ctxParent, Env.CTXNAME_AD_Role_ID, 4);
		Env.setContextItem(ctxParent, "DummyNotGlobal1", "DummyNotGlobal1_Value");

		final Properties ctx = newContext(ctxParent);
		assertDefaults(ctx, ctxParent);
		Env.setContextItem(ctx, Env.CTXNAME_AD_Client_ID, 10); // override the AD_Client_ID
		Env.setContextItem(ctx, "DummyNotGlobal2", "DummyNotGlobal2_Value");

		// Create the light context which includes only global properties
		final Properties ctxLight = Env.getRemoteCallCtx(ctx);

		// Test:
		assertDefaults(ctxLight, null); // no defaults whall be set in light context
		assertContextSet(ctxLight, Env.CTXNAME_AD_Client_ID, int.class, 10);
		assertContextSet(ctxLight, Env.CTXNAME_AD_Org_ID, int.class, 2);
		assertContextSet(ctxLight, Env.CTXNAME_AD_User_ID, int.class, 3);
		assertContextSet(ctxLight, Env.CTXNAME_AD_Role_ID, int.class, 4);
		assertContextNotSet(ctxLight, "DummyNotGlobal1");
		assertContextNotSet(ctxLight, "DummyNotGlobal2");
	}

	/**
	 * Makes sure the {@link Properties#propertyNames()} is also returning the properties from the underlying "defaults".
	 *
	 * NOTE: {@link Properties#propertyNames()} will fail for non-string keys.
	 */
	@Test
	public void test_Properties_stringPropertyNames()
	{
		//
		// Create the parent context and populate it with some keys
		final Properties ctxBase = newContext();

		final String key1 = "key1";
		final Object value1 = new Object();
		ctxBase.put(key1, value1);

		final String key2 = "key2";
		final String value2 = "value2";
		ctxBase.put(key2, value2);

		//
		// Create the context and populate it with some keys
		final Properties ctx = newContext(ctxBase);

		final String key3 = "key3";
		final Object value3 = new Object();
		ctx.put(key3, value3);

		final String key4 = "key4";
		final String value4 = "value4";
		ctx.put(key4, value4);

		//
		// Get all properties and add them to an identity set (to make sure we are not removing the duplicates)
		final Set<String> propertyNames = new IdentityHashSet<>();
		for (final Enumeration<?> e = ctx.propertyNames(); e.hasMoreElements();)
		{
			final String key = (String)e.nextElement();
			propertyNames.add(key);
		}

		//
		// Assert we got all property names, including the ones from parent context
		assertThat(propertyNames)
				.as("All keys shall be present")
				.isEqualTo(CollectionUtils.asSet(key1, key2, key3, key4));
	}

	@Nested
	public class temporaryChangeLoggedUserId
	{
		@Test
		public void withoutPreviousUser()
		{
			final Properties ctx = newContext();
			assertThat(Env.getLoggedUserIdIfExists(ctx)).isEmpty();

			final IAutoCloseable closeable = Env.temporaryChangeLoggedUserId(ctx, UserId.ofRepoId(111));
			assertThat(Env.getLoggedUserIdIfExists(ctx)).contains(UserId.ofRepoId(111));

			closeable.close();
			assertThat(Env.getLoggedUserIdIfExists(ctx)).isEmpty();
		}

		@Test
		public void withPreviousUser()
		{
			final Properties ctx = newContext();
			assertThat(Env.getLoggedUserIdIfExists(ctx)).isEmpty();

			Env.setLoggedUserId(ctx, UserId.ofRepoId(222));
			assertThat(Env.getLoggedUserIdIfExists(ctx)).contains(UserId.ofRepoId(222));

			final IAutoCloseable closeable = Env.temporaryChangeLoggedUserId(ctx, UserId.ofRepoId(111));
			assertThat(Env.getLoggedUserIdIfExists(ctx)).contains(UserId.ofRepoId(111));

			closeable.close();
			assertThat(Env.getLoggedUserIdIfExists(ctx)).contains(UserId.ofRepoId(222));
		}
	}

	public void test()
	{
	}

}
