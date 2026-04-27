package de.metas.dimension.model.validator;
 

import org.adempiere.ad.modelvalidator.AbstractModuleInterceptor;
import org.adempiere.ad.modelvalidator.IModelValidationEngine;

/**
 * Module activator
 *

 *
 */
public class Main extends AbstractModuleInterceptor
{
	@Override
	protected void registerInterceptors(final IModelValidationEngine engine)
	{
		engine.addModelValidator(new de.metas.dimension.model.validator.AD_Column());
		engine.addModelValidator(new de.metas.dimension.model.validator.DIM_Dimension_Spec());
		engine.addModelValidator(new de.metas.dimension.model.validator.DIM_Dimension_Spec_Attribute());
	}
}
