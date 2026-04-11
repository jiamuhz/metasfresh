package de.metas.ui.web.ztest.myincludedview;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@ToString
final class MyIncludedViewRowsData implements IRowsData<MyIncludedViewRow>
{
	public static MyIncludedViewRowsData ofSupplier(final Supplier<List<MyIncludedViewRow>> rowsSupplier)
	{
		return new MyIncludedViewRowsData(rowsSupplier);
	}

	public static MyIncludedViewRowsData cast(final IRowsData<MyIncludedViewRow> rowsData)
	{
		return (MyIncludedViewRowsData)rowsData;
	}

	public static final MyIncludedViewRowsData EMPTY = new MyIncludedViewRowsData(ImmutableList::of);

	private final ExtendedMemorizingSupplier<Map<DocumentId, MyIncludedViewRow>> topLevelRows;

	private MyIncludedViewRowsData(@NonNull final Supplier<List<MyIncludedViewRow>> rowsSupplier)
	{
		topLevelRows = ExtendedMemorizingSupplier.of(() -> Maps.uniqueIndex(rowsSupplier.get(), MyIncludedViewRow::getId));

	}

	@Override
	public Map<DocumentId, MyIncludedViewRow> getDocumentId2TopLevelRows()
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
