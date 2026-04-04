package de.metas.ui.web.document.references;

import de.metas.document.references.related_documents.RelatedDocuments;
import de.metas.document.references.related_documents.RelatedDocumentsCandidateGroup;
import de.metas.document.references.related_documents.RelatedDocumentsEvaluationContext;
import de.metas.document.references.related_documents.RelatedDocumentsTargetWindow;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.document.filter.provider.userQuery.MQueryDocumentFilterHelper;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
import lombok.ToString;

import java.util.stream.Stream;

 

@ToString
public class WebuiDocumentReferenceCandidate
{
	private final RelatedDocumentsCandidateGroup relatedDocumentsCandidateGroup;
	private final ITranslatableString filterCaption;

	public WebuiDocumentReferenceCandidate(
			@NonNull final RelatedDocumentsCandidateGroup relatedDocumentsCandidateGroup,
			@NonNull final ITranslatableString filterCaption)
	{
		this.relatedDocumentsCandidateGroup = relatedDocumentsCandidateGroup;
		this.filterCaption = filterCaption;
	}

	public Stream<WebuiDocumentReference> evaluateAndStream(@NonNull final RelatedDocumentsEvaluationContext context)
	{
		return relatedDocumentsCandidateGroup.evaluateAndStream(context)
				.map(relatedDocuments -> toDocumentReference(relatedDocuments, filterCaption));
	}

	public static WebuiDocumentReference toDocumentReference(
			@NonNull final RelatedDocuments relatedDocuments,
			@NonNull final ITranslatableString filterCaption)
	{
		return WebuiDocumentReference.builder()
				.id(WebuiDocumentReferenceId.ofRelatedDocumentsId(relatedDocuments.getId()))
				.internalName(relatedDocuments.getInternalName())
				.caption(relatedDocuments.getCaption())
				.targetWindow(toDocumentReferenceTargetWindow(relatedDocuments.getTargetWindow()))
				.priority(relatedDocuments.getPriority())
				.documentsCount(relatedDocuments.getRecordCount())
				.filter(MQueryDocumentFilterHelper.createDocumentFilterFromMQuery(relatedDocuments.getQuery(), filterCaption))
				.loadDuration(relatedDocuments.getRecordCountDuration())
				.build();
	}

	private static WebuiDocumentReferenceTargetWindow toDocumentReferenceTargetWindow(@NonNull final RelatedDocumentsTargetWindow relatedDocumentsTargetWindow)
	{
		final WindowId windowId = WindowId.of(relatedDocumentsTargetWindow.getAdWindowId());
		final String category = relatedDocumentsTargetWindow.getCategory();
		return category != null
				? WebuiDocumentReferenceTargetWindow.ofWindowIdAndCategory(windowId, category)
				: WebuiDocumentReferenceTargetWindow.ofWindowId(windowId);
	}

}
