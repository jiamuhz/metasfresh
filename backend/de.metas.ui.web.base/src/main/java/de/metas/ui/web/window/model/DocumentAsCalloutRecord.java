package de.metas.ui.web.window.model;

import com.google.common.base.MoreObjects;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
import org.adempiere.ad.callout.api.ICalloutRecord;
import org.adempiere.model.InterfaceWrapperHelper;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;


/* package */final class DocumentAsCalloutRecord implements ICalloutRecord
{
	private static final ReasonSupplier REASON_Value_DirectSetOnCalloutRecord = () -> "direct set on callout record";

	private final Reference<Document> _documentRef;

	/* package */ DocumentAsCalloutRecord(@NonNull final Document document)
	{
		_documentRef = new WeakReference<>(document);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.addValue(_documentRef.get())
				.toString();
	}

	private Document getDocument()
	{
		final Document document = _documentRef.get();
		if (document == null)
		{
			throw new IllegalStateException("Document reference already expired");
		}
		return document;
	}

	@Override
	public String getTableName()
	{
		final Document document = getDocument();
		return document.getEntityDescriptor().getTableName();
	}

	@Override
	public int getAD_Tab_ID()
	{
		final Document document = getDocument();
		return document.getEntityDescriptor().getAdTabId().getRepoId();
	}

	@Override
	public <T> T getModel(final Class<T> modelClass)
	{
		final Document document = getDocument();
		return DocumentInterfaceWrapper.wrap(document, modelClass);
	}

	@Override
	public <T> T getModelBeforeChanges(final Class<T> modelClass)
	{
		final Document document = getDocument();
		return DocumentInterfaceWrapper.wrapUsingOldValues(document, modelClass);
	}

	@Override
	public Object getValue(final String columnName)
	{
		final Document document = getDocument();
		return InterfaceWrapperHelper.getValueOrNull(document, columnName);
	}

	@Override
	public String setValue(final String columnName, final Object value)
	{
		final Document document = getDocument();
		document.setValue(columnName, value, REASON_Value_DirectSetOnCalloutRecord);
		return "";
	}

	@Override
	public void dataRefresh()
	{
		final Document document = getDocument();
		document.refreshFromRepository();
	}

	@Override
	public void dataRefreshAll()
	{
		// NOTE: there is no "All" concept here, so we are just refreshing this document
		final Document document = getDocument();
		document.refreshFromRepository();
	}

	@Override
	public void dataRefreshRecursively()
	{
		// TODO dataRefreshRecursively: refresh document and it's children
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean dataSave(final boolean manualCmd)
	{
		// TODO dataSave: save document but also update the DocumentsCollection!
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isLookupValuesContainingId(@NonNull final String columnName, @NonNull final RepoIdAware id)
	{
		//Querying all values because getLookupValueById doesn't take validation rul into consideration.
		// TODO: Implement possibility to fetch sqllookupbyid with validation rule considered.
		return getDocument().getFieldLookupValues(columnName).containsId(id);
	}

}
