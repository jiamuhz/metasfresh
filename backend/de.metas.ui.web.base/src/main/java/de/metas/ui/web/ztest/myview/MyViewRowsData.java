package de.metas.ui.web.ztest.myview;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Maps;
import de.metas.inout.ShipmentScheduleId;
import de.metas.inoutcandidate.model.I_M_ShipmentSchedule;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

@ToString
final class MyViewRowsData implements IRowsData<MyViewRow>
{
	public static MyViewRowsData ofSupplier(final Supplier<List<MyViewRow>> rowsSupplier)
	{
		return new MyViewRowsData(rowsSupplier);
	}

	public static MyViewRowsData cast(final IRowsData<MyViewRow> rowsData)
	{
		return (MyViewRowsData)rowsData;
	}

	public static final MyViewRowsData EMPTY = new MyViewRowsData(ImmutableList::of);

	private final ExtendedMemorizingSupplier<Map<DocumentId, MyViewRow>> topLevelRows;

	private MyViewRowsData(@NonNull final Supplier<List<MyViewRow>> rowsSupplier)
	{
		topLevelRows = ExtendedMemorizingSupplier.of(() -> Maps.uniqueIndex(rowsSupplier.get(), MyViewRow::getId));

	}

	@Override
	public Map<DocumentId, MyViewRow> getDocumentId2TopLevelRows()
	{
		return topLevelRows.get();
	}

	@Override
	public void invalidateAll()
	{
		topLevelRows.forget();
	}

	@Override
	public DocumentIdsSelection getDocumentIdsToInvalidate(final TableRecordReferenceSet recordRefs)
	{
		return DocumentIdsSelection.EMPTY;
	}

}
