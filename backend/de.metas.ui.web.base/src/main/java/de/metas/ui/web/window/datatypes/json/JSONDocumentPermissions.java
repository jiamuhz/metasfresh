package de.metas.ui.web.window.datatypes.json;

import de.metas.security.IUserRolePermissions;
import de.metas.ui.web.window.controller.DocumentPermissionsHelper;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentFieldLogicExpressionResultRevaluator;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

 

public class JSONDocumentPermissions
{
	@NonNull private final IUserRolePermissions permissions;

	private final Map<DocumentPath, Boolean> readonlyDocuments = new HashMap<>();
	private DocumentFieldLogicExpressionResultRevaluator logicExpressionRevaluator; // lazy

	JSONDocumentPermissions(@NonNull final IUserRolePermissions permissions)
	{
		this.permissions = permissions;
	}

	public void apply(final Document document, final JSONDocumentField jsonField)
	{
		if (!jsonField.isReadonly())
		{
			if (isReadonly(document))
			{
				jsonField.setReadonly(true, "no document access");
				return;
			}
		}

		// TODO: check column level access
	}

	public void apply(final DocumentPath documentPath, final JSONDocumentField jsonField)
	{
		// TODO: apply JSONDocumentPermissions to fields
		// atm it's not so important because user cannot reach in that situation,
		// because he/she cannot update the document in that case.
	}

	public void apply(final Document document, final JSONIncludedTabInfo jsonIncludedTabInfo)
	{
		if (isReadonly(document))
		{
			jsonIncludedTabInfo.setAllowCreateNew(false, "no document access");
			jsonIncludedTabInfo.setAllowDelete(false, "no document access");
		}
	}

	public void apply(final DocumentPath documentPath, final JSONIncludedTabInfo jsonIncludedTabInfo)
	{
		// TODO: implement... but it's not so critical atm
	}

	private boolean isReadonly(@NonNull final Document document)
	{
		return readonlyDocuments.computeIfAbsent(document.getDocumentPath(), documentPath -> !DocumentPermissionsHelper.canEdit(document, permissions));
	}

	public DocumentFieldLogicExpressionResultRevaluator getLogicExpressionResultRevaluator()
	{
		DocumentFieldLogicExpressionResultRevaluator logicExpressionRevaluator = this.logicExpressionRevaluator;
		if (logicExpressionRevaluator == null)
		{
			logicExpressionRevaluator = this.logicExpressionRevaluator = DocumentFieldLogicExpressionResultRevaluator.using(permissions);
		}
		return logicExpressionRevaluator;
	}
}
