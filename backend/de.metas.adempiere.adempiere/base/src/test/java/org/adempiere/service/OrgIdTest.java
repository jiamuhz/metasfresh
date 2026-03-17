package org.adempiere.service;

import de.metas.organization.OrgId;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** */

public class OrgIdTest
{
	@Test
	public void test_ofRepoIdOrNull()
	{
		assertThat(OrgId.ofRepoIdOrNull(-1)).isNull();
		assertThat(OrgId.ofRepoIdOrNull(0)).isSameAs(OrgId.ANY);
		assertThat(OrgId.ofRepoIdOrNull(1)).isEqualTo(OrgId.ofRepoId(1));
		assertThat(OrgId.ofRepoIdOrNull(2)).isEqualTo(OrgId.ofRepoId(2));
		assertThat(OrgId.ofRepoIdOrNull(OrgId.MAIN.getRepoId())).isSameAs(OrgId.MAIN);
	}

	@Test
	public void test_ofRepoId()
	{
		assertThatThrownBy(() -> OrgId.ofRepoId(-1)).isNotNull();

		assertThat(OrgId.ofRepoId(0)).isSameAs(OrgId.ANY);
		assertThat(OrgId.ofRepoId(1)).isEqualTo(OrgId.ofRepoId(1));
		assertThat(OrgId.ofRepoId(2)).isEqualTo(OrgId.ofRepoId(2));
		assertThat(OrgId.ofRepoId(OrgId.MAIN.getRepoId())).isSameAs(OrgId.MAIN);
	}

	@Test
	public void testIsAny()
	{
		assertThat(OrgId.ANY.isAny()).isTrue();
		assertThat(OrgId.ofRepoId(1).isAny()).isFalse();
	}
}
