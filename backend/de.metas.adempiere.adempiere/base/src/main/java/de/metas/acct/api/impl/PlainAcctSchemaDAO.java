package de.metas.acct.api.impl;

import de.metas.acct.api.AcctSchemaId;
import de.metas.organization.OrgId;
import lombok.NonNull;
import org.adempiere.service.ClientId;
import org.compiere.Adempiere;

/** */

public class PlainAcctSchemaDAO extends AcctSchemaDAO
{
	/**
	 * @return {@code null}. If you need to test code that relies in this method returning not-null,
	 *         the current practice is to register an anonymous subclass of {@link AcctSchemaDAO}.
	 */
	@Override
	public AcctSchemaId getAcctSchemaIdByClientAndOrg(@NonNull ClientId clientId, @NonNull OrgId orgId)
	{
		Adempiere.assertUnitTestMode();
		return null;
	}
}
