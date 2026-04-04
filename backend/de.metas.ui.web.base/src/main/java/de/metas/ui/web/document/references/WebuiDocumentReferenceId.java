package de.metas.ui.web.document.references;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import de.metas.document.references.related_documents.RelatedDocumentsId;
import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

 

@EqualsAndHashCode
public final class WebuiDocumentReferenceId
{
	@JsonCreator
	public static WebuiDocumentReferenceId ofString(@NonNull final String value)
	{
		return new WebuiDocumentReferenceId(value);
	}

	@JsonCreator
	public static WebuiDocumentReferenceId ofRelatedDocumentsId(@NonNull final RelatedDocumentsId relatedDocumentsId)
	{
		return new WebuiDocumentReferenceId(relatedDocumentsId.toJson());
	}

	private final String value;

	private WebuiDocumentReferenceId(@NonNull final String value)
	{
		Check.assumeNotEmpty(value, "value is not empty");
		this.value = value;
	}

	@Deprecated
	@Override
	public String toString()
	{
		return toJson();
	}

	@JsonValue
	public String toJson()
	{
		return value;
	}

	public RelatedDocumentsId toRelatedDocumentsId()
	{
		return RelatedDocumentsId.ofString(value);
	}
}
