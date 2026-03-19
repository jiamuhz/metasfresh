package de.metas.ui.web.process;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.impl.EqualsQueryFilter;
import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.impl.TableRecordReference;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.process.SelectionSize;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.model.Document;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

  
public final class DocumentProcessPreconditionsContext implements WebuiProcessPreconditionsContext
{
	private final Document document;
	@Getter
	private final String tableName;

	@Getter
	private final AdTabId adTabId;
	@Getter
	private final ImmutableSet<TableRecordReference> selectedIncludedRecords;

	@Getter
	private final DisplayPlace displayPlace;

	@Builder
	private DocumentProcessPreconditionsContext(
			@NonNull final Document document,
			@Nullable final DetailId selectedTabId,
			@Nullable final Set<TableRecordReference> selectedIncludedRecords,
			@Nullable final DisplayPlace displayPlace)
	{
		this.document = document;
		tableName = document.getEntityDescriptor().getTableName();

		this.adTabId = selectedTabId != null ? selectedTabId.toAdTabId() : null;
		this.selectedIncludedRecords = selectedIncludedRecords != null ? ImmutableSet.copyOf(selectedIncludedRecords) : ImmutableSet.of();

		this.displayPlace = displayPlace;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("documentPath", document.getDocumentPath())
				.add("tableName", tableName)
				.add("adTabId", adTabId)
				.add("selectedIncludedRecords", selectedIncludedRecords)
				.toString();
	}

	@Override
	public AdWindowId getAdWindowId()
	{
		return document.getDocumentPath().getAdWindowIdOrNull();
	}

	@Override
	public <T> T getSelectedModel(final Class<T> modelClass)
	{
		return InterfaceWrapperHelper.create(document, modelClass);
	}

	@Override
	public <T> List<T> getSelectedModels(final Class<T> modelClass)
	{
		return streamSelectedModels(modelClass)
				.collect(ImmutableList.toImmutableList());
	}

	@NonNull
	@Override
	public <T> Stream<T> streamSelectedModels(@NonNull final Class<T> modelClass)
	{
		return Stream.of(getSelectedModel(modelClass));
	}

	@Override
	public int getSingleSelectedRecordId()
	{
		return document.getDocumentIdAsInt();
	}

	@Override
	public SelectionSize getSelectionSize()
	{
		return SelectionSize.ofSize(1);
	}

	@Override
	public <T> IQueryFilter<T> getQueryFilter(@NonNull final Class<T> recordClass)
	{
		final String keyColumnName = InterfaceWrapperHelper.getKeyColumnName(tableName);
		return EqualsQueryFilter.of(keyColumnName, getSingleSelectedRecordId());
	}
}
