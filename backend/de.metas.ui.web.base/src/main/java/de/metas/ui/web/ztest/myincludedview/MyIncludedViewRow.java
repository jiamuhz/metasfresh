package de.metas.ui.web.ztest.myincludedview;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.inout.ShipmentScheduleId;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.*;
import de.metas.ui.web.view.ViewRow.DefaultRowType;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.ztest.MyViewConstants;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

/**
 *
 */
@ToString(exclude = "values")
public final class MyIncludedViewRow implements IViewRow
{
	private final ViewId viewId;
	private final DocumentId id;
	private final DocumentPath documentPath;

	@ViewColumn(widgetType = DocumentFieldWidgetType.Text, captionKey = "itemName", layouts = {
			@ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 20)
	})
	private final String itemName;

	@ViewColumn(widgetType = DocumentFieldWidgetType.Text, captionKey = "itemB", layouts = {
			@ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 25)
	})
	private final String itemB;


	private final ViewRowFieldNameAndJsonValuesHolder<MyIncludedViewRow> values = ViewRowFieldNameAndJsonValuesHolder.newInstance(MyIncludedViewRow.class);

	public static MyIncludedViewRow cast(final IViewRow row)
	{
		return (MyIncludedViewRow)row;
	}

	public static DocumentId createRowIdFromShipmentScheduleId(final ShipmentScheduleId shipmentScheduleId)
	{
		return DocumentId.of(shipmentScheduleId.getRepoId());
	}

	@Builder
	private MyIncludedViewRow(
			@NonNull final ViewId viewId,
			@NonNull final DocumentId id,
			final String itemName,
			final String itemB)
	{
		this.viewId = viewId;
		this.id = id;
		this.documentPath = DocumentPath.rootDocumentPath(MyViewConstants.WINDOWID_MyView, id);

		this.itemName = itemName;
		this.itemB = itemB;

		//this.includedViewId = PickingSlotViewsStorage.createViewId(viewId, id);
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
		return null;
	}

	public String getItemName() {

		return itemName;
	}

	public String getItemB() {
		return itemB;
	}

}
