package de.metas.ui.web.view.template;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.SynchronizedMutable;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

 

/**
 * Mutable/synchronized {@link ImmutableRowsIndex} holder.
 */
public class SynchronizedRowsIndexHolder<T extends IViewRow>
{
	public static <T extends IViewRow> SynchronizedRowsIndexHolder<T> empty() {return new SynchronizedRowsIndexHolder<>(ImmutableRowsIndex.empty());}

	public static <T extends IViewRow> SynchronizedRowsIndexHolder<T> of(@NonNull final List<T> rows) {return new SynchronizedRowsIndexHolder<>(ImmutableRowsIndex.of(rows));}

	public static <T extends IViewRow> SynchronizedRowsIndexHolder<T> of(@NonNull final ImmutableRowsIndex<T> initialRowIndex) {return new SynchronizedRowsIndexHolder<>(initialRowIndex);}

	private final SynchronizedMutable<ImmutableRowsIndex<T>> holder;

	private SynchronizedRowsIndexHolder(@NonNull final ImmutableRowsIndex<T> initialRowIndex)
	{
		holder = SynchronizedMutable.of(initialRowIndex);
	}

	public ImmutableMap<DocumentId, T> getDocumentId2TopLevelRows()
	{
		return getRowsIndex().getDocumentId2TopLevelRows();
	}

	public ImmutableMap<DocumentId, T> getDocumentId2TopLevelRows(@NonNull final Predicate<T> filter)
	{
		return getRowsIndex().getDocumentId2TopLevelRows(filter);
	}

	public <ID extends RepoIdAware> ImmutableSet<ID> getRecordIdsToRefresh(
			@NonNull final DocumentIdsSelection rowIds,
			@NonNull final Function<DocumentId, ID> idMapper)
	{
		return getRowsIndex().getRecordIdsToRefresh(rowIds, idMapper);
	}

	public Stream<T> stream() {return getRowsIndex().stream();}

	public Stream<T> stream(Predicate<T> predicate) {return getRowsIndex().stream(predicate);}

	public long count(Predicate<T> predicate) {return getRowsIndex().count(predicate);}

	public boolean anyMatch(final Predicate<T> predicate) {return getRowsIndex().anyMatch(predicate);}

	public List<T> list() {return getRowsIndex().list();}

	public void compute(@NonNull final UnaryOperator<ImmutableRowsIndex<T>> remappingFunction)
	{
		holder.compute(remappingFunction);
	}

	public void addRow(@NonNull final T row)
	{
		compute(rows -> rows.addingRow(row));
	}

	@SuppressWarnings("unused")
	public void removeRowsById(@NonNull final DocumentIdsSelection rowIds)
	{
		if (rowIds.isEmpty())
		{
			return;
		}

		compute(rows -> rows.removingRowIds(rowIds));
	}

	@SuppressWarnings("unused")
	public void removingIf(@NonNull final Predicate<T> predicate)
	{
		compute(rows -> rows.removingIf(predicate));
	}

	public void changeRowById(@NonNull DocumentId rowId, @NonNull final UnaryOperator<T> rowMapper)
	{
		compute(rows -> rows.changingRow(rowId, rowMapper));
	}

	public void changeRowsByIds(@NonNull DocumentIdsSelection rowIds, @NonNull final UnaryOperator<T> rowMapper)
	{
		compute(rows -> rows.changingRows(rowIds, rowMapper));
	}

	public void setRows(@NonNull final List<T> rows)
	{
		holder.setValue(ImmutableRowsIndex.of(rows));
	}

	@NonNull
	private ImmutableRowsIndex<T> getRowsIndex()
	{
		final ImmutableRowsIndex<T> rowsIndex = holder.getValue();

		// shall not happen
		if (rowsIndex == null)
		{
			throw new AdempiereException("rowsIndex shall be set");
		}

		return rowsIndex;
	}

	public Predicate<DocumentId> isRelevantForRefreshingByDocumentId()
	{
		final ImmutableRowsIndex<T> rows = getRowsIndex();
		return rows::isRelevantForRefreshing;
	}
}
