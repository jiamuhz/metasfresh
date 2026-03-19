package de.metas.ui.web.view;

import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
import lombok.Value;

import java.util.Set;



@Value
public class ViewRowIdsSelection
{
	public static ViewRowIdsSelection of(@NonNull final ViewId viewId, final DocumentIdsSelection rowIds)
	{
		return new ViewRowIdsSelection(viewId, rowIds);
	}

	public static ViewRowIdsSelection of(@NonNull final ViewId viewId, final Set<String> rowIdsStringSet)
	{
		final DocumentIdsSelection rowIds = DocumentIdsSelection.ofStringSet(rowIdsStringSet);
		return of(viewId, rowIds);
	}

	public static ViewRowIdsSelection ofNullableStrings(final String viewIdStr, final String rowIdsListStr)
	{
		if (viewIdStr == null || viewIdStr.isEmpty())
		{
			return null;
		}

		final ViewId viewId = ViewId.ofViewIdString(viewIdStr);
		final DocumentIdsSelection rowIds = DocumentIdsSelection.ofCommaSeparatedString(rowIdsListStr);
		return new ViewRowIdsSelection(viewId, rowIds);
	}

	public static ViewRowIdsSelection ofNullableStrings(String viewIdStr, Set<String> rowIdsStringSet)
	{
		if (viewIdStr == null || viewIdStr.isEmpty())
		{
			return null;
		}

		final ViewId viewId = ViewId.ofViewIdString(viewIdStr);
		final DocumentIdsSelection rowIds = DocumentIdsSelection.ofStringSet(rowIdsStringSet);
		return new ViewRowIdsSelection(viewId, rowIds);
	}

	ViewId viewId;
	DocumentIdsSelection rowIds;

	private ViewRowIdsSelection(@NonNull final ViewId viewId, final DocumentIdsSelection rowIds)
	{
		this.viewId = viewId;
		this.rowIds = rowIds != null ? rowIds : DocumentIdsSelection.EMPTY;
	}

	public boolean isEmpty()
	{
		return rowIds.isEmpty();
	}
}
