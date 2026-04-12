package de.metas.ui.web.view;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.references.WebuiDocumentReferenceId;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.view.ViewActionDescriptorsList;
import de.metas.ui.web.view.descriptor.SqlViewRowsWhereClause;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.LookupValuesPage;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlOptions;
import lombok.NonNull;
import org.adempiere.ad.dao.QueryLimit;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


/**
 * 列表视图 - 表格方式
 */

public interface IView
{
	ViewId getViewId();

	JSONViewDataType getViewType();

	@Nullable
	default ViewProfileId getProfileId()
	{
		return ViewProfileId.NULL;
	}

	default ITranslatableString getDescription()
	{
		return TranslatableStrings.empty();
	}

	default ViewHeaderProperties getHeaderProperties()
	{
		return ViewHeaderProperties.EMPTY;
	}

	/**
	 * 它回答了一个问题：“在系统中，有哪些地方（视图/文档）引用了当前视图里的记录？”
	 * 例如：
	 *  一个“销售订单”视图中的订单，可能被“发货单”视图引用
	 *  一个“产品”视图中的产品，可能被多个“BOM（物料清单）”视图引用
	 *  一个“业务伙伴”视图中的客户，可能被“合同”、“订单”等多个视图引用
	 *
	 * 当用户在某个视图中操作时，系统需要知道这个视图的数据还被哪些地方使用，以便：
	 * 数据联动：当主视图数据变化时，通知关联视图更新
	 * 引用检查：删除数据前检查是否被其他地方引用
	 * 导航支持：支持用户从当前视图跳转到引用它的其他视图
	 * 缓存管理：当数据变更时，使相关视图的缓存失效
	 */
	Set<DocumentPath> getReferencingDocumentPaths();

	/**
	 * 自身身份
	 * 我是谁？我代表哪个业务文档？
	 * @return
	 */
	@Nullable
	default WebuiDocumentReferenceId getDocumentReferenceId()
	{
		return null;
	}

	/**
	 * @param documentId can be used by multi-table implementations to return the correct table name for a given row.
	 * @return table name for the given row; might also return {@code null}.
	 */
	@Nullable
	String getTableNameOrNull(@Nullable DocumentId documentId);

	@Nullable
	default String getTableNameOrNull()
	{
		return getTableNameOrNull(null);
	}

	/**
	 * included view case, this method will return the parent's viewId. Else null will be returned.
	 */
	ViewId getParentViewId();

	/**
	 * included view case, this method will return the parent's view row id. Else null will be returned.
	 */
	DocumentId getParentRowId();

	long size();

	default boolean isAllowClosingPerUserRequest()
	{
		return true;
	}

	default void close(final ViewCloseAction closeAction)
	{
		// nothing
	}

	default void afterDestroy()
	{
		// nothing
	}

	int getQueryLimit();

	boolean isQueryLimitHit();

	@Nullable default EmptyReason getEmptyReason() {return null;}

	/**
	 * Invalidate ALL view rows.
	 * <p>
	 * NOTE: this method is NOT sending websocket notifications
	 */
	void invalidateAll();

	default void invalidateSelection()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Invalidate given row by ID.
	 * <p>
	 * If there is no custom implementation then this method will invoke {@link #invalidateAll()}.
	 */
	default void invalidateRowById(final DocumentId rowId)
	{
		invalidateAll();
	}

	ViewResult getPage(int firstRow, int pageLength, ViewRowsOrderBy orderBy);

	default ViewResult getPageWithRowIdsOnly(
			final int firstRow,
			final int pageLength,
			final ViewRowsOrderBy orderBy)
	{
		return getPage(firstRow, pageLength, orderBy);
	}

	default ImmutableMap<String, Object> getParameters()
	{
		return ImmutableMap.of();
	}

	IViewRow getRowDataById(DocumentId rowId) throws EntityNotFoundException;

	LookupValuesList getFilterParameterDropdown(String filterId, String filterParameterName, ViewFilterParameterLookupEvaluationCtx ctx);

	LookupValuesPage getFilterParameterTypeahead(String filterId, String filterParameterName, String query, ViewFilterParameterLookupEvaluationCtx ctx);

	/**
	 * Gets the stick filters.
	 * Sticky filters are those filters which cannot be changed by user and which shall be preserved between filtering.
	 * Sticky filters shall never be exported to frontend.
	 */
	DocumentFilterList getStickyFilters();

	/**
	 * Return the active filters for this view.<br>
	 * Note that whenever the user changes the filter settings on the frontend, a new view is created.<br>
	 * Therefore, if you implement a view yourself, you probably will want to give it a "static" list of filters to be returned by this method.
	 */
	DocumentFilterList getFilters();

	DocumentQueryOrderByList getDefaultOrderBys();

	@Nullable
	default TableRecordReference getTableRecordReferenceOrNull(@NonNull final DocumentId rowId)
	{
		final int recordId = rowId.toIntOr(-1);
		if (recordId < 0)
		{
			return null;
		}

		final String tableName = getTableNameOrNull(rowId);
		if (tableName == null)
		{
			return null;
		}

		return TableRecordReference.of(tableName, recordId);
	}

	@Nullable
	SqlViewRowsWhereClause getSqlWhereClause(DocumentIdsSelection rowIds, SqlOptions sqlOpts);

	default boolean hasAttributesSupport()
	{
		return false;
	}

	<T> List<T> retrieveModelsByIds(DocumentIdsSelection rowIds, Class<T> modelClass);

	@NonNull
	default <T> Stream<T> streamModelsByIds(@NonNull final DocumentIdsSelection rowIds, @NonNull final Class<T> modelClass)
	{
		return retrieveModelsByIds(rowIds, modelClass).stream();
	}

	/**
	 * @return a stream which contains only the {@link IViewRow}s which given <code>rowId</code>s.
	 * If a {@link IViewRow} was not found for given ID, this method simply ignores it.
	 */
	Stream<? extends IViewRow> streamByIds(DocumentIdsSelection rowIds);

	default Stream<? extends IViewRow> streamByIds(DocumentIdsSelection rowIds, QueryLimit suggestedLimit)
	{
		return streamByIds(rowIds);
	}

	/**
	 * Notify the view that given record(s) has changed.
	 */
	void notifyRecordsChanged(TableRecordReferenceSet recordRefs, boolean watchedByFrontend);

	/**
	 * @return actions which were registered particularly for this view instance
	 */
	default ViewActionDescriptorsList getActions()
	{
		return ViewActionDescriptorsList.EMPTY;
	}

	default boolean isConsiderTableRelatedProcessDescriptors(@NonNull final DocumentIdsSelection selectedRowIds)
	{
		return true;
	}

	default List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors()
	{
		return ImmutableList.of();
	}
}
