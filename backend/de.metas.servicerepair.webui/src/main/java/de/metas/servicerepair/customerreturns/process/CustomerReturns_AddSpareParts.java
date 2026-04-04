 

package de.metas.servicerepair.customerreturns.process;

import com.google.common.collect.ImmutableSet;
import de.metas.inout.InOutId;
import de.metas.process.IProcessParametersCallout;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.servicerepair.customerreturns.RepairCustomerReturnsService;
import de.metas.servicerepair.customerreturns.SparePartsReturnCalculation;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.uom.IUOMConversionBL;
import de.metas.uom.IUOMDAO;
import de.metas.uom.UomId;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.SpringContextHolder;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;

import javax.annotation.Nullable;
import java.math.BigDecimal;

public class CustomerReturns_AddSpareParts
		extends CustomerReturnsBasedProcess
		implements IProcessPrecondition, IProcessParametersCallout
{
	private final IUOMDAO uomDAO = Services.get(IUOMDAO.class);
	private final IUOMConversionBL uomConversionBL = Services.get(IUOMConversionBL.class);
	private final RepairCustomerReturnsService repairCustomerReturnsService = SpringContextHolder.instance.getBean(RepairCustomerReturnsService.class);
	private final LookupDataSourceFactory lookupDataSourceFactory = SpringContextHolder.instance.getBean(LookupDataSourceFactory.class);

	private static final String PARAM_M_Product_ID = "M_Product_ID";
	@Param(parameterName = PARAM_M_Product_ID, mandatory = true)
	private ProductId sparePartId;

	@Param(parameterName = "Qty", mandatory = true)
	@Nullable
	private BigDecimal qtyBD;

	private static final String PARAM_C_UOM_ID = "C_UOM_ID";
	@Param(parameterName = PARAM_C_UOM_ID, mandatory = true)
	@Nullable
	private UomId uomId;

	private SparePartsReturnCalculation _sparePartsCalculation; // lazy

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable(final @NonNull IProcessPreconditionsContext context)
	{
		return checkSingleDraftedServiceRepairReturns(context);
	}

	@ProcessParamLookupValuesProvider(
			parameterName = PARAM_M_Product_ID,
			numericKey = true,
			lookupSource = DocumentLayoutElementFieldDescriptor.LookupSource.list,
			lookupTableName = I_M_Product.Table_Name)
	public LookupValuesList getProducts()
	{
		final ImmutableSet<ProductId> sparePartIds = getSparePartsCalculation().getAllowedSparePartIds();
		return lookupDataSourceFactory.searchInTableLookup(I_M_Product.Table_Name).findByIdsOrdered(sparePartIds);
	}

	@Override
	public void onParameterChanged(@NonNull final String parameterName)
	{
		if (PARAM_M_Product_ID.equals(parameterName))
		{
			final ProductId sparePartId = this.sparePartId;
			if (sparePartId != null)
			{
				final Quantity qty = getSparePartsCalculation().computeQtyOfSparePartsRequiredNet(sparePartId, uomConversionBL).orElse(null);
				this.qtyBD = qty != null ? qty.toBigDecimal() : null;
				this.uomId = qty != null ? qty.getUomId() : null;

			}
		}
	}

	@Override
	protected String doIt()
	{
		final InOutId customerReturnId = getCustomerReturnId();
		final Quantity qtyReturned = getQtyReturned();

		repairCustomerReturnsService.prepareAddSparePartsToCustomerReturn()
				.customerReturnId(customerReturnId)
				.productId(sparePartId)
				.qtyReturned(qtyReturned)
				.build();

		return MSG_OK;
	}

	private Quantity getQtyReturned()
	{
		if (qtyBD == null)
		{
			throw new FillMandatoryException("Qty");
		}
		if (uomId == null)
		{
			throw new FillMandatoryException("C_UOM_ID");
		}

		final I_C_UOM uom = uomDAO.getById(uomId);
		return Quantity.of(qtyBD, uom);
	}

	private SparePartsReturnCalculation getSparePartsCalculation()
	{
		SparePartsReturnCalculation sparePartsCalculation = _sparePartsCalculation;
		if (sparePartsCalculation == null)
		{
			sparePartsCalculation = _sparePartsCalculation = repairCustomerReturnsService.getSparePartsCalculation(getCustomerReturnId());
		}
		return sparePartsCalculation;
	}

}
