package de.metas.ui.web.window.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.NonNull;
import lombok.ToString;



/**
 * Mutable ordered documents list.
 * 
 * It also contains {@link #getOrderBys()}.
 *
 *
 *
 */
@ToString
public final class OrderedDocumentsList
{
	public static OrderedDocumentsList of(final Collection<Document> documents, final DocumentQueryOrderByList orderBys)
	{
		return new OrderedDocumentsList(documents, orderBys);
	}

	public static OrderedDocumentsList newEmpty()
	{
		return new OrderedDocumentsList(ImmutableList.of(), DocumentQueryOrderByList.EMPTY);
	}

	public static OrderedDocumentsList newEmpty(final DocumentQueryOrderByList orderBys)
	{
		return new OrderedDocumentsList(ImmutableList.of(), orderBys);
	}

	private final ArrayList<Document> documents;
	private final DocumentQueryOrderByList orderBys;

	private OrderedDocumentsList(
			@Nullable final Collection<Document> documents,
			@NonNull final DocumentQueryOrderByList orderBys)
	{
		this.documents = documents == null ? new ArrayList<>() : new ArrayList<>(documents);
		this.orderBys = orderBys;
	}

	public ArrayList<Document> toList()
	{
		return documents;
	}

	public ImmutableMap<DocumentId, Document> toImmutableMap()
	{
		return Maps.uniqueIndex(documents, Document::getDocumentId);
	}

	public void addDocument(@NonNull final Document document)
	{
		documents.add(document);
	}

	public void addDocuments(@NonNull final Collection<Document> documents)
	{
		if (documents.isEmpty())
		{
			return;
		}

		documents.forEach(this::addDocument);
	}

	public int size()
	{
		return documents.size();
	}

	public boolean isEmpty()
	{
		return documents.isEmpty();
	}

	public Document get(final int index)
	{
		return documents.get(index);
	}

	public DocumentQueryOrderByList getOrderBys()
	{
		return orderBys;
	}
}
