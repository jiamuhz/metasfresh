package de.metas.product.process;

import java.util.List;

import org.adempiere.model.InterfaceWrapperHelper;

import de.metas.process.Param;
import de.metas.process.JavaProcess;
import de.metas.product.IProductDAO;
import de.metas.product.ProductConstants;
import de.metas.product.model.I_M_Product;
import de.metas.product.model.I_M_Product_Mapping;
import de.metas.util.Services;

/** */

/**
 *
 * 
 *         This process creates M_Product_Mappings between 2 products from 2 different organizations.
 *         In case the mapping exists for one of the products, it is set for the other one, too.
 *         In case the current product has a different mapping than the target, it will be set for the target product and all the other products with the same mapping.
 *
 */
public class M_Product_Create_Mappings_Process extends JavaProcess
{

	@Param(parameterName = ProductConstants.TARGET_PRODUCT_ID)
	private int p_targetProductID;

	@Override
	protected String doIt() throws Exception
	{

		final I_M_Product product = InterfaceWrapperHelper.create(getCtx(), getRecord_ID(), I_M_Product.class, getTrxName());

		if (product == null)
		{
			return "@NotFound@" + "@M_Product_ID@";
		}

		final I_M_Product targetProduct = InterfaceWrapperHelper.create(getCtx(), p_targetProductID, I_M_Product.class, getTrxName());

		if (targetProduct == null)
		{
			return "@NotFound@" + "@M_Product_Target_ID@";
		}

		final I_M_Product_Mapping currentProdMapping = product.getM_Product_Mapping();
		final I_M_Product_Mapping targetProdMapping = targetProduct.getM_Product_Mapping();

		// in case there is no mapping for any of the products, create one and assign it to both of them
		if (currentProdMapping == null && targetProdMapping == null)
		{
			final I_M_Product_Mapping mappingToSet = InterfaceWrapperHelper.newInstance(I_M_Product_Mapping.class, product);
			mappingToSet.setValue(product.getValue());
			InterfaceWrapperHelper.save(mappingToSet);

			product.setM_Product_Mapping(mappingToSet);
			targetProduct.setM_Product_Mapping(mappingToSet);
		}

		// in case the target product doesn't have a mapping, set the one of the given products
		else if (targetProdMapping == null)
		{
			targetProduct.setM_Product_Mapping(currentProdMapping);
		}

		// in case the current product doesn't have a mapping but the target has one, set it to the current product as well
		else if (currentProdMapping == null)
		{
			product.setM_Product_Mapping(targetProdMapping);
		}

		else
		{
			// in case the two products have different mappings, will pick the one from the current product,
			// set it to the target and update all the other products that have the target's ex-mapping with the current one

			final List<I_M_Product> mappedProducts = Services.get(IProductDAO.class).retrieveAllMappedProducts(targetProduct);

			for (final I_M_Product mappedProduct : mappedProducts)
			{
				mappedProduct.setM_Product_Mapping(currentProdMapping);

				InterfaceWrapperHelper.save(mappedProduct);
			}

			// finally, set the current mapping to the target product as well
			targetProduct.setM_Product_Mapping(currentProdMapping);
		}

		InterfaceWrapperHelper.save(product);
		InterfaceWrapperHelper.save(targetProduct);

		return "@Success@";
	}

}
