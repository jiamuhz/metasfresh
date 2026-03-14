package org.eevolution.api.impl;

/** */


import java.util.Iterator;

import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.IContextAware;
import org.adempiere.util.lang.ObjectUtils;
import org.compiere.model.I_M_Product;
import org.eevolution.api.IProductBOMBL;
import org.eevolution.api.IProductLowLevelUpdater;
import org.slf4j.Logger;

import de.metas.logging.LogManager;
import de.metas.product.ProductId;
import de.metas.util.Check;
import de.metas.util.Services;

/*package */class ProductLowLevelUpdater implements IProductLowLevelUpdater
{
	// services
	private final transient Logger logger = LogManager.getLogger(getClass());
	private final transient IProductBOMBL productBOMBL = Services.get(IProductBOMBL.class);
	private final transient IQueryBL queryBL = Services.get(IQueryBL.class);

	private IContextAware _context;
	private int count_ok = 0;
	private int count_err = 0;
	private boolean failOnFirstError = false;

	public ProductLowLevelUpdater()
	{
		super();
	}

	@Override
	public String toString()
	{
		return ObjectUtils.toString(this);
	}

	@Override
	public IProductLowLevelUpdater setContext(final IContextAware context)
	{
		this._context = context;
		return this;
	}

	private final IContextAware getContext()
	{
		Check.assumeNotNull(_context, "_context not null");
		return _context;
	}

	@Override
	public IProductLowLevelUpdater update()
	{
		final Iterator<I_M_Product> products = retrieveProductsToUpdate();
		while (products.hasNext())
		{
			final I_M_Product product = products.next();
			update(product);
		}

		return this;
	}

	private final void update(final I_M_Product product)
	{
		try
		{
			final int lowlevel = productBOMBL.calculateProductLowestLevel(ProductId.ofRepoId(product.getM_Product_ID()));
			product.setLowLevel(lowlevel);
			InterfaceWrapperHelper.save(product);
			count_ok++;
		}
		catch (Exception e)
		{
			final AdempiereException ex = new AdempiereException("Error while updating product: " + product.getName(), e);
			if (failOnFirstError)
			{
				throw ex;
			}

			logger.error(ex.getLocalizedMessage(), ex);
			count_err++;
		}
	}

	private Iterator<I_M_Product> retrieveProductsToUpdate()
	{
		final IQueryBuilder<I_M_Product> queryBuilder = queryBL
				.createQueryBuilder(I_M_Product.class, getContext())
				.addOnlyContextClient();

		queryBuilder.orderBy()
				.addColumn(I_M_Product.COLUMNNAME_M_Product_ID);

		final Iterator<I_M_Product> products = queryBuilder.create().iterate(I_M_Product.class);
		return products;
	}

	@Override
	public int getUpdatedCount()
	{
		return count_ok;
	}

	@Override
	public int getErrorsCount()
	{
		return count_err;
	}

	@Override
	public ProductLowLevelUpdater setFailOnFirstError(final boolean failOnFirstError)
	{
		this.failOnFirstError = failOnFirstError;
		return this;
	}
}
