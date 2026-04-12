 

package de.metas.ui.web.view;

import de.metas.ui.web.comments.CommentsService;
import de.metas.ui.web.comments.ViewRowCommentsSummary;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.view.IEditableView.RowEditingContext;
import de.metas.ui.web.view.json.JSONViewRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesPage;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.model.DocumentCollection;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API for editing a view row.
 *
 * 
 * Task <a href="https://github.com/metasfresh/metasfresh-webui-api/issues/577">577</a>
 */
@RestController
@RequestMapping(ViewRowEditRestController.ENDPOINT)
public class ViewRowEditRestController
{
	private static final String PARAM_WindowId = ViewRestController.PARAM_WindowId;
	private static final String PARAM_ViewId = "viewId";
	private static final String PARAM_RowId = "rowId";
	private static final String PARAM_FieldName = "fieldName";
	/* package */ static final String ENDPOINT = ViewRestController.ENDPOINT + "/{" + PARAM_ViewId + "}/{" + PARAM_RowId + "}/edit";

	private final UserSession userSession;
	private final IViewsRepository viewsRepo;
	private final DocumentCollection documentsCollection;
	private final CommentsService commentsService;

	public ViewRowEditRestController(
			@NonNull final UserSession userSession,
			@NonNull final IViewsRepository viewsRepo,
			@NonNull final DocumentCollection documentsCollection,
			@NonNull final CommentsService commentsService)
	{
		this.userSession = userSession;
		this.viewsRepo = viewsRepo;
		this.documentsCollection = documentsCollection;
		this.commentsService = commentsService;
	}

	private JSONOptions newJSONOptions()
	{
		return JSONOptions.of(userSession);
	}

	private IEditableView getEditableView(final ViewId viewId)
	{
		final IView view = viewsRepo.getView(viewId);
		return IEditableView.asEditableView(view);
	}

	private RowEditingContext createRowEditingContext(final ViewId viewId, final DocumentId rowId)
	{
		return RowEditingContext.builder()
				.viewId(viewId)
				.rowId(rowId)
				.documentsCollection(documentsCollection)
				.userRolePermissions(userSession.getUserRolePermissions())
				.build();
	}

	@PatchMapping
	public JSONViewRow patchRow(
			@PathVariable(PARAM_WindowId) final String windowIdStr,
			@PathVariable(PARAM_ViewId) final String viewIdStr,
			@PathVariable(PARAM_RowId) final String rowIdStr,
			@RequestBody final List<JSONDocumentChangedEvent> fieldChangeRequests)
	{
		userSession.assertLoggedIn();

		final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
		final DocumentId rowId = DocumentId.of(rowIdStr);

		final IEditableView view = getEditableView(viewId);
		final RowEditingContext editingCtx = createRowEditingContext(viewId, rowId);
		view.patchViewRow(editingCtx, fieldChangeRequests);

		final IViewRow row = view.getRowDataById(rowId);
		final IViewRowOverrides rowOverrides = ViewRowOverridesHelper.getViewRowOverrides(view);
		final JSONOptions jsonOpts = newJSONOptions();

		final ViewRowCommentsSummary viewRowCommentsSummary = commentsService.getRowCommentsSummary(row);

		return JSONViewRow.ofRow(row, rowOverrides, jsonOpts, viewRowCommentsSummary);
	}

	@GetMapping("/{fieldName}/typeahead")
	public JSONLookupValuesPage getFieldTypeahead(
			@PathVariable(PARAM_WindowId) final String windowIdStr,
			@PathVariable(PARAM_ViewId) final String viewIdStr,
			@PathVariable(PARAM_RowId) final String rowIdStr,
			@PathVariable(PARAM_FieldName) final String fieldName,
			@RequestParam("query") final String query)
	{
		userSession.assertLoggedIn();

		final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
		final DocumentId rowId = DocumentId.of(rowIdStr);

		final IEditableView view = getEditableView(viewId);
		final RowEditingContext editingCtx = createRowEditingContext(viewId, rowId);
		return view.getFieldTypeahead(editingCtx, fieldName, query)
				.transform(page -> JSONLookupValuesPage.of(page, userSession.getAD_Language()));
	}

	private JSONLookupValuesList toJSONLookupValuesList(final LookupValuesList lookupValuesList)
	{
		return JSONLookupValuesList.ofLookupValuesList(lookupValuesList, userSession.getAD_Language());
	}

	@GetMapping("/{fieldName}/dropdown")
	public JSONLookupValuesList getFieldDropdown(
			@PathVariable(PARAM_WindowId) final String windowIdStr,
			@PathVariable(PARAM_ViewId) final String viewIdStr,
			@PathVariable(PARAM_RowId) final String rowIdStr,
			@PathVariable(PARAM_FieldName) final String fieldName)
	{
		userSession.assertLoggedIn();

		final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
		final DocumentId rowId = DocumentId.of(rowIdStr);

		final IEditableView view = getEditableView(viewId);
		final RowEditingContext editingCtx = createRowEditingContext(viewId, rowId);
		return view.getFieldDropdown(editingCtx, fieldName)
				.transform(this::toJSONLookupValuesList);
	}
}
