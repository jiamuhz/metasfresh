package de.metas.dimension.model.validator;
 

import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.compiere.model.ModelValidator;

import de.metas.dimension.IDimensionspecDAO;
import de.metas.dimension.model.I_AD_Column;
import de.metas.util.Services;

@Interceptor(I_AD_Column.class)
public class AD_Column
{
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_DELETE })
	public void validate(final I_AD_Column column)
	{
		Services.get(IDimensionspecDAO.class).deleteAllAssociations(column);
	}

}
