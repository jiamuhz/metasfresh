package de.metas.ui.web.process;

import java.util.Collection;
import java.util.List;

import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.LookupValuesPage;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;

 

/**
 * Process instance controller.
 * 
 * Implementations of this call are able to manage a process instance life-cycle:
 * <ul>
 * <li>parameters: providing current values, changing parameters, providing lookup values
 * <li>starting the process
 * </ul>
 *
 *
 *
 */
public interface IProcessInstanceController
{
	DocumentId getInstanceId();

	boolean isStartProcessDirectly();

	ProcessInstanceResult startProcess(ProcessExecutionContext context);

	/**
	 * @return execution result or throws exception if the process was not already executed
	 */
	ProcessInstanceResult getExecutionResult();

	//
	// Process parameters
	//@formatter:off
	Collection<IProcessInstanceParameter> getParameters();
	LookupValuesList getParameterLookupValues(String parameterName);
	LookupValuesPage getParameterLookupValuesForQuery(String parameterName, String query);
	void processParameterValueChanges(List<JSONDocumentChangedEvent> events, ReasonSupplier reason);
	//@formatter:on

}
