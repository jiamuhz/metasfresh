 

package de.metas.ui.web.comments;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public final class ViewRowCommentsSummary
{
	private final ImmutableMap<DocumentId, Boolean> hasCommentsByDocumentId;

	public static final ViewRowCommentsSummary EMPTY = new ViewRowCommentsSummary(ImmutableMap.of());

	public static ViewRowCommentsSummary ofMap(@NonNull final Map<DocumentId, Boolean> hasCommentsByDocumentId)
	{
		return !hasCommentsByDocumentId.isEmpty()
				? new ViewRowCommentsSummary(ImmutableMap.copyOf(hasCommentsByDocumentId))
				: EMPTY;
	}

	private ViewRowCommentsSummary(@NonNull final ImmutableMap<DocumentId, Boolean> documentsWithComments)
	{
		this.hasCommentsByDocumentId = documentsWithComments;
	}

	public boolean hasComments(@NonNull final DocumentId documentId)
	{
		return hasCommentsByDocumentId.getOrDefault(documentId, false);
	}
}
