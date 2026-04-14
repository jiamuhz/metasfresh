package de.metas.ui.web.ztest.myincludedview;


import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ConstantTranslatableString;
import de.metas.process.AdProcessId;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_TU_Label;
import de.metas.ui.web.view.*;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.ztest.MyViewConstants;
import de.metas.ui.web.ztest.process.WEBUI_MyProcess4IncludedViewLauncher;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.List;


/**
 * Factory class for {@link MyIncludedView} intances.
 *
 *
 */
@ViewFactory(windowId = MyViewConstants.WINDOWID_MyIncludedView_String, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class MyIncludedViewFactory implements IViewFactory
{
	private final MyIncludedViewRowsRepository myIncludedViewRepo;

	public MyIncludedViewFactory(
			@NonNull final MyIncludedViewRowsRepository myIncludedViewRepo
	)
	{
		this.myIncludedViewRepo = myIncludedViewRepo;
	}

	@Override
	public ViewLayout getViewLayout(
			@NonNull final WindowId windowId,
			@NonNull final JSONViewDataType viewDataType,
			@Nullable final ViewProfileId profileId)
	{
		return ViewLayout.builder()
				.setWindowId(MyViewConstants.WINDOWID_MyIncludedView)
				.setCaption("MyIncludedView")
				//
				//.setIncludedViewLayout(IncludedViewLayout.builder()
				//							   .openOnSelect(true)
				//							   .build())
				//
				.addElementsFromViewRowClass(MyIncludedViewRow.class, viewDataType)
				//
				.build();
	}

	@Override
	public IView createView(@NonNull final CreateViewRequest request)
	{
		final ViewId viewId = request.getViewId();
		if (!MyViewConstants.WINDOWID_MyIncludedView.equals(viewId.getWindowId()))
		{
			throw new IllegalArgumentException("Invalid request's windowId: " + request);
		}

		final MyIncludedViewRowsData rowsData = myIncludedViewRepo.createRowsData(viewId); //

		return MyIncludedView.builder()
				.viewId(viewId)
			  .description( ConstantTranslatableString.of("MyIncludedView") )
				.rowsData(rowsData)
			  .additionalRelatedProcessDescriptors( createAdditionalRelatedProcessDescriptors() )
				.build();
	}

	@Builder(builderMethodName = "createViewRequest", builderClassName = "$CreateViewRequestBuilder")
	private static CreateViewRequest createCreateViewRequest()
	{
		return CreateViewRequest.builder(
				MyViewConstants.WINDOWID_MyIncludedView)
				.build();
	}

	private List<RelatedProcessDescriptor> createAdditionalRelatedProcessDescriptors() {
		return ImmutableList.of(
				createProcessDescriptorForMyIncludedView(WEBUI_MyProcess4IncludedViewLauncher.class));
	}

	private static RelatedProcessDescriptor createProcessDescriptorForMyIncludedView(@NonNull final Class<?> processClass)
	{
		final IADProcessDAO adProcessDAO = Services.get(IADProcessDAO.class);

		final AdProcessId processId = adProcessDAO.retrieveProcessIdByClassIfUnique(processClass);
		Preconditions.checkArgument(processId != null, "No AD_Process_ID found for %s", processClass);

		return RelatedProcessDescriptor.builder()
			.processId(processId)
			.displayPlace(RelatedProcessDescriptor.DisplayPlace.ViewQuickActions)
			.build();
	}

}
