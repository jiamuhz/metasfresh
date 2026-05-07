package org.adempiere.ad.column.model.interceptor;

import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.ad.table.api.IADTableDAO;
import org.compiere.model.I_AD_SQLColumn_SourceTableColumn;
import org.compiere.model.ModelValidator;

import de.metas.util.Services;

@Interceptor(I_AD_SQLColumn_SourceTableColumn.class)
public class AD_SQLColumn_SourceTableColumn
{
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW, ModelValidator.TYPE_BEFORE_CHANGE })
	public void beforeSave(final I_AD_SQLColumn_SourceTableColumn record)
	{
		final IADTableDAO adTableDAO = Services.get(IADTableDAO.class);
		adTableDAO.validate(record);
	}
}
