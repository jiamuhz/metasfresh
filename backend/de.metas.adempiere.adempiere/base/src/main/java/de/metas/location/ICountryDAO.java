/**
 *
 */
package de.metas.location;

/** */

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.compiere.model.I_C_Country;
import org.compiere.model.I_C_Region;
import org.compiere.util.Env;

import de.metas.i18n.ITranslatableString;
import de.metas.money.CurrencyId;
import de.metas.organization.OrgId;
import de.metas.util.ISingletonService;

/**
 * @author cg
 *
 */
public interface ICountryDAO extends ISingletonService
{
	I_C_Country getById(CountryId id);

	/**
	 * retrieve custom user info
	 *
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	CountryCustomInfo retriveCountryCustomInfo(Properties ctx, String trxName);

	/**
	 * Get Default Country
	 *
	 * @param ctx
	 *            context
	 * @return Country
	 */
	I_C_Country getDefault(Properties ctx);

	default CountryId getDefaultCountryId()
	{
		return CountryId.ofRepoId(getDefault(Env.getCtx()).getC_Country_ID());
	}

	@Deprecated
	public I_C_Country get(Properties ctx, int C_Country_ID);

	/**
	 * Return Countries as Array
	 *
	 * @param ctx
	 *            context
	 * @return countries
	 */
	public List<I_C_Country> getCountries(Properties ctx);

	List<I_C_Region> retrieveRegions(Properties ctx, int countryId);

	Optional<CountrySequences> getCountrySequences(CountryId countryId, OrgId orgId, String adLanguage);

	I_C_Country retrieveCountryByCountryCode(String countryCode);

	CountryId getCountryIdByCountryCode(String countryCode);

	String retrieveCountryCode2ByCountryId(CountryId countryId);

	String retrieveCountryCode3ByCountryId(CountryId countryId);

	ITranslatableString getCountryNameById(CountryId countryId);

	Optional<CurrencyId> getCountryCurrencyId(CountryId countryId);

	boolean isEnforceCorrectionInvoice(CountryId countryId);
}
