package de.metas.ui.web.view;

import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.view.descriptor.ViewRowAttributesLayout;
import de.metas.ui.web.view.json.JSONViewRowAttributes;
import de.metas.ui.web.view.json.JSONViewRowAttributesLayout;
import de.metas.ui.web.window.controller.Execution;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocument;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONDocumentOptions;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = ViewRowAttributesRestController.ENDPOINT)
public class ViewRowAttributesRestController
{
	private static final String PARAM_WindowId = ViewRestController.PARAM_WindowId;
	private static final String PARAM_ViewId = "viewId";
	private static final String PARAM_RowId = "rowId";

	/* package */static final String ENDPOINT = ViewRestController.ENDPOINT + "/{" + PARAM_ViewId + "}/{" + PARAM_RowId + "}/attributes";

	@Autowired
	private UserSession userSession;

	@Autowired
	private IViewsRepository viewsRepo;

	private JSONOptions newJSONOptions()
	{
		return JSONOptions.of(userSession);
	}

	private JSONDocumentLayoutOptions newJSONLayoutOptions()
	{
		return JSONDocumentLayoutOptions.of(userSession);
	}

	private JSONDocumentOptions newJSONDocumentOptions()
	{
		return JSONDocumentOptions.of(userSession);
	}

	@GetMapping("/layout")
	public JSONViewRowAttributesLayout getAttributesLayout(
			@PathVariable(PARAM_WindowId) final String windowIdStr //
			, @PathVariable(PARAM_ViewId) final String viewIdStr //
			, @PathVariable(PARAM_RowId) final String rowIdStr //
	)
	{
		userSession.assertLoggedIn();

		final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
		final ViewRowAttributesLayout layout = viewsRepo.getView(viewId)
				.getById(DocumentId.of(rowIdStr))
				.getAttributes()
				.getLayout();

		return JSONViewRowAttributesLayout.of(layout, newJSONLayoutOptions());
	}

	@GetMapping
	public JSONViewRowAttributes getData(
			@PathVariable(PARAM_WindowId) final String windowIdStr //
			, @PathVariable(PARAM_ViewId) final String viewIdStr //
			, @PathVariable(PARAM_RowId) final String rowIdStr //
	)
	{
		userSession.assertLoggedIn();

		final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
		final DocumentId rowId = DocumentId.of(rowIdStr);
		return viewsRepo.getView(viewId)
				.getById(rowId)
				.getAttributes()
				.toJson(newJSONOptions());
	}

	@PatchMapping
	public List<JSONDocument> processChanges(
			@PathVariable(PARAM_WindowId) final String windowIdStr //
			, @PathVariable(PARAM_ViewId) final String viewIdStr //
			, @PathVariable(PARAM_RowId) final String rowIdStr //
			, @RequestBody final List<JSONDocumentChangedEvent> events //
	)
	{
		userSession.assertLoggedIn();

		final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
		final DocumentId rowId = DocumentId.of(rowIdStr);
		return Execution.callInNewExecution("processChanges", () -> {
			viewsRepo.getView(viewId)
					.getById(rowId)
					.getAttributes()
					.processChanges(events);
			return JSONDocument.ofEvents(Execution.getCurrentDocumentChangesCollectorOrNull(), newJSONDocumentOptions());
		});
	}

	@GetMapping("/attribute/{attributeName}/typeahead")
	public JSONLookupValuesList getAttributeTypeahead(
			@PathVariable(PARAM_WindowId) final String windowIdStr //
			, @PathVariable(PARAM_ViewId) final String viewIdStr//
			, @PathVariable(PARAM_RowId) final String rowIdStr //
			, @PathVariable("attributeName") final String attributeName //
			, @RequestParam(name = "query", required = true) final String query //
	)
	{
		userSession.assertLoggedIn();

		final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
		final DocumentId rowId = DocumentId.of(rowIdStr);
		return viewsRepo.getView(viewId)
				.getById(rowId)
				.getAttributes()
				.getAttributeTypeahead(attributeName, query)
				.transform(this::toJSONLookupValuesList);
	}

	private JSONLookupValuesList toJSONLookupValuesList(final LookupValuesList lookupValuesList)
	{
		return JSONLookupValuesList.ofLookupValuesList(lookupValuesList, userSession.getAD_Language());
	}

	@GetMapping("/attribute/{attributeName}/dropdown")
	public JSONLookupValuesList getAttributeDropdown(
			@PathVariable(PARAM_WindowId) final String windowIdStr //
			, @PathVariable(PARAM_ViewId) final String viewIdStr //
			, @PathVariable(PARAM_RowId) final String rowIdStr //
			, @PathVariable("attributeName") final String attributeName //
	)
	{
		userSession.assertLoggedIn();

		final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
		final DocumentId rowId = DocumentId.of(rowIdStr);
		return viewsRepo.getView(viewId)
				.getById(rowId)
				.getAttributes()
				.getAttributeDropdown(attributeName)
				.transform(this::toJSONLookupValuesList);
	}
}
