 

package de.metas.servicerepair.customerreturns;

import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.inout.InOutId;
import de.metas.servicerepair.customerreturns.process.HUsToReturn_CreateShippedHU;
import de.metas.servicerepair.customerreturns.process.HUsToReturn_SelectHU;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorViewBuilder;
import de.metas.ui.web.handlingunits.HUEditorViewFactoryTemplate;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper.ClassViewColumnOverrides;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import lombok.NonNull;
import org.compiere.util.DB;

import javax.annotation.Nullable;

@ViewFactory(windowId = HUsToReturnViewFactory.Window_ID_String, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class HUsToReturnViewFactory extends HUEditorViewFactoryTemplate
{
	static final String Window_ID_String = "541011"; // FIXME: hardcoded
	private static final WindowDocumentTypeId Window_ID = WindowDocumentTypeId.fromJson(Window_ID_String);

	public static final String PARAM_HUsToReturnViewContext = "HUsToReturnViewContext";

	protected HUsToReturnViewFactory()
	{
		super(ImmutableList.of());
	}

	public static CreateViewRequest createViewRequest(@NonNull final InOutId customerReturnsId)
	{
		return CreateViewRequest.builder(Window_ID)
				.setParameter(PARAM_HUsToReturnViewContext, HUsToReturnViewContext.builder()
						.customerReturnsId(customerReturnsId)
						.build())
				.build();
	}

	@Override
	protected void customizeViewLayout(
			@NonNull final ViewLayout.Builder viewLayoutBuilder,
			final JSONViewDataType viewDataType)
	{
		viewLayoutBuilder
				.clearElements()
				.addElementsFromViewRowClassAndFieldNames(HUEditorRow.class,
														  viewDataType,
														  ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HUCode).restrictToMediaType(MediaType.SCREEN).build(),
														  ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_Locator),
														  ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_Product),
														  ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_PackingInfo).restrictToMediaType(MediaType.SCREEN).build(),
														  ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_QtyCU),
														  ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_UOM),
														  ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_SerialNo),
														  ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_ServiceContract),
														  ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HUStatus).restrictToMediaType(MediaType.SCREEN).build());
	}

	@Override
	protected void customizeHUEditorView(final HUEditorViewBuilder huViewBuilder)
	{
		huViewBuilder.assertParameterSet(PARAM_HUsToReturnViewContext);

		huViewBuilder.considerTableRelatedProcessDescriptors(false)
				.addAdditionalRelatedProcessDescriptor(createProcessDescriptor(HUsToReturn_SelectHU.class))
				.addAdditionalRelatedProcessDescriptor(createProcessDescriptor(HUsToReturn_CreateShippedHU.class));
	}

	@Nullable
	@Override
	protected String getAdditionalSqlWhereClause()
	{
		return I_M_HU.COLUMNNAME_HUStatus + "=" + DB.TO_STRING(X_M_HU.HUSTATUS_Shipped);
	}

	/**
	 * This view is not configuration dependent always should be false to execute the customizeViewLayout method
	 */
	@Override
	protected boolean isAlwaysUseSameLayout()
	{
		return false;
	}
}
