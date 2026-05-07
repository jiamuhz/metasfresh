package de.metas.handlingunits.attributes.sscc18;

import org.adempiere.exceptions.AdempiereException;

import de.metas.organization.OrgId;
import de.metas.util.ISingletonService;

public interface ISSCC18CodeBL extends ISingletonService
{
	String SSCC18_SERIALNUMBER_SEQUENCENAME = "SSCC18_SerialNumber";

	/**
	 * @return true if the check digit is correct, false otherwise
	 */
	boolean isCheckDigitValid(SSCC18 sscc18);

	/**
	 * Generates a new SSCC18 code for given serialNumber
	 *
	 * @param orgId there might be different GS1-manufacturer-IDs for different orgs.
	 *
	 * @return generated SSCC18; never return null
	 * @throws AdempiereException if serialNumber or ManufacturerCode is not valid
	 */
	SSCC18 generate(OrgId orgId, int serialNumber);

	/**
	 * Uses its own sequence for serialNumbers (see {@link #SSCC18_SERIALNUMBER_SEQUENCENAME}) and creates a new SSCC18.
	 *
	 * @param orgId there might be different sequences and GS1-manufacturer-IDs for different orgs.
	 */
	SSCC18 generate(OrgId orgId);

	/**
	 * From the right to left, start with odd position, assign the odd/even position to each digit. Sum all digits in odd position and multiply the result by 3. Sum all digits in even position. Sum
	 * the results of step 3 and step 4. Divide the result of step 4 by 10. The check digit is the number which adds the remainder to 10.
	 *
	 * @see <a href="http://mdn.morovia.com/kb/Serial-Shipping-Container-Code-SSCC18-10601.html">SSCC18 - Check Digit Calculation</a>
	 */
	int computeCheckDigit(String stringSSCC18ToVerify);

	/**
	 * Validate the components of SSCC18
	 *
	 * throws exception if it's not valid
	 */
	void validate(SSCC18 sscc18ToValidate);
}
