package de.metas.acct.api;

import de.metas.acct.api.impl.ElementValueId;
import de.metas.util.ISingletonService;
import lombok.NonNull;
import org.compiere.model.MAccount;
import org.compiere.util.Env;

import javax.annotation.Nullable;
import java.util.Properties;

/** */

public interface IAccountDAO extends ISingletonService
{
	@NonNull
	MAccount getById(Properties ctx, int validCombinationId);

	@NonNull
	default MAccount getById(final int validCombinationId)
	{
		return getById(Env.getCtx(), validCombinationId);
	}

	@NonNull
	MAccount getById(Properties ctx, AccountId accountId);

	@NonNull
	default MAccount getById(@NonNull final AccountId accountId)
	{
		return getById(Env.getCtx(), accountId);
	}

	ElementValueId getElementValueIdByAccountId(@NonNull AccountId accountId);

	@Nullable
	MAccount retrieveAccount(Properties ctx, AccountDimension dimension);

	@NonNull
	AccountId getOrCreate(@NonNull AccountDimension dimension);
}
