package de.metas.dimension.model.validator;
 

import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.compiere.model.ModelValidator;

import de.metas.dimension.IDimensionspecDAO;
import de.metas.dimension.model.I_DIM_Dimension_Spec;
import de.metas.util.Services;

@Interceptor(I_DIM_Dimension_Spec.class)
public class DIM_Dimension_Spec
{
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_DELETE })
	public void validate(final I_DIM_Dimension_Spec spec)
	{
		Services.get(IDimensionspecDAO.class).deleteAllAssociations(spec);
		Services.get(IDimensionspecDAO.class).deleteAllSpecAttributes(spec);
	}
}
