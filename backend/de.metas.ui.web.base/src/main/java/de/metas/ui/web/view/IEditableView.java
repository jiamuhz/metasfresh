package de.metas.ui.web.view;

import de.metas.security.IUserRolePermissions;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.LookupValuesPage;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.model.DocumentCollection;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.exceptions.AdempiereException;

import java.util.List;



public interface IEditableView extends IView
{
	static IEditableView asEditableView(final IView view)
	{
		if (view instanceof IEditableView)
		{
			return (IEditableView)view;
		}
		else
		{
			throw new AdempiereException("View is not editable")
					.setParameter("view", view);
		}
	}

	void patchViewRow(RowEditingContext ctx, List<JSONDocumentChangedEvent> fieldChangeRequests);

	default LookupValuesPage getFieldTypeahead(RowEditingContext ctx, String fieldName, String query) {throw new UnsupportedOperationException();}

	default LookupValuesList getFieldDropdown(RowEditingContext ctx, String fieldName) {throw new UnsupportedOperationException();}

	@Builder
	@Getter
	@ToString(exclude = "documentsCollection")
	class RowEditingContext
	{
		@NonNull private final ViewId viewId;
		@NonNull private final DocumentId rowId;
		@NonNull private final DocumentCollection documentsCollection;
		@NonNull private final IUserRolePermissions userRolePermissions;
	}
}
