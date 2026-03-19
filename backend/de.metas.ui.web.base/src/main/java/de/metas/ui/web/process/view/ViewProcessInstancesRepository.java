package de.metas.ui.web.process.view;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;

import de.metas.common.util.pair.IPair;
import de.metas.common.util.pair.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metas.cache.CCache;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.CreateProcessInstanceRequest;
import de.metas.ui.web.process.IProcessInstanceController;
import de.metas.ui.web.process.IProcessInstancesRepository;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.process.ViewProcessPreconditionsContext;
import de.metas.ui.web.process.WebuiProcessPreconditionsContext;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import lombok.NonNull;
import lombok.ToString;



@Component
public class ViewProcessInstancesRepository implements IProcessInstancesRepository
{
	@Autowired
	private IViewsRepository viewsRepository;

	private static final String PROCESS_HANDLER_TYPE = "View";

	// private final CCache<String, ViewActionDescriptorsList> viewActionsDescriptorByViewClassname = CCache.newCache("viewActionsDescriptorByViewClassname", 50, 0);

	private final CCache<String, ViewActionInstancesList> viewActionInstancesByViewId = CCache.newLRUCache("viewActionInstancesByViewId", 100, 60);

	@Override
	public String getProcessHandlerType()
	{
		return PROCESS_HANDLER_TYPE;
	}

	private final ViewActionDescriptorsList getViewActionDescriptors(@NonNull final IView view)
	{
		final ViewActionDescriptorsList viewClassActions = ViewActionDescriptorsFactory.instance.getFromClass(view.getClass());

		final ViewActionDescriptorsList viewActions = view.getActions();

		return viewClassActions.mergeWith(viewActions);
	}

	private final ViewActionDescriptor getViewActionDescriptor(final ProcessId processId)
	{
		final IPair<String, String> viewIdAndActionId = extractViewIdAndActionId(processId);
		final String viewId = viewIdAndActionId.getLeft();
		final String actionId = viewIdAndActionId.getRight();
		final IView view = viewsRepository.getView(viewId);

		return getViewActionDescriptors(view)
				.getAction(actionId);
	}

	@Override
	public ProcessDescriptor getProcessDescriptor(final ProcessId processId)
	{
		return getViewActionDescriptor(processId).getProcessDescriptor(processId);
	}

	@Override
	public Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(final WebuiProcessPreconditionsContext preconditionsContext)
	{
		final ViewProcessPreconditionsContext viewContext = ViewProcessPreconditionsContext.castOrNull(preconditionsContext);
		if (viewContext == null)
		{
			return Stream.empty();
		}

		final IView view = viewContext.getView();
		return getViewActionDescriptors(view)
				.streamDocumentRelatedProcesses(viewContext);
	}

	static final ProcessId buildProcessId(final ViewId viewId, final String viewActionId)
	{
		return ProcessId.of(PROCESS_HANDLER_TYPE, viewId.getViewId() + "_" + viewActionId);
	}

	private static final IPair<String, String> extractViewIdAndActionId(final ProcessId processId)
	{
		final String processIdStr = processId.getProcessId();
		final int idx = processIdStr.indexOf("_");
		if (idx <= 0)
		{
			throw new IllegalArgumentException("Invalid view action ID: " + processId);
		}
		final String viewId = processIdStr.substring(0, idx);
		final String actionId = processIdStr.substring(idx + 1);

		return ImmutablePair.of(viewId, actionId);
	}

	@Override
	public IProcessInstanceController createNewProcessInstance(final CreateProcessInstanceRequest request)
	{
		//
		// Get the view and and the viewActionDescriptor
		final IPair<String, String> viewIdAndActionId = extractViewIdAndActionId(request.getProcessId());
		final String viewId = viewIdAndActionId.getLeft();
		final String actionId = viewIdAndActionId.getRight();
		final IView view = viewsRepository.getView(viewId);
		final ViewActionDescriptor viewActionDescriptor = getViewActionDescriptors(view).getAction(actionId);

		//
		// Create the view action instance
		// and add it to our internal list of current view action instances
		final ViewActionInstancesList viewActionInstancesList = viewActionInstancesByViewId.getOrLoad(viewId, () -> new ViewActionInstancesList(viewId));
		final DocumentId pinstanceId = viewActionInstancesList.nextPInstanceId();
		final ViewActionInstance viewActionInstance = ViewActionInstance.builder()
				.pinstanceId(pinstanceId)
				.view(view)
				.viewActionDescriptor(viewActionDescriptor)
				.selectedDocumentIds(request.getViewRowIdsSelection().getRowIds())
				.build();
		request.assertProcessIdEquals(viewActionInstance.getProcessId());
		viewActionInstancesList.add(viewActionInstance);

		//
		// Return the newly created instance
		return viewActionInstance;
	}

	private ViewActionInstance getActionInstance(final DocumentId pinstanceId)
	{
		final String viewId = ViewActionInstancesList.extractViewId(pinstanceId);
		final ViewActionInstancesList viewActionInstancesList = viewActionInstancesByViewId.get(viewId);
		if (viewActionInstancesList == null)
		{
			throw new EntityNotFoundException("No view action instance found for " + pinstanceId);
		}

		return viewActionInstancesList.getByInstanceId(pinstanceId);
	}

	@Override
	public <R> R forProcessInstanceReadonly(final DocumentId pinstanceId, final Function<IProcessInstanceController, R> processor)
	{
		final ViewActionInstance actionInstance = getActionInstance(pinstanceId);
		return processor.apply(actionInstance);
	}

	@Override
	public <R> R forProcessInstanceWritable(final DocumentId pinstanceId, final IDocumentChangesCollector changesCollector, final Function<IProcessInstanceController, R> processor)
	{
		final ViewActionInstance actionInstance = getActionInstance(pinstanceId);

		// Make sure the process was not already executed.
		// If it was executed we are not allowed to change it.
		actionInstance.assertNotExecuted();

		return processor.apply(actionInstance);
	}

	@Override
	public void cacheReset()
	{
		viewActionInstancesByViewId.reset();
	}

	@ToString
	private static final class ViewActionInstancesList
	{
		private final String viewId;
		private final AtomicInteger nextIdSupplier = new AtomicInteger(1);
		private final ConcurrentHashMap<DocumentId, ViewActionInstance> instances = new ConcurrentHashMap<>();

		public ViewActionInstancesList(@NonNull final String viewId)
		{
			this.viewId = viewId;
		}

		public ViewActionInstance getByInstanceId(final DocumentId pinstanceId)
		{
			final ViewActionInstance actionInstance = instances.get(pinstanceId);
			if (actionInstance == null)
			{
				throw new EntityNotFoundException("No view action instance found for " + pinstanceId);
			}
			return actionInstance;
		}

		private DocumentId nextPInstanceId()
		{
			final int nextId = nextIdSupplier.incrementAndGet();
			return DocumentId.ofString(viewId + "_" + nextId);
		}

		public static final String extractViewId(@NonNull final DocumentId pinstanceId)
		{
			final String pinstanceIdStr = pinstanceId.toJson();
			final int idx = pinstanceIdStr.indexOf("_");
			final String viewId = pinstanceIdStr.substring(0, idx);
			return viewId;
		}

		public void add(final ViewActionInstance viewActionInstance)
		{
			instances.put(viewActionInstance.getInstanceId(), viewActionInstance);
		}
	}
}
