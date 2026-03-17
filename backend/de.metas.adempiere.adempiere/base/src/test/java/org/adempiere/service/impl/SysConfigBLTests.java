package org.adempiere.service.impl;

/** */

import de.metas.organization.ClientAndOrgId;
import de.metas.organization.OrgId;
import de.metas.util.Services;
import org.adempiere.service.ClientId;
import org.adempiere.service.ISysConfigBL;
import org.adempiere.test.AdempiereTestHelper;
import org.adempiere.test.AdempiereTestWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(AdempiereTestWatcher.class)
public class SysConfigBLTests
{
	private ISysConfigBL sysConfigBL;

	@BeforeEach
	public void beforeEach()
	{
		AdempiereTestHelper.get().init();
		sysConfigBL = Services.get(ISysConfigBL.class);
	}

	@Nested
	class testSetGetString
	{
		@Test
		void sameOrg()
		{
			final ClientAndOrgId clientAndOrgId = ClientAndOrgId.ofClientAndOrg(ClientId.ofRepoId(1), OrgId.ofRepoId(1));

			sysConfigBL.setValue("name", "valueStr", clientAndOrgId.getClientId(), clientAndOrgId.getOrgId());

			assertThat(sysConfigBL.getValue("name", clientAndOrgId)).isEqualTo("valueStr");
		}

		@Test
		void setOn_System_getOn_Org1()
		{
			sysConfigBL.setValue("name", "valueStr", ClientId.SYSTEM, OrgId.ANY);

			final String value = sysConfigBL.getValue("name", ClientAndOrgId.ofClientAndOrg(ClientId.ofRepoId(1), OrgId.ofRepoId(1)));
			assertThat(value).isEqualTo("valueStr");
		}

		@Test
		void setOn_Org0_getOn_Org1()
		{
			sysConfigBL.setValue("name", "valueStr", ClientId.ofRepoId(1), OrgId.ANY);

			final String value = sysConfigBL.getValue("name", ClientAndOrgId.ofClientAndOrg(ClientId.ofRepoId(1), OrgId.ofRepoId(1)));
			assertThat(value).isEqualTo("valueStr");
		}
	}
}
