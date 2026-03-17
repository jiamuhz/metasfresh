package de.metas.security.model.interceptor;

import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.compiere.model.I_AD_User_AuthToken;
import org.compiere.model.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metas.security.UserAuthTokenRepository;

/** */

@Interceptor(I_AD_User_AuthToken.class)
@Component
public class AD_User_AuthToken
{
	@Autowired
	private UserAuthTokenRepository userAuthTokenRepo;

	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW, ModelValidator.TYPE_BEFORE_CHANGE })
	public void beforeSave(final I_AD_User_AuthToken userAuthToken)
	{
		userAuthTokenRepo.beforeSave(userAuthToken);
	}
}
