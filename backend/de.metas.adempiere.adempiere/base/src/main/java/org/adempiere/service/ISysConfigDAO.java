package org.adempiere.service;

/** */

import de.metas.organization.ClientAndOrgId;
import de.metas.util.ISingletonService;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public interface ISysConfigDAO extends ISingletonService
{
	/**
	 * Notes:
	 * <ul>
	 * <li>If we run as spring application, then values can also be set via commandLine <code>-Dname=value</code> or via spring <code>application.properties</code> to take precendence over the
	 * <code>AD_SysConfig</code> record.<br>
	 * But note that in this case, <code>AD_Client_ID</code> and <code>AD_Org_ID</code> are ignored.
	 * <li>If there is more than one matching record, the value of the first <code>AD_SysConfig</code> record, according to <code>ORDER BY AD_Client_ID DESC, AD_Org_ID DESC</code> will be returned.
	 * </ul>
	 */
	Optional<String> getValue(String name, ClientAndOrgId clientAndOrgId);

	List<String> retrieveNamesForPrefix(String prefix, ClientAndOrgId clientAndOrgId);

	void setValue(@NonNull final String name, @Nullable final String value, @NonNull final ClientAndOrgId clientAndOrgId);

	void setValue(@NonNull final String name, final boolean value, @NonNull final ClientAndOrgId clientAndOrgId);

	void setValue(@NonNull final String name, final int value, @NonNull final ClientAndOrgId clientAndOrgId);
}
