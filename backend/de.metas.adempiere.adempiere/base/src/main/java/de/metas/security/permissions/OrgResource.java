package de.metas.security.permissions;

/** */

import de.metas.organization.ClientAndOrgId;
import de.metas.organization.OrgId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.service.ClientId;

import javax.annotation.Nullable;

/**
 * Identifies a particular organization.
 *
 * @author tsa
 */
@EqualsAndHashCode(of = "clientAndOrgId")
@ToString(of = "clientAndOrgId")
public final class OrgResource implements Resource
{
	/**
	 * Any Org
	 */
	public static final OrgResource ANY = new OrgResource();

	public static OrgResource of(
			@NonNull final ClientId adClientId,
			@NonNull final OrgId adOrgId,
			final boolean isGroupingOrg)
	{
		return new OrgResource(ClientAndOrgId.ofClientAndOrg(adClientId, adOrgId), isGroupingOrg);
	}

	public static OrgResource anyOrg(@NonNull final ClientId adClientId)
	{
		return new OrgResource(ClientAndOrgId.ofClientAndOrg(adClientId, OrgId.ANY), false);
	}

	@Nullable private final ClientAndOrgId clientAndOrgId;

	@Getter
	private final boolean isGroupingOrg;

	private OrgResource(
			@NonNull final ClientAndOrgId clientAndOrgId,
			final boolean isGroupingOrg)
	{
		this.clientAndOrgId = clientAndOrgId;
		this.isGroupingOrg = isGroupingOrg;
	}

	/**
	 * Any Org constructor
	 */
	private OrgResource()
	{
		clientAndOrgId = null;
		isGroupingOrg = false;
	}

	public boolean isRegularOrg() {return clientAndOrgId != null && clientAndOrgId.getOrgId().isRegular();}

	@Nullable
	public ClientId getClientId() {return clientAndOrgId != null ? clientAndOrgId.getClientId() : null;}

	@Nullable
	public OrgId getOrgId() {return clientAndOrgId != null ? clientAndOrgId.getOrgId() : null;}

	@Nullable
	public OrgId getOrgIdOrAny() {return clientAndOrgId != null ? clientAndOrgId.getOrgId() : OrgId.ANY;}
}
