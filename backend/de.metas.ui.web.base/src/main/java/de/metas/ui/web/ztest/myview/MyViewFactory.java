package de.metas.ui.web.ztest.myview;


import de.metas.i18n.ConstantTranslatableString;
import de.metas.ui.web.view.*;
import de.metas.ui.web.view.descriptor.IncludedViewLayout;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.ztest.MyViewConstants;
import lombok.Builder;
import lombok.NonNull;

import javax.annotation.Nullable;
 

/**
 * Factory class for {@link MyView} intances.
 *
 *
 */
@ViewFactory(windowId = MyViewConstants.WINDOWID_MyView_String, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class MyViewFactory implements IViewFactory
{
	private final MyViewRowsRepository myViewRepo;

	public MyViewFactory(
			@NonNull final MyViewRowsRepository myViewRepo
	)
	{
		this.myViewRepo = myViewRepo;
	}

	@Override
	public ViewLayout getViewLayout(
			@NonNull final WindowDocumentTypeId windowId,
			@NonNull final JSONViewDataType viewDataType,
			@Nullable final ViewProfileId profileId)
	{
		return ViewLayout.builder()
				.setWindowId(MyViewConstants.WINDOWID_MyView)
				.setCaption("MyView")
				//
				.setIncludedViewLayout(IncludedViewLayout.builder()
											   .openOnSelect(true)
											   .build())
				//
				.addElementsFromViewRowClass(MyViewRow.class, viewDataType)
				//
				.build();
	}

	@Override
	public IView createView(@NonNull final CreateViewRequest request)
	{
		final ViewId viewId = request.getViewId();
		if (!MyViewConstants.WINDOWID_MyView.equals(viewId.getWindowId()))
		{
			throw new IllegalArgumentException("Invalid request's windowId: " + request);
		}

		final MyViewRowsData rowsData = myViewRepo.createRowsData(viewId); //

		return MyView.builder()
				.viewId(viewId)
			  .description( ConstantTranslatableString.of("MyView") )
				.rowsData(rowsData)
				.build();
	}

	@Builder(builderMethodName = "createViewRequest", builderClassName = "$CreateViewRequestBuilder")
	private static CreateViewRequest createCreateViewRequest()
	{
		return CreateViewRequest.builder(
				MyViewConstants.WINDOWID_MyView)
				.build();
	}

}
