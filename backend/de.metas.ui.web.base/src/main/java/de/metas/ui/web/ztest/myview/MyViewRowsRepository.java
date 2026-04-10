package de.metas.ui.web.ztest.myview;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.inout.ShipmentScheduleId;
import de.metas.order.OrderLineId;
import de.metas.organization.IOrgDAO;
import de.metas.picking.api.IPackagingDAO;
import de.metas.picking.api.Packageable;
import de.metas.quantity.Quantity;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.uom.IUOMDAO;
import de.metas.util.Services;
import lombok.NonNull;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;



/**
 * Class to retrieve the rows shown in {@link MyView}.
 *
 *
 *
 */
@Component
public class MyViewRowsRepository
{
	private final IOrgDAO orgDAO = Services.get(IOrgDAO.class);
	private final IUOMDAO uomsRepo = Services.get(IUOMDAO.class);
	private final Supplier<LookupDataSource> orderLookup;
	private final Supplier<LookupDataSource> productLookup;
	private final Supplier<LookupDataSource> bpartnerLookup;

	public MyViewRowsRepository(
			@NonNull final LookupDataSourceFactory lookupDataSourceFactory)
	{
		// creating those LookupDataSources requires DB access. So, to allow this component to be initialized early during startup
		// and also to allow it to be unit-tested (when the lookups are not part of the test), I use those suppliers.

		orderLookup = Suppliers.memoize(() -> lookupDataSourceFactory.searchInTableLookup(I_C_Order.Table_Name));
		productLookup = Suppliers.memoize(() -> lookupDataSourceFactory.searchInTableLookup(I_M_Product.Table_Name));
		bpartnerLookup = Suppliers.memoize(() -> lookupDataSourceFactory.searchInTableLookup(I_C_BPartner.Table_Name));
	}

	private List<MyViewRow> retrieveRowsByShipmentScheduleIds(final ViewId viewId)
	{
		MyViewRow myViewRow = createMyRow(viewId,  100, 2005626, 8);
		MyViewRow myViewRow2 = createMyRow(viewId, 200, 2005626, 9);
		List<MyViewRow> rows = ImmutableList.of(myViewRow, myViewRow2);
		return rows;
	}

	private MyViewRow createMyRow(final ViewId viewId, int docId, int productId, int qtyOrdered)
	{
		final I_C_UOM uom = uomsRepo.getById(540048);
		final Quantity qtyPickedOrDelivered = Quantity.of(1,  uom);

		return MyViewRow.builder()
				.viewId(viewId)
			  .id( DocumentId.of(docId))
				//
				.product(productLookup.get().findById(productId))
				//
				.qtyOrdered(Quantity.of(qtyOrdered, uom))
				.qtyPicked(qtyPickedOrDelivered)
				//
				.build();
	}

	public MyViewRowsData createRowsData(
			@NonNull final ViewId viewId)
	{
		return MyViewRowsData.ofSupplier(() -> retrieveRowsByShipmentScheduleIds(viewId));
	}
}
