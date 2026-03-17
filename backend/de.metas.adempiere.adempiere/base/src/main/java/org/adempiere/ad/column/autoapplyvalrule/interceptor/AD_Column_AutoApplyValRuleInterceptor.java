package org.adempiere.ad.column.autoapplyvalrule.interceptor;

import org.adempiere.ad.column.autoapplyvalrule.ValRuleAutoApplierService;
import org.adempiere.ad.modelvalidator.IModelInterceptor;
import org.adempiere.ad.modelvalidator.IModelValidationEngine;
import org.adempiere.ad.modelvalidator.ModelChangeType;
import org.compiere.model.I_AD_Client;

import lombok.NonNull;
import lombok.ToString;

/** */

/**
 * A model interceptor that can auto-apply validation rules to new records which
 * do not yet have a value set for the respective columns.
 */
@ToString(exclude = { "m_AD_Client_ID" })
public class AD_Column_AutoApplyValRuleInterceptor implements IModelInterceptor
{
	private int m_AD_Client_ID = -1;

	private final ValRuleAutoApplierService valRuleAutoApplierService;

	public AD_Column_AutoApplyValRuleInterceptor(@NonNull final ValRuleAutoApplierService valRuleAutoApplierService)
	{
		this.valRuleAutoApplierService = valRuleAutoApplierService;
	}

	@Override
	public void initialize(
			@NonNull final IModelValidationEngine engine,
			@NonNull final I_AD_Client client)
	{
		if (client != null)
		{
			m_AD_Client_ID = client.getAD_Client_ID();
		}
	}

	@Override
	public int getAD_Client_ID()
	{
		return m_AD_Client_ID;
	}

	@Override
	public void onModelChange(
			@NonNull final Object recordModel,
			@NonNull final ModelChangeType changeType)
	{
		if (!ModelChangeType.BEFORE_NEW.equals(changeType))
		{
			return;
		}
		valRuleAutoApplierService.invokeApplierFor(recordModel);
	}
}
