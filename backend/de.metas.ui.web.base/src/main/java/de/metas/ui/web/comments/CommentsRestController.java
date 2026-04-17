 

package de.metas.ui.web.comments;

import de.metas.ui.web.comments.json.JSONComment;
import de.metas.ui.web.comments.json.JSONCommentCreateRequest;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.controller.WindowRestController;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(CommentsRestController.ENDPOINT)
public class CommentsRestController
{
	protected static final String ENDPOINT = WindowRestController.ENDPOINT + "/{windowId}/{documentId}/comments";
	private final UserSession userSession;

	private final DocumentDescriptorFactory documentDescriptorFactory;
	private final CommentsService commentsService;

	public CommentsRestController(
			final UserSession userSession,
			final DocumentDescriptorFactory documentDescriptorFactory, final CommentsService commentsService)
	{
		this.userSession = userSession;
		this.documentDescriptorFactory = documentDescriptorFactory;
		this.commentsService = commentsService;
	}

	@GetMapping
	public List<JSONComment> getAll(
			@PathVariable("windowId") final String windowIdStr,
			@PathVariable("documentId") final String documentId
	)
	{
		userSession.assertLoggedIn();

		final DocumentPath documentPath = DocumentPath.rootDocumentPath(WindowDocumentTypeId.fromJson(windowIdStr), documentId);

		final ZoneId zoneId = JSONOptions.of(userSession).getZoneId();
		return commentsService.getRowCommentsAsJson(documentPath, zoneId);
	}

	@PostMapping
	public void addComment(
			@PathVariable("windowId") final String windowIdStr,
			@PathVariable("documentId") final String documentId,
			@RequestBody final JSONCommentCreateRequest jsonCommentCreateRequest
	)
	{
		userSession.assertLoggedIn();

		final DocumentPath documentPath = DocumentPath.rootDocumentPath(WindowDocumentTypeId.fromJson(windowIdStr), documentId);

		commentsService.addComment(documentPath, jsonCommentCreateRequest);
	}
}
