package de.metas.ui.web.ztest.myincludedview;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import de.metas.organization.IOrgDAO;
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
import java.util.function.Supplier;


/**
 * Class to retrieve the rows shown in {@link MyIncludedView}.
 *
 */
@Component
public class MyIncludedViewRowsRepository
{
	private final IOrgDAO orgDAO = Services.get(IOrgDAO.class);
	private final IUOMDAO uomsRepo = Services.get(IUOMDAO.class);
	private final Supplier<LookupDataSource> orderLookup;
	private final Supplier<LookupDataSource> productLookup;
	private final Supplier<LookupDataSource> bpartnerLookup;

	public MyIncludedViewRowsRepository(
			@NonNull final LookupDataSourceFactory lookupDataSourceFactory)
	{
		// creating those LookupDataSources requires DB access. So, to allow this component to be initialized early during startup
		// and also to allow it to be unit-tested (when the lookups are not part of the test), I use those suppliers.

		orderLookup = Suppliers.memoize(() -> lookupDataSourceFactory.searchInTableLookup(I_C_Order.Table_Name));
		productLookup = Suppliers.memoize(() -> lookupDataSourceFactory.searchInTableLookup(I_M_Product.Table_Name));
		bpartnerLookup = Suppliers.memoize(() -> lookupDataSourceFactory.searchInTableLookup(I_C_BPartner.Table_Name));
	}

	private List<MyIncludedViewRow> retrieveRowsByShipmentScheduleIds(final ViewId viewId)
	{
		MyIncludedViewRow myIncludedViewRow = createMyRow(viewId,  550, "apple", "yellow");
		MyIncludedViewRow myIncludedViewRow2 = createMyRow(viewId, 670, "strawberry", "red");
		List<MyIncludedViewRow> rows = ImmutableList.of(myIncludedViewRow, myIncludedViewRow2);
		return rows;
	}

	private MyIncludedViewRow createMyRow(final ViewId viewId, int docId, String itemName, String itemB)
	{
		final I_C_UOM uom = uomsRepo.getById(540048);
		final Quantity qtyPickedOrDelivered = Quantity.of(1,  uom);

		return MyIncludedViewRow.builder()
				.viewId(viewId)
			  .id( DocumentId.of(docId))
	  		.itemName( itemName )
			  .itemB( itemB )
			  //
				.build();
	}

	public MyIncludedViewRowsData createRowsData(
			@NonNull final ViewId viewId)
	{
		return MyIncludedViewRowsData.ofSupplier(() -> retrieveRowsByShipmentScheduleIds(viewId));
	}
}
