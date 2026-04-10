package de.metas.ui.web.ztest.myview;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.inout.ShipmentScheduleId;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.order.OrderLineId;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.picking.PickingConstants;
import de.metas.ui.web.picking.pickingslot.PickingSlotViewsStorage;
import de.metas.ui.web.view.*;
import de.metas.ui.web.view.ViewRow.DefaultRowType;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.WidgetSize;
import de.metas.ui.web.ztest.MyViewConstants;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.util.lang.impl.TableRecordReference;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 */
@ToString(exclude = "values")
public final class MyViewRow implements IViewRow
{
	private final ViewId viewId;
	private final DocumentId id;
	private final DocumentPath documentPath;

	@ViewColumn(widgetType = DocumentFieldWidgetType.Lookup, captionKey = I_M_Packageable_V.COLUMNNAME_M_Product_ID, layouts = {
			@ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 20)
	})
	private final LookupValue product;

	@ViewColumn(widgetType = DocumentFieldWidgetType.YesNo, captionKey = "Picked", layouts = {
			@ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 25)
	})
	private final boolean picked;

	@ViewColumn(widgetType = DocumentFieldWidgetType.Quantity, captionKey = I_M_Packageable_V.COLUMNNAME_QtyOrdered, layouts = {
			@ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 30)
	})
	private final Quantity qtyOrdered;

	@ViewColumn(widgetType = DocumentFieldWidgetType.Quantity, captionKey = "QtyPicked", layouts = {
			@ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 35)
	})
	private final Quantity qtyPicked;


	private final ViewId includedViewId;

	private final ViewRowFieldNameAndJsonValuesHolder<MyViewRow> values = ViewRowFieldNameAndJsonValuesHolder.newInstance(MyViewRow.class);

	public static MyViewRow cast(final IViewRow row)
	{
		return (MyViewRow)row;
	}

	public static DocumentId createRowIdFromShipmentScheduleId(final ShipmentScheduleId shipmentScheduleId)
	{
		return DocumentId.of(shipmentScheduleId.getRepoId());
	}

	@Builder
	private MyViewRow(
			@NonNull final ViewId viewId,
			@NonNull final DocumentId id,
			final LookupValue product,
			@NonNull final Quantity qtyOrdered,
			final Quantity qtyPicked)
	{
		this.viewId = viewId;
		this.id = id;
		this.documentPath = DocumentPath.rootDocumentPath(MyViewConstants.WINDOWID_MyView, id);

		this.product = product;
		this.qtyOrdered = qtyOrdered;
		this.qtyPicked = qtyPicked;
		this.picked = qtyPicked != null && qtyPicked.compareTo(qtyOrdered) >= 0;

		//this.includedViewId = PickingSlotViewsStorage.createViewId(viewId, id);
		this.includedViewId = ViewId.of(MyViewConstants.WINDOWID_MySubView_String, MyViewConstants.WINDOWID_MySubView_String + "-E");
	}

	@Override
	public DocumentId getId()
	{
		return id;
	}

	@Override
	public IViewRowType getType()
	{
		return DefaultRowType.Row;
	}

	@Override
	public boolean isProcessed()
	{
		return false;
	}

	@Override
	public DocumentPath getDocumentPath()
	{
		return documentPath;
	}

	@Override
	public ImmutableSet<String> getFieldNames()
	{
		return values.getFieldNames();
	}

	@Override
	public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues()
	{
		return values.get(this);
	}

	@Override
	public List<? extends IViewRow> getIncludedRows()
	{
		return ImmutableList.of();
	}

	@Override
	public boolean hasAttributes()
	{
		return false;
	}

	@Override
	public IViewRowAttributes getAttributes() throws EntityNotFoundException
	{
		throw new EntityNotFoundException("Row does not support attributes");
	}

	@Override
	public ViewId getIncludedViewId()
	{
		return includedViewId;
	}

	public ProductId getProductId()
	{
		return product != null ? ProductId.ofRepoIdOrNull(product.getIdAsInt()) : null;
	}

	public Quantity getQtyOrderedWithoutPicked()
	{
		return qtyOrdered.subtract(qtyPicked).toZeroIfNegative();
	}
}
