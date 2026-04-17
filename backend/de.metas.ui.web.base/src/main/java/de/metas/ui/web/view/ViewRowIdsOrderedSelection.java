package de.metas.ui.web.view;

import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.ad.dao.QueryLimit;

import javax.annotation.Nullable;
import java.util.Objects;

  
@Value
public class ViewRowIdsOrderedSelection
{
	ViewId viewId;
	long size;
	DocumentQueryOrderByList orderBys;

	QueryLimit queryLimit;
	boolean queryLimitHit;

	@Nullable EmptyReason emptyReason;

	@Builder(toBuilder = true)
	private ViewRowIdsOrderedSelection(
			@NonNull final ViewId viewId,
			final long size,
			@Nullable final DocumentQueryOrderByList orderBys,
			@Nullable final QueryLimit queryLimit,
			@Nullable final EmptyReason emptyReason)
	{
		this.viewId = viewId;
		this.size = size;
		this.orderBys = orderBys != null ? orderBys : DocumentQueryOrderByList.EMPTY;
		this.queryLimit = queryLimit != null ? queryLimit : QueryLimit.NO_LIMIT;
		this.emptyReason = emptyReason;

		this.queryLimitHit = this.queryLimit.isLimited()
				&& size > 0
				&& size >= this.queryLimit.toInt();
	}

	public static boolean equals(@Nullable final ViewRowIdsOrderedSelection s1, @Nullable final ViewRowIdsOrderedSelection s2)
	{
		return Objects.equals(s1, s2);
	}

	public WindowDocumentTypeId getWindowId()
	{
		return getViewId().getWindowId();
	}

	public String getSelectionId()
	{
		return getViewId().getViewId();
	}

	public ViewRowIdsOrderedSelection withSize(final int size)
	{
		return this.size == size
				? this
				: toBuilder().size(size).build();
	}
}
