package de.metas.ui.web.quickinput.forecastline;

import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.model.I_M_ForecastLine;
import de.metas.handlingunits.order.api.IHUOrderBL;
import de.metas.i18n.IMsgBL;
import de.metas.order.SOTrx;
import de.metas.material.cockpit.availableforsales.AvailableForSalesConfigRepo;
import de.metas.product.ProductId;
import de.metas.ui.web.material.adapter.AvailableForSaleAdapter;
import de.metas.ui.web.material.adapter.AvailableToPromiseAdapter;
import de.metas.ui.web.quickinput.IQuickInputDescriptorFactory;
import de.metas.ui.web.quickinput.QuickInput;
import de.metas.ui.web.quickinput.QuickInputConstants;
import de.metas.ui.web.quickinput.QuickInputDescriptor;
import de.metas.ui.web.quickinput.QuickInputLayoutDescriptor;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Builder;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptorProviders;
import de.metas.ui.web.window.descriptor.sql.ProductLookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.ProductLookupDescriptor.ProductAndAttributes;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.adempiere.ad.validationRule.AdValRuleId;
import org.compiere.model.I_M_Forecast;
import org.compiere.util.DisplayType;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

 

@Component
public class ForecastLineQuickInputDescriptorFactory implements IQuickInputDescriptorFactory
{
	private final AvailableToPromiseAdapter availableToPromiseAdapter;
	private final AvailableForSaleAdapter availableForSaleAdapter;
	private final AvailableForSalesConfigRepo availableForSalesConfigRepo;
	private final LookupDescriptorProviders lookupDescriptorProviders;

	public ForecastLineQuickInputDescriptorFactory(
			@NonNull final AvailableToPromiseAdapter availableToPromiseAdapter,
			@NonNull final AvailableForSaleAdapter availableForSaleAdapter,
			@NonNull final AvailableForSalesConfigRepo availableForSalesConfigRepo,
			@NonNull final LookupDescriptorProviders lookupDescriptorProviders)
	{
		this.availableToPromiseAdapter = availableToPromiseAdapter;
		this.availableForSaleAdapter = availableForSaleAdapter;
		this.availableForSalesConfigRepo = availableForSalesConfigRepo;
		this.lookupDescriptorProviders = lookupDescriptorProviders;
	}

	@Override
	public Set<MatchingKey> getMatchingKeys()
	{
		return ImmutableSet.of(MatchingKey.ofTableName(I_M_ForecastLine.Table_Name));
	}

	@Override
	public QuickInputDescriptor createQuickInputDescriptor(
			final DocumentType documentType,
			final DocumentId documentTypeId,
			final DetailId detailId,
			@NonNull final Optional<SOTrx> soTrx)
	{
		final DocumentEntityDescriptor entityDescriptor = createEntityDescriptor(documentTypeId, detailId, soTrx);
		final QuickInputLayoutDescriptor layout = createLayout(entityDescriptor);

		return QuickInputDescriptor.of(entityDescriptor, layout, ForecastLineQuickInputProcessor.class);
	}

	private static DocumentEntityDescriptor.Builder createDescriptorBuilder(final DocumentId documentTypeId, final DetailId detailId)
	{
		return DocumentEntityDescriptor.builder()
				.setDocumentType(DocumentType.QuickInput, documentTypeId)
				.disableDefaultTableCallouts()
				// Defaults:
				.setDetailId(detailId)
				.setTableName(I_M_ForecastLine.Table_Name);
	}

	private Builder createPackingInstructionFieldBuilder()
	{
		return DocumentFieldDescriptor.builder(IForecastLineQuickInput.COLUMNNAME_M_HU_PI_Item_Product_ID)
				.setCaption(Services.get(IMsgBL.class).translatable(IForecastLineQuickInput.COLUMNNAME_M_HU_PI_Item_Product_ID))
				//
				.setWidgetType(DocumentFieldWidgetType.Lookup)
				.setLookupDescriptorProvider(lookupDescriptorProviders.sql()
						.setCtxTableName(null) // ctxTableName
						.setCtxColumnName(IForecastLineQuickInput.COLUMNNAME_M_HU_PI_Item_Product_ID)
						.setDisplayType(DisplayType.TableDir)
						.setAD_Val_Rule_ID(AdValRuleId.ofRepoId(540365)) // FIXME: hardcoded "M_HU_PI_Item_Product_For_Org_and_Product_and_DatePromised"
						.build())
				.setValueClass(IntegerLookupValue.class)
				.setReadonlyLogic(ConstantLogicExpression.FALSE)
				.setAlwaysUpdateable(true)
				.setMandatoryLogic(ConstantLogicExpression.FALSE)
				.setDisplayLogic(ConstantLogicExpression.TRUE)
				.addCharacteristic(Characteristic.PublicField);
	}

