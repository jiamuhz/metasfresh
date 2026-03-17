package de.metas.product.process;

import org.adempiere.model.InterfaceWrapperHelper;

import de.metas.process.JavaProcess;
import de.metas.product.model.I_M_Product;

/** */

/**
 *
 *
 *         This process removes the M_Product_Mapping reference from the current product.
 *         The rest of the products from other organizations that have the same M_Product_Mapping_ID are not touched.
 */
public class M_Product_Remove_Mapping_Process extends JavaProcess
{

	@Override
	protected String doIt() throws Exception
	{
		// the product will be determined by the record ID which is also the M_Product_Mapped_V_ID of the view M_Product_Mapped_V
		final I_M_Product product = InterfaceWrapperHelper.create(getCtx(), getRecord_ID(), I_M_Product.class, getTrxName());

		if (product == null)
		{
			return "@NotFound@" + "@M_Product_Target_ID@";
		}

		product.setM_Product_Mapping(null);

		InterfaceWrapperHelper.save(product);

		return "@Success@";
	}

}
