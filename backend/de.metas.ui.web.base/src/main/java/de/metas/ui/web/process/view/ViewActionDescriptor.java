package de.metas.ui.web.process.view;

import java.lang.reflect.Method;

import org.adempiere.exceptions.AdempiereException;

import com.google.common.collect.ImmutableList;

import de.metas.i18n.ITranslatableString;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.process.ProcessInstanceResult;
import de.metas.ui.web.process.ViewProcessPreconditionsContext;
import de.metas.ui.web.process.descriptor.InternalName;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.ProcessDescriptor.ProcessDescriptorType;
import de.metas.ui.web.process.descriptor.ProcessLayout;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.process.view.ViewAction.Precondition;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.model.Document;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;

import javax.annotation.Nullable;

 

/**
 * Descriptor of a method annotated with {@link ViewAction}.
 *
 *
 *
 */
@ToString
@Builder
public final class ViewActionDescriptor
{
	private final @NonNull String actionId;
	private final @NonNull Method viewActionMethod; // hard reference, because else it would be too sensible on things like cache reset

	private final @NonNull ITranslatableString caption;
	private final @NonNull ITranslatableString description;
	private final boolean defaultAction;
	private final Class<? extends Precondition> preconditionClass;
	private final Precondition preconditionSharedInstance;

	private final @NonNull PanelLayoutType layoutType;

	@Singular
	private final ImmutableList<ViewActionParamDescriptor> viewActionParamDescriptors;
	private final @NonNull ViewActionMethodReturnTypeConverter viewActionReturnTypeConverter;

	public String getActionId()
	{
		return actionId;
	}

	@Nullable public DocumentEntityDescriptor createParametersEntityDescriptor(@NonNull final ProcessId processId)
	{
		final DocumentEntityDescriptor.Builder parametersDescriptor = DocumentEntityDescriptor.builder()
				.setDocumentType(DocumentType.Process, processId.toDocumentId())
				.disableDefaultTableCallouts();

		addParametersDescriptor(parametersDescriptor);

		if (parametersDescriptor.getFieldsCount() == 0)
		{
			return null;
		}

		return parametersDescriptor.build();
	}

	private void addParametersDescriptor(final DocumentEntityDescriptor.Builder parametersDescriptor)
	{
		viewActionParamDescriptors.stream()
				.filter(ViewActionParamDescriptor::isUserParameter)
				.map(ViewActionParamDescriptor::createParameterFieldDescriptor)
				.forEach(parametersDescriptor::addField);
	}

	public ProcessDescriptor getProcessDescriptor(@NonNull final ProcessId processId)
	{
		final DocumentEntityDescriptor parametersDescriptor = createParametersEntityDescriptor(processId);

		final ProcessLayout processLayout = ProcessLayout.builder()
				.setProcessId(processId)
				.setLayoutType(layoutType)
				.setCaption(caption)
				.setDescription(description)
				.addElements(parametersDescriptor)
				.build();

		return ProcessDescriptor.builder()
				.setProcessId(processId)
				.setInternalName(InternalName.ofString(actionId))
				.setType(ProcessDescriptorType.Process)
				//
				.setLayout(processLayout)
				//
				.build();
	}

	public WebuiRelatedProcessDescriptor toWebuiRelatedProcessDescriptor(final ViewProcessPreconditionsContext viewContext)
	{
		final IView view = viewContext.getView();
		final DocumentIdsSelection selectedDocumentIds = viewContext.getSelectedRowIds();

		return WebuiRelatedProcessDescriptor.builder()
				.processId(ViewProcessInstancesRepository.buildProcessId(view.getViewId(), actionId))
				.processCaption(caption)
				.processDescription(description)
				//
				.displayPlace(DisplayPlace.ViewQuickActions)
				.defaultQuickAction(defaultAction)
				//
				.preconditionsResolutionSupplier(() -> checkPreconditions(view, selectedDocumentIds))
				//
				.build();
	}

	private ProcessPreconditionsResolution checkPreconditions(final IView view, final DocumentIdsSelection selectedDocumentIds)
	{
		try
		{
			return getPreconditionsInstance().matches(view, selectedDocumentIds);
		}
		catch (final InstantiationException | IllegalAccessException ex)
		{
			throw AdempiereException.wrapIfNeeded(ex);
		}
	}

	private Precondition getPreconditionsInstance() throws InstantiationException, IllegalAccessException
	{
		if (preconditionSharedInstance != null)
		{
			return preconditionSharedInstance;
		}
		return preconditionClass.newInstance();
	}

	@NonNull public Method getViewActionMethod()
	{
		return viewActionMethod;
	}

	public ProcessInstanceResult.ResultAction convertReturnType(final Object returnValue)
	{
		return viewActionReturnTypeConverter.convert(returnValue);
	}

	@NonNull public Object[] extractMethodArguments(final IView view, final Document processParameters, final DocumentIdsSelection selectedDocumentIds)
	{
		return viewActionParamDescriptors.stream()
				.map(paramDesc -> paramDesc.extractArgument(view, processParameters, selectedDocumentIds))
				.toArray();
	}

	@FunctionalInterface
	public interface ViewActionMethodReturnTypeConverter
	{
		ProcessInstanceResult.ResultAction convert(Object returnValue);
	}

	@FunctionalInterface
	public interface ViewActionMethodArgumentExtractor
	{
		Object extractArgument(IView view, Document processParameters, DocumentIdsSelection selectedDocumentIds);
	}
}
