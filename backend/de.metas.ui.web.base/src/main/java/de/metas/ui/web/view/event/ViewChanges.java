package de.metas.ui.web.view.event;

import com.google.common.base.MoreObjects;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;



public class ViewChanges
{
	private final ViewId viewId;

	private boolean fullyChanged;
	private boolean headerPropertiesChanged;
	private Set<DocumentId> changedRowIds = null;

	public ViewChanges(@NonNull final ViewId viewId)
	{
		this.viewId = viewId;
	}

	public void collectFrom(final ViewChanges changes)
	{
		if (changes.isFullyChanged())
		{
			fullyChanged = true;
		}

		if (changes.changedRowIds != null && !changes.changedRowIds.isEmpty())
		{
			if (changedRowIds == null)
			{
				changedRowIds = new HashSet<>();
			}
			changedRowIds.addAll(changes.changedRowIds);
		}
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("viewId", viewId)
				.add("fullyChanged", fullyChanged ? Boolean.TRUE : null)
				.add("headerPropertiesChanged", headerPropertiesChanged ? Boolean.TRUE : null)
				.add("changedRowIds", changedRowIds)
				.toString();
	}

	public ViewId getViewId()
	{
		return viewId;
	}

	public void setFullyChanged()
	{
		fullyChanged = true;
	}

	public boolean isHeaderPropertiesChanged()
	{
		return headerPropertiesChanged;
	}

	public void setHeaderPropertiesChanged()
	{
		this.headerPropertiesChanged = true;
	}

	public boolean isFullyChanged()
	{
		return fullyChanged;
	}

	public boolean hasChanges()
	{
		if (fullyChanged)
		{
			return true;
		}

		if (headerPropertiesChanged)
		{
			return true;
		}

		return changedRowIds != null && !changedRowIds.isEmpty();
	}

	public void addChangedRowIds(@Nullable final DocumentIdsSelection rowIds)
	{
		// Don't collect rowIds if this was already flagged as fully changed.
		if (fullyChanged)
		{
			return;
		}

		if (rowIds == null || rowIds.isEmpty())
		{
			return;
		}

		else if (rowIds.isAll())
		{
			fullyChanged = true;
			changedRowIds = null;
		}
		else
		{
			if (changedRowIds == null)
			{
				changedRowIds = new HashSet<>();
			}
			changedRowIds.addAll(rowIds.toSet());
		}
	}

	public void addChangedRowIds(final Collection<DocumentId> rowIds)
	{
		if (rowIds.isEmpty())
		{
			return;
		}

		if (changedRowIds == null)
		{
			changedRowIds = new HashSet<>();
		}
		changedRowIds.addAll(rowIds);
	}

	public void addChangedRowId(@NonNull final DocumentId rowId)
	{
		if (changedRowIds == null)
		{
			changedRowIds = new HashSet<>();
		}
		changedRowIds.add(rowId);
	}

	public DocumentIdsSelection getChangedRowIds()
	{
		final boolean fullyChanged = this.fullyChanged;
		final Set<DocumentId> changedRowIds = this.changedRowIds;

		if (fullyChanged)
		{
			return DocumentIdsSelection.ALL;
		}
		else if (changedRowIds == null || changedRowIds.isEmpty())
		{
			return DocumentIdsSelection.EMPTY;
		}
		else
		{
			return DocumentIdsSelection.of(changedRowIds);
		}
	}
}
