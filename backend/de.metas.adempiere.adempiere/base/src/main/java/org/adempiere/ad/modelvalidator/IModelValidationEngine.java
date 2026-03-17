package org.adempiere.ad.modelvalidator;

/** */

import org.compiere.model.I_AD_Client;

import de.metas.impexp.processing.IImportInterceptor;
import lombok.NonNull;

/**
 * Model Validation Engine
 *
 * @author tsa
 *
 */
public interface IModelValidationEngine
{
	void addModelValidator(@NonNull Object validator);

	void addModelValidator(Object interceptorObj, I_AD_Client client);

	void addModelChange(String tableName, IModelInterceptor interceptor);

	void addDocValidate(String tableName, IModelInterceptor interceptor);

	void removeModelChange(String tableName, IModelInterceptor interceptor);

	void addImportInterceptor(String importTableName, IImportInterceptor listener);
}
