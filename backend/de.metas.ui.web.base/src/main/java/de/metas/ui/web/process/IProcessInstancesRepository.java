package de.metas.ui.web.process;

import java.util.function.Function;
import java.util.stream.Stream;

import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.model.IDocumentChangesCollector;

 

/**
 * Process descriptors and instances repository.
 * 
 *
 *
 */
public interface IProcessInstancesRepository
{
	/**
	 * Gets the handler type.
	 * The handler type shall be unique across all {@link IProcessInstancesRepository} implementations.
	 * 
	 * @return handler type
	 */
	String getProcessHandlerType();

	/**
	 * @return process descriptor; never returns null
	 */
	ProcessDescriptor getProcessDescriptor(ProcessId processId);

	/** @return related process descriptors which are available to be called for given <code>preconditionsContext</code> */
	Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(WebuiProcessPreconditionsContext preconditionsContext);

	/**
	 * Creates a new process instance for given request.
	 * 
	 * @param request
	 * @param changesCollector 
	 * @return newly created process instance; never returns null
	 */
	IProcessInstanceController createNewProcessInstance(CreateProcessInstanceRequest request);

	/**
	 * Fetching the process instance for given <code>pinstanceId</code> (readonly) and processes it using given processor.
	 * 
	 * @param pinstanceId
	 * @param processor
	 * @return <code>processor</code>'s return value
	 */
	<R> R forProcessInstanceReadonly(DocumentId pinstanceId, Function<IProcessInstanceController, R> processor);

	/**
	 * Fetching the process instance for given <code>pinstanceId</code> (read-write) and processes it using given processor.
	 * 
	 * @param pinstanceId
	 * @param processor
	 * @return <code>processor</code>'s return value
	 */
	<R> R forProcessInstanceWritable(DocumentId pinstanceId, IDocumentChangesCollector changesCollector, Function<IProcessInstanceController, R> processor);

	/**
	 * Resets internal caches.
	 */
	void cacheReset();
}
