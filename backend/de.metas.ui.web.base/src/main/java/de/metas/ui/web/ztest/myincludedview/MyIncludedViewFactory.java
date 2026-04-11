package de.metas.ui.web.ztest.myincludedview;


import de.metas.i18n.ConstantTranslatableString;
import de.metas.ui.web.view.*;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.ztest.MyViewConstants;
import lombok.Builder;
import lombok.NonNull;

import javax.annotation.Nullable;
 

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
			  .description( ConstantTranslatableString.of("MySubView") )
				.rowsData(rowsData)
				.build();
	}

	@Builder(builderMethodName = "createViewRequest", builderClassName = "$CreateViewRequestBuilder")
	private static CreateViewRequest createCreateViewRequest()
	{
		return CreateViewRequest.builder(
				MyViewConstants.WINDOWID_MyIncludedView)
				.build();
	}

}
