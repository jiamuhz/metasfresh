 

package de.metas.ui.web.comments;

import java.time.ZoneId;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.adempiere.util.lang.impl.TableRecordReference;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import de.metas.comments.CommentEntry;
import de.metas.comments.CommentsRepository;
import de.metas.ui.web.comments.json.JSONComment;
import de.metas.ui.web.comments.json.JSONCommentCreateRequest;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.user.api.IUserDAO;
import de.metas.util.Services;
import lombok.NonNull;

@Service
public class CommentsService
{
	private final CommentsRepository commentsRepository;
	private final DocumentDescriptorFactory documentDescriptorFactory;
	private final IUserDAO userDAO = Services.get(IUserDAO.class);

	public CommentsService(
			@NonNull final CommentsRepository commentsRepository,
			@NonNull final DocumentDescriptorFactory documentDescriptorFactory)
	{
		this.commentsRepository = commentsRepository;
		this.documentDescriptorFactory = documentDescriptorFactory;
	}

	@NonNull
	public final ViewRowCommentsSummary getRowCommentsSummary(@NonNull final IViewRow row)
	{
		return getRowCommentsSummary(ImmutableList.of(row));
	}

	@NonNull
	public final ViewRowCommentsSummary getRowCommentsSummary(@NonNull final Collection<? extends IViewRow> rows)
	{
		if (rows.isEmpty())
		{
			return ViewRowCommentsSummary.EMPTY;
		}

		final ImmutableList<IViewRow> allRows = rows.stream()
				.flatMap(IViewRow::streamRecursive)
				.collect(ImmutableList.toImmutableList());

		final Map<DocumentPath, TableRecordReference> recordRefsByDocumentPath = getTableRecordReferences(allRows);
		final ImmutableMap<TableRecordReference, Boolean> hasCommentsByRecordRef = commentsRepository.hasComments(recordRefsByDocumentPath.values());

		final ImmutableMap.Builder<DocumentId, Boolean> hasCommentsByDocumentId = ImmutableMap.builder();
		for (final IViewRow row : allRows)
		{
			final DocumentPath documentPath = row.getDocumentPath();
			if (documentPath == null)
			{
				continue;
			}

			final TableRecordReference recordRef = recordRefsByDocumentPath.get(documentPath);
			if (recordRef == null)
			{
				continue;
			}

			final boolean hasComments = hasCommentsByRecordRef.getOrDefault(recordRef, false);
			hasCommentsByDocumentId.put(row.getId(), hasComments);
		}

		return ViewRowCommentsSummary.ofMap(hasCommentsByDocumentId.build());
	}

	private HashMap<DocumentPath, TableRecordReference> getTableRecordReferences(@NonNull final ImmutableList<IViewRow> rows)
	{
		final ImmutableSet<DocumentPath> documentPaths = rows.stream()
				.map(IViewRow::getDocumentPath)
				.filter(Objects::nonNull)
				.collect(ImmutableSet.toImmutableSet());

		final HashMap<DocumentPath, TableRecordReference> recordRefsByDocumentPath = new HashMap<>(rows.size());
		for (final DocumentPath documentPath : documentPaths)
		{
			@SuppressWarnings("SimplifyOptionalCallChains")
			final TableRecordReference recordRef = documentDescriptorFactory.getTableRecordReferenceIfPossible(documentPath).orElse(null);
			if (recordRef != null)
			{
				recordRefsByDocumentPath.put(documentPath, recordRef);
			}
		}

		return recordRefsByDocumentPath;
	}

	public boolean hasComments(@NonNull final DocumentPath documentPath)
	{
		final TableRecordReference reference = documentDescriptorFactory.getTableRecordReferenceIfPossible(documentPath).orElse(null);
		if (reference == null)
		{
			return false;
		}

		final Map<TableRecordReference, Boolean> referencesWithComments = commentsRepository.hasComments(ImmutableList.of(reference));
		return referencesWithComments.getOrDefault(reference, false);
	}

	@NonNull
	public ImmutableList<JSONComment> getRowCommentsAsJson(@NonNull final DocumentPath documentPath, final ZoneId zoneId)
	{
		final TableRecordReference tableRecordReference = documentDescriptorFactory.getTableRecordReference(documentPath);
		final List<CommentEntry> comments = commentsRepository.retrieveLastCommentEntries(tableRecordReference, 100);

		return comments.stream()
				.map(comment -> toJsonComment(comment, zoneId))
				.sorted(Comparator.comparing(JSONComment::getCreated).reversed())
				.collect(ImmutableList.toImmutableList());
	}

	public void addComment(@NonNull final DocumentPath documentPath, @NonNull final JSONCommentCreateRequest jsonCommentCreateRequest)
	{
		final TableRecordReference tableRecordReference = documentDescriptorFactory.getTableRecordReference(documentPath);

		commentsRepository.createCommentEntry(jsonCommentCreateRequest.getText(), tableRecordReference);
	}

	@NonNull
	private JSONComment toJsonComment(@NonNull final CommentEntry comment, final ZoneId zoneId)
	{
		final String text = comment.getText();
		final String created = DateTimeConverters.toJson(comment.getCreated(), zoneId);
		final String createdBy = userDAO.retrieveUserFullName(comment.getCreatedBy());

		return JSONComment.builder()
				.text(text)
				.created(created)
				.createdBy(createdBy)
				.build();
	}
}
