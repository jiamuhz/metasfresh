
package de.metas.bpartner.service;

import com.google.common.collect.ImmutableListMultimap;
import de.metas.bpartner.BPartnerBankAccountId;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.composite.BPartnerBankAccount;
import de.metas.util.ISingletonService;
import lombok.NonNull;
import org.compiere.model.I_C_BP_BankAccount;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/** */

public interface IBPBankAccountDAO extends ISingletonService
{
	/**
	 * Retrieve all the bank accounts of the currency <code>currencyID</code> for the partner <code> partnerID</code>
	 * In case the currencyID is not set (<=0) just retrieve all accounts of the bpartner
	 * The bank accounts will be ordered by their IsDefault values, with true first.
	 */
	List<I_C_BP_BankAccount> retrieveBankAccountsForPartnerAndCurrency(Properties ctx, int partnerID, int currencyID);

	Optional<I_C_BP_BankAccount> retrieveDefaultBankAccountInTrx(@NonNull BPartnerId bpartnerId);

	/**
	 * Deactivate all {@link I_C_BP_BankAccount} records for the given bPartnerId, besides
	 * <ul>
	 * <li>the ones whose id is in the given {@code exceptIds}</li>
	 * <li>the ones that have no IBAN; why: this is used for persisting {@code BPartnerComposite}s which never have no-iban-backaccounts; so we need to prevent them from being deactivated.</li>
	 * </ul>
	 */
	void deactivateIBANAccountsByBPartnerExcept(BPartnerId bpartnerId, Collection<BPartnerBankAccountId> exceptIds);

	ImmutableListMultimap<BPartnerId, I_C_BP_BankAccount> getAllByBPartnerIds(@NonNull Collection<BPartnerId> bpartnerIds);

	List<BPartnerBankAccount> getBpartnerBankAccount(BankAccountQuery query);
}
