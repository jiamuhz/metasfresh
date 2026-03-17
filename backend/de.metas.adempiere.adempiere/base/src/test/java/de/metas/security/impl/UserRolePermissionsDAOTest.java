package de.metas.security.impl;

/** */

import de.metas.common.util.time.SystemTime;
import de.metas.event.log.EventLogService;
import de.metas.event.log.EventLogsRepository;
import org.adempiere.ad.modelvalidator.IModelInterceptorRegistry;
import org.adempiere.service.ClientId;
import org.adempiere.test.AdempiereTestHelper;
import org.compiere.SpringContextHolder;
import org.junit.Before;
import org.junit.Test;

import de.metas.security.IUserRolePermissionsDAO;
import de.metas.security.RoleId;
import de.metas.security.model.interceptor.SecurityMainInterceptor;
import de.metas.user.UserId;
import de.metas.util.Services;

public class UserRolePermissionsDAOTest
{
	private UserRolePermissionsDAO dao;

	private SecurityMainInterceptor securityMainInterceptor;

	@Before
	public void init()
	{
		AdempiereTestHelper.get().init();

		SpringContextHolder.registerJUnitBean(new EventLogService(new EventLogsRepository()));
		securityMainInterceptor = new SecurityMainInterceptor();
		Services.get(IModelInterceptorRegistry.class)
				.addModelInterceptor(securityMainInterceptor);

		dao = (UserRolePermissionsDAO)Services.get(IUserRolePermissionsDAO.class);
	}

	@Test(expected = RolePermissionsNotFoundException.class)
	public void test_retrieveUserRolePermissions_NotExistingRole()
	{
		dao.getUserRolePermissions(
				RoleId.ofRepoId(1),
				UserId.ofRepoId(2),
				ClientId.ofRepoId(3),
				SystemTime.asLocalDate());
	}
}