	private static Builder createQuantityFieldBuilder()
	{
		return DocumentFieldDescriptor.builder(IForecastLineQuickInput.COLUMNNAME_Qty)
				.setCaption(Services.get(IMsgBL.class).translatable(IForecastLineQuickInput.COLUMNNAME_Qty))
				.setWidgetType(DocumentFieldWidgetType.Quantity)
				.setReadonlyLogic(ConstantLogicExpression.FALSE)
				.setAlwaysUpdateable(true)
				.setMandatoryLogic(ConstantLogicExpression.TRUE)
				.setDisplayLogic(ConstantLogicExpression.TRUE)
				.addCharacteristic(Characteristic.PublicField);
	}

	private static void onProductChangedCallout(final ICalloutField calloutField)
	{
		final QuickInput quickInput = QuickInput.getQuickInputOrNull(calloutField);
		if (quickInput == null)
		{
			return;
		}

		final IForecastLineQuickInput quickInputModel = quickInput.getQuickInputDocumentAs(IForecastLineQuickInput.class);
		final LookupValue productLookupValue = quickInputModel.getM_Product_ID();
		if (productLookupValue == null)
		{
			return;
		}

		final ProductAndAttributes productAndAttributes = ProductLookupDescriptor.toProductAndAttributes(productLookupValue);
		final ProductId quickInputProduct = productAndAttributes.getProductId();

		final I_M_Forecast forecast = quickInput.getRootDocumentAs(I_M_Forecast.class);
		Services.get(IHUOrderBL.class).findM_HU_PI_Item_ProductForForecast(forecast, quickInputProduct, quickInputModel::setM_HU_PI_Item_Product);
	}

	private DocumentEntityDescriptor createEntityDescriptor(
			final DocumentId documentTypeId,
			final DetailId detailId,
			@NonNull final Optional<SOTrx> soTrx)
	{
		return createDescriptorBuilder(documentTypeId, detailId)
				.addField(createProductFieldBuilder())
				.addFieldIf(QuickInputConstants.isEnablePackingInstructionsField(), this::createPackingInstructionFieldBuilder)
				.addField(createQuantityFieldBuilder())
				.setIsSOTrx(soTrx)
				.build();
	}

	private Builder createProductFieldBuilder()
	{
		return DocumentFieldDescriptor.builder(IForecastLineQuickInput.COLUMNNAME_M_Product_ID)
				.setCaption(Services.get(IMsgBL.class).translatable(IForecastLineQuickInput.COLUMNNAME_M_Product_ID))
				//
				.setWidgetType(DocumentFieldWidgetType.Lookup)
				.setLookupDescriptorProvider(ProductLookupDescriptor.builderWithStockInfo()
						.bpartnerParamName(I_M_Forecast.COLUMNNAME_C_BPartner_ID)
						.pricingDateParamName(I_M_Forecast.COLUMNNAME_DatePromised)
						.availableStockDateParamName(I_M_Forecast.COLUMNNAME_DatePromised)
						.availableToPromiseAdapter(availableToPromiseAdapter)
						.availableForSaleAdapter(availableForSaleAdapter)
						.availableForSalesConfigRepo(availableForSalesConfigRepo)
						.build())
				.setReadonlyLogic(ConstantLogicExpression.FALSE)
				.setAlwaysUpdateable(true)
				.setMandatoryLogic(ConstantLogicExpression.TRUE)
				.setDisplayLogic(ConstantLogicExpression.TRUE)
				.addCallout(ForecastLineQuickInputDescriptorFactory::onProductChangedCallout)
				.addCharacteristic(Characteristic.PublicField);
	}

	private static QuickInputLayoutDescriptor createLayout(final DocumentEntityDescriptor entityDescriptor)
	{
		return QuickInputLayoutDescriptor.onlyFields(entityDescriptor, new String[][] {
				{ "M_Product_ID", "M_HU_PI_Item_Product_ID" } //
				, { "Qty" }
		});
	}
}
