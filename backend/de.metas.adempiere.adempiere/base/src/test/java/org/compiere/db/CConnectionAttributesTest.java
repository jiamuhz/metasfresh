package org.compiere.db;

/** */

import org.junit.Assert;
import org.junit.Test;

public class CConnectionAttributesTest
{
	@Test
	public void test_parse_and_toString()
	{
		final String attributesStr = "CConnection[DBhost=roddb001,DBport=5432,DBname=ad_rt_tsa,UID=metasfresh,PWD=metas]";
		final CConnectionAttributes attrs = CConnectionAttributes.of(attributesStr);

		Assert.assertEquals("DbHost", "roddb001", attrs.getDbHost());
		Assert.assertEquals("DbPort", 5432, attrs.getDbPort());
		Assert.assertEquals("DbName", "ad_rt_tsa", attrs.getDbName());
		Assert.assertEquals("DbUid", "metasfresh", attrs.getDbUid());
		Assert.assertEquals("DbPwd", "metas", attrs.getDbPwd());

		// Convert back to string and test
		Assert.assertEquals(attributesStr, attrs.toString());
	}

	@Test
	public void test_parse_and_toString_old()
	{
		final String attributesStrOld = "CConnection[name=MyAppsServer{roddb001-ad_rt_tsa-adempiere},AppsHost=MyAppsServer,AppsPort=1099,type=PostgreSQL,DBhost=roddb001,DBport=5432,DBname=ad_rt_tsa,BQ=false,FW=false,FWhost=,FWport=0,UID=metasfresh,PWD=metas]";
		final String attributesStr = "CConnection[DBhost=roddb001,DBport=5432,DBname=ad_rt_tsa,UID=metasfresh,PWD=metas]";
		final CConnectionAttributes attrs = CConnectionAttributes.of(attributesStrOld);

		Assert.assertEquals("DbHost", "roddb001", attrs.getDbHost());
		Assert.assertEquals("DbPort", 5432, attrs.getDbPort());
		Assert.assertEquals("DbName", "ad_rt_tsa", attrs.getDbName());
		Assert.assertEquals("DbUid", "metasfresh", attrs.getDbUid());
		Assert.assertEquals("DbPwd", "metas", attrs.getDbPwd());

		// Convert back to string and test
		Assert.assertEquals(attributesStr, attrs.toString());
	}
}
