package de.metas.notification;

import com.google.common.collect.ImmutableList;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.util.Services;
import org.adempiere.test.AdempiereTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/** */

public class NotificationMessageFormatterTest
{
	private MockedMsgBL mockedMsgBL;

	@Before
	public void init()
	{
		AdempiereTestHelper.get().init();

		mockedMsgBL = new MockedMsgBL();
		Services.registerService(IMsgBL.class, mockedMsgBL);
	}

	@After
	public void after()
	{
		mockedMsgBL = null;
		Services.clear();
	}

	@Test
	public void test_formatURLs()
	{
		final NotificationMessageFormatter formatter = NotificationMessageFormatter.newInstance()
				.html(true);

		final AdMessageKey adMessage = AdMessageKey.of("TestMessage");
		mockedMsgBL.putMsgText(adMessage, "the url is {0}.");
		final String result = formatter.format(
				adMessage,
				ImmutableList.<Object> of("http://www.metasfresh.com"));
		assertThat(result).isEqualTo("the url is <a href=\"http://www.metasfresh.com\">http://www.metasfresh.com</a>.");
	}

	@Test
	public void test_formatURLsWithCaption()
	{
		final NotificationMessageFormatter formatter = NotificationMessageFormatter.newInstance()
				.html(true);

		final AdMessageKey adMessage = AdMessageKey.of("TestMessage");
		mockedMsgBL.putMsgText(adMessage, "the url is {0}.");
		final String result = formatter.format(
				adMessage,
				ImmutableList.<Object> of(
						NotificationMessageFormatter.createUrlWithTitle("http://www.metasfresh.com", "metas gmbH") //
				));
		assertThat(result).isEqualTo("the url is <a href=\"http://www.metasfresh.com\">metas gmbH</a>.");
	}
}
