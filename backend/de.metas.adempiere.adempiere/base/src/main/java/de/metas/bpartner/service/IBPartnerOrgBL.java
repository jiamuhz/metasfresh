package de.metas.bpartner.service;

/** */

import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.BPartnerLocationId;
import de.metas.location.CountryId;
import de.metas.organization.OrgId;
import de.metas.user.UserId;
import de.metas.util.ISingletonService;
import lombok.NonNull;
import org.compiere.model.I_AD_Org;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_Location;

import java.util.Optional;
import java.util.Properties;

public interface IBPartnerOrgBL extends ISingletonService
{
	I_C_BPartner retrieveLinkedBPartner(I_AD_Org org);

	I_C_BPartner retrieveLinkedBPartner(int adOrgId);

	Optional<BPartnerId> retrieveLinkedBPartnerId(OrgId orgId);

	I_C_Location retrieveOrgLocation(final OrgId orgId);

	default CountryId getOrgCountryId(@NonNull final OrgId orgId)
	{
		I_C_Location orgLocation = retrieveOrgLocation(orgId);
		if (orgLocation == null)
		{
			// 03378 : temporary null check. Will be removed when OrgBP_Location is mandatory.
			return null;
		}

		return CountryId.ofRepoIdOrNull(orgLocation.getC_Country_ID());
	}

	BPartnerLocationId retrieveOrgBPLocationId(OrgId orgId);

	/**
	 * Returns the default UserId of the given <code>orgId</code>'s bpartner.
	 */
	Optional<UserId> retrieveUserInChargeOrNull(@NonNull final OrgId orgId);

	/**
	 * Returns the default contact of the given <code>orgId</code>'s bpartner.
	 *
	 * @deprecated Please use instead {@link #retrieveUserInChargeOrNull(OrgId)}
	 *
	 * @param ctx
	 * @param orgId
	 * @param trxName
	 * @return
	 */
	@Deprecated
	I_AD_User retrieveUserInChargeOrNull(Properties ctx, int orgId, String trxName);

	@NonNull String getOrgLanguageOrLoggedInUserLanguage(@NonNull OrgId orgId);
}
